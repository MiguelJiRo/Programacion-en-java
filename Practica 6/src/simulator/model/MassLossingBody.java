package simulator.model;

import simulator.misc.Vector;

public class MassLossingBody extends Body{
	// numero entre 0 y 1 que representa 
	// el factor de perdida de masa
	protected double lossFactor;
	// numero positivo que indica el intervalo
	// de tiempo (en segundos) despues del cual
	// el objeto pierde masa
	protected double lossFrequency;
	protected double c;
	
	public MassLossingBody(String id, Vector v, Vector a, Vector p, double m, double factor, double frequency) {
		super(id,v,a,p,m);
		lossFactor = factor;
		lossFrequency = frequency;
		c = 0.0;
	}
	
	void move(double t) {
		super.move(t);
		c += t;
		if(c >= lossFrequency) {
			m = m * ( 1 - lossFactor );
			c = 0.0;
		}
	}
}
