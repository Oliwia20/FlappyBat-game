package com.oliwia.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.oliwia.game.states.GameStateManager;
import com.oliwia.game.states.MenuState;

public class FlappyBirb extends ApplicationAdapter
{
	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;
	public static final String TITLE = "Flappy Bat";
	private GameStateManager gsm;
	private SpriteBatch batch;

	//public int score = 2;

	private Music music;
	
	@Override
	public void create ()
	{
		batch = new SpriteBatch();

		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("musicCave.mp3")); //muzyka w tle
		music.setLooping(true); //zeby nie konczyla sie nigdy
		music.setVolume(0.3f); //glosnosc
		music.play();
		ScreenUtils.clear(1, 0, 0, 1); //usuwa grafike i rysuje ja na nowo
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render ()
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime()); //okreslenie dt
		gsm.render(batch);
	}

	@Override
	public void dispose() {
		super.dispose();
		music.dispose();
	}
	
	/*@Override
	public void dispose ()
	{
		//batch.dispose();
		//img.dispose();
	}*/
}
