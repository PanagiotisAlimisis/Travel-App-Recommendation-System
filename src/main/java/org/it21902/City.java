package org.it21902;

public class City {
	
	private int[] termsVector;
	private double[] geodesicVector;
	
	public City() {
		this.termsVector = new int[10];
		this.geodesicVector = new double[2];
	}

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
