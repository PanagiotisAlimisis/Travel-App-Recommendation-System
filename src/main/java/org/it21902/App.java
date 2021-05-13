package org.it21902;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import db.connection.OracleDbConnection;

public class App {
	

	public static void main(String[] args) throws InterruptedException {
		Connection connection=null;
		try {
			connection = OracleDbConnection.getInstance().getOracleConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		City.readCitiesFromDb(connection);
		Traveller.readTravellersFromJson();

//		new YoungTraveller("John Smith", "Leipzig");

		
		Map<String, City> allCities = City.getAllCities();
//		
		System.out.print("The winner of the free ticket for Athens is : ");
		System.out.println(Traveller.giveFreeTicket(allCities.get("Athens")).getFullName());
		
//		Iterator<Map.Entry<String, City>> it = allCities.entrySet().iterator();
//		while(it.hasNext()) {
//			Map.Entry<String, City> entry =  it.next();
//			
//			System.out.println(entry.getKey() + " -- " + entry.getValue().getTermsVector());
//		}
		
		System.out.println("Total cities: " + allCities.size());
		
		ArrayList<Traveller> allTravellers = Traveller.getAllTravellersList();

		for (Traveller t: allTravellers) {
			ArrayList<Integer> termsVector = new ArrayList<>();
			for (int i=0; i<10; ++i) {
				Random random = new Random();
				termsVector.add(random.nextInt(11));
			}
			t.setTermsVector(termsVector);
			System.out.println(t.getFullName() + " " + termsVector + " -- " + t.getCurrentCity() + " " + t.compareCities(allCities).getNameCity());;
		
			System.out.println("3 best recommended cities for traveller based in: " + t.getCurrentCity());
			try {
				t.compareCities(City.getAllCities(), 3).forEach(i -> {i.printCityName();});
			} catch (IllegalArgumentException e) {
				System.err.println("Illegal Argument Exception");
			}
		}

		Traveller.printTravellersFromLastToFirst();

		Traveller.writeTravellersToJson();
		City.writeCitiesToDatabase(connection);
	}
}