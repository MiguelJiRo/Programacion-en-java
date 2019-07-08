package simulator.factories;

import org.json.JSONObject;

public abstract class Builder<T> {
	protected String typeTag;
	protected String desc;
	
	/* 
	 * si la informaci�n suministrada 
	 * por info es correcta, entonces crea 
	 * un objeto de tipo T (i.e., una instancia 
	 * de una subclase de T). En otro caso 
	 * devuelve null para indicar que este 
	 * constructor es incapaz de reconocer 
	 * ese formato. En caso de que reconozca 
	 * el campo type pero haya un error en 
	 * alguno de los valores suministrados 
	 * por la secci�n data, el m�todo lanza 
	 * una excepci�n IllegalArgumentException.
	 */
	public T createInstance(JSONObject info){
		// si la info es correcta		
		if(info.get("type").equals(typeTag))
		{
			return createTheInstance(info.getJSONObject("data"));
		}
		else
			return null;
	}
	
	/*
	 * devuelve un objeto JSON que sirve de 
	 * plantilla para el correspondiente 
	 * constructor, i.e., un valor v�lido 
	 * para el par�metro de createInstance
	 * (ver getInfo() de Factory<T>).
	 */
	public JSONObject getBuilderInfo(){
		JSONObject json = new JSONObject();
		json.put("type", typeTag)
				.put("desc", desc)
					.put("data", new JSONObject());
		return json;
	}
	
	/*
	 * se invoca en createInstance cuando 
	 * typeTag coincide con el valor de la clave 
	 * type del par�metro info. 
	 * En esta invocaci�n se usa el JSONObject de 
	 * la clave data de info.
	 */
	protected abstract T createTheInstance(JSONObject jsonObject);
}
