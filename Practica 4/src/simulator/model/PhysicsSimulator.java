package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSimulator {
	
	private double tiemporpp;
	private GravityLaws lg;
	private List<Body> lista;
	private double tiempoActual;
	
	public PhysicsSimulator(double tiempoRealPorPaso, GravityLaws leyesGravedad) {
		/*
		 Tiempo real por paso: un número de 
		 tipo double que representa el tiempo 
		 (en segundos) que corresponde a un paso
		  de simulación se pasará al método move
		  de los cuerpos. Debe lanzar una 
		  excepción IllegalArgumentException 
		  en caso de que el valor no sea válido.
		 */
		if(tiempoRealPorPaso < 0) throw new IllegalArgumentException("Tiempo no valido.");
		
		/*
		 Leyes de la gravedad:un objeto del 
		 tipo GravityLaws, que representa las 
		 leyes de la gravedad que el simulador 
		 aplicará a los cuerpos. Si el valor 
		 es null, debe lanzar una excepción del 
		 tipo IllegalArgumentException.
		 */
		else if(leyesGravedad == null)	throw new IllegalArgumentException("Ley de gravedad no valida.");
		
		this.tiemporpp = tiempoRealPorPaso;
		this.lg = leyesGravedad;
		lista = new ArrayList<>();
		this.tiempoActual = 0;
	}
	
	/*
	 * aplica un paso de simulación, i.e., 
	 * primero llama al método apply de las 
	 * leyes de la gravedad, después llama 
	 * a move(dt)para cada cuerpo, donde dt
	 * es el tiempo real por paso, y finalmente 
	 * incrementa el tiempo actual en dt segundos.
	 */
	public void advance() {
		lg.apply(lista);
		for(int i = 0; i < lista.size(); ++i) {
			lista.get(i).move(tiemporpp);
		}
		tiempoActual += tiemporpp;
	}
	
	/*
	 * añade el cuerpo b al simulador. 
	 * El método debe comprobar que no existe 
	 * ningún otro cuerpo en el simulador con 
	 * el mismo identificador.Si existiera, el 
	 * método debe lanzar una excepción del 
	 * tipo IllegalArgumentException.
	 */
	public void addBody(Body b) {
		
		boolean igual = false;
		//System.out.println("33333 lista.size: " + lista.size());
		for(int i = 0;i < lista.size() - 1; ++i) 
		{
			//System.out.println("44444 i : " + i);
			// comprueba si el cuerpo existe
			if(lista.get(i).equals(b))
				igual = true;
		}
		
		if(!igual) {
			lista.add(b);
		}	
		// si existe el cuerpo se lanza exception
		else {
			throw new IllegalArgumentException("Cuerpo ya existe");
		}
			
	}
	
	/*
	 * devuelve un string que representa un 
	 * estado del simulador,utilizando el 
	 * siguiente formatoJSON:
	 * 
	 * { "time":T, "bodies": [json1,json2,. . .] }
	 * 
	 * donde T es el tiempo actual y jsoni
	 * es el string devuelto por el método toString 
	 * del iésimo cuerpo en la lista de cuerpos
	 */
	public String toString() {
		return "{ \"time\": " + this.tiempoActual 
				+ ", \"bodies\": " + this.lista.toString()
				+ " }";
	}
}
