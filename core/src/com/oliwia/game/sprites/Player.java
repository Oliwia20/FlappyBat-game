package com.oliwia.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class Player
{
    private Vector3 pos; //pozycja gracza
    private Vector3 velocity; //predkosc
    private Texture player;
    private static final int GRAVITY = -15; //grawitacja do opadania gracza gdy nie skacze
    private static final int MOVEMENT = 100;
    private Rectangle bounds;
    private animation playerAnim;
    private Texture texture;
    private Sound wing;

    public Player(int x, int y)
    {
        pos = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        player = new Texture("playerUp.png");
        texture = new Texture("batAnimation.png");
        playerAnim = new animation(new TextureRegion(texture), 3, 0.5f); //3 frames - 3 klatki do animacji
        bounds = new Rectangle(x,y, texture.getWidth()/3, texture.getHeight());
        wing = Gdx.audio.newSound(Gdx.files.internal("wing.ogg"));
    }

    public void update(float dt)
    {
        playerAnim.update(dt);
        if(pos.y > 0) //zeby nie "wypadl" gracz poza ekran
        {
            velocity.add(0,GRAVITY,0);
        }
        velocity.scl(dt); //pomnoz wszystko przez dt , scl - scale
        pos.add(MOVEMENT * dt,velocity.y,0); //spadanie wiec tylko y
        if(pos.y < 0)
        {
            pos.y = 0;
        }
        velocity.scl(1/dt); //odracam zeby wrocic, kolejna klatka
        bounds.setPosition(pos.x, pos.y); //update granic obiektu
    }

    public void jump()
    {
        velocity.y = 250; //musi byc na + zeby podskoczyc
        wing.play(0.6f); //dzwiek machania skrzydlami
    }

    //gettery
    public Vector3 getPos()
    {
        return pos;
    }

    public TextureRegion getPlayer()
    {
        return playerAnim.getFrame();
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void dispose()
    {
        texture.dispose();
        wing.dispose();
    }
}
