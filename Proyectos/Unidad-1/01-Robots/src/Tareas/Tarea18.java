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

/**
 * ZigZag - Un robot que se mueve en zigzag y dispara al detectar enemigos.
 */
public class Tarea18 extends Robot {

    @Override
    public void run() {
        setColors(Color.BLUE, Color.BLACK, Color.CYAN); 

        while (true) {
            ahead(30);
            turnRight(45);
            ahead(30);
            turnLeft(45);
        }
    }

    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        fire(1);
    }

    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        back(10);
    }

    @Override
    public void onHitWall(HitWallEvent e) {
        back(20);
        turnRight(90);
    }
}
