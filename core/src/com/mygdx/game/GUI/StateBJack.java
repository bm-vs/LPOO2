package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;



public class StateBJack extends ScreenState {

    private Texture background;
    private SpriteBatch batch;
    private TextButton button;
    public Stage stage;
    public Skin skin;
    BitmapFont font;
    Texture pixmapTexture;


    public StateBJack(ScreenManager sm) {
        super(sm);
        create();


    }

    @Override
    public void create() {

        batch = new SpriteBatch();
        background = new Texture("blackjack.png");


        skin = new Skin();
        stage = new Stage();

        font = new BitmapFont();
        skin.add("default", font);


        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 10, Pixmap.Format.RGBA8888);//cor das letras
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        pixmapTexture = new Texture(pixmap);
        skin.add("background", pixmapTexture);



        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GREEN);
        textButtonStyle.down = skin.newDrawable("background", Color.GREEN);
        textButtonStyle.checked = skin.newDrawable("background", Color.GREEN);
        textButtonStyle.over = skin.newDrawable("background", Color.CYAN);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);


        button = new TextButton("Play", skin);
        button.getLabel().setFontScale(2, 2);

        Table buttonsTable = new Table();
        buttonsTable.add(button).width(Gdx.graphics.getWidth() / 3).padBottom(50);
        buttonsTable.setFillParent(true);

        buttonsTable.top().padTop(Gdx.graphics.getWidth() / 5);


        Gdx.input.setInputProcessor(stage);
        stage.addActor(buttonsTable);


        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("play");
                sm.add(new PlayBJack(sm));

            }
        });
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            sm.remove();
        }

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        batch.begin();
        batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        batch.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
        background.dispose();
        font.dispose();
        pixmapTexture.dispose();

    }

}
