package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class PhysicsSimulator {
	
	private double tiemporpp;
	private GravityLaws lg;
	private List<Body> lista;
	private double tiempoActual;
	
	private List<SimulatorObserver> observerList;
	
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
		observerList = new ArrayList<>();
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
		
		// notificacion onAdvance a todos los observadores
		for(int i = 0;i <= observerList.size() - 1; ++i) 
			observerList.get(i).onAdvance(this.lista, this.tiempoActual);				
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
			// notificacion onBodyAdded a todos los observadores			
			for(int i = 0;i <= observerList.size() - 1; ++i) 
				observerList.get(i).onBodyAdded(this.lista,b);						
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
	
	////////////////////////////////////////////
	/////////////// Practica 5 /////////////////
	////////////////////////////////////////////
	
	public void reset() {
		// vacia la lista de cuerpos
		this.lista.clear();
		// pone el tiempo a 0.0
		this.tiempoActual = 0.0;
		
		// notificacion onReset a todos los observadores		
		for(int i = 0;i <= observerList.size() - 1; ++i) 
			observerList.get(i).onReset(this.lista,this.tiempoActual,this.tiemporpp,this.lg.toString());			
	}
	
	/*
	 * Cambia el tiempo real por paso (delta-time)
	 * a dt. Si dt tiene un valor no valido lanza una
	 * excepcion de tipo IllegalArgumentException.
	 */
	public void setDeltaTime(double dt) {
		if(dt < 0.0)
			throw new IllegalArgumentException();
		else {
			this.tiemporpp = dt;
			// notificacion onDeltaTime a todos los observadores			
			for(int i = 0;i <= observerList.size() - 1; ++i) 
				observerList.get(i).onDeltaTimeChanged(dt);					
		}				
	}
	
	/*
	 * Cambia las leyes de gravedad del simulador
	 * a gravityLaws. Lanza una IllegalArgumentException
	 * si el valor no es valido, es decir, es null.
	 */
	public void setGravityLaws(GravityLaws gravityLaws) {
		if(gravityLaws == null)
			throw new IllegalArgumentException();
		else {
			this.lg = gravityLaws;
			// notificacion onGravityLawsChanged a todos los observadores			
			for(int i = 0;i <= observerList.size() - 1; ++i) 
				observerList.get(i).onGravityLawChanged(gravityLaws.toString());						
		}			
	}
	
	public void addObserver(SimulatorObserver o) {
		// añade o a la lista de observador,
		// si es que no está ya en la lista
		boolean encontrado = false;
		for(int i = 0;i < observerList.size() - 1; ++i) 
		{
			if(observerList.get(i).equals(o))
				encontrado = true;
		}
		
		if(!encontrado) {
			observerList.add(o);
			//envia una notificacion onRegister solo
			// al observador que se acaba de registrar
			// para pasarle el estado actual del simulador
			observerList.get(observerList.size() - 1).onRegister(this.lista,this.tiempoActual,this.tiemporpp,this.lg.toString());		
		}	
	}
}
