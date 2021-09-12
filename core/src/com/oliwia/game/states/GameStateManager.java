package com.oliwia.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

//Stack - do przechowania stanow gry i aktualizowanie obecnego

public class GameStateManager
{
    private Stack<State> states;

    public GameStateManager()
    {
        states = new Stack<State>();
    }

    public void push(State state)
    {
        states.push(state);
    }

    public void pop()
    {
        states.pop().dispose(); // dispose - clear
    }

    public void set(State state)
    {
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt)
    {
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb)
    {
        states.peek().render(sb);
    }
}
