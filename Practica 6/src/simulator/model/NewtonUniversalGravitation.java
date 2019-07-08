package simulator.model;

import java.util.List;

import simulator.misc.Vector;
import simulator.model.Body;

public class NewtonUniversalGravitation implements GravityLaws{

	public static final double G = 6.67E-11;
	/*
	 *  Que cambiará la aceleración de los cuerpos
	 *  de la siguiente forma: dos cuerpos Bi y Bj
	 *  aplican una fuerza gravitacional uno sobre 
	 *  otro, i.e., se atraen mutuamente.
	 */
	@Override
	public void apply(List<Body> bodies) {
		for(int i = 0; i < bodies.size(); ++i) 
		{
			/*
			 Como un caso especial, si m es 
			 igual a 0,0, ponemos los vectores 
			 de aceleración y velocidad de Bi a 
			 o = (0, . . . ,0)
			 (sin necesidad de calcular Fi). 
			 */
			if(bodies.get(i).getMass() == 0) {
				bodies.get(i).setAcceleration(
						bodies.get(i).getAcceleration());
				bodies.get(i).setVelocity(
						bodies.get(i).getVelocity());
			}
			else 
			{
				Vector f = new Vector(bodies.get(i)
						.getAcceleration().dim());
				for(int j = 0; j < bodies.size(); ++j) 
				{
					if(i != j)
					{
						/*  		( mi * mj )
						* f = G *  --------------------
						* 		|vector pj - vector pi| ^2
						*/
						double parteA = G 
								* bodies.get(i).getMass() 
								* bodies.get(j).getMass();
						//System.out.println("parteA - " + parteA);
						double parteB = (Math.pow(bodies.get(j)
								.getPosition()
								.distanceTo(bodies.get(i)
										.getPosition()), 2));
						//System.out.println("parteB - " + parteB);
						f = f.plus((bodies.get(j)
								.getPosition()
								.minus(bodies.get(i)
										.getPosition()))
										.direction()
										.scale(	parteA	/ parteB));					
					}				
				}
				bodies.get(i)
				.setAcceleration(
						f.scale(1.0/bodies.get(i)
								.getMass()));
			}
		}
	}
	
	////////////////////////////////////////////
	/////////////// Practica 5 /////////////////
	////////////////////////////////////////////

	public String toString() {
		return "Newton Universal Gravitation";
	}

}
