/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multirobots;

import java.awt.Color;
import java.awt.Point;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.MessageEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;
import robots.ISCRobotUtilidad;


/**
 *
 * @author crist
 */
public class ISCOtro extends ISCRobotUtilidad {
    
    private boolean statusLider;
    
    @Override
    public void run() {
        this.statusLider = true;
        this.setColors(Color.GREEN, Color.RED, Color.YELLOW);
        while(true) {
            if (this.statusLider) {
                ahead(1);
            } else {
                sensarAmbiente();
            }
        }
    }
    
    @Override
    public void onScannedRobot(ScannedRobotEvent e) {
        if (!statusLider && !this.isTeammate(e.getName())) {
            super.onScannedRobot(e);
        } 
    }
    
    @Override
    public void onHitRobot(HitRobotEvent e) {
        if (!statusLider && !this.isTeammate(e.getName())) {
            super.onHitRobot(e);
        } else this.back(10);
    }
    
    @Override
    public void onHitWall(HitWallEvent e) {
        if(!statusLider) {
            super.onHitWall(e);
        }
    }
    
    @Override
    public void onMessageReceived(MessageEvent e) {
        if (e.getMessage() instanceof Point) {
            Point punto = (Point) e.getMessage();
            double dX = punto.x - this.getX();
            double dY = punto.y - this.getY();
            double theta = Math.toDegrees(Math.atan2(dX, dY));
            double beta = theta - this.getGunHeading();
            beta = Utils.normalRelativeAngleDegrees(beta);
            this.turnGunRight(beta);
            this.fire(3); 
        } else {
            String mensaje = (String) e.getMessage();
            statusLider = !mensaje.equals("Sin lider");
            if (!statusLider) {
                moverseLejos();
            }
        }
    }
}
