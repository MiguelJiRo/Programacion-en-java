package simulator.factories;

import java.util.List;

import org.json.JSONObject;

public interface Factory<T> {
	/*
	 * recibe un objeto JSON que describe el 
	 * componente a crear, y devuelve una 
	 * instancia de la clase correspondiente 
	 * � una instancia de un subtipo de T.
	 *  En caso de que infosea incorrecto,
	 *  entonces lanza una excepci�n del tipo 
	 *  IllegalArgumentException.
	 */
	public T createInstance(JSONObject info);
	/*
	 * devuelve una lista de objetos JSON, 
	 * que son �plantillas� para estructuras 
	 * JSON v�lidas. Los objetos de esta lista 
	 * se pueden pasar como par�metro al m�todo 
	 * createInstance. Esto es muy �til para 
	 * saber cu�les son los valores v�lidos 
	 * para una factor�a concreta, sin saber 
	 * mucho sobre la factor�a en si misma. 
	 * Por ejemplo, utilizaremos este m�todo 
	 * cuando mostremos al usuario los posibles 
	 * valores de las leyes de la gravedad.
	 */
	public List<JSONObject> getInfo();
}
