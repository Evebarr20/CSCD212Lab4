package cscd212classes.lab4.map;

public class MapFactory {

	public static Map getMap(final String mapString) {
		if (mapString.equalsIgnoreCase("basic")) {
			Map.setCurrentMap(new BasicMap());
			return Map.getCurrentMap();
		}
		else if(mapString.equalsIgnoreCase("city")) {
			Map.setCurrentMap(new CityMap());
			return Map.getCurrentMap();
		}
		else if(mapString.equalsIgnoreCase("mexico")) {
			Map.setCurrentMap(new MexicoMap());
			return Map.getCurrentMap();
		}
		else {
			return Map.getCurrentMap();
		}

	}

	public static String[] getMapStrings() {

		String[] mapsString = {"Basic", "City", "Mexico"};
		return mapsString;
	}
}
