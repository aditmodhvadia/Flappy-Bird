package com.aditmodhvadia.flappybird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class FlappyBird extends ApplicationAdapter {
    SpriteBatch batch;
    Texture background;
    Texture[] birds;
    Texture topTube;
    Texture bottomTube;
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
        background = new Texture("bg.png");
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        birds = new Texture[2];
        birds[0] = new Texture("bird.png");
        birds[1] = new Texture("bird2.png");
        birdY = Gdx.graphics.getHeight() / 2 - birds[flapState].getHeight() / 2;
        maxTubeOffset = Gdx.graphics.getHeight() / 2 - gap / 2 - 100;
        ran = new Random();
        distBetweenTubes = Gdx.graphics.getWidth() / 1.5f;

        for (int i = 0; i < noOfTubes; i++) {
            tubeOffset[i] = (ran.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200);

            tubeX[i] = Gdx.graphics.getWidth() / 2 - topTube.getWidth() / 2 + i * distBetweenTubes;
        }
    }

    @Override
    public void render() {

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        if (gameState != 0) {


            if (Gdx.input.justTouched()) {
                velocity = -30;


            }

            for (int i = 0; i < noOfTubes; i++) {

                if (tubeX[i] < -topTube.getWidth()) {
                    tubeX[i] += noOfTubes * distBetweenTubes;
                } else {
                    tubeX[i] = tubeX[i] - tubeVelocity;
                }


                batch.draw(topTube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i]);
                batch.draw(bottomTube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i]);

            }

            if (birdY > 0 || velocity < 0) {
                velocity += gravity;
                birdY -= velocity;
            }

        } else {
            if (Gdx.input.justTouched()) {
                gameState = 1;
            }
        }

        if (flapState == 0) {
            flapState = 1;
        } else {
            flapState = 0;
        }

        batch.draw(birds[flapState], Gdx.graphics.getWidth() / 2 - birds[flapState].getWidth() / 2, birdY);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
    }
}
