package org.it21902;

public class MiddleTraveller extends Traveller{

	@Override
	public double calculateSimilarity(City c) {
		double p = 0.5; /*p must be in [0, 1]. See how it gets its value.????????!!!!!*/  
	
		return p * similarityTermsVector(c) + (1-p) * similarityGeodesicVector(c);
	}

	/**
	 * Similarity between the young traveller and the city
 	 * based on Cosine similarity .
 	 * @return The similarity between the city and the traveler.
	 */
	public double similarityTermsVector(City c) {
		/*Multiply the two vectors in order to find the numerator.*/
		double numerator = 0;
		for (int i=0; i<this.getTermsVector().size(); ++i) {
			numerator += this.getTermsVector().get(i) * c.getTermsVector().get(i);
		}
		
		/*Find the length of each vector for the denominator.*/
		double user = 0;
		for (int i=0; i<this.getTermsVector().size(); ++i) {
			user += this.getTermsVector().get(i)^2;
		}
		user = Math.sqrt(user);
		
		double city = 0;
		for (int i=0; i<c.getTermsVector().size(); ++i) {
			city += c.getTermsVector().get(i)^2;
		}
		city = Math.sqrt(city);
		
		
		return user*city != 0 ? (numerator / (user * city)): 0;
	}	
	
}
