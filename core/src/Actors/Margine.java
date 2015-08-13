package Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.math.EarClippingTriangulator;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.TimeUtils;
import screens.GameScreen;
import utils.Assets;
import utils.Const;

import java.util.ArrayList;

/**
 * Created by Cornel on 16.07.2015.
 */
public class Margine extends Actor {

    Pool<Vector2> pool = new Pool<Vector2>() {
        @Override
        protected Vector2 newObject() {
            return new Vector2();
        }
    };
    ArrayList<Vector2> points;
    PolygonSpriteBatch pSB = new PolygonSpriteBatch();
    GameScreen gs;
    private int face;
    private float reference;
    private float standardY;
    private float rule;
    private PolygonRegion toDrawPolygon;
    private EarClippingTriangulator triangulator = new EarClippingTriangulator();

    public Margine(int face, float reference, int rule, GameScreen gs) // face right->1 left->-1, reference its the X axis reference
    {
        this.face = face;
        this.reference = reference;
        this.rule = rule;
        this.gs = gs;
        points = new ArrayList<Vector2>();

        while (standardY < Const.zero + Const.SCREEN_HEIGHT + 2 * Const.STANDARD_MARGIN.y) {
            createNewPoint(reference + face * Const.STANDARD_MARGIN.x, standardY);
            standardY = standardY + Const.STANDARD_MARGIN.y/2;
            createNewPoint(reference + face * Const.STANDARD_MARGIN.x, standardY);
            standardY = standardY + Const.STANDARD_MARGIN.y/2;
        }
        updateVertexArray();
    }

    public void createNewPoint(float x, float y)
    {
        Vector2 pct = pool.obtain();
        pct.set(x, y);
        points.add(pct);
    }

    public void deletePoint(int index)
    {
        pool.free(points.get(index));
        points.remove(index);
    }

    public void createStandardPoint()
    {
        float x = reference + face * Const.STANDARD_MARGIN.x;
        createNewPoint(x, standardY);
        standardY += Const.STANDARD_MARGIN.y;
    }

    public Vector2 getLastPoint(){
        if (face * points.get(points.size()-2).x < face * points.get(points.size()-1).x)
            return points.get(points.size()-1);
        return points.get(points.size() - 2);
    }

    public float getXwithRuleForRandomPoint(){
        if (rule == 1) {        /// leftMid 2 crystal size dif max
            return MathUtils.random(gs.leftLeft.getLastPoint().x + 2.1f * Const.crystalSize, reference);
        }
        if (rule == 2) {        /// rightMid 2 crystal size
            return MathUtils.random(reference, Const.PRO_WIDTH - 3.9f * Const.crystalSize + gs.leftMid.getLastPoint().x);
        }
        if (rule == 3) {        /// rightRight
            return MathUtils.random(Math.max(gs.rightMid.getLastPoint().x + 2.1f * Const.crystalSize, gs.leftLeft.getLastPoint().x + Const.PRO_WIDTH + 0.9f * Const.crystalSize), reference);
        }
        /// leftLeft
        return MathUtils.random(reference, reference + Const.SCREEN_WIDTH / 2 - 3.9f * Const.crystalSize);
    }

    public float getYwithRuleForRandomPoint(){
        return MathUtils.random(points.get(points.size() - 1).y + 2 * Const.crystalSize, standardY);
    }

    public void createRandomPoint()
    {
        float x = getXwithRuleForRandomPoint();
        float y = getYwithRuleForRandomPoint();
        createNewPoint(x, y);
    }

    private boolean intersects(Vector2 p1, Vector2 p2, Vector2 p3, Vector2 p4){
        return com.badlogic.gdx.math.Intersector.intersectSegments(p1, p2, p3, p4, new Vector2());
    }

    public boolean intersects(Vector2 pointDown, Vector2 pointUp){
        int indexL = findLower(pointDown);
        int indexU = findUpper(pointUp);

        for (int i = indexL; i < indexU; ++i){
            if (intersects(points.get(i), points.get(i+1), pointDown, pointUp))
                return true;
        }
        return false;
    }

    private float determinant(Vector2 p1, Vector2 p2, Vector2 p3){
        return p1.x * p2.y + p2.x * p3.y + p1.y * p3.x - p2.y * p3.x - p1.x * p3.y - p1.y * p2.x;
    }

    public boolean contains(Vector2 point){
        int indexL = findLower(point);
        int indexU = findUpper(point);

        float part = points.get(0).x > reference ? 1 : -1;
        float d = determinant(points.get(indexL), point, points.get(indexU));

        return (d / part) < 0;
    }

    public boolean collides(Vector2 pointDown, Vector2 pointUp){
        if (intersects(pointDown, pointUp)){
            return true;
        }
        if (contains(pointDown)){
            return true;
        }
        if (contains(pointUp)){
            return true;
        }
        return false;
    }

    private int findLower(Vector2 point){
        for (int i = 0; i < points.size(); ++i){
            if (points.get(i).y > point.y)
                return i-1;
        }
        return -1;
    }

    private int findUpper(Vector2 point){
        for (int i = points.size() - 1; i >= 0; --i){
            if (points.get(i).y < point.y)
                return i+1;
        }
        return -1;
    }

    private void updateVertexArray()
    {
        float[] vertices;

        vertices = new float[points.size()*2+4];
        vertices[0] = reference - face * 5;
        vertices[1] = Const.zero - Const.SCREEN_HEIGHT/2;
        for (int i = 0, pointsSize = points.size(); i < pointsSize; i++) {
            vertices[2*i+2] = points.get(i).x;
            vertices[2*i+1+2] = points.get(i).y;
        }
        vertices[2*points.size()+2] = reference;
        vertices[2*points.size()+3] = vertices[2*points.size()+1] + Const.zero;

        toDrawPolygon = new PolygonRegion(Assets.brick, vertices, triangulator.computeTriangles(vertices).toArray());
    }

    @Override
    public void act(float delta)
    {
        if (points.get(2).y < Const.zero) {
            deletePoint(0);
            deletePoint(0);
            createRandomPoint();
            createStandardPoint();
            updateVertexArray();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha)
    {
        long g = TimeUtils.millis();
        batch.end();
//        ShapeRenderer shape = new ShapeRenderer();
//        shape.setColor(com.badlogic.gdx.graphics.Color.MAGENTA);
//        shape.setProjectionMatrix(getStage().getCamera().combined);
//
//        /// draw points + lines
//        shape.begin(ShapeRenderer.ShapeType.Filled);
//
//        for (int i = 1, pointsSize = points.size(); i < pointsSize; i++) {
//          //  Vector2 v = points.get(i);
//           // shape.circle(v.x, v.y, 1);
//           // if (i != 0)
//                shape.line(points.get(i - 1), points.get(i));
//        }
//        shape.end();


//        / draw texture


        //pSB = new  PolygonSpriteBatch();
        pSB.setProjectionMatrix(getStage().getCamera().combined);
        pSB.begin();
        pSB.draw(toDrawPolygon, 0, 0);
        pSB.end();

        batch.begin();
        System.out.println("\t" + TimeUtils.timeSinceMillis(g) + " drawing Margine");
    }

}
