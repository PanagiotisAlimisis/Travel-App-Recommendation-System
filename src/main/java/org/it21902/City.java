package org.it21902;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import myexceptions.NoSuchCityException;
import myexceptions.WikipediaArticleNotFoundException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

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
	private String nameCity;
	private String nameCountry;

	
	public City(String nameCity, String nameCountry) {
		this.termsVector = new ArrayList<Integer>();
		this.geodesicVector = new ArrayList<Double>();
		this.nameCity = nameCity;
		this.nameCountry = nameCountry;
	}
	
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
	
	public String getNameCity() {
		return nameCity;
	}

	public void setNameCity(String nameCity) {
		this.nameCity = nameCity;
	}

	public String getNameCountry() {
		return nameCountry;
	}

	public void setNameCountry(String nameCountry) {
		this.nameCountry = nameCountry;
	}
	
	
	public void printCityName() {
		System.out.println(this.nameCity);
	}
	

	 public void retrieveDataFromWikipedia() throws WikipediaArticleNotFoundException{
		 ObjectMapper mapper = new ObjectMapper(); 

		 MediaWiki mediaWikiObject=null;
		try {
			mediaWikiObject =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="+this.getNameCity()+"&format=json&formatversion=2"),MediaWiki.class);
			this.setTermsFromWikipedia(mediaWikiObject.getQuery().getPages().get(0).getExtract());
		} catch (JsonParseException e) {
			throw new WikipediaArticleNotFoundException(this.nameCity);
		} catch (JsonMappingException e) {
			throw new WikipediaArticleNotFoundException(this.nameCity);
		} catch (MalformedURLException e) {
			throw new WikipediaArticleNotFoundException(this.nameCity);
		} catch (NullPointerException e) {
			throw new WikipediaArticleNotFoundException(this.nameCity);
		} catch (Exception e) {
			throw new WikipediaArticleNotFoundException(this.nameCity);
		}	
	 }
	 
	 public void retrieveDataFromOpenWeatherMap(String appid) throws NoSuchCityException {
	    ObjectMapper mapper = new ObjectMapper(); 
		 
		OpenWeatherMap weatherObject=null;
	    try {
	    	weatherObject = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+this.getNameCity()+","+this.getNameCountry()+"&APPID="+appid+""), OpenWeatherMap.class);
			 this.setTermsFromOpenWeatherMap(weatherObject.getCoord().getLat(), weatherObject.getCoord().getLon());
			 /*Sets the original names because the user might have misspelled the name. For example the user could enter "Athen" instead of "Athens"
			  * and the search wouldn't fail in such minor misspellings, so we set the real names here.*/
			 this.setNameCity(weatherObject.getName());
			 this.setNameCountry(weatherObject.getSys().getCountry());
	    } catch (JsonParseException e) {
			throw new NoSuchCityException(this.nameCity);
		} catch (JsonMappingException e) {
			throw new NoSuchCityException(this.nameCity);
		} catch (MalformedURLException e) {
			throw new NoSuchCityException(this.nameCity);
		} catch (NullPointerException e) {
			throw new NoSuchCityException(this.nameCity);
		} catch (Exception e) {
			throw new NoSuchCityException(this.nameCity);
		}
	 }
	 
	 private void setTermsFromOpenWeatherMap(Double lat, Double lon) {
		 ArrayList<Double> temp = new ArrayList<Double>();
		 temp.add(lat);
		 temp.add(lon);
		 this.setGeodesicVector(temp);		 	 
	 }
	 
	 
	 private void setTermsFromWikipedia(String text) {
		 String s[] = text.split(" ");

		 /*Initialize 10 cells with 0.*/
		 ArrayList<Integer> terms = new ArrayList<Integer>();
		 for (int i=0; i<10; i++) {
			 terms.add(0);
		 }
		 
		 for (int i=0; i<s.length; ++i) {
			 if (s[i].contains("museum")) {
				 terms.set(0, terms.get(0)+1);
			 } else if (s[i].contains("sea")) {
				 terms.set(1, terms.get(1)+1);	 
			 } else if (s[i].contains("mountain")) {
				 terms.set(2, terms.get(2)+1);	 
			 } else if (s[i].contains("cafe")) {
				 terms.set(3, terms.get(3)+1);	 
			 } else if (s[i].contains("restaurant")) {
				 terms.set(4, terms.get(4)+1);	 
			 } else if (s[i].contains("bar")) {
				 terms.set(5, terms.get(5)+1);	 
			 } else if (s[i].contains("stadium")) {
				 terms.set(6, terms.get(6)+1);	 
			 } else if (s[i].contains("park")) {
				 terms.set(7, terms.get(7)+1);	 
			 } else if (s[i].contains("statue")) {
				 terms.set(8, terms.get(8)+1);	 
			 } else if (s[i].contains("square")) {
				 terms.set(9, terms.get(9)+1);	 
			 }
		 }
 		 this.setTermsVector(terms);	 
	 }
}