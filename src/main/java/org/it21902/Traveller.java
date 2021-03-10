package org.it21902;

public abstract class Traveller {

	private int[] termsVector;
	private double[] geodesicVector;
	
	public Traveller() {
		this.termsVector = new int[10];
		this.geodesicVector = new double[2];
	}

	public abstract double calculateSimilarity(City c);
	
	/*Getters & Setters*/
	/**
	 * @return int[]
	 */
	public int[] getTermsVector() {
		return termsVector;
	}

	public void setTermsVector(int[] termsVector) {
		this.termsVector = termsVector;
	}

	/**
	 * @return double[]
	 */
	public double[] getGeodesicVector() {
		return geodesicVector;
	}

	public void setGeodesicVector(double[] geodesicVector) {
		this.geodesicVector = geodesicVector;
	}
	
	
}
