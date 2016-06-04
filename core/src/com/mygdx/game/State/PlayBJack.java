package com.mygdx.game.State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
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

    private static final int DEALER = 0;
    private static final int PLAYER = 1;

    Player P;
    Blackjack blackjack;
    private Texture background;
    private TextButton start, giveCard, stand;
    private boolean START;
    private Texture texturaCartas;
    Label money;

    private SpriteBatch batch;

    public Stage stage;
    public Skin skin;
    BitmapFont font;
    Texture pixmapTexture;
    Integer value;
    Label result;


    public PlayBJack(ScreenManeger sm) {
        super(sm);
        create();
    }


    @Override
    protected void create() {
        P = new Player(100);

        background = new Texture("blackJackMesa.png");
        texturaCartas = new Texture("cartas.png");

        START = new Boolean(false);


        batch = new SpriteBatch();
        skin = new Skin();
        stage = new Stage();
        font = new BitmapFont();

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
        giveCard.setTouchable(Touchable.disabled);


        stand = new TextButton("Stand", skin);
        stand.getLabel().setFontScale(2, 1.5f);
        stand.setTouchable(Touchable.disabled);


        stand.addListener(new ClickListener() {
                              @Override
                              public void clicked(InputEvent event, float x, float y) {

                                  blackjack.stand();
                                  blackjack.win();

                                  start.setTouchable(Touchable.enabled);
                                  stand.setTouchable(Touchable.disabled);
                                  giveCard.setTouchable(Touchable.disabled);
                              }
                          }

        );

        start.addListener(new ClickListener() {
                              @Override
                              public void clicked(InputEvent event, float x, float y) {
                                  start.setTouchable(Touchable.disabled);

                                  stand.setTouchable(Touchable.enabled);
                                  giveCard.setTouchable(Touchable.enabled);


                                  if (P.getMoney() < 10)
                                  {
                                      giveCard.setLayoutEnabled(false);
                                      sm.remove();
                                      sm.add(new EndGame(sm));
                                  }
                                  else {
                                      P.setMoney(P.getMoney() - 10);

                                      START = true;
                                      blackjack = new Blackjack(10, P);

                                     System.out.print(blackjack.giveCard(DEALER).toda());
                                      System.out.print(blackjack.giveCard(PLAYER).toda());
                                      System.out.print(blackjack.giveCard(PLAYER).toda());


                                  }
                              }
                          }

        );

        giveCard.addListener(new ClickListener() {
                                 @Override
                                 public void clicked(InputEvent event, float x, float y) {

                                     blackjack.giveCard(PLAYER);
                                     if (blackjack.getPlayers().get(PLAYER).getValuePlayer() > 21) {

                                         start.setTouchable(Touchable.enabled);
                                         stand.setTouchable(Touchable.disabled);
                                         giveCard.setTouchable(Touchable.disabled);
                                         blackjack.win();

                                     }

                                 }
                             }

        );
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
        batch.draw(background, 0, 0, WIDTH, HEIGHT);
        batch.end();

        drawScene();
        stage.act();
        stage.draw();

        if (START) {
            drawCards(DEALER);
            drawCards(PLAYER);

            if (blackjack.LOSE)
                lose();
            else
                if (blackjack.WIN)
                win();
        }
    }

    public void drawCards(int jogador) {

        Vector<Carta> cartas = blackjack.getPlayers().get(jogador).getcartasJogada();
        int dx = 0;

        batch.begin();
        if (jogador == DEALER)
        {
            for (Carta card : cartas) {
                int j = "PECO".indexOf(card.getNaipe());
                int i = card.getcarta();
                i--;
                dx += 20;

                TextureRegion region = new TextureRegion(texturaCartas, i * 43, j * 57 + 1, 40, 55);
                batch.draw(region, Gdx.graphics.getWidth() / 2 - 50 + dx, Gdx.graphics.getHeight() * 3 / 4);

            }
        }

        if (jogador == PLAYER)
        {
            for (Carta card : cartas) {
                int j = "PECO".indexOf(card.getNaipe());
                int i = card.getcarta();
                i--;
                dx += 20;

                TextureRegion region = new TextureRegion(texturaCartas, i * 43, j * 57 + 1, 40, 55);
                batch.draw(region, WIDTH / 2 - 50 + dx, HEIGHT / 5);
            }
        }
        batch.end();
    }

    public void drawScene() {
        value = Integer.valueOf((int) P.getMoney());
        money = new Label(String.format("£%3d", value), new Label.LabelStyle(font, Color.WHITE));
        money.setFontScale(1.5f, 1.5f);

        Table buttonsTable = new Table();

        buttonsTable.add(money).left();
        buttonsTable.row();
        buttonsTable.add(start).pad(10);
        buttonsTable.add(giveCard).pad(10);
        buttonsTable.add(stand).pad(10);
        buttonsTable.setFillParent(true);

        buttonsTable.right().bottom();
        buttonsTable.pad(10);


        Gdx.input.setInputProcessor(stage);
        stage.addActor(buttonsTable);
    }

    public void lose() {
        result = new Label("LOSE !!", new Label.LabelStyle(font, Color.RED));
        result.setFontScale(3, 3);

        Table table = new Table();
        table.add(result);
        table.setFillParent(true);

        Stage stage2 = new Stage();
        stage2.addActor(table);
        stage2.act();
        stage2.draw();


    }

    public void win() {

        result = new Label("WIN !!", new Label.LabelStyle(font, Color.RED));
        result.setFontScale(3, 3);

        Table table = new Table();
        table.add(result);
        table.setFillParent(true);

        Stage stage2 = new Stage();
        stage2.addActor(table);
        stage2.act();
        stage2.draw();

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
