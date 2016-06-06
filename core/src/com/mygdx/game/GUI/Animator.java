package com.mygdx.game.GUI;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class Animator extends Widget {
    private Image[] images;
    private int current_pos;
    private boolean changing;
    private boolean spinning;
    private float angle;
    private boolean translation;
    private float rot_speed;

    public Animator(Image[] i) {
        current_pos = 0;
        angle = 0;
        rot_speed = 3;
        changing = false;
        spinning = false;
        translation = false;
        images = i;
        setHeight(i[0].getHeight());
        setWidth(i[0].getWidth());
    }

    public float getAngle() { return angle; }

    public void setAngle(float a) { angle = a; }

    public void setTranslation(boolean b) { translation = b; }

    public void setChanging(boolean b) {
        changing = b;
    }

    public boolean getChanging() {
        return changing;
    }

    public void setSpinning(boolean b) {
        spinning = b;
    }

    public boolean getSpinning() {
        return spinning;
    }

    public float getRotSpeed() { return rot_speed; }

    public void rotSpeedDec() { if (rot_speed > 0) { rot_speed -= 0.005; } }

    public void resetRotSpeed() { rot_speed = 3; }

    public void angleInc(int direction) {
        int i = direction/Math.abs(direction);
        angle = angle + i*rot_speed;
    }

    public int getCurrentPos() {
        return current_pos;
    }

    public void setCurrentPos (int i) {
        current_pos = i;
    }

    public void currentPosInc() {
        current_pos = (current_pos + 1) % images.length;
    }

    public void setOrigin (float originX, float originY) {
        super.setOrigin(originX, originY);
        translation = true;
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        if (translation) {
            images[current_pos].setOrigin(getOriginX(),getOriginY());
        }
        else {
            images[current_pos].setOrigin(images[current_pos].getWidth()/2,images[current_pos].getHeight()/2);
        }
        images[current_pos].setRotation(angle);
        images[current_pos].setSize(getWidth(), getHeight());
        images[current_pos].setX(getX());
        images[current_pos].setY(getY());
        images[current_pos].draw(batch, parentAlpha);
    }
}
