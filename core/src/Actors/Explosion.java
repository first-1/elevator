package Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import utils.Animation;
import utils.Assets;

/**
 * Created by Cornel on 14.08.2015.
 */
public class Explosion extends Actor {
    private Animation animation;
    private float posX, posY, width, height;

    public Explosion(float x, float y, float width, float height){
        posX = x;
        posY = y;
        this.width = width;
        this.height = height;

        TextureRegion[] tex = Assets.obstacleExplosion.split(Assets.obstacleExplosion.getRegionWidth() / 7, Assets.obstacleExplosion.getRegionHeight())[0];
        animation = new Animation(this);
        animation.setFrames(tex, 1/10f, 0, 1);

    }

    @Override
    public void act(float delta)
    {
        animation.update(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

      //  batch.begin();
        batch.draw(animation.getFrame(), posX, posY, width, height);
       // batch.end();

    }

}
