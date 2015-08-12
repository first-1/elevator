package Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import utils.Const;

/**
 * Created by Cornel on 16.07.2015.
 */
public abstract class DynamicActor  extends Actor {

    private Vector2 velocity = new Vector2(0, 0);
    private TextureRegion texture;

    public DynamicActor(float x, float y, float width, float height) {
        setBounds(x, y, width, height);
    }



    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(float x, float y)
    {
        this.velocity.set(x, y);
    }

    public TextureRegion getTexture()
    {
        return texture;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public void update()
    {
        setPosition(getX()+ velocity.x + Const.GAME_SPEED.x, getY() + velocity.y + Const.GAME_SPEED.y);
    }

    public void drawMe(Batch batch)
    {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
