package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

public class BuilderBasedFactory<T> implements Factory<T> {

	private List<Builder<T>> builder;
	
	public BuilderBasedFactory(List<Builder<T>> builders) {
		this.builder = builders;
	}
	
	@Override
	public T createInstance(JSONObject info) {
		for(int i = 0; i < builder.size(); ++i) {
			if(builder.get(i).createInstance(info) != null) {
				return builder.get(i).createInstance(info);
			}
		}
		
		// en caso de fallo lanza una exception
		throw new IllegalArgumentException("Fallo en crear instancia.");
	}

	@Override
	public List<JSONObject> getInfo() {
		List<JSONObject> list = new ArrayList<JSONObject>();
		for(int i = 0; i < builder.size(); ++i) {
			list.add(builder.get(i).getBuilderInfo());
		}
		return list;
	}

}
