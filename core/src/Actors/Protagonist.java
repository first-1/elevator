package Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import utils.Assets;
import utils.Const;

/**
 * Created by Cornel on 16.07.2015.
 */
public class Protagonist extends DynamicActor {

    private final int LEFT = 0;
    private final int RIGHT = 1;

    public Protagonist(float x, float y, float width, float height) {
        super(x, y, width, height);
        setTexture(Assets.pro);
        setVelocity(1.35f, 0);
    }
                                                            // 1 2
    public Vector2 getCrystalCorner(int side, int corner){  // 0 3
        float x = this.getBounds().getX();
        float y = this.getBounds().getY();
        // corners
        x = x + Const.crystalSize * (corner/2);
        if (corner == 1 || corner == 2)
            y = y + Const.crystalSize;

        // side
        x += side * (Const.PRO_WIDTH - Const.crystalSize);

        return new Vector2(x, y);
    }

    public void changeDirection()
    {
        setVelocity(getVelocity().scl(-1f));
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
