package org.it21902;

public class YoungTraveller extends Traveller{

	
	@Override
	public double calculateSimilarity(City c) {
		double p = 0.5; /*p must be in [0, 1]. See how it gets its value.????????!!!!!*/  
		
		return p * similarityTermsVector(c) + (1-p) * similarityGeodesicVector(c);
	}
	
	
	/**
	 * Similarity between the young traveller and the city
 	 * based on Euclidean distance.
 	 * @return The similarity between the city and the traveler.
	 */
	public double similarityTermsVector(City c) {
		double sum = 1;
		
		for (int i=0; i<this.getTermsVector().size(); i++) {
			sum += (this.getTermsVector().get(i) - c.getTermsVector().get(i)) ^ 2;
		}
		
		return 1 / sum;
	}

}
