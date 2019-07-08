package simulator.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.PhysicsSimulator;

public class Controller {

	private PhysicsSimulator ps;
	private Factory<Body> factory;
	
	public Controller(PhysicsSimulator ps, Factory<Body> fb) {
		this.ps = ps;
		this.factory = fb;
	}
	
	public void loadBodies(InputStream in) {
		JSONObject jsonInupt  = new JSONObject(new JSONTokener(in));
		JSONArray jarray = jsonInupt.getJSONArray("bodies");
		for(int i = 0; i < jarray.length(); ++i) {
			//System.out.println("##### " + i);
			//System.out.println("##### " + factory.createInstance(jarray.getJSONObject(i)));
			ps.addBody(factory.createInstance(jarray.getJSONObject(i)));
		}
	}
	
	public void run (int n, OutputStream out) throws IOException {
		out.write("{\n\"states\": [\n".getBytes());
		out.write(ps.toString().getBytes());
		for(int i = 0; i < n; ++i) {
			this.ps.advance();
			out.write(",\n".getBytes());
			out.write(ps.toString().getBytes());		
		}
		out.write("\n]\n}\n".getBytes());
	}
}
