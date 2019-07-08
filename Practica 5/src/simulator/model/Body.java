package simulator.model;

import simulator.misc.Vector;

public class Body {
	protected String id;
	protected Vector v,
					a,
					p;
	protected double m;
	
	
	public Body(String id, Vector v, Vector a, Vector p, double m) {
		this.id = id;
		this.v = v;
		this.a = a;
		this.p = p;
		this.m = m;
	}
	
	public String getId() {
		return this.id;
	}
	
	public Vector getVelocity() {
		return v;
	}
	
	public Vector getAcceleration() {
		return a;
	}
	
	public Vector getPosition() {
		return p;
	}
	
	public double getMass() {
		return m;
	}
	
	void setVelocity(Vector v) {
		this.v = new Vector(v);
	}
	
	void setAcceleration(Vector a) {
		this.a = new Vector(a);
	}
	
	void setPosition(Vector p) {
		this.p = new Vector(p);
	}
	
	void move(double t) {
		p = p.plus(v.scale(t)
				.plus(a.scale(t)
						.scale(t)
						.scale(0.5)));
		v = v.plus(a.scale(t));
	}
	
	public String toString() {
		return " {  \"id\": \"" + id 
				+ "\", \"mass\": " + m 
				+ ", \"pos\": "	+ p.toString() 
				+ ", \"vel\": " + v.toString() 
				+ ", \"acc\": " + a.toString() 
				+ " } ";
	}
}
