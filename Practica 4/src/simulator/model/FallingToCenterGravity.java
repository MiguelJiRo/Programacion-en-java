package simulator.model;

import java.util.List;

import simulator.model.Body;

public class FallingToCenterGravity implements GravityLaws{

	public static final double g = 9.81;
	/* Esta ley simula un escenario en el 
	 * cual todos los cuerpos caen hacia 
	 * el “centro del universo”, i.e. 
	 * tienen una aceleración fija de g= 9,81 
	 * en dirección al origen
	 */
	@Override
	public void apply(List<Body> bodies) {
		for(int i = 0; i < bodies.size(); ++i) 
		{
			bodies.get(i)
			.setAcceleration(
					bodies
					.get(i)
					.getPosition()
					.direction()
					.scale(-g));
					/* 
					 * Para un cuerpo B, asumiendo que di->
					 * es su direccion, su aceleracion deberia
					 * ponerse a:
					 * -g * di->
					*/
		}
	}

}
