package org.it21902;

public class YoungTraveller extends Traveller{

	
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

		sum = Math.sqrt(sum) + 1; /*denominator*/
		
		return 1 / sum;
	}

}

