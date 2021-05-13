package org.it21902;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;



@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
@JsonSubTypes({
    @JsonSubTypes.Type(value = YoungTraveller.class),
    @JsonSubTypes.Type(value = MiddleTraveller.class),
    @JsonSubTypes.Type(value = ElderTraveller.class)
})
public abstract class Traveller implements Comparable<Traveller>{
	
	/*Largest distance between two cities on Earth that a trip can be achieved.*/
	private static final int MAX_DISTANCE = 18000;
	
	
	private String fullName;
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
	private static ArrayList<Traveller> allTravellersList = new ArrayList<Traveller>();
	private int age; //TODO: add age functionality when interactivity with the user is achieved
	private long timestamp;
	private String recommendedCity; //TODO: this is used in 4rth deliverable. it could be type of object City.
	private String currentCity;
	
	
	public Traveller()
		{}
	
	public Traveller(String fullName, String currentCity) {
		this.termsVector = new ArrayList<Integer>();
		this.geodesicVector = new ArrayList<Double>();
		this.timestamp = new Date().getTime();
		this.recommendedCity = "";
		this.fullName = fullName;
		this.geodesicVector = City.getAllCities().get(currentCity).getGeodesicVector();//TODO: implement logic when city isn't already stored.
		this.currentCity = currentCity; 


		if (!allTravellersList.contains(this)) {
				allTravellersList.add(this);
		} else {
			System.out.println("here");
			
			for (int i=0; i<allTravellersList.size(); ++i) {
				if (allTravellersList.get(i).equals(this)) {
					System.out.println("here");
					allTravellersList.set(i, this);
				}
			}
		}
	}

	/**
	 * @param c
	 * @return The degree of the similarity between the traveller and the city.
	 */
	public abstract double calculateSimilarity(City c);

	/**
	 * Calculates the similarity of all the known cities with this traveller's options and return the best city for them.
	 * @param cities
	 * @return
	 */
	public City compareCities(Map<String, City> cities) {
//		double max = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		City bestCity = null;
		
		for (Map.Entry<String, City> city: cities.entrySet()) {
			double similarity = this.calculateSimilarity(city.getValue());
//			System.out.println(city.getKey()+" - - "+similarity);
		
			if (similarity > max && !(city.getValue().getGeodesicVector().get(0) == this.getGeodesicVector().get(0) && city.getValue().getGeodesicVector().get(1) == this.getGeodesicVector().get(1))) {
				max = similarity;
				bestCity = city.getValue();
			}
		}
		
		return bestCity;
	}
	
	/**
	 * Calculates the similarity of all the known cities with this traveller's options and return the n best cities for them.
	 *
	 * @param cities
	 * @param n in [2, 5]
	 * @return
	 */
	public ArrayList<City> compareCities(Map<String, City> cities, int n) {
		if (n < 2 || n > 5) {
			throw new IllegalArgumentException();
		}
		
		Map<City, Double> citiesWithSimilarities = new HashMap<>();
		
		for (Map.Entry<String, City> c: cities.entrySet()) {
			citiesWithSimilarities.put(c.getValue(), this.calculateSimilarity(c.getValue()));
		}
		
		ArrayList<City> bestCities = new ArrayList<>();
		for (int i=0; i<n; ++i) {
			City c = Collections.max(citiesWithSimilarities.entrySet(), Map.Entry.comparingByValue()).getKey();
			citiesWithSimilarities.remove(c);
			if (c.getGeodesicVector().get(0) == this.getGeodesicVector().get(0) && c.getGeodesicVector().get(1) == this.getGeodesicVector().get(1)) {
				--i;
				continue;
			}
			bestCities.add(c);
		}
		
		return bestCities;
	}
	
	public static void printTravellersFromLastToFirst() {
		Set<Traveller> noDuplicateSet = new HashSet<>(allTravellersList);
		ArrayList<Traveller> noDuplicateList = new ArrayList<>(noDuplicateSet);
		Collections.sort(noDuplicateList);
		Collections.reverse(noDuplicateList);
		
		Iterator<Traveller> it = noDuplicateList.iterator();
		while (it.hasNext()) {
			Traveller t = it.next();
			System.out.println(t.getFullName() + " " + t.getTimestamp());
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void writeTravellersToJson() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
	
		objectMapper.enableDefaultTyping(
			    ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
		
		try {
			objectMapper.writeValue(new File("Travellers.json"), allTravellersList);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void readTravellersFromJson() {
		ObjectMapper objectMapper = new ObjectMapper();
		 try {
			ArrayList<Traveller>  travellers = objectMapper.readValue(new File("Travellers.json"), objectMapper.getTypeFactory().constructCollectionType(List.class, Traveller.class));
			setAllTravellersList(travellers);
		 } catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static Traveller giveFreeTicket(City city) {
		double maxSimilarity = -1;
		Traveller winnerOfTheFreeTicket = null;
		for (Traveller t: allTravellersList) {
			double similarity = t.calculateSimilarity(city);
			if (similarity > maxSimilarity && !(city.getGeodesicVector().get(0) == t.getGeodesicVector().get(0) && city.getGeodesicVector().get(1) == t.getGeodesicVector().get(1))) {
				maxSimilarity = similarity;
				winnerOfTheFreeTicket = t;
			}
		}
		return winnerOfTheFreeTicket;
	}
	
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
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public static ArrayList<Traveller> getAllTravellersList() {
		return allTravellersList;
	}

	public static void setAllTravellersList(ArrayList<Traveller> allTravellersList) {
		Traveller.allTravellersList = allTravellersList;
	}
	
	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getRecommendedCity() {
		return recommendedCity;
	}

	public void setRecommendedCity(String recommendedCity) {
		this.recommendedCity = recommendedCity;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getCurrentCity() {
		return currentCity;
	}

	public void setCurrentCity(String currentCity) {
		this.currentCity = currentCity;
	}

	
		
	protected double similarityGeodesicVector(City c) {
		return log2(2 / (2 - calculateDistance(c) / MAX_DISTANCE));
	}
	
	/**
	 * Calculates log with base 2.
	 * @param N
	 * @return
	 */
	private static double log2(double N) { 
        return (Math.log(N) / Math.log(2));
    } 

	
	/**
	 * Distance between two points in kilometers.
	 * @param c
	 * @return
	 */
	private double calculateDistance(City c) {
		double latTraveller = this.getGeodesicVector().get(0), latCity = c.getGeodesicVector().get(0);
		double lonTraveller = this.getGeodesicVector().get(1), lonCity = c.getGeodesicVector().get(1);
		
		/*Traveller is located in the same city that they want to travel.*/
		if ((latTraveller == latCity) && (lonTraveller == lonCity)) {
			return 0;
		}
		
		double theta = lonTraveller - lonCity;
		double dist = Math.sin(Math.toRadians(latTraveller)) * Math.sin(Math.toRadians(latCity)) + Math.cos(Math.toRadians(latTraveller)) * Math.cos(Math.toRadians(latCity)) * Math.cos(Math.toRadians(theta));
		dist = Math.acos(dist);
		dist = Math.toDegrees(dist);
		dist = dist * 60 * 1.1515;
		
		/*Return distance in kilometers*/
		return dist*1.609344;		
	}
	
	@Override
    public boolean equals(Object o) {
		 if (o == this) {
            return true;
        }

        if (!(o instanceof Traveller)) {
            return false;
        }
          
        Traveller c = (Traveller) o;

        return this.fullName.equals(c.fullName);
    }
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
	    result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
		return result;
	 }
}