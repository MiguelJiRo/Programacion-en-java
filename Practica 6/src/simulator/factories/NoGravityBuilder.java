package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NoGravity;

public class NoGravityBuilder extends Builder<GravityLaws>{

	public static final String desc = "Sin gravedad";
	public static final String tag = "ng";
	
	public NoGravityBuilder() {
		super.desc = desc;
		super.typeTag = tag;
	}
	
	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) {
		
		try 
		{
			try 
			{
				GravityLaws nogravity = (GravityLaws) new NoGravity();
				return nogravity;
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
