package com.oliwia.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.oliwia.game.FlappyBirb;

public class MenuState extends State //rozszerza State ktore jest odpowiedzialne za scene
{
    private Texture bg; // tlo
    private Texture playBtn; // przycisk graj
    private BitmapFont font;
    private String str = "FlappyBat";

    public MenuState(GameStateManager gsm)
    {
        super(gsm);
        //cam.setToOrtho(false, FlappyBirb.WIDTH, FlappyBirb.HEIGHT);
        bg = new Texture("bg.png");
        playBtn = new Texture("play.png");
        font = new BitmapFont(Gdx.files.internal("font.fnt"),Gdx.files.internal("font.png"),false,true);
        font.getData().setScale(2);
    }

    @Override
    public void handleInput()
    {
        if(Gdx.input.justTouched())
        {
            gsm.set(new PlayState(gsm));
            //dispose(); // free mem
        }
    }

    @Override
    public void update(float dt) { handleInput(); }

    @Override
    public void render(SpriteBatch sb)
    {
        sb.setProjectionMatrix(cam.combined);
        //wyswietlenie odpowiednich grafik
        sb.begin();
        sb.draw(bg,0,0, FlappyBirb.WIDTH, FlappyBirb.HEIGHT);
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        font.draw(sb, str, (FlappyBirb.WIDTH/2), FlappyBirb.HEIGHT/1.2f, 0, Align.center, false);
        //sb.draw(bg,cam.position.x-(cam.viewportWidth/2),0);
        sb.draw(playBtn, (FlappyBirb.WIDTH/2) - (playBtn.getWidth()/2), FlappyBirb.HEIGHT/2); // pozycja przycisku start /2 zeby wysrodkowac
        sb.end();
    }

    @Override
    public void dispose()
    {
        bg.dispose(); // potrzebne do przejscia miedzy scenami
        playBtn.dispose();
        font.dispose();
    }
}
