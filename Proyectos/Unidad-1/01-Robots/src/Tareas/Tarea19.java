/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tareas;

/**
 *
 * @author crist
 */

import robocode.*;
import java.awt.Color;

public class Tarea19 extends AdvancedRobot {

    private boolean movingForward = true;

    public void run() {
        setColors(Color.RED, Color.BLACK, Color.WHITE); 

        while (true) {
            moveInFigureEight();
            scan(); 
        }
    }

    private void moveInFigureEight() {
        setTurnRight(45);
        setAhead(100);
        execute();

        setTurnLeft(90);
        setAhead(100);
        execute();

        setTurnRight(45); 
        setAhead(100);
        execute();
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent event) {
        fire(1);
    }

    @Override
    public void onHitWall(HitWallEvent event) {
        double arenaWidth = getBattleFieldWidth();
        double arenaHeight = getBattleFieldHeight();
        double centerX = arenaWidth / 2;
        double centerY = arenaHeight / 2;

        goTo(centerX, centerY);
    }

    @Override
    public void onHitRobot(HitRobotEvent event) {
        fire(2);
        setTurnGunRight(90);
        setBack(100);
        execute();
    }

    private void goTo(double x, double y) {
        double dx = x - getX();
        double dy = y - getY();
        double angleToTarget = Math.toDegrees(Math.atan2(dx, dy));

        double angle = normalizeBearing(angleToTarget - getHeading());

        setTurnRight(angle);
        setAhead(Math.hypot(dx, dy));
        execute();
    }

    private double normalizeBearing(double angle) {
        while (angle > 180) angle -= 360;
        while (angle < -180) angle += 360;
        return angle;
    }
}
