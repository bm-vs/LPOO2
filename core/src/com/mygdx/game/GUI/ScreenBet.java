package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;

public class ScreenBet extends com.mygdx.game.GUI.ScreenState {

    public Stage stage;
    private Animator numbers;
    private boolean touchdown;
    private ImageButton bttn_bet;
    private ImageButton first12;
    private ImageButton second12;
    private ImageButton third12;
    private ImageButton even;
    private ImageButton odd;
    private ImageButton black;
    private ImageButton red;
    private ImageButton firsthalf;
    private ImageButton secondhalf;
    private ButtonGroup buttonGroup;

    private int delay = 0;

    public ScreenBet(ScreenManager sm) {
        super(sm);
        create();
    }

    public void create() {
        stage = new Stage(new ScreenViewport());
        touchdown = false;

        float screen_width = Gdx.graphics.getWidth();
        float screen_height = Gdx.graphics.getHeight();


        //================================================================================================================================================================
        // OTHER BETS

        Sprite s = new Sprite(new Texture("roulette/bets/1st12.png"));
        float scale = (screen_width/3)/s.getWidth();
        s.setSize(screen_width/3, scale*s.getHeight());
        Sprite sd = new Sprite(new Texture("roulette/bets/1st12_d.png"));
        sd.setSize(screen_width/3, scale*sd.getHeight());
        first12 = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd), new SpriteDrawable(sd));
        first12.setY(screen_height/2+first12.getHeight());

        s = new Sprite(new Texture("roulette/bets/2nd12.png"));
        s.setSize(screen_width/3, scale*s.getHeight());
        sd = new Sprite(new Texture("roulette/bets/2nd12_d.png"));
        sd.setSize(screen_width/3, scale*sd.getHeight());
        second12 = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd), new SpriteDrawable(sd));
        second12.setY(screen_height/2);

        s = new Sprite(new Texture("roulette/bets/3rd12.png"));
        s.setSize(screen_width/3, scale*s.getHeight());
        sd = new Sprite(new Texture("roulette/bets/3rd12_d.png"));
        sd.setSize(screen_width/3, scale*sd.getHeight());
        third12 = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd), new SpriteDrawable(sd));
        third12.setY(screen_height/2-first12.getHeight());

        s = new Sprite(new Texture("roulette/bets/even.png"));
        s.setSize(scale*s.getWidth(), scale*s.getHeight());
        sd = new Sprite(new Texture("roulette/bets/even_d.png"));
        sd.setSize(scale*sd.getWidth(), scale*sd.getHeight());
        even = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd), new SpriteDrawable(sd));
        even.setY(first12.getY());

        s = new Sprite(new Texture("roulette/bets/odd.png"));
        s.setSize(scale*s.getWidth(), scale*s.getHeight());
        sd = new Sprite(new Texture("roulette/bets/odd_d.png"));
        sd.setSize(scale*sd.getWidth(), scale*sd.getHeight());
        odd = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd), new SpriteDrawable(sd));
        odd.setY(first12.getY());

        s = new Sprite(new Texture("roulette/bets/red.png"));
        s.setSize(scale*s.getWidth(), scale*s.getHeight());
        sd = new Sprite(new Texture("roulette/bets/red_d.png"));
        sd.setSize(scale*sd.getWidth(), scale*sd.getHeight());
        red = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd), new SpriteDrawable(sd));
        red.setY(second12.getY());

        s = new Sprite(new Texture("roulette/bets/black.png"));
        s.setSize(scale*s.getWidth(), scale*s.getHeight());
        sd = new Sprite(new Texture("roulette/bets/black_d.png"));
        sd.setSize(scale*sd.getWidth(), scale*sd.getHeight());
        black = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd), new SpriteDrawable(sd));
        black.setY(second12.getY());

        s = new Sprite(new Texture("roulette/bets/1-18.png"));
        s.setSize(scale*s.getWidth(), scale*s.getHeight());
        sd = new Sprite(new Texture("roulette/bets/1-18_d.png"));
        sd.setSize(scale*sd.getWidth(), scale*sd.getHeight());
        firsthalf = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd), new SpriteDrawable(sd));
        firsthalf.setY(third12.getY());

        s = new Sprite(new Texture("roulette/bets/19-36.png"));
        s.setSize(scale*s.getWidth(), scale*s.getHeight());
        sd = new Sprite(new Texture("roulette/bets/19-36_d.png"));
        sd.setSize(scale*sd.getWidth(), scale*sd.getHeight());
        secondhalf = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd), new SpriteDrawable(sd));
        secondhalf.setY(third12.getY());

        first12.setX(screen_width/2-(first12.getWidth()+even.getWidth()+odd.getWidth())/2);
        second12.setX(screen_width/2-(first12.getWidth()+even.getWidth()+odd.getWidth())/2);
        third12.setX(screen_width/2-(first12.getWidth()+even.getWidth()+odd.getWidth())/2);
        even.setX(third12.getX() + third12.getWidth());
        odd.setX(even.getX() + even.getWidth());
        red.setX(third12.getX() + third12.getWidth());
        black.setX(red.getX() + red.getWidth());
        firsthalf.setX(third12.getX() + third12.getWidth());
        secondhalf.setX(firsthalf.getX() + firsthalf.getWidth());


        //================================================================================================================================================================
        // NUMBERS BET

        Image[] n = new Image[37];
        for (int i = 0; i <= 36; i++) {
            String path = "roulette/bets/";
            path += Integer.toString(i) + ".png";
            Image img = new Image(new TextureRegion(new Texture(path)));
            n[i] = img;
        }

        numbers = new Animator(n);
        scale = (screen_width/4)/numbers.getWidth();
        numbers.setSize(screen_width/4, scale*numbers.getHeight());
        numbers.setX(screen_width/2-numbers.getWidth()/2);
        numbers.setY(first12.getY()+first12.getHeight()*2);


        //================================================================================================================================================================
        // BUTTON BET
        s = new Sprite(new Texture("roulette/bttn_bet.png"));
        scale = (screen_width/4)/s.getWidth();
        s.setSize(screen_width/4, scale*s.getHeight());
        sd = new Sprite(new Texture("roulette/bttn_d_bet.png"));
        sd.setSize(screen_width/4, scale*sd.getHeight());

        bttn_bet = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_bet.setX(screen_width/2-bttn_bet.getWidth()/2);
        bttn_bet.setY(screen_height/5+bttn_bet.getHeight()/2);

        //================================================================================================================================================================
        // STAGE

        buttonGroup = new ButtonGroup(first12, second12, third12, even, odd, black, red, firsthalf, secondhalf);
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setUncheckLast(true);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        stage.addActor(numbers);
        stage.addActor(bttn_bet);
        stage.addActor(first12);
        stage.addActor(second12);
        stage.addActor(third12);
        stage.addActor(even);
        stage.addActor(odd);
        stage.addActor(black);
        stage.addActor(red);
        stage.addActor(firsthalf);
        stage.addActor(secondhalf);

        numbers.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                touchdown = false;
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                touchdown = true;
                buttonGroup.uncheckAll();
                return true;
            }
        });

        bttn_bet.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ArrayList<Integer> bet = new ArrayList<Integer>();
                Integer[] bet_arr = new Integer[1];

                if (first12.isChecked()) {for(int i=1; i<=12; i++){bet.add(i);}}
                else if (second12.isChecked()) {for(int i=13; i<=24; i++){bet.add(i);}}
                else if (third12.isChecked()) {for(int i=25; i<=36; i++){bet.add(i);}}
                else if (even.isChecked()) {for(int i=2; i<=36; i+=2){bet.add(i);}}
                else if (odd.isChecked()) {for(int i=1; i<=35; i+=2){bet.add(i);}}
                else if (red.isChecked()) {bet_arr = new Integer[] {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};}
                else if (black.isChecked()) {bet_arr = new Integer[] {2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35};}
                else if (firsthalf.isChecked()) {for(int i=1; i<=18; i++){bet.add(i);}}
                else if (secondhalf.isChecked()) {for(int i=19; i<=36; i++){bet.add(i);}}
                else {bet_arr[0] = numbers.getCurrentPos();}

                if (bet.size() != 0) {
                    bet_arr = new Integer[bet.size()];
                    bet.toArray(bet_arr);
                }

                sm.remove();
                sm.add(new ScreenRoulette(sm, bet_arr));
            }
        });


    }

    public void render(){
        Gdx.gl.glClearColor(0.086f, 0.22f, 0.157f, 1);
        stage.act();
        if (touchdown && delay < 0) {
            numbers.currentPosInc();
            delay = 5;
        }
        delay--;


        // WHEN BACK BUTTON IS PRESSED RETURN TO MENU
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            sm.remove();
        }

        stage.draw();
    }


    protected void handleInput(){}

    public void update(float dt){}

    public void dispose(){
        stage.dispose();
    }

}
