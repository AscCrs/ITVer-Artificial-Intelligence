/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multirobots;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import robocode.HitByBulletEvent;
import robocode.ScannedRobotEvent;
import robocode.TeamRobot;

/**
 *
 * @author crist
 */
public class ISCLider extends TeamRobot {
    public void run() {
        setColors(Color.GREEN, Color.BLACK, Color.GREEN);
        
        while (true) {
            this.setAhead(100);
            this.setBack(100);
            this.turnRadarLeft(360);
            this.execute();
        }
    }
    
    @Override
    public void onHitByBullet(HitByBulletEvent e) {
        this.turnLeft(90 - e.getBearing());
        if (this.getEnergy() < 50) {
            try {
                this.broadcastMessage("Sin lider");
            } catch (IOException ex) {
                System.out.println("Error de comunicación");
            }
        }
        
        this.ahead(30);
    }
    
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        if (!this.isTeammate(e.getName())) {
            double theta = this.getHeading() + e.getBearing();
            double rad = Math.toRadians(theta);
            double x = this.getX() + e.getDistance() * Math.sin(rad);
            double y = this.getY() + e.getDistance() * Math.cos(rad);
            Point p = new Point((int)x, (int)y);
            try {
                this.broadcastMessage(p);
            } catch(IOException ex) {
                System.out.println("Error al enviar el mensaje");
            }
        } 
    }
    
    
}
