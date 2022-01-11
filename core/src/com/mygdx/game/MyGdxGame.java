package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
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
	Texture usMustang;
	Texture sunBackground;
	Texture nightBackground;
	Texture ground;
	Texture explosionDamage;
	Texture[] clouds = new Texture[4];
	Texture[] airExplosion = new Texture[26];
	Texture fence;
	Texture moon;
	int x = 0;
	int airExplosionAnimation = 1;
	int breachX = 0;
	int y = 250;
	int X = 20;
	int explosionX = 200;
	int bomberAnimation = 0;
	int[] cloudXPositions;
	int[] cloudYPositions;


	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 1080, 720);
		stuka = new Texture("GER_Ju87R.png");
		nightBackground = new Texture("sky.png");
		sunBackground = new Texture("sky_sun.png");
		ground = new Texture("road.png");
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
		usMustang = new Texture("US_p51.png");
		cloudYPositions =new int[]{calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY(), calculateY()};
		cloudXPositions =new int[]{calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX(), calculateX()};
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(sunBackground, 0, 0);
		//batch.draw(moon, -250, Gdx.graphics.getHeight()-1000);
		batch.draw(ground, explosionX-ground.getWidth(), 0);
		batch.draw(ground, explosionX, 0);
		batch.draw(ground, explosionX+ground.getWidth(), 0);
		batch.draw(explosionDamage, explosionX, 0);

		batch.draw(fence, explosionX-fence.getWidth(), -150);
		batch.draw(fence, explosionX, -150);
		batch.draw(fence, explosionX+fence.getWidth(), -150);
		batch.draw(airExplosion[airExplosionAnimation], X, y);
		batch.draw(usMustang, x+80, 360);
		batch.draw(usMustang, x-100, 530);
		batch.draw(usBomber[bomberAnimation], x, 490);
		batch.draw(usMustang, x-90, 390);
		drawClouds(batch);
		batch.draw(usMustang, x+90, 590);

		x++;
		if (x%2==0){
			explosionX--;
			breachX = explosionX;
			if (explosionX == -1920){
				explosionX = 200;
			}
		}
		if (bomberAnimation == 3){
			bomberAnimation = 0;
		}
		else{
			bomberAnimation++;
		}

		if (x == Gdx.graphics.getWidth() + usMustang.getWidth()) {
			//Si se sale del borde los aviones se generan a la izquierda
			x = -120;
		}
		if (x%3==0){
			if (airExplosionAnimation == airExplosion.length-1){
				y = calculateY();
				X = calculateX();

				airExplosionAnimation = 1;
			}
			else {
				airExplosionAnimation++;
			}
			for (int i = 0; i < cloudXPositions.length; i++) {
				if (cloudXPositions[i] == -100){
					cloudXPositions[i] = Gdx.graphics.getWidth();
					cloudYPositions[i] = calculateY();
				}
				else {
					cloudXPositions[i]--;
				}
			}
		}




		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public int calculateY(){
		return (int) (200 + (Math.random()*(Gdx.graphics.getHeight())));
	}
	public int calculateX(){
		return (int) (Math.random()*(Gdx.graphics.getWidth()));
	}
	public void drawClouds(SpriteBatch batch){
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
