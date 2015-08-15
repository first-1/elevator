package utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.badlogic.gdx.graphics.Texture.TextureWrap.Repeat;

/**
 * Created by Cornel on 16.07.2015.
 */
public abstract class Assets {

    public static TextureRegion background;
    public static TextureRegion pro;
    public static TextureRegion brick;
    public static TextureRegion obstacleExplosion;
    public static TextureRegion obstacle;

    public static void load()
    {
        background = new TextureRegion(new Texture(Gdx.files.internal("images/background5.png")));
        pro =  new TextureRegion(new Texture(Gdx.files.internal("images/protagonist5.png")));
        Texture aux = new Texture(Gdx.files.internal("images/brick4.png"));
        aux.setWrap(Repeat, Repeat);
        brick =  new TextureRegion(aux);
        obstacleExplosion = new TextureRegion(new Texture(Gdx.files.internal("images/obstacleExplosion2.png")));
        obstacle = new TextureRegion(new Texture(Gdx.files.internal("images/obstacle.png")));

    }

}
