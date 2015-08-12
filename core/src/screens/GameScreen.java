package screens;

import Actors.BackGround;
import Actors.Margine;
import Actors.Protagonist;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.yahoo.cornelg7.elevator.Elevator;
import utils.Const;
import utils.GameListener;

/**
 * Created by Cornel on 16.07.2015.
 */
public class GameScreen extends MyScreen {

    Stage stage;
    public Protagonist pro;
    public Margine leftLeft, leftMid, rightMid, rightRight;
    public BackGround bg;
    private int collidedMargin;

    public  GameScreen(Elevator elevator)
    {
        super(elevator);
        stage = new Stage();
        pro = new Protagonist(Const.SCREEN_WIDTH/2-Const.PRO_WIDTH/2, Const.SCREEN_HEIGHT/2 - Const.PRO_HEIGHT/2, Const.PRO_WIDTH, Const.PRO_HEIGHT);
        leftLeft = new Margine(1, 0, 0, this);
        leftMid = new Margine(-1, Const.SCREEN_WIDTH/2, 1, this);
        rightMid = new Margine(1, Const.SCREEN_WIDTH/2, 2, this);
        rightRight =  new Margine(-1, Const.SCREEN_WIDTH, 3, this);
        bg = new BackGround(0, 0, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT * 2);

        stage.getCamera().position.set(Const.SCREEN_WIDTH/2, Const.SCREEN_HEIGHT/2, 0);
        stage.addActor(bg);
        stage.addActor(pro);
        stage.addActor(leftLeft);
        stage.addActor(leftMid);
        stage.addActor(rightMid);
        stage.addActor(rightRight);
    }

    @Override
    public void show()
    {
        Gdx.input.setInputProcessor(new InputMultiplexer(new GameListener(this), stage));
    }

    public boolean proCollides(){

        // check collisions
        // pro sides:   0 1
        //
        // pro corners: 1 2
        //              0 3
        if (leftLeft.collides(pro.getCrystalCorner(0, 0), pro.getCrystalCorner(0, 1))) {
            collidedMargin = 0;
            return true;
        }
        if (leftMid.collides(pro.getCrystalCorner(0, 3), pro.getCrystalCorner(0, 2))) {
            collidedMargin = 1;
            return true;
        }
        if (rightMid.collides(pro.getCrystalCorner(1, 0), pro.getCrystalCorner(1, 1))) {
            collidedMargin = 2;
            return true;
        }
        if (rightRight.collides(pro.getCrystalCorner(1, 3), pro.getCrystalCorner(1, 2))) {
            collidedMargin = 3;
            return true;
        }

        return false;
    }

    public void gameOver(){
        System.out.println("YOU DIED !! " + collidedMargin);
    }

    public void updateGame(){
        // move screen + margins
        Const.zero += Const.GAME_SPEED.y;

        // move bg
        if (bg.getBounds().getY() < Const.zero - Const.SCREEN_HEIGHT + Const.GAME_SPEED.y){
            bg.moveBack();
        }

        // check collisions
        if (proCollides()){
            gameOver();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updateGame();

        stage.act();
        stage.draw();

        stage.getCamera().position.add(Const.GAME_SPEED.x, Const.GAME_SPEED.y, 0);
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
