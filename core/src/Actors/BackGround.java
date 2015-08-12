package Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import utils.Assets;
import utils.Const;

/**
 * Created by Cornel on 19.07.2015.
 */
public class BackGround extends DynamicActor {
    public BackGround(float x, float y, float width, float height) {
        super(x, y, width, height);
        setTexture(Assets.background);
        setVelocity(0, -1.28f);
    }

    public void moveBack(){
        setBounds(0, Const.zero, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT * 2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        super.update();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        super.drawMe(batch);
    }
}
