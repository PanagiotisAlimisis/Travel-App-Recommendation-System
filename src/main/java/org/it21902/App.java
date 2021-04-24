package org.it21902;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class App {
	

	public static void main(String[] args) throws InterruptedException {
		Traveller.readTravellersFromJson();

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
//		new YoungTraveller("george", "Athens");
//		new MiddleTraveller("peter", "Berlin");
//		Thread.sleep(400);
//		new YoungTraveller("john", "Madrid");
//		new ElderTraveller("mary", "Jakarta");
//		new MiddleTraveller("john", "Tripoli");
//		Thread.sleep(400);
//		new ElderTraveller("anastasia", "California");
//		new YoungTraveller("panagiotis", "Vienna");
//		Thread.sleep(400);
//		new MiddleTraveller("panagiotis", "Vienna");
//		new ElderTraveller("panagiotis", "Vienna");
		

		System.out.println("All cities");
		
		Map<String, City> allCities = City.getAllCities();
		
		Iterator<Map.Entry<String, City>> it = allCities.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, City> entry =  it.next();
			
			System.out.println(entry.getKey() + " -- " + entry.getValue().getTermsVector());
		}
		System.out.println("Total cities: " + allCities.size());
		
		ArrayList<Traveller> allTravellers = Traveller.getAllTravellersList();

		for (Traveller t: allTravellers) {
			System.out.print(t.getFullName() + " -- ");
			ArrayList<Integer> termsVector = new ArrayList<>();
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
		Traveller.getAllTravellersList().forEach(i -> {System.out.println(i.getFullName());});
		System.out.println("\n");
		Traveller.printTravellersFromLastToFirst();
		Traveller.writeTravellersToJson();
	}
}