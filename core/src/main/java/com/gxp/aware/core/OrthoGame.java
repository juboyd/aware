package com.gxp.aware.core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.math.Rectangle;

public class OrthoGame
{
	private OrthographicCamera cam;
	private Rectangle glViewport;
	private float rotationSpeed;
	
	private Texture map;
	private Mesh mesh;

	public OrthoGame(int width, int height)
	{
		rotationSpeed = 0.5f;
		mesh = new Mesh(true, 4, 6,
				new VertexAttribute(VertexAttributes.Usage.Position, 3, "attr_Position"),
				new VertexAttribute(VertexAttributes.Usage.TextureCoordinates, 2, "attr_texCoords"));
		map = new Texture(Gdx.files.internal("map/sc_map.png"));
		mesh.setVertices(new float[]
				{
					-1024f, -1024f, 0, 0, 1,
					1024f, -1024f, 0, 1, 1,
					1024f, 1024f, 0, 1, 0,
					-1024f, 1024f, 0, 0, 0
				});
		mesh.setIndices(new short[]
				{
					0, 1, 2, 2, 3, 0
				});
		
		cam = new OrthographicCamera(width, height);
		cam.position.set(width / 2, height / 2, 0);
		
		glViewport = new Rectangle(0, 0, width, height);
	}
	
	

	public void handleInput()
	{
		if (Gdx.input.isKeyPressed(Input.Keys.A))
		{
			cam.zoom += 0.02;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.Q))
		{
			cam.zoom -= 0.02;
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			if (cam.position.x > 0)
			{
				cam.translate(-3, 0, 0);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			if (cam.position.x < 1024)
			{
				cam.translate(3, 0, 0);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.DOWN))
		{
			if (cam.position.y > 0)
			{
				cam.translate(0, -3, 0);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.UP))
		{
			if (cam.position.y < 1024)
			{
				cam.translate(0, 3, 0);
			}
		}
		if (Gdx.input.isKeyPressed(Input.Keys.W))
		{
			cam.rotate(-rotationSpeed, 0, 0, 1);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.E))
		{
			cam.rotate(rotationSpeed, 0, 0, 1);
		}
	}

	public void cameraRender()
	{
		GL10 gl = Gdx.graphics.getGL11();

		// Camera --------------------- /
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		gl.glViewport((int) glViewport.x, (int) glViewport.y,
				(int) glViewport.width, (int) glViewport.height);

		cam.update();
		cam.apply(gl);

		// Texturing --------------------- /
		gl.glActiveTexture(GL10.GL_TEXTURE0);
		gl.glEnable(GL10.GL_TEXTURE_2D);
		map.bind();

		mesh.render(GL10.GL_TRIANGLES);
	}
}
