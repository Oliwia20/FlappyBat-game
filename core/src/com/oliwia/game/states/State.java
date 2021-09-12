package com.oliwia.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.oliwia.game.FlappyBirb;

public abstract class State //abstract - nie moge utworzyc instancji obiektu State
{
    protected OrthographicCamera cam; //kamera do sledzenia
    protected OrthographicCamera camHUD; //kamera do sledzenia
    protected Viewport viewport;
    protected Vector3 mouse; //sterowanie(polozenie)
    protected GameStateManager gsm; //do manipulacji "state", przejścia między stanami np. Play

    protected State(GameStateManager gsm) //konstruktor
    {
        this.gsm = gsm;
        cam = new OrthographicCamera();
        cam.setToOrtho(false, FlappyBirb.WIDTH, FlappyBirb.HEIGHT);
        camHUD = new OrthographicCamera(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
        viewport = new StretchViewport(FlappyBirb.WIDTH, FlappyBirb.HEIGHT,cam);
        mouse = new Vector3();
    }

    protected abstract void handleInput();
    public abstract void update(float dt); //update miedzy klatkami
    public abstract void render(SpriteBatch sb); //kontener na textury itp.
    public abstract void dispose();

}
