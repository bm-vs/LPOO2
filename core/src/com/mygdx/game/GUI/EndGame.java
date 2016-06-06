package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
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
import com.mygdx.game.GUI.ScreenManager;
import com.mygdx.game.GUI.ScreenState;

public class EndGame extends ScreenState {

    Texture background;
    TextButton newGame;
    TextButton exit;
    SpriteBatch batch;

    public Stage stage;
    public Skin skin;
    BitmapFont font;
    Texture pixmapTexture;


    public EndGame(ScreenManager sm) {
        super(sm);
        create();
    }

    @Override
    public void create() {
        background = new Texture("gameover.png");
        skin = new Skin();
        stage = new Stage();
        batch = new SpriteBatch();


        font = new BitmapFont();
        skin.add("default", font);


        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 10, Pixmap.Format.RGBA8888);//cor das letras        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        pixmapTexture = new Texture(pixmap);
        skin.add("background", pixmapTexture);


        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.BLUE);
        textButtonStyle.down = skin.newDrawable("background", Color.BLUE);
        textButtonStyle.checked = skin.newDrawable("background", Color.BLUE);
        textButtonStyle.over = skin.newDrawable("background", Color.CYAN);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        newGame = new TextButton("New Game", skin);
        newGame.getLabel().setFontScale(2, 2);


        exit = new TextButton("Exit", skin);
        exit.getLabel().setFontScale(2, 2);


        Table buttonsTable = new Table();
        buttonsTable.add(newGame).width(Gdx.graphics.getWidth() / 2).padBottom(50);
        buttonsTable.row();
        buttonsTable.add(exit).width(Gdx.graphics.getWidth() / 2).padBottom(50);
        buttonsTable.row();
        buttonsTable.setFillParent(true);

        //buttonsTable.top().padTop(Gdx.graphics.getHeight() / 4);


        Gdx.input.setInputProcessor(stage);
        stage.addActor(buttonsTable);

        exit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });

        newGame.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sm.remove();
                sm.add(new ScreenMenu(sm));
            }
        });


    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(background, 0, 0, WIDTH, HEIGHT);
        batch.end();

        stage.act();
        stage.draw();


    }

    @Override
    public void dispose() {
        stage.dispose();
        background.dispose();
        batch.dispose();
        skin.dispose();
        font.dispose();
        pixmapTexture.dispose();


    }
}

