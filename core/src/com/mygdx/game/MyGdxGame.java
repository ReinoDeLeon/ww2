package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
    private OrthographicCamera camera;
    SpriteBatch batch;
    Texture img;
    Texture stuka;
    Texture[] usBomber = new Texture[4];
    Texture[] usMustang = new Texture[8];
    Texture sunBackground;
    Texture nightBackground;
    Texture ground;
    Texture groundBackground;
    Texture explosionDamage;
    Texture[] clouds = new Texture[4];
    Texture[] airExplosion = new Texture[26];
    Texture fence;
    Texture moon;
    int x = 0;
    int airExplosionAnimation = 1;
    int airExplosionAnimation2 = 1;
    int airExplosionAnimation3 = 1;
    int breachX = 0;
    int y = 250;
    int X = 20;
    int groundPosition = 200;
    int bomberAnimation = 0;
    int fighterPlaneAnimation = 0;
    int[] cloudXPositions;
    int[] cloudYPositions;
    private double skyMinimum = 400;
    int[] airplaneFormation;
    int skyMaximum;
    int[] explosionY;
    int[] explosionX;
    Music backgroundMusic;
    Sound explosionSound;


    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stuka = new Texture("GER_Ju87R.png");
        nightBackground = new Texture("sky.png");
        sunBackground = new Texture("sky_sun.png");
        ground = new Texture("road.png");
        groundBackground = new Texture("plainx2.png");
        moon = new Texture("moon.png");
        for (int i = 1; i < airExplosion.length; i++) {
            airExplosion[i] = new Texture("E" + i + ".png");
        }
        explosionDamage = new Texture("crater1.png");
        fence = new Texture("fence.png");
        for (int i = 0; i < usBomber.length; i++) {
            usBomber[i] = new Texture("B17_" + i + ".png");
        }
        for (int i = 0; i < clouds.length; i++) {
            clouds[i] = new Texture("cloud_" + i + ".png");
        }
        for (int i = 0; i < usMustang.length; i++) {
            usMustang[i] = new Texture("us_p51_" + i + ".png");
        }
        cloudYPositions = new int[]{calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY()};
        cloudXPositions = new int[]{calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX()};
        airplaneFormation = new int[]{calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY()};
        skyMaximum = Gdx.graphics.getHeight() - 400;
        explosionY = new int[]{calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY()};
        explosionX = new int[]{calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX()};
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("AirBattleRaidSound.wav"));
        backgroundMusic.play();
        backgroundMusic.setVolume(0.25f);
        explosionSound = Gdx.audio.newSound(Gdx.files.internal("bomb.wav"));

    }


    @Override
    public void render() {
        ScreenUtils.clear(0, 0, 0, 0.2f);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(groundBackground, 0, 0);
        batch.draw(airExplosion[airExplosionAnimation], explosionX[0], explosionY[0]);
        batch.draw(usMustang[fighterPlaneAnimation], x + 250, airplaneFormation[0]);
        batch.draw(usMustang[fighterPlaneAnimation], x - 300, airplaneFormation[1]);
        batch.draw(airExplosion[airExplosionAnimation], explosionX[1], explosionY[1]);
        batch.draw(usMustang[fighterPlaneAnimation], x - 370, airplaneFormation[2]);
        batch.draw(usMustang[fighterPlaneAnimation], x - 200, airplaneFormation[3]);

        batch.draw(airExplosion[airExplosionAnimation], explosionX[2], explosionY[2]);
        batch.draw(usBomber[bomberAnimation], x, airplaneFormation[4]);
        batch.draw(usMustang[fighterPlaneAnimation], x - 750, airplaneFormation[5]);

        batch.draw(airExplosion[airExplosionAnimation2], explosionX[3], explosionY[3]);
        drawClouds(batch);
        batch.draw(usMustang[fighterPlaneAnimation], x + 120, airplaneFormation[6]);

        batch.draw(airExplosion[airExplosionAnimation2], explosionX[4], explosionY[4]);
        batch.draw(usMustang[fighterPlaneAnimation], x - 110, airplaneFormation[7]);
        batch.draw(usBomber[bomberAnimation], x - 600, airplaneFormation[8]);

        batch.draw(airExplosion[airExplosionAnimation3], explosionX[5], explosionY[5]);

        x++;
        if (x % 2 == 0) {
            groundPosition--;
            breachX = groundPosition;
            if (groundPosition == -Gdx.graphics.getWidth()) {
                groundPosition = 200;
            }
        }
        if (bomberAnimation == 3) {
            bomberAnimation = 0;
        } else {
            bomberAnimation++;
        }
        if (fighterPlaneAnimation == 7) {
            fighterPlaneAnimation = 0;
        } else {
            fighterPlaneAnimation++;
        }

        if (x == Gdx.graphics.getWidth() + +750) {
            //Si se sale del borde los aviones se generan a la izquierda
            for (int i = 0; i < airplaneFormation.length; i++) {
                airplaneFormation[i] = calculateY();
            }
            x = -750;
        }
        if (x % 6 == 0) {
            if (airExplosionAnimation2 == airExplosion.length - 1) {
                for (int i = 2; i < 4; i++) {
                    explosionY[i] = calculateY();
                }
                for (int i = 2; i < 4; i++) {
                    explosionX[i] = calculateX();
                }

                explosionSound.play(0.2f);
                airExplosionAnimation2 = 1;
            } else {
                airExplosionAnimation2++;
            }


            for (int i = 0; i < cloudXPositions.length; i++) {
                if (cloudXPositions[i] == -clouds[3].getWidth()) {
                    cloudXPositions[i] = Gdx.graphics.getWidth();
                    cloudYPositions[i] = calculateY();
                } else {
                    cloudXPositions[i]--;
                }
            }
        }
        if (x % 4 == 0) {

            if (airExplosionAnimation3 == airExplosion.length - 1) {
                for (int i = 4; i < 5; i++) {
                    explosionY[i] = calculateY();
                }
                for (int i = 4; i < 5; i++) {
                    explosionX[i] = calculateX();
                }

                explosionSound.play(0.2f);
                airExplosionAnimation3 = 1;
            } else {
                airExplosionAnimation3++;
            }
        }
        if (x % 5 == 0) {
            if (airExplosionAnimation == airExplosion.length - 1) {
                for (int i = 0; i < 2; i++) {
                    explosionY[i] = calculateY();
                }
                for (int i = 0; i < 2; i++) {
                    explosionX[i] = calculateX();
                }
                explosionSound.play(0.2f);
                airExplosionAnimation = 1;
            } else {
                airExplosionAnimation++;
            }
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
        backgroundMusic.dispose();
    }

    public int calculateY() {

        return (int) (Math.random() * Gdx.graphics.getHeight());

    }

    public int calculateX() {
        return (int) (Math.random() * (Gdx.graphics.getWidth()));
    }

    public void drawClouds(SpriteBatch batch) {
        batch.draw(clouds[1], cloudXPositions[0], cloudYPositions[0]);
        batch.draw(clouds[3], cloudXPositions[1], cloudYPositions[1]);
        batch.draw(clouds[0], cloudXPositions[2], cloudYPositions[2]);
        batch.draw(clouds[2], cloudXPositions[3], cloudYPositions[3]);
        batch.draw(clouds[1], cloudXPositions[4], cloudYPositions[4]);
        batch.draw(clouds[3], cloudXPositions[5], cloudYPositions[5]);
        batch.draw(clouds[2], cloudXPositions[6], cloudYPositions[6]);
        batch.draw(clouds[0], cloudXPositions[7], cloudYPositions[7]);
        batch.draw(clouds[3], cloudXPositions[8], cloudYPositions[8]);
        batch.draw(clouds[0], cloudXPositions[9], cloudYPositions[9]);
        batch.draw(clouds[2], cloudXPositions[10], cloudYPositions[10]);
        batch.draw(clouds[1], cloudXPositions[11], cloudYPositions[11]);
        batch.draw(clouds[3], cloudXPositions[12], cloudYPositions[12]);
        batch.draw(clouds[2], cloudXPositions[13], cloudYPositions[13]);
        batch.draw(clouds[0], cloudXPositions[14], cloudYPositions[14]);

    }
}
