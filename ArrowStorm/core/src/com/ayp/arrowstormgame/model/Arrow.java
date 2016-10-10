package com.ayp.arrowstormgame.model;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Theerawuth on 9/23/2016.
 */
public class Arrow {
    private static float accuracy;
    private static float hit;
    private static float miss;
    private Vector2 arrowPosition;
    private float width = 32;
    private float height = 64;
    private float arrowSpriteAngle;
    private Vector2 velocity;
    private Circle arrowBound;
    private static float CIRCLE_RADIUS_BOUND = 16;
    private static float BOUND_POINT_FROM_CENTER_TO_HEAD = 16;
    private static float ARROW_VELOCITY = 400;
    private float velocityX;
    private float velocityY;
    private float arrowBoundDeltaX;
    private float arrowBoundDeltaY;
    private Vector2 arrowBoundPosition;

    public Arrow(float x, float y, float arrowDirectionDegree, float arrowSpriteAngle) {
        arrowPosition = new Vector2(x, y);
        velocityX =
                (float) (ARROW_VELOCITY * Math.cos(Math.toRadians(arrowDirectionDegree)));
        velocityY =
                (float) (ARROW_VELOCITY * Math.sin(Math.toRadians(arrowDirectionDegree)));
        arrowBoundDeltaX =
                (float) (BOUND_POINT_FROM_CENTER_TO_HEAD * Math.cos(Math.toRadians(arrowDirectionDegree)));
        arrowBoundDeltaY =
                (float) (BOUND_POINT_FROM_CENTER_TO_HEAD * Math.sin(Math.toRadians(arrowDirectionDegree)));

        arrowBoundPosition = new Vector2(arrowPosition.x, arrowPosition.y + getHeight() / 2);
        arrowBoundPosition.sub(arrowBoundDeltaX, arrowBoundDeltaY);
        arrowBound = new Circle(arrowBoundPosition, CIRCLE_RADIUS_BOUND);
        this.arrowSpriteAngle = arrowSpriteAngle;
    }

    public void move(float delta) {
        velocity = new Vector2(velocityX * delta, velocityY * delta);
        setArrowPosition(arrowPosition.sub(velocity));
        arrowBound.setPosition(arrowPosition);
        setArrowBoundPosition(arrowBoundPosition.sub(velocity));
        arrowBound.set(arrowBoundPosition, CIRCLE_RADIUS_BOUND);
    }

    public Vector2 getArrowBoundPosition() {
        return arrowBoundPosition;
    }

    public void setArrowBoundPosition(Vector2 arrowBoundPosition) {
        this.arrowBoundPosition = arrowBoundPosition;
    }

    public Vector2 getArrowPosition() {
        return arrowPosition;
    }

    public void setArrowPosition(Vector2 arrowPosition) {
        this.arrowPosition = arrowPosition;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getArrowSpriteAngle() {
        return arrowSpriteAngle;
    }

    public void setArrowSpriteAngle(float arrowSpriteAngle) {
        this.arrowSpriteAngle = arrowSpriteAngle;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setArrowBound(Circle arrowBound) {
        this.arrowBound = arrowBound;
    }

    public Circle getArrowBound() {
        return arrowBound;
    }

    public boolean isHit(Enemy enemy) {
        return Intersector.overlaps(enemy.getEnemyBound(), this.getArrowBound());
    }

    public static float getAccuracy() {
        return accuracy;
    }

    public static void setAccuracy(float accuracy) {
        Arrow.accuracy = accuracy;
    }

    public static float getHit() {
        return hit;
    }

    public static void setHit(float hit) {
        Arrow.hit = hit;
    }

    public static float getMiss() {
        return miss;
    }

    public static void setMiss(float miss) {
        Arrow.miss = miss;
    }
}