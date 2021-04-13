package org.it21902;

import java.util.ArrayList;

import myexceptions.NoSuchCityException;
import myexceptions.WikipediaArticleNotFoundException;


public class App {
	
	private static final String APPID = "8268b79c6118c5f5c576506e09e1318d";

	public static void main(String[] args)  {
		ArrayList<City> cities = new ArrayList();
		cities.add(new City("Athens", "gr"));
		cities.add(new City("Thessaloniki", "gr"));
		cities.add(new City("Madrid", "es"));
		cities.add(new City("Berlin", "de"));
		cities.add(new City("asdasd", "asdasd"));
		cities.add(new City("California", "us"));
		cities.add(new City("Tokyo", "jp"));
		cities.add(new City("Jakarta", "id"));
		cities.add(new City("city", "country"));/*This was intended to be a false value but Open Weather Map detects a city with the name "City" in Australia and corrects the country name.*/
		cities.add(new City("Vienna", "at"));
		cities.add(new City("Tripolis", "gr"));
		
		try {
			cities.get(4).retrieveDataFromWikipedia();
		} catch (WikipediaArticleNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println("Total cities before fetching data: " + cities.size());
		for (int i=0; i<cities.size(); ++i) {
			try {
				cities.get(i).retrieveDataFromOpenWeatherMap(APPID);
				cities.get(i).retrieveDataFromWikipedia();
			} catch (NoSuchCityException exception){
//				exception.printStackTrace();
				cities.remove(cities.get(i));
				--i;
				continue;
			} catch (WikipediaArticleNotFoundException exception) {
				cities.remove(cities.get(i));
				--i;
				continue;
//				exception.printStackTrace();
			}
			ArrayList<Double> geodesicVector = new ArrayList();
			geodesicVector = cities.get(i).getGeodesicVector();
			System.out.print(cities.get(i).getNameCity() + " " + cities.get(i).getNameCountry() + " lat: " + geodesicVector.get(0) + " lon: " + geodesicVector.get(1) + "  ");
			
			ArrayList<Integer> termsVector = new ArrayList();
			termsVector = cities.get(i).getTermsVector();
			System.out.print("[");
			for (Integer term: termsVector) {
				System.out.print(term + ", ");
			}
			System.out.println("]");
		}
		
		System.out.println("Total cities after fetching data: " + cities.size());
		
		Traveller youngTraveller = new YoungTraveller();
		Traveller middleTraveller = new MiddleTraveller();
		Traveller elderTraveller = new ElderTraveller();
		
		ArrayList<Double> geodesicVector = new ArrayList();
		geodesicVector = cities.get(0).getGeodesicVector(); /*Athens*/
		youngTraveller.setGeodesicVector(geodesicVector);
		ArrayList<Integer> termsVector = new ArrayList();
		termsVector.add(10);		
		termsVector.add(9);		
		termsVector.add(2);		
		termsVector.add(0);		
		termsVector.add(0);		
		termsVector.add(6);		
		termsVector.add(6);		
		termsVector.add(9);		
		termsVector.add(6);		
		termsVector.add(5);		
		youngTraveller.setTermsVector(termsVector);

		System.out.println();
		System.out.println("Young traveller is in Athens.");
		System.out.println("Best city for young traveller: "+youngTraveller.compareCities(cities).getNameCity());
		System.out.println("Best 3 cities for young traveller: ");
		youngTraveller.compareCities(cities, 3).forEach(i -> {i.printCityName();});
		
		geodesicVector = cities.get(3).getGeodesicVector(); /*Berlin*/
		middleTraveller.setGeodesicVector(geodesicVector);
		termsVector.removeAll(termsVector);
		termsVector.add(3);
		termsVector.add(8);
		termsVector.add(1);
		termsVector.add(0);
		termsVector.add(5);
		termsVector.add(7);
		termsVector.add(6);
		termsVector.add(5);
		termsVector.add(1);
		termsVector.add(0);
		middleTraveller.setTermsVector(termsVector);

		System.out.println();
		System.out.println("Middle traveller is in Berlin.");
		System.out.println("Best city for middle traveller: "+middleTraveller.compareCities(cities).getNameCity());
		System.out.println("Best 4 cities for middle traveller: ");
		middleTraveller.compareCities(cities, 4).forEach(i -> {i.printCityName();});
		
		/*force an exception*/
		try {
			middleTraveller.compareCities(cities, 0).forEach(i -> {i.printCityName();});
		} catch (NullPointerException exception) {
//			exception.printStackTrace();
			System.err.println("NULL POINTER EXCEPTION. Tried to pass a wrong parameter on compareCities.");
		}

		
		geodesicVector = cities.get(7).getGeodesicVector(); /*City*/
		elderTraveller.setGeodesicVector(geodesicVector);
		termsVector.removeAll(termsVector);
		termsVector.add(1);
		termsVector.add(5);
		termsVector.add(1);
		termsVector.add(0);
		termsVector.add(0);
		termsVector.add(2);
		termsVector.add(0);
		termsVector.add(0);
		termsVector.add(0);
		termsVector.add(1);
		elderTraveller.setTermsVector(termsVector);

		System.out.println();
		System.out.println("Elder traveller is in City.");
		System.out.println("Best city for elder traveller: "+elderTraveller.compareCities(cities).getNameCity());
		System.out.println("Best 5 cities for elder traveller: ");
		elderTraveller.compareCities(cities, 5).forEach(i -> {i.printCityName();});
	}
}