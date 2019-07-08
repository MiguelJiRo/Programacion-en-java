package simulator.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Body;
import simulator.model.GravityLaws;
import simulator.model.PhysicsSimulator;
import simulator.model.SimulatorObserver;

public class Controller {

	private PhysicsSimulator ps;
	private Factory<Body> factory;
	private Factory<GravityLaws> factoryGL;
	
	public Controller(PhysicsSimulator ps, Factory<Body> fb, Factory<GravityLaws> fgl) {
		this.ps = ps;
		this.factory = fb;
		// Practica 5
		this.factoryGL = fgl;
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
	
	////////////////////////////////////////////
	/////////////// Practica 5 /////////////////
	////////////////////////////////////////////
	
	public void reset() {
		this.ps.reset();
	}
	
	public void setDeltaTime(double dt) {
		this.ps.setDeltaTime(dt);
	}
	
	public void addObserver(SimulatorObserver o) {
		this.ps.addObserver(o);
	}
	
	public void run(int n) {
		for(int i = 0; i < n; ++i)
			this.ps.advance();
	}
	
	public Factory<GravityLaws> getGravityLawsFactory(){
		return this.factoryGL;
	}
	
	public void setGravityLaws(JSONObject info) {
		this.ps.setGravityLaws(this.factoryGL.createInstance(info));		
	}
	
	public List<Body> getListaBodies(){
		return ps.getListaBodies();
	}
}
