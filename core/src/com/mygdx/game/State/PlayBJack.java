package com.mygdx.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.logic.Blackjack;
import com.mygdx.game.logic.Carta;
import com.mygdx.game.logic.Player;

import java.util.Vector;


public class PlayBJack extends StateBase {
    Player P;
    Blackjack blackjack;
    private Texture background;
    private TextButton start, giveCard, stand; //
    private boolean inicio, jogavel;
    private Texture cartas;
    Texture texturaCartas;
    Label money;

    private SpriteBatch batch;

    public Stage stage;
    public Skin skin;
    BitmapFont font;
    Texture pixmapTexture;
    Player player;
    Integer value;


    public PlayBJack(ScreenManeger sm) {
        super(sm);
        create();
    }


    @Override
    protected void create() {

        P = new Player(100);
        blackjack = new Blackjack(10, P);
        background = new Texture("blackJackMesa.png");
        inicio = new Boolean(true);
        jogavel = new Boolean(false);

        batch = new SpriteBatch();
        skin = new Skin();
        stage = new Stage();
        font = new BitmapFont();
        value= Integer.valueOf((int) P.getMoney());
        money = new Label (String.format("%03d",value), new Label.LabelStyle(font, Color.BLACK));


        skin.add("default", font);


        Pixmap pixmap = new Pixmap(Gdx.graphics.getWidth() / 4, Gdx.graphics.getHeight() / 10, Pixmap.Format.RGBA8888);//cor das letras
        pixmap.setColor(Color.WHITE);
        pixmap.fill();

        pixmapTexture = new Texture(pixmap);
        skin.add("background", pixmapTexture);

        // Creating button styles
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.down = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.checked = skin.newDrawable("background", Color.GRAY);
        textButtonStyle.over = skin.newDrawable("background", Color.BLUE);
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        start = new TextButton("Start", skin);
        start.getLabel().setScale(2, 2);
        start.bottom().setScale(2, 2);


        giveCard = new TextButton("Give", skin);
        giveCard.getLabel().setFontScale(2, 1.5f);

        stand = new TextButton("Stand", skin);

        stand.getLabel().setFontScale(2, 1.5f);



        Table buttonsTable = new Table();

        buttonsTable.add(money);
        buttonsTable.add(start).pad(10);
        buttonsTable.add(giveCard).pad(10);
        buttonsTable.add(stand).pad(10);
        buttonsTable.setFillParent(true);

        buttonsTable.right().bottom();
        buttonsTable.pad(10);


        Gdx.input.setInputProcessor(stage);
        stage.addActor(buttonsTable);

        stand.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
              //  stand.setLayoutEnabled(false);

            }
        });

        start.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                start.setLayoutEnabled(false);
                stand.setLayoutEnabled(true);
                giveCard.setLayoutEnabled(true);

               /* if (P.getMoney() < 10) ;
                    //============================estdo fim
                else {*/
                    P.setMoney(P.getMoney() - 10);


                    blackjack.giveCard(0);
                    blackjack.giveCard(1);
                    blackjack.giveCard(1);

               // }

            }
        });

        giveCard.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //giveCard.setLayoutEnabled(false);

            }
        });


        texturaCartas = new Texture("cartas.png");


    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Vector<Carta> cartasDealer= blackjack.getPlayers().get(0).getcartasJogada();

        for (int i = 0; i < cartasDealer.size(); i++)
        {

               int k= "PECO".indexOf(cartasDealer.get(i).getNaipe());
            int j=cartasDealer.get(i).getcarta();

                TextureRegion region = new TextureRegion(texturaCartas,j * 43, k * 57+1, 40, 55 );
                batch.draw(region,j * 12 + k * 100+10, j * 10+10);




        }


        batch.end();

        stage.act();
        stage.draw();


    }

   /* public void drawCard(int PLAYER)
    {
        if(PLAYER==0)//dealer
        {

        }

    }*/

    @Override
    public void dispose() {

        stage.dispose();
        skin.dispose();
        background.dispose();
        font.dispose();
        pixmapTexture.dispose();

    }
}
