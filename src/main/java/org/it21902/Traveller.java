package org.it21902;

import java.util.ArrayList;

public abstract class Traveller {
	
	/*Largest distance between two cities on Earth that a trip can be achieved.*/
	private static final int MAX_DISTANCE = 20000;
	
	private ArrayList<Integer> termsVector;
	private ArrayList<Double> geodesicVector;
	
	
	private ArrayList<Traveller> allTravelers;/*Check this out a little bit.*/
	
	private int age;
	
	public Traveller() {
		this.termsVector = new ArrayList();
		this.geodesicVector = new ArrayList();
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
	public City compareCities(ArrayList<City> cities) {
		double max = -100;
		int bestCity = -1;
		for (int i = 0; i<cities.size(); ++i) {
			double sim = this.calculateSimilarity(cities.get(i));
			if (sim > max) {
				max = sim;
				bestCity = i;
			}
		}
		
		return cities.get(bestCity);
	}
	
	/**
	 * Calculates the similarity of all the known cities with this traveller's options and return the n best cities for them.
	 *
	 * @param cities
	 * @param n in [2, 5]
	 * @return
	 */
	public ArrayList<City> compareCities(ArrayList<City> cities, int n) {
		if (n < 2 || n > 5) {
			return null;
		}
		
		int worstPos = -1;
		double minSimilarity = -100; /*The worst similarity of the n best similarities.*/
		
		ArrayList<City> bestCities = new ArrayList();
		ArrayList<Double> bestSimilarities = new ArrayList();

		/*Fill with n cites the bestCities verctor and find the minSimilarity among them.*/
		for (int i=0; i<n; ++i) {
			double sim = this.calculateSimilarity(cities.get(i));
			
			if (sim > minSimilarity) {
				minSimilarity = sim;
				worstPos = i;
			}			
			bestCities.add(cities.get(i));
			bestSimilarities.add(sim);
		}
		
		/*Check the remaining cities and find the best n.*/
		for (int i=n; i<cities.size(); ++i) {
			double sim = this.calculateSimilarity(cities.get(i));
			
			if (sim > minSimilarity) {
				bestCities.remove(worstPos);
				bestSimilarities.remove(worstPos);
				bestCities.add(cities.get(i));
				bestSimilarities.add(sim);
		
				/*Finds the worst similarity of the best cities*/
				minSimilarity = 100;
				for (int j=0; j<bestSimilarities.size(); ++j) {
					if (bestSimilarities.get(j) < minSimilarity) {
						minSimilarity = bestSimilarities.get(j);
						worstPos = j;
					}
				}
			}
		}
		
		return bestCities;
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

	protected double similarityGeodesicVector(City c) {
		return log2(2 / (2 - calculateDistance(c) / MAX_DISTANCE));
	}
	
	/**
	 * Calculates log with base 2.
	 * @pa)ram N
	 * @return
	 */
	private static double log2(double N) 
    { 
        // calculate log2 N indirectly 
        // using log() method 
        return (Math.log(N) / Math.log(2)); 
    } 

	
	/**
	 * Distance between two points in kilometers.
	 * @param c
	 * @return
	 */
	private double calculateDistance(City c) {
		double latT = this.getGeodesicVector().get(0), latC = c.getGeodesicVector().get(0);
		double lonT = this.getGeodesicVector().get(1), lonC = c.getGeodesicVector().get(1);
		
		/*Traveller is located in the same city that they want to travel.*/
		if ((latT == latC) && (lonT == lonC)) {
			return 0;
		}
		
		double theta = lonT - lonC;
		double dist = Math.sin(Math.toRadians(latT)) * Math.sin(Math.toRadians(latC)) + Math.cos(Math.toRadians(latT)) * Math.cos(Math.toRadians(latC)) * Math.cos(theta);
		dist = Math.acos(dist);
		dist = Math.toDegrees(dist);
		dist = dist * 60 * 1.1515;
		
		/*Return distance in kilometers*/
		return dist*1.609344;
	}
}
