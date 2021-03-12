package org.it21902;

import java.util.ArrayList;

import java.io.IOException;
import java.net.URL;
import com.fasterxml.jackson.databind.ObjectMapper;
//import weather.OpenWeatherMap;
//import wikipedia.MediaWiki;

public class City {
	
	private ArrayList<Integer> termsVector;
	private ArrayList<Double> geodesicVector;
	private String name;
	
	public City() {
		this.termsVector = new ArrayList();
		this.geodesicVector = new ArrayList();
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
//	 public static void RetrieveData(String city, String country, String appid) throws  IOException {
//		 ObjectMapper mapper = new ObjectMapper(); 
//		 OpenWeatherMap weather_obj = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+city+","+country+"&APPID="+appid+""), OpenWeatherMap.class);
//		 System.out.println(city+" temperature: " + (weather_obj.getMain()).getTemp());
//		 System.out.println(city+" lat: " + weather_obj.getCoord().getLat()+" lon: " + weather_obj.getCoord().getLon());
//		 MediaWiki mediaWiki_obj =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="+city+"&format=json&formatversion=2"),MediaWiki.class);
//		 System.out.println(city+" Wikipedia article: "+mediaWiki_obj.getQuery().getPages().get(0).getExtract());	 
//	}



}
