package Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import screens.GameScreen;
import utils.Animation;
import utils.Assets;

/**
 * Created by Cornel on 14.08.2015.
 */
public class Obstacol extends Actor {

    private Animation animation;
    private float posX, posY, width, height;
    private Explosion explosion;
    private int totalTicks;
    private Group group;
    private GameScreen gs;
    private TextureRegion[] tex;

    public Obstacol(float x, float y, float width, float height, int totalTicks, GameScreen gs){
        posX = x;
        posY = y;
        this.width = width;
        this.height = height;
        this.totalTicks = totalTicks;
        this.gs = gs;
        this.group = group;

        tex = Assets.obstacle.split(Assets.obstacle.getRegionWidth() / 6, Assets.obstacle.getRegionHeight())[0];
        animation = new Animation(this);
        animation.setFrames(tex, 0, 0, 1);

    }

    public void explode(){
        explosion = new Explosion(posX, posY - height * 6, width, height * 6);
        gs.back.addActor(explosion);
        gs.obstacole.remove(this);
        this.remove();
    }

    public void tick(){
        animation.setFrames(tex, 0, animation.getCurrentFrame() + 1, 0);
        if (animation.getCurrentFrame() >= totalTicks || animation.getCurrentFrame() >= 6){
            explode();
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(posX, posY, width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

     //   batch.begin();
        batch.draw(animation.getFrame(), posX, posY, width, height);
    //    batch.end();

    }

}
