package tp.p3.control.factory;

import tp.p3.logic.plants.*;

public class PlantFactory {
	
	private static Plant[] availablePlants = {
			new Sunflower(),
			new Peashooter(),
			new Cherrybomb(),
			new Wallnut()
	};
	
	public static Plant getPlant(String plantName) {
		Plant plant = null;
		int i = 0;
		while(i < availablePlants.length) {
			if(availablePlants[i].getName() == plantName)
				plant = availablePlants[i];
			++i;
		}
		return plant;
	}
	
	public void listOfAvailablePlants() {
		for(int i = 0; i < availablePlants.length;++i) {
			System.out.println(availablePlants[i].getInfo());
		}	
		System.out.println("\n");
	}	
}
