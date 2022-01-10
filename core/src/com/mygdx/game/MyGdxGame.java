package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Texture stuka;
	Texture background;
	Texture ground;
	Texture m147[] = new Texture[13];
	Texture[] deagle = new Texture[2];
	int x = 0;
	int deagleAnimation = 0;
	int machineGunAnimation = 0;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture( "Aven Games Transparent.png");
		stuka = new Texture("GER_Ju87R.png");
		background = new Texture("sky.png");
		ground = new Texture("road.png");
		for (int i = 0; i < m147.length; i++) {
			m147[i] = new Texture("417_" + i + ".png");
		}
		deagle[0] = new Texture("DEagle_0.png");
		deagle[1] = new Texture("DEagle_1.png");

	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.draw(ground, 0, 0);
		batch.draw(stuka, x+20, 250);
		batch.draw(stuka, x, 310);
		batch.draw(stuka, x+35, 370);
		x++;
		if (x == 750) {
			//Si se sale del borde los aviones se generan a la izquierda
			x = -55;
		}
		batch.draw(m147[machineGunAnimation], 10, 0);
		if (machineGunAnimation == 12){
			machineGunAnimation = 0;
		}
		else {
			machineGunAnimation++;
		}
		batch.draw(deagle[deagleAnimation], 100, 0);
		if (x%40 == 0){ //Cada 40 iteraciones el arma dispara
			if (deagleAnimation == 1){
				deagleAnimation = 0;
			}
			else {
				deagleAnimation++;
			}
		}

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
