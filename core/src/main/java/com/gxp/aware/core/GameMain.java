package com.gxp.aware.core;

import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameMain implements ApplicationListener
{
	static final Logger LOG = LoggerFactory.getLogger(GameMain.class);
	
	private int gameWidth;
	private int gameHeight;
	
	Texture texture;
	SpriteBatch batch;
	float elapsed;

	@Override
	public void create()
	{
		texture = new Texture(Gdx.files.internal("monsters/type_1.png"));
		batch = new SpriteBatch();
	}

	@Override
	public void resize(int width, int height)
	{
		LOG.info("initial size {}x{}", width, height);
		gameWidth = width;
		gameHeight = height;
	}

	@Override
	public void render()
	{
		elapsed += Gdx.graphics.getDeltaTime();

		clearBlack();

		handleTouch();

		doDraw();
	}

	@Override
	public void pause()
	{
	}

	@Override
	public void resume()
	{
	}

	@Override
	public void dispose()
	{
	}

	private void clearBlack()
	{
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	}

	private void doDraw()
	{
		batch.begin();
		batch.draw(texture, 100f, 100f, 64f, 64f);
		batch.end();
	}

	private void handleTouch()
	{
		boolean isTouched = Gdx.input.isTouched();
		int x = Gdx.input.getX();
		int y = Gdx.input.getY();

		if (isTouched)
		{
			System.out.println("x:y " + x + ":" + y);
		}
	}
}
