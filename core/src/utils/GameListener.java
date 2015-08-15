package utils;

import com.badlogic.gdx.InputAdapter;
import screens.GameScreen;

/**
 * Created by Cornel on 16.07.2015.
 */
public class GameListener extends InputAdapter {

    private GameScreen gS;

    public GameListener(GameScreen gS)
    {
        this.gS = gS;
    }

    @Override
    public boolean keyDown(int keycode) {
        touchDown(0, 0, 0, 0);
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        gS.pro.changeDirection();
        for (int i = gS.obstacole.size() - 1; i >=0 ; i--) {
            gS.obstacole.get(i).tick();
        }
        return false;
    }

}
