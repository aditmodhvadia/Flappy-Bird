package com.aditmodhvadia.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;
    Texture gameover;
    Texture play;
    Texture[] birds;
    Texture topTube;
    Texture bottomTube;
    //    ShapeRenderer shapeRenderer;
    Circle birdCirle;
    Rectangle[] topTubeRect, bottomTubeRect;
    BitmapFont font;

    int score = 0;
    int scoringTube = 0;

    int flapState = 0;
    float birdY = 0;
    float velocity = 0;
    float gravity = 1.5f;
    float gap = 400;
    float maxTubeOffset;
    Random ran;
    float tubeVelocity = 4;
    int noOfTubes = 4;
    float[] tubeX = new float[noOfTubes];
    float[] tubeOffset = new float[noOfTubes];
    float distBetweenTubes;

    int gameState = 0;


    @Override
    public void create() {
        batch = new SpriteBatch();
//        shapeRenderer = new ShapeRenderer();
        birdCirle = new Circle();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(10);
        background = new Texture("bg.png");
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        gameover = new Texture("gameover.jpg");
        play = new Texture("play.png");
        birds = new Texture[2];
        birds[0] = new Texture("bird.png");
        birds[1] = new Texture("bird2.png");
        maxTubeOffset = Gdx.graphics.getHeight() / 2 - gap / 2 - 100;
        ran = new Random();
        topTubeRect = new Rectangle[noOfTubes];
        bottomTubeRect = new Rectangle[noOfTubes];
        distBetweenTubes = Gdx.graphics.getWidth() / 1.5f;

        startGame();
    }

    private void startGame() {
        birdY = Gdx.graphics.getHeight() / 2 - birds[flapState].getHeight() / 2;

        for (int i = 0; i < noOfTubes; i++) {
            tubeOffset[i] = (ran.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);

            tubeX[i] = Gdx.graphics.getWidth() / 2 - topTube.getWidth() / 2 + Gdx.graphics.getWidth() + i * distBetweenTubes;

            topTubeRect[i] = new Rectangle();
            bottomTubeRect[i] = new Rectangle();
        }

    }

    @Override
    public void render() {

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (gameState == 1) {

            if (tubeX[scoringTube] < Gdx.graphics.getWidth() / 2) {
                score++;
                Gdx.app.log("Score", String.valueOf(score));
                scoringTube = (++scoringTube) % noOfTubes;
            }

            if (Gdx.input.justTouched()) {
                velocity = -30;
            }

            for (int i = 0; i < noOfTubes; i++) {

                if (tubeX[i] < -topTube.getWidth()) {

                    tubeOffset[i] = (ran.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);
                    tubeX[i] += noOfTubes * distBetweenTubes;
                } else {

                    tubeX[i] = tubeX[i] - tubeVelocity;


                }


                batch.draw(topTube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i]);
                batch.draw(bottomTube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i]);

                topTubeRect[i] = new Rectangle(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i], topTube.getWidth(), topTube.getHeight());
                bottomTubeRect[i] = new Rectangle(tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i], bottomTube.getWidth(), bottomTube.getHeight());
            }

            if (birdY > 0) {
                velocity += gravity;
                birdY -= velocity;
            } else {
                gameState = 2;
            }

        } else if (gameState == 0) {
            batch.draw(play, Gdx.graphics.getWidth()/2 - play.getWidth()/2, Gdx.graphics.getHeight()/2 - play.getHeight()/2 - 100);
            if (Gdx.input.justTouched()) {
                gameState = 1;
            }
        } else if (gameState == 2) {
            batch.draw(gameover, Gdx.graphics.getWidth()/2 - gameover.getWidth()/2, Gdx.graphics.getHeight()/2 - gameover.getHeight()/2);
            batch.draw(play, Gdx.graphics.getWidth()/2 - play.getWidth()/2, Gdx.graphics.getHeight()/2 - play.getHeight()/2 - 100);
            if (Gdx.input.justTouched()) {
                gameState = 1;
                startGame();
                score = 0;
                scoringTube = 0;
                velocity = 0;

            }
        }

        if (flapState == 0) {
            flapState = 1;
        } else {
            flapState = 0;
        }

        batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth() / 2, birdY);

        font.draw(batch, String.valueOf(score), 100, 200);

        batch.end();

        birdCirle.set(Gdx.graphics.getWidth() / 2, birdY + birds[flapState].getWidth() / 2, birds[flapState].getWidth() / 2 - 10);
//        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//        shapeRenderer.setColor(Color.RED);
//        shapeRenderer.circle(birdCirle.x, birdCirle.y, birdCirle.radius);

        for (int i = 0; i < noOfTubes; i++) {
//            shapeRenderer.rect(tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i], topTube.getWidth(), topTube.getHeight());
//            shapeRenderer.rect(tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i], bottomTube.getWidth(), bottomTube.getHeight());

            if (Intersector.overlaps(birdCirle, topTubeRect[i]) || Intersector.overlaps(birdCirle, bottomTubeRect[i])) {
                Gdx.app.log("Collision", "Yes");
                gameState = 2;


            }

        }

//        shapeRenderer.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
    }
}
