package com.mygdx.game.State;

import java.util.Stack;

public class ScreenManeger {
    private Stack<StateBase> state;

    public ScreenManeger() {

        state = new Stack<StateBase>();

    }

    public void add(StateBase stat) {
        state.push(stat);
    }

    public void remove() {
        state.pop();
    }

    public void update(float dt) {
        state.peek().update(dt);
    }

    public void render() {
        state.peek().render();
    }
}
