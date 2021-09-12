package com.oliwia.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.oliwia.game.FlappyBirb;
import com.oliwia.game.sprites.Player;
import com.oliwia.game.sprites.Rocks;
import com.badlogic.gdx.utils.Array;


public class PlayState extends State
{
    private Player player;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;
    //private Rocks rock;

    private static final int ROCK_SPACING = 120; //odstep w X miedzy skalami
    private static final int ROCK_COUNT = 50;
    public static  final int GROUND_OFFSET = -15;

    public int score = 0;
    private String scoreName;
    //BitmapFont fontName;
    private BitmapFont font;
    CharSequence str = "";

    //private Array<Rocks> rocks;
    private Array<Rocks> rocks;

    public PlayState(GameStateManager gsm)
    {
        super(gsm);
        player = new Player(50,200);
        cam.setToOrtho(false, FlappyBirb.WIDTH/2, FlappyBirb.HEIGHT/2);
        bg = new Texture("bg.png");
        font = new BitmapFont(Gdx.files.internal("font.fnt"),Gdx.files.internal("font.png"),false,true);

        //rock = new Rocks(100);
        rocks = new Array<Rocks>();
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(cam.position.x - cam.viewportWidth/2, GROUND_OFFSET);
        groundPos2 = new Vector2((cam.position.x - cam.viewportWidth/2)+ground.getWidth(),GROUND_OFFSET);

        for (int i = 0; i <= ROCK_COUNT; i++) //ograniczenie
        {
            rocks.add(new Rocks(i * (ROCK_SPACING + Rocks.ROCK_WIDTH)));
        }
    }

    @Override
    protected void handleInput()
    {
        if(Gdx.input.justTouched()) //jesli klikniecie to skocz
        {
            player.jump();
        }
    }

    @Override
    public void update(float dt)
    {
        handleInput();
        updateGround();
        player.update(dt);
        cam.position.x = player.getPos().x + 80; //offset 80

        //for(Rocks rocks : rocks) //generowanie nowych obiektow
        for (int i = 0; i < rocks.size; i++) {
            Rocks rock = rocks.get(i);

            if(cam.position.x - (cam.viewportWidth/2) > rock.getPosStalactite().x + rock.getStalactite().getWidth())
            {
                score++; //gdy mine obiekt + 1 do wyniku
                rock.reposition(rock.getPosStalactite().x + ((Rocks.ROCK_WIDTH + ROCK_SPACING) * ROCK_COUNT));
            }

            if(rock.collides(player.getBounds()))
            {
                gsm.set(new MenuState(gsm));
                score = 0;
            }
        }
        str = String.valueOf(score);
        if(player.getPos().y <= ground.getHeight() + GROUND_OFFSET) //kolizja przy dotknieciu ziemi
        {
            gsm.set(new MenuState(gsm));
            score = 0;
        }
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) //"rysowanie"
    {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(bg,cam.position.x-(cam.viewportWidth/2),0);
        sb.draw(player.getPlayer(),player.getPos().x,player.getPos().y);
        for(Rocks rocks : rocks)
        {
            sb.draw(rocks.getStalactite(),rocks.getPosStalactite().x, rocks.getPosStalactite().y);
            sb.draw(rocks.getStalagmite(),rocks.getPosStalagmite().x, rocks.getPosStalagmite().y);
        }

        sb.draw(ground,groundPos1.x,groundPos1.y);
        sb.draw(ground,groundPos2.x,groundPos2.y);
        sb.end();

        sb.setProjectionMatrix(camHUD.combined); //stabilna kamera ktora pokazuje wynik
        sb.begin();
        font.setColor(1.0f, 1.0f, 1.0f, 1.0f);
        font.draw(sb, str, -2, FlappyBirb.HEIGHT/3);
        sb.end();

    }

    @Override
    public void dispose()
    {
        bg.dispose();
        player.dispose();
        ground.dispose();
        for(Rocks rocks : rocks)
        {
            rocks.dispose();
        }
    }

    private void updateGround() //zeby ziemia nie konczyla sie przy przeuwaniu
    {
        if(((cam.position.x) - (cam.viewportWidth/2)) > (groundPos1.x + ground.getWidth()))
        {
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(((cam.position.x) - (cam.viewportWidth/2))> (groundPos2.x + ground.getWidth()))
        {
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
