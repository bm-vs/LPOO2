package com.mygdx.game.GUI;

import java.util.Stack;

public class ScreenManager {
    private Stack<ScreenState> state;

    public ScreenManager() {
        state = new Stack<ScreenState>();
    }

    public void add(ScreenState stat) {
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

