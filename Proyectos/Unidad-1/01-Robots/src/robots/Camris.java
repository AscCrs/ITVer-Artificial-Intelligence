package robots;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.Color;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

/**
 *
 * @author crist
 */
public class Camris extends TeamRobot {
    @Override
    public void run() {
        this.setColors(Color.red, Color.cyan, Color.black);
        this.turnGunLeft(90);
        while(true) {
            irAPared();
        }
    }
    
    public void irAPared() {
        turnLeft(getHeading() % 90);
        moverseLejos();
    }
    
    public void moverseLejos() {
        double ancho = this.getBattleFieldWidth();
        double alto = this.getBattleFieldHeight();
        double maximo = Math.max(ancho, alto);
        System.out.println(maximo);
        ahead(maximo);
    }
    
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        fire(3);
        fire(1);
        fire(1);
//        ahead(50);
    }
    
    @Override
    public void onHitWall(HitWallEvent e) {
        this.turnLeft(90);
        System.out.println(e.getBearing());
//        this.turnGunLeft(90);
    }
}
