package org.it21902;

public class MiddleTraveller extends Traveller{

	@Override
	public double calculateSimilarity(City c) {
		double p = 0.5; 
	
		return p * similarityTermsVector(c) + (1-p) * similarityGeodesicVector(c);
	}

	/**
	 * Similarity between the young traveller and the city
 	 * based on Cosine similarity .
 	 * @return The similarity between the city and the traveler.
	 */
	private double similarityTermsVector(City c) {
		/*Multiply the two vectors in order to find the numerator.*/
		double numerator = 0;
		double user = 0;
		double city = 0;
		
		for (int i=0; i<super.getTermsVector().size(); ++i) {
			numerator += super.getTermsVector().get(i) * c.getTermsVector().get(i);
			user += Math.pow(super.getTermsVector().get(i), 2);
			city += Math.pow(c.getTermsVector().get(i), 2);
		}
		
		user = Math.sqrt(user);
		city = Math.sqrt(city);
		
		return user*city != 0 ? (numerator / (user * city)): -1;
	}	
	
}
