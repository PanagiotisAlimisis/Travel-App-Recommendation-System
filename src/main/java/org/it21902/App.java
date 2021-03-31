package org.it21902;

import java.io.IOException;
import java.util.ArrayList;


//TODO: Write good output messages in order to show that everything works.
public class App {

	public static void main(String[] args) {
		/*Hardcoded data are used for the first part of the assignment. Not meaningful variable names are used in this class,  
		 * because none of them will exist in the final assignment and everything will be replaced from real user input.*/
		
		/*10 cities*/
        ArrayList<Double> a = new ArrayList();
        ArrayList<Double> b = new ArrayList();
        ArrayList<Double> c = new ArrayList();
        ArrayList<Double> d = new ArrayList();
        ArrayList<Double> e = new ArrayList();
        ArrayList<Double> f = new ArrayList();
        ArrayList<Double> g = new ArrayList();
        ArrayList<Double> h = new ArrayList();
        ArrayList<Double> i = new ArrayList();
        ArrayList<Double> j = new ArrayList();
        ArrayList<Double> k = new ArrayList();
        
        City c1 = new City();
        City c2 = new City();
        City c3 = new City();
        City c4 = new City();
        City c5 = new City();
        City c6 = new City();
        City c7 = new City();
        City c8 = new City();
        City c9 = new City();
        City c10 = new City();

        c1.setName("Athens");
        try {
        	c1.retrieveDataFromWikipedia("Athens", "gr", "8268b79c6118c5f5c576506e09e1318d");
        	c1.retrieveDataFromOpenWeatherMap("Athens", "gr", "8268b79c6118c5f5c576506e09e1318d");
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    	c2.setName("Thessaloniki");
        try {
			c2.retrieveDataFromWikipedia("Thessaloniki", "gr", "8268b79c6118c5f5c576506e09e1318d");
			c2.retrieveDataFromOpenWeatherMap("Thessaloniki", "gr", "8268b79c6118c5f5c576506e09e1318d");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        c3.setName("London");
        try {
			c3.retrieveDataFromWikipedia("London", "gb", "8268b79c6118c5f5c576506e09e1318d");
			c3.retrieveDataFromOpenWeatherMap("London", "gb", "8268b79c6118c5f5c576506e09e1318d");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        c4.setName("Rome");
        try {
			c4.retrieveDataFromWikipedia("Rome", "it", "8268b79c6118c5f5c576506e09e1318d");
			c4.retrieveDataFromOpenWeatherMap("Rome", "it", "8268b79c6118c5f5c576506e09e1318d");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        c5.setName("Tokyo");
        try {
			c5.retrieveDataFromWikipedia("Tokyo", "jp", "8268b79c6118c5f5c576506e09e1318d");
			c5.retrieveDataFromOpenWeatherMap("Tokyo", "jp", "8268b79c6118c5f5c576506e09e1318d");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
        c6.setName("New York");
        try {
			c6.retrieveDataFromWikipedia("New+York", "us", "8268b79c6118c5f5c576506e09e1318d");
			c6.retrieveDataFromOpenWeatherMap("New+York", "us", "8268b79c6118c5f5c576506e09e1318d");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
        c7.setName("Kyiv");
        try {
			c7.retrieveDataFromWikipedia("Kyiv", "ua", "8268b79c6118c5f5c576506e09e1318d");
			c7.retrieveDataFromOpenWeatherMap("Kyiv", "ua", "8268b79c6118c5f5c576506e09e1318d");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
        c8.setName("Paris");
        try {
			c8.retrieveDataFromWikipedia("Paris", "fr", "8268b79c6118c5f5c576506e09e1318d");
			c8.retrieveDataFromOpenWeatherMap("Paris", "fr", "8268b79c6118c5f5c576506e09e1318d");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        c9.setName("Vienna");
        try {
			c9.retrieveDataFromWikipedia("Vienna", "at", "8268b79c6118c5f5c576506e09e1318d");
			c9.retrieveDataFromOpenWeatherMap("Vienna", "at", "8268b79c6118c5f5c576506e09e1318d");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        c10.setName("Berlin");
        try {
			c10.retrieveDataFromWikipedia("Berlin", "de", "8268b79c6118c5f5c576506e09e1318d");
			c10.retrieveDataFromOpenWeatherMap("Berlin", "de", "8268b79c6118c5f5c576506e09e1318d");
        } catch (IOException e1) {
			e1.printStackTrace();
		}
        
        
        /*Add all the cities in an arraylist in order to print their terms vectors.*/
        
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(c1);
        cities.add(c2);
        cities.add(c3);
        cities.add(c4);
        cities.add(c5);
        cities.add(c6);
        cities.add(c7);
        cities.add(c8);
        cities.add(c9);
        cities.add(c10);
        for (City ci: cities) {
        	System.out.print(ci.getName() + ": [");
        	ArrayList<Integer> z = new ArrayList<Integer>();
        	z = ci.getTermsVector();
        	for (int o=0; o<10; ++o) {
        		System.out.print(z.get(o)+" ,");
        	}
        	System.out.println("]");
        }
        System.out.println();
        
        
        /*4 travellers*/
        Traveller t1 = new YoungTraveller();
        Traveller t2 = new YoungTraveller();
        Traveller t3 = new MiddleTraveller();
        Traveller t4 = new ElderTraveller();
       
        /*t1 is located in Berlin. Find the best city for him.*/
        ArrayList<Double> t1G = new ArrayList();
        ArrayList<Integer> t1T = new ArrayList();
        t1G.add(52.5244);
        t1G.add(13.4105);
        t1.setGeodesicVector(t1G);
        t1T.add(1);
        t1T.add(2);
        t1T.add(3);
        t1T.add(4);
        t1T.add(5);
        t1T.add(6);
        t1T.add(7);
        t1T.add(8);
        t1T.add(9);
        t1T.add(10);
        t1.setTermsVector(t1T);
        System.out.print("The best city for the traveller who is located in Berlin is: ");
        System.out.println(t1.compareCities(cities).getName());
        System.out.println();
        
        /*t2 is located in Rome. Find the 5 best cities for him.*/
        ArrayList<Double> t2G = new ArrayList();
        ArrayList<Integer> t2T = new ArrayList();
        t2G.add(34.257);
        t2G.add(-85.1647);
        t2.setGeodesicVector(t2G);
        t2T.add(10);
        t2T.add(3);
        t2T.add(4);
        t2T.add(6);
        t2T.add(5);
        t2T.add(6);
        t2T.add(1);
        t2T.add(0);
        t2T.add(10);
        t2T.add(10);
        t2.setTermsVector(t2T);
        ArrayList<City> re = new ArrayList<City>();
        re = t2.compareCities(cities,5);
        System.out.println("Best cities for traveller who is located in Rome: ");
        for (City ci: re) {
        	System.out.println("    "+ci.getName());
        }
        System.out.println();
        
        /*t3 is located in New York. Find the best city for him.*/
        ArrayList<Double> t3G = new ArrayList();
        ArrayList<Integer> t3T = new ArrayList();
        t3G.add(40.7143);
        t3G.add(-74.006);
        t3.setGeodesicVector(t3G);
        t3T.add(7);
        t3T.add(5);
        t3T.add(4);
        t3T.add(7);
        t3T.add(1);
        t3T.add(10);
        t3T.add(10);
        t3T.add(0);
        t3T.add(1);
        t3T.add(2);
        t3.setTermsVector(t3T);
        System.out.print("The best city for the traveller who is located in New York is: ");
        System.out.println(t3.compareCities(cities).getName());
        System.out.println();
        
        /*t4 is located in Athens. Find the 5 best cities for him.*/
        ArrayList<Double> t4G = new ArrayList();
        ArrayList<Integer> t4T = new ArrayList();
        t4G.add(23.7162);
        t4G.add(37.9795);
        t4.setGeodesicVector(t4G);
        t4T.add(0);
        t4T.add(2);
        t4T.add(10);
        t4T.add(10);
        t4T.add(9);
        t4T.add(10);
        t4T.add(10);
        t4T.add(4);
        t4T.add(5);
        t4T.add(6);
        t4.setTermsVector(t4T);
        ArrayList<City> ret = new ArrayList<City>();
        ret = t4.compareCities(cities,5);
        System.out.println("Best cities for traveller who is located in Athens: ");
        for (City ci: re) {
        	System.out.println("    "+ci.getName());
        }
        System.out.println();
	}
}
