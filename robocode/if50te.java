package ec.app.robocode;
import ec.*;
import ec.gp.*;
import ec.util.*;

public class if50te extends GPNode{
	public String toString(){return "if50te";}
	
	/*public String extraToString(){
		String r = "if(";
		r += children[0].extraToString();
		r += ") then(";
		r += children[1].extraToString();
		r += ") else(";
		r += children[2].extraToString();
		r += ")";
		return r;
	}*/
	
	public void checkConstraints(final EvolutionState state,
				 final int tree,
				 final GPIndividual typicalIndividual,
				 final Parameter individualBase)
	{
	super.checkConstraints(state,tree,typicalIndividual,individualBase);
	if (children.length!=3)
	    state.output.error("Incorrect number of children for node " + 
			       toStringForError() + " at " +
			       individualBase);
	}
	
	public void eval(final EvolutionState state,
        final int thread,
        final GPData input,
        final ADFStack stack,
        final GPIndividual individual,
        final Problem problem)
		{
			EnemyPos rd = (EnemyPos)input;
			Battle r = (Battle)problem;
			
			children[0].eval(state,thread,input,stack,individual,problem);
			
			if(rd.distance < 50){
				children[1].eval(state,thread,input,stack,individual,problem);
			}
			else{
				children[2].eval(state,thread,input,stack,individual,problem);
			}
			
			rd.bearing = r.currBearing;
			rd.distance = r.currDistance;
			rd.velocity = r.currVelocity;
		}
}