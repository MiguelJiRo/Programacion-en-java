package simulator.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Vector;
import simulator.model.Body;
import simulator.model.MassLossingBody;

public class MassLosingBodyBuilder extends Builder<Body>{

	/* que extiende a Builder<Body>, 
	 * para crear objetos de la 
	 * clase MassLosingBody– pone la 
	 * aceleración a(0, . . . ,0).
	 */
	
	public static final String desc = "Cuerpo que pierde masa";
	public static final String tag = "mlb";
	
	public MassLosingBodyBuilder() {
		super.desc = desc;
		super.typeTag = tag;
	}
	
	@Override
	protected Body createTheInstance(JSONObject jsonObject) {
		// MassLossingBody(String id, Vector v, Vector a, Vector p, double m, double factor, double frequency) {
		try 
		{
			try 
			{
				Body body;
				double[] vel = cambio(jsonObject.getJSONArray("vel"));
				double[] pos = cambio(jsonObject.getJSONArray("pos"));
				Vector v = new Vector(vel);
				Vector a = new Vector(v.dim());
				Vector p = new Vector(pos);
				
				body = new MassLossingBody(jsonObject.getString("id"), 
						v, 
						a, 
						p, 
						jsonObject.getDouble("mass"),
						jsonObject.getDouble("factor"),
						jsonObject.getDouble("freq"));
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
		// MassLossingBody(String id, Vector v, Vector a, Vector p, double m, double factor, double frequency) {
		Body body = new MassLossingBody("id", 
				new Vector(0), 
				new Vector(0), 
				new Vector(0), 
				0.0,
				0.0,
				0.0);
		JSONObject js = new JSONObject(body.toString());
		return js;
	}
}
