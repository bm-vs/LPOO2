package com.mygdx.game.GUI;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Logic.SlotMachine;

public class ScreenSlotMachine extends ScreenState {


    public Stage stage;
    private SlotMachine machine;
    private Animator wheel1;
    private Animator wheel2;
    private Animator wheel3;
    private long t_start;
    private ImageButton bttn_play;
    private Animator result;

    public ScreenSlotMachine(ScreenManager sm) {
        super(sm);
        create();
    }

    @Override
    public void create() {
        stage = new Stage(new ScreenViewport());
        machine = new SlotMachine();

        float screen_width = Gdx.graphics.getWidth();
        float screen_height = Gdx.graphics.getHeight();
        float pad = screen_width/29;

        //================================================================================================================================================================
        // BUTTON PLAY
        Sprite s = new Sprite(new Texture("slot_machine/bttn_play.png"));
        float scale = (screen_width/4)/s.getWidth();
        s.setSize(screen_width/4, scale*s.getHeight());

        bttn_play = new ImageButton(new SpriteDrawable(s));
        bttn_play.setX(screen_width/2-bttn_play.getWidth()/2);
        bttn_play.setY(screen_height/5-bttn_play.getHeight()/2);

        //================================================================================================================================================================
        // SLOT WHEELS
        Image[] wheel = new Image[20];
        wheel[0] = new Image(new TextureRegion(new Texture("slot_machine/0.png"))); wheel[1] = new Image(new TextureRegion(new Texture("slot_machine/01.png")));
        wheel[2] = new Image(new TextureRegion(new Texture("slot_machine/1.png"))); wheel[3] = new Image(new TextureRegion(new Texture("slot_machine/12.png")));
        wheel[4] = new Image(new TextureRegion(new Texture("slot_machine/2.png"))); wheel[5] = new Image(new TextureRegion(new Texture("slot_machine/23.png")));
        wheel[6] = new Image(new TextureRegion(new Texture("slot_machine/3.png"))); wheel[7] = new Image(new TextureRegion(new Texture("slot_machine/34.png")));
        wheel[8] = new Image(new TextureRegion(new Texture("slot_machine/4.png"))); wheel[9] = new Image(new TextureRegion(new Texture("slot_machine/45.png")));
        wheel[10] = new Image(new TextureRegion(new Texture("slot_machine/5.png"))); wheel[11] = new Image(new TextureRegion(new Texture("slot_machine/56.png")));
        wheel[12] = new Image(new TextureRegion(new Texture("slot_machine/6.png"))); wheel[13] = new Image(new TextureRegion(new Texture("slot_machine/67.png")));
        wheel[14] = new Image(new TextureRegion(new Texture("slot_machine/7.png"))); wheel[15] = new Image(new TextureRegion(new Texture("slot_machine/78.png")));
        wheel[16] = new Image(new TextureRegion(new Texture("slot_machine/8.png"))); wheel[17] = new Image(new TextureRegion(new Texture("slot_machine/89.png")));
        wheel[18] = new Image(new TextureRegion(new Texture("slot_machine/9.png"))); wheel[19] = new Image(new TextureRegion(new Texture("slot_machine/90.png")));

        wheel1 = new Animator(wheel);
        scale = (screen_width/4)/wheel1.getWidth();
        wheel1.setSize(screen_width/4, scale*wheel1.getHeight());
        wheel1.setX(screen_width/2-wheel1.getWidth()*3/2-pad);
        wheel1.setY(screen_height/2+wheel1.getHeight()/8);

        wheel2 = new Animator(wheel);
        wheel2.setSize(screen_width/4, scale*wheel2.getHeight());
        wheel2.setX(screen_width/2-wheel2.getWidth()/2);
        wheel2.setY(screen_height/2+wheel2.getHeight()/8);

        wheel3 = new Animator(wheel);
        wheel3.setSize(screen_width/4, scale*wheel3.getHeight());
        wheel3.setX(screen_width/2+wheel3.getWidth()/2+pad);
        wheel3.setY(screen_height/2+wheel3.getHeight()/8);

        //================================================================================================================================================================
        // WINNINGS IMAGE
        Image[] results = new Image[4];
        results[0] = new Image(new TextureRegion(new Texture("slot_machine/winnings_0.png"))); results[1] = new Image(new TextureRegion(new Texture("slot_machine/winnings_2.png")));
        results[2] = new Image(new TextureRegion(new Texture("slot_machine/winnings_5.png"))); results[3] = new Image(new TextureRegion(new Texture("slot_machine/winnings_10.png")));

        result = new Animator(results);
        scale = (screen_width/3)/result.getWidth();
        result.setSize(screen_width/3, scale*result.getHeight());
        result.setX(screen_width/2-result.getWidth()/2);
        result.setY(screen_height/3-result.getHeight()/4);


        //================================================================================================================================================================
        // STAGE

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        stage.addActor(bttn_play);
        stage.addActor(wheel1);
        stage.addActor(wheel2);
        stage.addActor(wheel3);
        stage.addActor(result);

        bttn_play.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (wheel1.getChanging() || wheel2.getChanging() || wheel3.getChanging()) {
                    return;
                }

                wheel1.setChanging(true);
                wheel2.setChanging(true);
                wheel3.setChanging(true);
                machine.roll();
                t_start = TimeUtils.millis();
            }
        });
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.086f, 0.22f, 0.157f, 1);
        stage.act();

        // SPIN WHEELS
        int dur1 = 1500; int dur2 = 2000; int dur3 = 2500;
        if (wheel1.getCurrentPos() == machine.getN1()*2 && TimeUtils.millis()-t_start > dur1) {
            wheel1.setChanging(false);
        }
        if (wheel2.getCurrentPos() == machine.getN2()*2 && TimeUtils.millis()-t_start > dur2) {
            wheel2.setChanging(false);
        }
        if (wheel3.getCurrentPos() == machine.getN3()*2 && TimeUtils.millis()-t_start > dur3) {
            wheel3.setChanging(false);

            switch(machine.prize()) {
                case 2:
                    result.setCurrentPos(1);
                    break;
                case 5:
                    result.setCurrentPos(2);
                    break;
                case 10:
                    result.setCurrentPos(3);
                    break;
                default:
                    result.setCurrentPos(0);
                    break;
            }

        }

        // CHANGE WHEELS POSITION
        if (wheel1.getChanging()) {
            wheel1.currentPosInc();
        }
        if (wheel2.getChanging()) {
            wheel2.currentPosInc();
        }
        if (wheel3.getChanging()) {
            wheel3.currentPosInc();
        }

        // WHEN BACK BUTTON IS PRESSED RETURN TO MENU
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            sm.remove();
        }

        stage.draw();
    }

    @Override
    protected void handleInput() {}

    @Override
    public void update(float dt) {}

    @Override
    public void dispose() {
        stage.dispose();
    }
}
