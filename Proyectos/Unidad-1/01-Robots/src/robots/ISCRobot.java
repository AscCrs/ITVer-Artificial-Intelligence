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

/**
 *
 * @author crist
 * Roboto basado en metas
 */
public class ISCRobot extends TeamRobot {
    
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
        if (this.getEnergy() > minimo) {
            this.ahead(100);
            this.turnGunLeft(180);
            this.turnGunRight(180);
        } else {
            this.moverseLejos();
        }
    }
    
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        if (this.getEnergy() > minimo || this.getOthers() == 1) {
            this.fire(1);
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
        this.back(50);
    }
}
