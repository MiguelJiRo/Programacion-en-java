package simulator.factories;

import java.util.List;

import org.json.JSONObject;

public interface Factory<T> {
	/*
	 * recibe un objeto JSON que describe el 
	 * componente a crear, y devuelve una 
	 * instancia de la clase correspondiente 
	 * – una instancia de un subtipo de T.
	 *  En caso de que infosea incorrecto,
	 *  entonces lanza una excepción del tipo 
	 *  IllegalArgumentException.
	 */
	public T createInstance(JSONObject info);
	/*
	 * devuelve una lista de objetos JSON, 
	 * que son “plantillas” para estructuras 
	 * JSON válidas. Los objetos de esta lista 
	 * se pueden pasar como parámetro al método 
	 * createInstance. Esto es muy útil para 
	 * saber cuáles son los valores válidos 
	 * para una factoría concreta, sin saber 
	 * mucho sobre la factoría en si misma. 
	 * Por ejemplo, utilizaremos este método 
	 * cuando mostremos al usuario los posibles 
	 * valores de las leyes de la gravedad.
	 */
	public List<JSONObject> getInfo();
}
