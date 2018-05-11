package ec.app.robocode;
import ec.util.*;
import ec.*;
import ec.gp.*;

/**
*	Travis Atchison
*	CIS 598 Senior Project
*/

public class EnemyPos extends GPData{
	public double bearing;
	public double distance;
	public double velocity;
	
	//changed return value to void because of issue
	//said that copyTo here couldn't override copyTo in GPData
	public void copyTo(final GPData gpd){
		EnemyPos rd = (EnemyPos)gpd;
		rd.bearing = bearing;
		rd.distance = distance;
		rd.velocity = velocity;
	}
}