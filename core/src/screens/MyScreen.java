package screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yahoo.cornelg7.elevator.Elevator;
import utils.Const;

/**
 * Created by Cornel on 16.07.2015.
 */
public class MyScreen implements Screen {

    private Elevator elevator;
    public OrthographicCamera camera;
    public SpriteBatch batch;

    public  MyScreen(Elevator elevator)
    {
        this.elevator = elevator;
        camera = new OrthographicCamera(Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
        camera.position.set(Const.SCREEN_WIDTH/2f, Const.SCREEN_HEIGHT/2f, 0);
        camera.update();
        batch = new SpriteBatch();
        batch.setProjectionMatrix(camera.combined);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
