package org.it21902;

import java.util.ArrayList;

public abstract class Traveller {
	
	/*Largest distance between two cities on Earth that a trip can be achieved.*/
	private static final int MAX_DISTANCE = 15000;
	
	private ArrayList<Integer> termsVector;
	private ArrayList<Double> geodesicVector;
	
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
	 * @param N
	 * @return
	 */
	private static double log2(double N) 
    { 
        // calculate log2 N indirectly 
        // using log() method 
        return (Math.log(N) / Math.log(2)); 
    } 

	
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
