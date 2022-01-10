package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture stuka;
	Texture usBomber;
	Texture usMustang;
	Texture sunBackground;
	Texture nightBackground;
	Texture ground;
	Texture explosionDamage;
	Texture m147[] = new Texture[13];
	Texture[] deagle = new Texture[2];
	Texture[] airExplotion = new Texture[26];
	Texture fence;
	Texture moon;
	int x = 0;
	int airExplotionAnimation = 1;
	int breachX = 0;
	int y = 250;
	int X = 20;
	int explosionX = 200;



	@Override
	public void create () {
		batch = new SpriteBatch();
		stuka = new Texture("GER_Ju87R.png");
		nightBackground = new Texture("sky.png");
		sunBackground = new Texture("sky_sun.png");
		ground = new Texture("road.png");
		moon = new Texture("moon.png");
		for (int i = 1; i < airExplotion.length; i++) {
			airExplotion[i] = new Texture("E" + i + ".png");
		}
		explosionDamage = new Texture("crater1.png");
		fence = new Texture("fence.png");
		usBomber = new Texture("US_b17.png");
		usMustang = new Texture("US_p51.png");
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(nightBackground, 0, 0);
		batch.draw(moon, -250, Gdx.graphics.getHeight()-1000);
		batch.draw(ground, explosionX-ground.getWidth(), 0);
		batch.draw(ground, explosionX, 0);
		batch.draw(ground, explosionX+ground.getWidth(), 0);
		batch.draw(explosionDamage, explosionX, 0);

		batch.draw(fence, explosionX-fence.getWidth(), -150);
		batch.draw(fence, explosionX, -150);
		batch.draw(fence, explosionX+fence.getWidth(), -150);

		batch.draw(airExplotion[airExplotionAnimation], X, y);
		batch.draw(usMustang, x+80, 360);
		batch.draw(usMustang, x-100, 530);
		batch.draw(usBomber, x, 490);
		batch.draw(usMustang, x+90, 590);
		batch.draw(usMustang, x-90, 390);
		x++;
		if (x%2==0){
			explosionX--;
			breachX = explosionX;
			if (explosionX == -1920){
				explosionX = 200;
			}
		}

		if (x == Gdx.graphics.getWidth()+100) {
			//Si se sale del borde los aviones se generan a la izquierda
			x = -120;
		}
		if (x%3==0){
			if (airExplotionAnimation == 25){
				y = calculateY();
				X = calculateX();

				airExplotionAnimation = 1;
			}
			else {
				airExplotionAnimation++;
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
		return (int) (200 + (Math.random()*(Gdx.graphics.getHeight()-200)));
	}
	public int calculateX(){
		return (int) (Math.random()*(Gdx.graphics.getWidth()));
	}
	public int calculateSumY(){
		return (int) Math.random()*200;
	}
	public int calculateSumX(){
		return (int) Math.random()*20;
	}
}
