package com.mygdx.game.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.Logic.Card;
import com.mygdx.game.Logic.Player;
import com.mygdx.game.Logic.Blackjack;
import com.badlogic.gdx.graphics.Color;

import java.util.Vector;

public class ScreenBlackjack extends ScreenState {

    private static final int DEALER = 0;
    private static final int PLAYER = 1;

    private Blackjack blackjack;
    private boolean START;
    private Texture texturaCartas;
    private SpriteBatch batch;
    private Stage stage;
    private Image result;
    private ImageButton bttn_deal;
    private ImageButton bttn_hit;
    private ImageButton bttn_stand;
    private float dealer_y;
    private float player_y;
    private float card_height;
    private Label player_money;

    public ScreenBlackjack(ScreenManager sm, Player P) {
        super(sm, P);
        create();
    }


    @Override
    public void create() {

        texturaCartas = new Texture("blackjack/cartas.png");
        START = false;

        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());


        int n = (int) Math.floor(P.getMoney());
        player_money = new Label(new String(Integer.toString(n)), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        float scale = (WIDTH/16)/player_money.getHeight();
        player_money.setFontScale(scale);
        player_money.setX(WIDTH - WIDTH/6);
        player_money.setY(HEIGHT - scale*player_money.getHeight());


        //===============================================================================================================================================
        // BUTTONS
        Sprite s = new Sprite(new Texture("blackjack/bttn_deal.png"));
        scale = (WIDTH/3)/s.getWidth();
        s.setSize(WIDTH/3, scale*s.getHeight());
        Sprite sd = new Sprite(new Texture("blackjack/bttn_d_deal.png"));
        sd.setSize(WIDTH/3, scale*sd.getHeight());
        bttn_deal = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_deal.setX(WIDTH/2-bttn_deal.getWidth()/2);
        bttn_deal.setY(WIDTH/10);

        s = new Sprite(new Texture("blackjack/bttn_hit.png"));
        s.setSize(WIDTH/3, scale*s.getHeight());
        sd = new Sprite(new Texture("blackjack/bttn_d_hit.png"));
        sd.setSize(WIDTH/3, scale*sd.getHeight());
        bttn_hit = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_hit.setX(WIDTH/9);
        bttn_hit.setY(WIDTH/10);

        s = new Sprite(new Texture("blackjack/bttn_stand.png"));
        s.setSize(WIDTH/3, scale*s.getHeight());
        sd = new Sprite(new Texture("blackjack/bttn_d_stand.png"));
        sd.setSize(WIDTH/3, scale*sd.getHeight());
        bttn_stand = new ImageButton(new SpriteDrawable(s), new SpriteDrawable(sd));
        bttn_stand.setX(bttn_hit.getX()+bttn_hit.getWidth()+WIDTH/9);
        bttn_stand.setY(WIDTH/10);

        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        stage.addActor(bttn_deal);
        stage.addActor(player_money);


        bttn_stand.addListener(new ClickListener() {
          @Override
          public void clicked(InputEvent event, float x, float y) {

              blackjack.stand();
              blackjack.win();

              int n = (int) Math.floor(P.getMoney());
              player_money.setText(new String(Integer.toString(n)));

              bttn_hit.remove();
              bttn_stand.remove();
              stage.addActor(bttn_deal);
          }
        });

        bttn_deal.addListener(new ClickListener() {
          @Override
          public void clicked(InputEvent event, float x, float y) {
              stage.addActor(bttn_hit);
              stage.addActor(bttn_stand);
              bttn_deal.remove();


              if (P.getMoney() < 10) {
                  P.setMoney(150);
              } else {
                  P.setMoney(P.getMoney() - 10);
              }

              int n = (int) Math.floor(P.getMoney());
              player_money.setText(new String(Integer.toString(n)));

              START = true;
              blackjack = new Blackjack(10,P);

              blackjack.giveCard(DEALER);
              blackjack.giveCard(PLAYER);
              blackjack.giveCard(PLAYER);
          }
        });

        bttn_hit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                blackjack.giveCard(PLAYER);
                if (blackjack.getPlayers().get(PLAYER).getValuePlayer() > 21) {
                    bttn_hit.remove();
                    bttn_stand.remove();
                    stage.addActor(bttn_deal);

                    blackjack.win();

                    int n = (int) Math.floor(P.getMoney());
                    player_money.setText(new String(Integer.toString(n)));
                }
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
        Gdx.gl.glClearColor(0.086f, 0.22f, 0.157f, 1);

        stage.act();
        stage.draw();

        if (START) {
            drawCards(DEALER);
            drawCards(PLAYER);

            if (blackjack.LOSE)
                lose();
            else if (blackjack.WIN)
                win();
        }

        // WHEN BACK BUTTON IS PRESSED RETURN TO MENU
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            sm.remove();
        }
    }

    public void drawCards(int jogador) {

        Vector<Card> cartas = blackjack.getPlayers().get(jogador).getcartasJogada();
        int dx = 0;

        batch.begin();
        if (jogador == DEALER) {
            for (Card card : cartas) {
                int j = "PECO".indexOf(card.getNaipe());
                int i = card.getcarta();
                i--;

                Sprite sprite = new Sprite (new TextureRegion(texturaCartas, i * 43, j * 57 + 1, 40, 55));
                float scale = (WIDTH/4)/sprite.getWidth();
                sprite.setSize(WIDTH/4, scale*sprite.getHeight());
                sprite.setX(WIDTH/2-sprite.getWidth()/2 + dx - sprite.getWidth()/4*(cartas.size()-1));
                sprite.setY(HEIGHT*2/3);
                dealer_y = sprite.getY();

                dx += sprite.getWidth()/2;

                sprite.draw(batch);
            }
        }

        if (jogador == PLAYER) {
            for (Card card : cartas) {
                int j = "PECO".indexOf(card.getNaipe());
                int i = card.getcarta();
                i--;

                Sprite sprite = new Sprite(new TextureRegion(texturaCartas, i * 43, j * 57 + 1, 40, 55));
                float scale = (WIDTH/4)/sprite.getWidth();
                sprite.setSize(WIDTH/4, scale*sprite.getHeight());
                sprite.setX(WIDTH/2-sprite.getWidth()/2 + dx - sprite.getWidth()/4*(cartas.size()-1));
                sprite.setY(HEIGHT/3-sprite.getHeight()/2);
                player_y = sprite.getY();
                card_height = sprite.getHeight();

                dx += sprite.getWidth()/2;

                sprite.draw(batch);
            }
        }
        batch.end();
    }

    public void lose() {
        result = new Image (new Texture("blackjack/lost.png"));
        float scale = (WIDTH/4)/result.getWidth();
        result.setSize(WIDTH/4, scale*result.getHeight());
        result.setX(WIDTH/2-result.getWidth()/2);
        result.setY(dealer_y-(dealer_y-player_y-card_height)/2-result.getHeight()/2);

        Stage stage2 = new Stage();
        stage2.addActor(result);
        stage2.act();
        stage2.draw();

        stage2.dispose();
    }

    public void win() {
        result = new Image (new Texture("blackjack/win.png"));
        float scale = (WIDTH/4)/result.getWidth();
        result.setSize(WIDTH/4, scale*result.getHeight());
        result.setX(WIDTH/2-result.getWidth()/2);
        result.setY(dealer_y-(dealer_y-player_y-card_height)/2-result.getHeight()/2);

        Stage stage2 = new Stage();
        stage2.addActor(result);
        stage2.act();
        stage2.draw();

        stage2.dispose();
    }

    @Override
    public void dispose() {
        stage.dispose();
        texturaCartas.dispose();
        batch.dispose();
    }
}