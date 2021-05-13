package org.it21902;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import myexceptions.NoSuchCityException;
import myexceptions.WikipediaArticleNotFoundException;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import weather.OpenWeatherMap;
import wikipedia.MediaWiki;

public class City {
	private static final String APPID = "8268b79c6118c5f5c576506e09e1318d";

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
	private static Map<String, City> allCities = new HashMap<>();
	
	public City(String nameCity, String nameCountry) {
		if (allCities.containsKey(nameCity)) return;
		
		this.termsVector = new ArrayList<Integer>();
		this.geodesicVector = new ArrayList<Double>();
		this.nameCity = nameCity;
		this.nameCountry = nameCountry;
		try {
			this.retrieveDataFromOpenWeatherMap();
			this.retrieveDataFromWikipedia();
			allCities.put(this.nameCity, this);
		} catch (NoSuchCityException e) {
			e.printStackTrace();
		} catch (WikipediaArticleNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public City(ArrayList<Integer> termsVector, ArrayList<Double> geodesicVector, String nameCity, String nameCountry) {
		super();
		this.termsVector = termsVector;
		this.geodesicVector = geodesicVector;
		this.nameCity = nameCity;
		this.nameCountry = nameCountry;
		allCities.put(this.nameCity, this);
	}

//	public static void addNewCity(String cityName, String countryName) {
		
//		new City(cityName, countryName);
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
	

	public static Map<String, City> getAllCities() {
		return allCities;
	}

	public static void setAllCities(Map<String, City> allCities) {
		City.allCities = allCities;
	}
	
	public void printCityName() {
		System.out.println(this.nameCity);
	}
	
	public static void writeCitiesToDatabase(Connection connection) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CITY VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			Iterator<Map.Entry<String, City>> it = allCities.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, City> entry = it.next();
				preparedStatement.setString(1, entry.getKey());
				preparedStatement.setString(2, entry.getValue().getNameCountry());
				preparedStatement.setDouble(3, entry.getValue().getGeodesicVector().get(0));
				preparedStatement.setDouble(4, entry.getValue().getGeodesicVector().get(1));
				preparedStatement.setInt(5, entry.getValue().getTermsVector().get(0));
				preparedStatement.setInt(6, entry.getValue().getTermsVector().get(1));
				preparedStatement.setInt(7, entry.getValue().getTermsVector().get(2));
				preparedStatement.setInt(8, entry.getValue().getTermsVector().get(3));
				preparedStatement.setInt(9, entry.getValue().getTermsVector().get(4));
				preparedStatement.setInt(10, entry.getValue().getTermsVector().get(5));
				preparedStatement.setInt(11, entry.getValue().getTermsVector().get(6));
				preparedStatement.setInt(12, entry.getValue().getTermsVector().get(7));
				preparedStatement.setInt(13, entry.getValue().getTermsVector().get(8));
				preparedStatement.setInt(14, entry.getValue().getTermsVector().get(9));
				
				try {
					int numRowChanged = preparedStatement.executeUpdate();
					System.out.println(numRowChanged+" rows inserted.");
				} catch (SQLIntegrityConstraintViolationException e) {
					System.err.println("IntegrityConstraintViolationException");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void readCitiesFromDb(Connection connection) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM CITY");
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				String cityName = resultSet.getString("name_city");
				String countryName = resultSet.getString("name_country");
				
				ArrayList<Double> geoVect = new ArrayList<>();
				geoVect.add(resultSet.getDouble("latitude"));
				geoVect.add(resultSet.getDouble("longitude"));
				
				ArrayList<Integer> termsVect = new ArrayList<>();
				termsVect.add(resultSet.getInt("museum"));
				termsVect.add(resultSet.getInt("sea"));
				termsVect.add(resultSet.getInt("mountain"));
				termsVect.add(resultSet.getInt("cafe"));
				termsVect.add(resultSet.getInt("restaurant"));
				termsVect.add(resultSet.getInt("bar"));
				termsVect.add(resultSet.getInt("stadium"));
				termsVect.add(resultSet.getInt("park"));
				termsVect.add(resultSet.getInt("statue"));
				termsVect.add(resultSet.getInt("square"));
				
//				System.out.println(cityName + " " + countryName + " " + geoVect + " " + termsVect);
				new City(termsVect, geoVect, cityName, countryName);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	

	 public void retrieveDataFromWikipedia() throws WikipediaArticleNotFoundException{
		 ObjectMapper mapper = new ObjectMapper(); 

		 MediaWiki mediaWikiObject=null;
		try {
			mediaWikiObject =  mapper.readValue(new URL("https://en.wikipedia.org/w/api.php?action=query&prop=extracts&titles="+this.getNameCity()+"&format=json&formatversion=2"),MediaWiki.class);
			this.setTermsFromWikipedia(mediaWikiObject.getQuery().getPages().get(0).getExtract());
		} catch (JsonParseException e) {
			throw new WikipediaArticleNotFoundException(this.nameCity);
		} catch (MalformedURLException e) {
			throw new WikipediaArticleNotFoundException(this.nameCity);
		} catch (NullPointerException e) {
			throw new WikipediaArticleNotFoundException(this.nameCity);
		} catch (Exception e) {
			throw new WikipediaArticleNotFoundException(this.nameCity);
		}	
	 }
	 
	 public void retrieveDataFromOpenWeatherMap() throws NoSuchCityException {
	    ObjectMapper mapper = new ObjectMapper(); 
		 
		OpenWeatherMap weatherObject=null;
	    try {
	    	weatherObject = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?q="+this.getNameCity()+","+this.getNameCountry()+"&APPID="+APPID+""), OpenWeatherMap.class);
			 this.setTermsFromOpenWeatherMap(weatherObject.getCoord().getLat(), weatherObject.getCoord().getLon());
			 /*Sets the original names because the user might have misspelled the name. For example the user could enter "Athen" instead of "Athens"
			  * and the search wouldn't fail in such minor misspellings, so we set the real names here.*/
			 this.setNameCity(weatherObject.getName());
			 this.setNameCountry(weatherObject.getSys().getCountry());
	    } catch (JsonParseException e) {
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