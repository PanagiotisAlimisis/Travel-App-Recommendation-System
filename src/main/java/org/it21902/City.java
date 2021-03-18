package org.it21902;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import weather.OpenWeatherMap;
import wikipedia.MediaWiki;

public class City {
	/**
	 * 0: museum
	 * 1: sea
	 * 2: mountain
	 * 3: cafe
	 * 4: restaurant
	 * 5: bar
	 * 6: stadium
	 * 7: park
	 * 8: statue
	 * 9: square
	 */
	private ArrayList<Integer> termsVector;
	/**
	 * 0: lat
	 * 1: lon
	 */
	private ArrayList<Double> geodesicVector;
	private String name;
	
	public City() {
		this.termsVector = new ArrayList<Integer>();
		this.geodesicVector = new ArrayList<Double>();
		this.name = "";
	}

//	public boolean equals(City c) {
//		return c.getName() == this.name;
//	}
	
	/*Getters & Setters*/
	public ArrayList<Integer> getTermsVector() {
		return termsVector;
	}

	public void setTermsVector(ArrayList<Integer> termsVector) {
		this.termsVector = termsVector;
		for (int i=0; i<10; ++i) {
			if (this.termsVector.get(i) > 10) {
				this.termsVector.set(i, 10);
			}
		}
	}

	public ArrayList<Double> getGeodesicVector() {
		return geodesicVector;
	}

	public void setGeodesicVector(ArrayList<Double> geodesicVector) {
		this.geodesicVector = geodesicVector;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	/**Retrieves weather information, geotag (lan, lon) and a Wikipedia article for a given city.
	* @param city The Wikipedia article and OpenWeatherMap city. 
	* @param country The country initials (i.e. gr, it, de).
	* @param appid Your API key of the OpenWeatherMap.*/ 
	 public void retrieveData(String city, String country, String appid) throws  IOException {
		 ObjectMapper mapper = new ObjectMapper(); 
		 mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		 OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+","+country+"&APPID="+appid+""), OpenWeatherMap.class);
//		 System.out.println(city+" temperature: " + (weather_obj.getMain()).getTemp());
//		 System.out.println(city+" lat: " + weather_obj.getCoord().getLat()+" lon: " + weather_obj.getCoord().getLon());
		 
		 /*Set geodesic vector of this city*/
		 ArrayList<Double> temp = new ArrayList<Double>();
		 temp.add(weather_obj.getCoord().getLat());
		 temp.add(weather_obj.getCoord().getLon());
		 this.setGeodesicVector(temp);
		 
		 MediaWiki mediaWiki_obj =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="+city+"&format=json&formatversion=2"),MediaWiki.class);
//		 System.out.println(city+" Wikipedia article: "+mediaWiki_obj.getQuery().getPages().get(0).getExtract());
		 this.setTermsFromWiki(mediaWiki_obj.getQuery().getPages().get(0).getExtract());
	}

	 
	 private void setTermsFromWiki(String text) {
		 String s[] = text.split(" ");
		 
		 Map<String, Integer> map = new HashMap<String, Integer>();
		 for (int i=0; i<s.length; ++i) {
			 
			 if (!map.containsKey(s[i])) {
				 map.put(s[i], 1);
			 } else {
				 map.put(s[i], map.get(s[i])+1);				 
			 }
			 
		 }
		 
		 /*Initialize 10 cells with 0.*/
		 ArrayList<Integer> terms = new ArrayList<Integer>();
		 for (int i=0; i<10; i++) {
			 terms.add(0);
		 }
		 
		 /*Find each term.*/
		 for (Map.Entry<String, Integer> m: map.entrySet()) {
			 if (m.getKey().contains("museum")) {
				 terms.set(0, terms.get(0)+1);
			 } else if (m.getKey().contains("sea")) {
				 terms.set(1, terms.get(1)+1);	 
			 } else if (m.getKey().contains("mountain")) {
				 terms.set(2, terms.get(2)+1);	 
			 } else if (m.getKey().contains("cafe")) {
				 terms.set(3, terms.get(3)+1);	 
			 } else if (m.getKey().contains("restaurant")) {
				 terms.set(4, terms.get(4)+1);	 
			 } else if (m.getKey().contains("bar")) {
				 terms.set(5, terms.get(5)+1);	 
			 } else if (m.getKey().contains("stadium")) {
				 terms.set(6, terms.get(6)+1);	 
			 } else if (m.getKey().contains("park")) {
				 terms.set(7, terms.get(7)+1);	 
			 } else if (m.getKey().contains("statue")) {
				 terms.set(8, terms.get(8)+1);	 
			 } else if (m.getKey().contains("square")) {
				 terms.set(9, terms.get(9)+1);	 
			 }
		 }
		 
		 this.setTermsVector(terms);
		 
	 }


}
