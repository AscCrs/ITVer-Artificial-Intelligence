/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robots;

import java.awt.Color;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;
import robocode.util.Utils;

/**
 *
 * @author crist
 * Robot basado en utilidad
 */
public class ISCRobotUtilidad extends TeamRobot {
    
    private final int minimo = 70;
    
    @Override
    public void run() {
        this.setColors(Color.blue, Color.black, Color.cyan);
        irAPared();
        while(true) {
            sensarAmbiente();
        }
    }
    
    public void irAPared() {
        turnLeft(getHeading() % 90);
        moverseLejos();
    }
    
    public void sensarAmbiente() {
        if (this.getGunHeading() != this.getHeading()) {
            this.turnGunRight(
                    Utils.normalRelativeAngle(
                            this.getHeading() - this.getGunHeading()
                    )
            );
        }
        if (!(this.getEnergy() > minimo)) {
            this.moverseLejos();            
        } else {
            this.ahead(100);
            this.turnGunLeft(180);
            this.turnGunRight(180);
        }
    }
    
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        if (this.getEnergy() > minimo || this.getOthers() == 1) {
            combatir(e.getDistance(), e.getVelocity());
        }
    }
    
    public void combatir(double distancia, double velocidad) {
        if (velocidad == 0) {
            fire(3);
        } else if (distancia > 200) {
            fire(1);
        } else if (distancia > 50) {
            fire(2);
        } else {
            fire(3);
        }
    }
    
    public void moverseLejos() {
        double ancho = this.getBattleFieldWidth();
        double alto = this.getBattleFieldHeight();
        double maximo = Math.max(ancho, alto);
        System.out.println(maximo);
        ahead(maximo);
    }

    @Override
    public void onHitWall(HitWallEvent e) {
        this.turnLeft(90);
    }
   
    @Override
    public void onHitRobot(HitRobotEvent e) {
        double theta = this.getHeading() + e.getBearing() - this.getGunHeading();
        theta = Utils.normalRelativeAngleDegrees(theta);
        this.turnGunRight(theta);
        this.fire(3);
        this.turnGunLeft(theta);
//        this.back(50);
    }
}
