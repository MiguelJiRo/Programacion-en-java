package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.model.FallingToCenterGravity;
import simulator.model.GravityLaws;

public class FallingToCenterGravityBuilder extends Builder<GravityLaws>{

	public static final String desc = "Cayendo hacia el centro";
	public static final String tag = "ftcg";
	
	public FallingToCenterGravityBuilder() {
		super.desc = desc;
		super.typeTag = tag;
	}
	
	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) {
		
		try 
		{
			try 
			{
				GravityLaws falling = (GravityLaws) new FallingToCenterGravity();
				return falling;
			}
			catch(JSONException e) {
				throw new IllegalArgumentException();
			}
		}
		catch(IllegalArgumentException e) {
			System.out.println(e);
		}
		return null;
	}

}
