package org.it21902;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

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
		
		ArrayList<Traveller> t = Traveller.getAllTravellersList();

		Map<String, City> cities = City.getAllCities();
		
		Random r = new Random();
		
//		for (int i=0;i<100;++i) {
//			Traveller tr=null;
//			if (i%2==0) {
//				tr=new YoungTraveller("Traveller"+String.valueOf(i), r.nextInt(25), cities.keySet().toArray()[r.nextInt(cities.size())].toString(), new ArrayList<>(Arrays.asList(r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11))));
//			}
//			else if (i%3==0) {
//				tr=new MiddleTraveller("Traveller"+String.valueOf(i), r.nextInt(55), cities.keySet().toArray()[r.nextInt(cities.size())].toString(), new ArrayList<>(Arrays.asList(r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11))));
//			}
//			else if (i%5==0) {
//				tr=new ElderTraveller("Traveller"+String.valueOf(i), r.nextInt(110), cities.keySet().toArray()[r.nextInt(cities.size())].toString(), new ArrayList<>(Arrays.asList(r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11))));
//			}
//			else {
//				tr=new MiddleTraveller("Traveller"+String.valueOf(i), r.nextInt(55), cities.keySet().toArray()[r.nextInt(cities.size())].toString(), new ArrayList<>(Arrays.asList(r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11))));	
//			}
//		
//			tr.setRecommendedCity(cities.keySet().toArray()[r.nextInt(cities.size())].toString());
//		}	
		
		ArrayList<Integer> terms = new ArrayList<>(Arrays.asList(r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),
				r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11),r.nextInt(11)));
		Traveller candidateTraveller = new YoungTraveller("Traveller_Candidate", 22, "Thessaloniki", terms);  		
		ArrayList<Integer> ter = candidateTraveller.getTermsVector();
	
		candidateTraveller.bestCityCollaborativeRecommendation();
		
	}
}