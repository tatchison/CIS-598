package cis598;
import robocode.*;
import robocode.AdvancedRobot;
import robocode.RobocodeFileOutputStream;
import java.io.*;
import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * CIS598GPRobot - a robot by Travis Atchison
 * A robot created using genetic programming with ECJ as the simulator
 */
public class CIS598GPRobot extends AdvancedRobot
{
	int direction = 1;
	ScannedRobotEvent enemy = null;
	
	public void run() {
		setColors(Color.red,Color.blue,Color.green); // body,gun,radar
		// Robot main loop
		while(true) {
			setAdjustRadarForRobotTurn(true);
			setAdjustGunForRobotTurn(true);
			turnRadarRightRadians(Double.POSITIVE_INFINITY);
			moveTree();
			fireTree();
		}
	}
	
	public ScannedRobotEvent forward(ScannedRobotEvent e){
		setAhead(140*direction);
		return enemy;
	}
	
	public ScannedRobotEvent reverse(ScannedRobotEvent e){
		direction = -direction;
		return enemy;
	}
	
	public ScannedRobotEvent turnLeft(ScannedRobotEvent e){
		setTurnLeft(45);
		return enemy;
	}
	
	public ScannedRobotEvent turnRight(ScannedRobotEvent e){
		setTurnRight(45);
		return enemy;
	}
	
	public ScannedRobotEvent speedUp(ScannedRobotEvent e){
		setMaxVelocity(2*getVelocity());
		return enemy;
	}
	
	public ScannedRobotEvent slowDown(ScannedRobotEvent e){
		setMaxVelocity(getVelocity()/2);
		return enemy;
	}

	public ScannedRobotEvent fire(ScannedRobotEvent e){
		double bearing = e.getBearing();
		double latVel = e.getVelocity();
		double distance = e.getDistance();
		double turnAmt;
		if(distance > 150){
			turnAmt = robocode.util.Utils.normalRelativeAngle(bearing- getGunHeadingRadians()+latVel/22);
			setTurnGunRightRadians(turnAmt);
		}
		else {
			turnAmt = robocode.util.Utils.normalRelativeAngle(bearing- getGunHeadingRadians()+latVel/15);
			setTurnGunRightRadians(turnAmt);
		}
		setFire(3);
		return enemy;
	}
	
	public void moveTree(){
		if(enemy != null){
			reverse(enemy);
		}
	}
	
	public void fireTree(){
		if(enemy != null){
			slowDown(enemy);
			if(turnLeft(enemy).getDistance() < 100){
				if(speedUp(enemy).getDistance() < 50){
					forward(enemy);
				}
				else{
					turnRight(enemy);
				}
			}
			else{
				if(fire(enemy).getDistance() < 200){
					reverse(enemy);
				}
			}
		}
	}
	
	
	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		enemy = e;
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		reverse(enemy);
	}	
	
	public void onBattleEnded(BattleEndedEvent b){
		BattleResults results = b.getResults();
		int eval = results.getFirsts();
		if(results.getBulletDamage() >= 100) eval += (results.getBulletDamage()/100);
		if(eval < 0) eval = 0;
		PrintStream print = null;
		try{
			print =  new PrintStream(new RobocodeFileOutputStream(getDataFile("results.dat")));
			print.println(eval);
			print.close();
		}catch(IOException e){
			out.println("File IO Exception");
		}
	}
}

