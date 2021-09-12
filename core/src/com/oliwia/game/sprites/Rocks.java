package com.oliwia.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

public class Rocks
{
    private Texture stalactite,stalagmite;
    private Vector2 posStalactite,posStalagmite;
    private Random rand;
    private static final int posY = 120; //px , do losowania na osi Y
    private static  final int GAP = 130;
    private static  final int lowestPoint = 120; //najmniejszy mozliwy punkt w ktorym moze byc "czubek"
    public static  final int ROCK_WIDTH = 50;
    private Rectangle boundsTop, boundsBot; // "niewidzialne" ograniczniki do kolizji

    public Rocks(float x)
    {
        stalactite = new Texture("stalactite.png");
        stalagmite = new Texture("stalagmite.png");

        rand = new Random();

        // polozenie stalaktytow i stalagmitow
        posStalactite = new Vector2(x,rand.nextInt(posY) + GAP + lowestPoint);
        posStalagmite = new Vector2(x,posStalactite.y - GAP - stalagmite.getHeight());

        boundsTop = new Rectangle(posStalactite.x, posStalactite.y, stalactite.getWidth(), stalactite.getHeight());
        boundsBot = new Rectangle(posStalagmite.x, posStalagmite.y, stalagmite.getWidth(), stalagmite.getHeight());
    }

    //gettery
    public Texture getStalactite() { return stalactite; }

    public Texture getStalagmite() { return stalagmite; }

    public Vector2 getPosStalactite() { return posStalactite; }

    public Vector2 getPosStalagmite() { return posStalagmite; }

    public void reposition(float x) //przemieszczenie
    {
        posStalactite.set(x,rand.nextInt(posY) + GAP + lowestPoint);
        posStalagmite.set(x,posStalactite.y - GAP - stalagmite.getHeight());
        boundsTop.setPosition(posStalactite.x,posStalactite.y);
        boundsBot.setPosition(posStalagmite.x,posStalagmite.y);
    }

    public boolean collides(Rectangle playerRec)
    {
        return playerRec.overlaps(boundsTop) || playerRec.overlaps(boundsBot);
    }

    public void dispose()
    {
        stalactite.dispose();
        stalagmite.dispose();
    }
}
