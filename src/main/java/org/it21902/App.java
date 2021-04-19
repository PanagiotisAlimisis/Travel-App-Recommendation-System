package org.it21902;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class App {
	

	public static void main(String[] args)  {
		City.addNewCity("Athens", "gr");
		City.addNewCity("Thessaloniki", "gr");
		City.addNewCity("Madrid", "es");
		City.addNewCity("Berlin", "de");
		City.addNewCity("fggs", "bf");
		City.addNewCity("California", "us");
		City.addNewCity("Tokyo", "jp");
		City.addNewCity("Jakarta", "id");
		City.addNewCity("city", "country");
		City.addNewCity("Vienna", "at");
		City.addNewCity("Tripolis", "gr");

		Map<String, City> allCities = City.getAllCities();
		
		new YoungTraveller("george", "Athens");
		new MiddleTraveller("peter", "Berlin");
		new ElderTraveller("mary", "Jakarta");
		new YoungTraveller("john", "Madrid");
		new MiddleTraveller("jonh", "Tripoli");
		new ElderTraveller("anastasia", "California");
		
		ArrayList<Traveller> allTravellers = Traveller.getAllTravellersList();
		System.out.println("All travellers");
		for (Traveller t: allTravellers) {
			System.out.print(t.getName() + " -- ");
			ArrayList<Integer> termsVector = new ArrayList();
			for (int i=0; i<10; ++i) {
				Random random = new Random();
				termsVector.add(random.nextInt(11));
			}
			t.setTermsVector(termsVector);
			System.out.println(termsVector + " -- " + t.getCurrentCity());
		
			System.out.println("Recommended city: " + t.compareCities(City.getAllCities()).getNameCity()+"\n");
		}

		new YoungTraveller("panagiotis", "Vienna");

		System.out.println("New cities insertion");
		City.addNewCity("Athens", "gr");
		City.addNewCity("Thessaloniki", "gr");
		City.addNewCity("Madrid", "es");
		City.addNewCity("Berlin", "de");
		City.addNewCity("fggs", "bf");
		City.addNewCity("California", "us");
		City.addNewCity("Tokyo", "jp");
		City.addNewCity("Jakarta", "id");
		City.addNewCity("city", "country");
		City.addNewCity("Vienna", "at");
		City.addNewCity("Tripolis", "gr");
		City.addNewCity("Rome", "it");
		City.addNewCity("Los Angeles", "us");
		City.addNewCity("Sparta", "gr");
		City.addNewCity("Kalamata", "gr");
		
		System.out.println("All cities");
	
		allCities = City.getAllCities();
		Iterator<Map.Entry<String, City>> it = allCities.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, City> entry =  it.next();
			
			System.out.println(entry.getKey() + " -- " + entry.getValue().getTermsVector());
		}
		System.out.println(allCities.size());
		
		
		for (Traveller t: allTravellers) {
			System.out.print(t.getName() + " -- ");
			ArrayList<Integer> termsVector = new ArrayList();
			for (int i=0; i<10; ++i) {
				Random random = new Random();
				termsVector.add(random.nextInt(11));
			}
			t.setTermsVector(termsVector);
			System.out.println(termsVector + " -- " + t.getCurrentCity());
		
			System.out.println("3 best recommended cities: ");
			try {
				t.compareCities(City.getAllCities(), 3).forEach(i -> {i.printCityName();});
			} catch (IllegalArgumentException e) {
				System.err.println("Illegal Argument Exception");
			}
		}
		Traveller.getAllTravellersList().forEach(i -> {System.out.println(i.getName());});
		
	}
}