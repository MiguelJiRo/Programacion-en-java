package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.model.GravityLaws;
import simulator.model.NewtonUniversalGravitation;

public class NewtonUniversalGravitationBuilder extends Builder<GravityLaws>{

	public static final String desc = "Ley de Newton de la gravitacion universal";
	public static final String tag = "nlug";
	
	public NewtonUniversalGravitationBuilder() {
		super.desc = desc;
		super.typeTag = tag;
	}
	
	@Override
	protected GravityLaws createTheInstance(JSONObject jsonObject) {
		
		try 
		{
			try 
			{
				GravityLaws newton = (GravityLaws) new NewtonUniversalGravitation();
				return newton;
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
