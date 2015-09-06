package actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.awt.*;

/**
 * Created by timi on 26.08.2015.
 */
public class MenuButton extends Actor{

    TextureRegion region;
    final Runnable toDo;

    public MenuButton(TextureRegion region, float x, float y, float width, float height, Runnable toDo)
    {
        super();
        this.region = region;
        this.setBounds(x, y, width, height);
        this.toDo = toDo;
        this.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MenuButton.this.toDo.run();
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region, getX(), getY(), getWidth(), getHeight());
    }
}
