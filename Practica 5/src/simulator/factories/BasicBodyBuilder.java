package simulator.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;

public class BasicBodyBuilder extends Builder<Body>{

	/* para crear objetos de la clase Body 
	 * pone la aceleración a(0, . . . ,0).
	 */
	
	public static final String desc = "Cuerpo basico";
	public static final String tag = "basic";
	
	public BasicBodyBuilder() {
		super.desc = desc;
		super.typeTag = tag;
	}
	
	@Override
	protected Body createTheInstance(JSONObject jsonObject) {
		// Body(String id, Vector v, Vector a, Vector p, double m)
		try 
		{
			try 
			{
				Body body;
				double[] vel = cambio(jsonObject.getJSONArray("vel"));
				double[] pos = cambio(jsonObject.getJSONArray("pos"));
				//System.out.println(jsonObject.getJSONArray("pos"));
				//for(int i = 0; i < pos.length;++i)
				//	System.out.println(pos[i]);
				Vector v = new Vector(vel);
				Vector a = new Vector(v.dim());
				Vector p = new Vector(pos);
				
				body = new Body(jsonObject.getString("id"), 
						v, 
						a, 
						p, 
						jsonObject.getDouble("mass"));
				return body;
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
	
	private double[] cambio(JSONArray jarray){
        double[] array = new double[jarray.length()];
        for(int i = 0; i < jarray.length(); i++){
            array[i] = jarray.getDouble(i);
        }
        return array;
    }
	
	public JSONObject getBuilderInfo(){
		// Body(String id, Vector v, Vector a, Vector p, double m)
		Body body = new Body("id", 
				new Vector(0), 
				new Vector(0), 
				new Vector(0), 
				0.0);
		JSONObject js = new JSONObject(body.toString());
		return js;
	}
}
