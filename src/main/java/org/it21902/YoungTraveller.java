package org.it21902;

import java.util.ArrayList;

public class YoungTraveller extends Traveller{

	public YoungTraveller()
		{super();}
	
	public YoungTraveller(String name, int age, String currentCity, ArrayList<Integer> termsVector) {
		super(name,age,currentCity, termsVector);
	}
	
	
	@Override
	public double calculateSimilarity(City c) {
		double p = 0.97; 
		return p * similarityTermsVector(c) + (1-p) * similarityGeodesicVector(c);
	}
	
	
	/**
	 * Similarity between the young traveller and the city
 	 * based on Euclidean distance.
 	 * @return The similarity between the city and the traveler.
	 */
	private double similarityTermsVector(City c) {
		double sum = 0; 
		for (int i=0; i<super.getTermsVector().size(); i++) {
			sum += Math.pow(super.getTermsVector().get(i) - c.getTermsVector().get(i), 2);
		}
		sum = Math.sqrt(sum) + 1;
		return 1 / sum;
	}


	@Override
	public int compareTo(Traveller trav) {
		if (this.getTimestamp() == trav.getTimestamp())
			return 0;
		else if (this.getTimestamp() > trav.getTimestamp())
			return 1;
		return -1;
	}


}

