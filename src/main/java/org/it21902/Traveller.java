package org.it21902;

import java.util.ArrayList;

public abstract class Traveller {

	private ArrayList<Integer> termsVector;
	private ArrayList<Double> geodesicVector;
	
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
	
	
}
