package org.it21902;

import java.util.ArrayList;

public class City {
	
	private ArrayList<Integer> termsVector;
	private ArrayList<Double> geodesicVector;
	private String name;
	
	public City() {
		this.termsVector = new ArrayList();
		this.geodesicVector = new ArrayList();
		this.name = "";
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

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


}
