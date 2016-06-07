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
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.TimeUtils;
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
    private ImageButton add;
    private ImageButton sub;
    private int bet_amount;
    private Label amount;
    private boolean end_game;
    private long end_time;


    public ScreenBlackjack(ScreenManager sm, Player P) {
        super(sm, P);
        create();
    }


    @Override
    public void create() {
        end_game = false;
        texturaCartas = new Texture("blackjack/cartas.png");
        START = false;
        bet_amount = 0;

        batch = new SpriteBatch();
        stage = new Stage(new ScreenViewport());


        int n = (int) Math.floor(P.getMoney());
        player_money = new Label(new String(Integer.toString(n)), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        float scale = (WIDTH/16)/player_money.getHeight();
        player_money.setFontScale(scale);
        player_money.setX(WIDTH - WIDTH/6);
        player_money.setY(HEIGHT - 2*scale*player_money.getHeight());


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

        //================================================================================================================================================================
        // BET AMOUNT
        s = new Sprite(new Texture("roulette/bets/chip_d.png"));
        scale = (WIDTH/6)/s.getWidth();
        s.setSize(WIDTH/6, scale*s.getHeight());

        add = new ImageButton(new SpriteDrawable(s));
        add.setX(WIDTH/2-add.getWidth()*2);
        add.setY(bttn_deal.getY()+bttn_deal.getHeight()*2);

        s = new Sprite(new Texture("roulette/bets/chip.png"));
        scale = (WIDTH/6)/s.getWidth();
        s.setSize(WIDTH/6, scale*s.getHeight());

        sub = new ImageButton(new SpriteDrawable(s));
        sub.setX(WIDTH/2+sub.getWidth());
        sub.setY(bttn_deal.getY()+bttn_deal.getHeight()*2);

        //================================================================================================================================================================
        // AMOUNT

        int a = (int) Math.floor(bet_amount);
        amount = new Label(new String(Integer.toString(a)), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scale = (WIDTH/16)/amount.getHeight();
        amount.setFontScale(scale);
        amount.setWidth(WIDTH);
        amount.setAlignment(Align.center);
        amount.setX(WIDTH/2 - amount.getWidth()/2);
        amount.setY(sub.getY() + sub.getHeight()/2);




        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        stage.addActor(bttn_deal);
        stage.addActor(player_money);
        stage.addActor(amount);
        stage.addActor(sub);
        stage.addActor(add);


        bttn_stand.addListener(new ClickListener() {
          @Override
          public void clicked(InputEvent event, float x, float y) {

              blackjack.stand();
              blackjack.win();

              int n = (int) Math.floor(P.getMoney());
              player_money.setText(new String(Integer.toString(n)));

              bttn_hit.remove();
              bttn_stand.remove();
          }
        });

        bttn_deal.addListener(new ClickListener() {
          @Override
          public void clicked(InputEvent event, float x, float y) {
              if (bet_amount == 0 || bet_amount > P.getMoney()) {
                  return;
              }

              stage.addActor(bttn_hit);
              stage.addActor(bttn_stand);
              bttn_deal.remove();
              add.remove();
              sub.remove();
              amount.remove();


              if (P.getMoney() == 0) {
                  P.setMoney(150);
              } else {
                  P.setMoney(P.getMoney() - bet_amount);
                  P.addEarningsBlackjack(-bet_amount);
              }

              int n = (int) Math.floor(P.getMoney());
              player_money.setText(new String(Integer.toString(n)));

              START = true;
              blackjack = new Blackjack(bet_amount,P);

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

                    blackjack.win();

                    int n = (int) Math.floor(P.getMoney());
                    player_money.setText(new String(Integer.toString(n)));
                }
            }
        });

        add.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (bet_amount < 10 && bet_amount+1 <= P.getMoney()) {
                    bet_amount++;
                }
                else if (bet_amount < 100 && bet_amount+10 <= P.getMoney()) {
                    bet_amount += 10;
                }
                else if (bet_amount+100 <= P.getMoney()){
                    bet_amount += 100;
                }

                int n = (int) Math.floor(bet_amount);
                amount.setText(new String(Integer.toString(n)));
            }
        });

        sub.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (bet_amount == 0) {
                    return;
                }

                if (bet_amount <= 10) {
                    bet_amount--;
                }
                else if (bet_amount <= 100) {
                    bet_amount -= 10;
                }
                else {
                    bet_amount -= 100;
                }



                int n = (int) Math.floor(bet_amount);
                amount.setText(new String(Integer.toString(n)));
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

            if (blackjack.LOSE) {
                lose();
                if (!end_game)
                    end_time = TimeUtils.millis();

                end_game = true;
            }
            else if (blackjack.WIN) {
                win();
                if (!end_game) {
                    end_time = TimeUtils.millis();
                    Gdx.input.vibrate(new long[] { 0, 300, 100, 300}, -1);
                }

                end_game = true;

            }
            else if (blackjack.DRAW) {
                tie();
                if (!end_game)
                    end_time = TimeUtils.millis();

                end_game = true;
            }
        }

        if (end_game && TimeUtils.millis() - end_time > 1500) {
            START = false;
            stage.addActor(bttn_deal);
            stage.addActor(player_money);
            stage.addActor(amount);
            stage.addActor(sub);
            stage.addActor(add);

            end_game = false;
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

                Sprite sprite = new Sprite(new TextureRegion(texturaCartas, i * 81, j * 117 + 1, 81, 117));
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

                Sprite sprite = new Sprite(new TextureRegion(texturaCartas, i * 81, j * 117 + 1, 81, 117));
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

    public void tie() {
        result = new Image (new Texture("blackjack/tie.png"));
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