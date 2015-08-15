package utils;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Cornel on 14.08.2015.
 */

public class Animation {

    private TextureRegion[] frames;
    private float time;
    private float delay;
    private int currentFrame;
    private int timesPlayed;
    private int timesToPlay;
    private Actor actor;

    public Animation(Actor actor){
        this.actor = actor;
    }

    public Animation(TextureRegion[] frames){
        this(frames, 1 / 12f);
    }

    public Animation(TextureRegion[] frames, float delay){
        setFrames(frames, delay, 0, 0);
    }

    public void setFrames(TextureRegion[] frames, float delay, int startingFrame, int times){
        this.frames = frames;
        this.delay = delay;
        time = 0;
        currentFrame = startingFrame;
        timesPlayed = 0;
        timesToPlay = times;
    }

    public void update(float dt){
        if (delay <= 0) return;
        time += dt;
        while (time >= delay){
            step();
        }
    }

    public void step(){
        time -= delay;
        currentFrame++;
        if (currentFrame == frames.length){
            currentFrame = 0;
            timesPlayed++;
            if (timesPlayed >= timesToPlay){
                actor.remove();
            }
        }
    }

    public TextureRegion getFrame(){return frames[currentFrame];}
    public int getTimesPlayed(){return timesPlayed;}


    public int getCurrentFrame() {
        return currentFrame;
    }
    public int getTimesToPlay() {
        return timesToPlay;
    }
    public boolean isPlaying(TextureRegion[] tex){
        return tex == frames;
    }
}
