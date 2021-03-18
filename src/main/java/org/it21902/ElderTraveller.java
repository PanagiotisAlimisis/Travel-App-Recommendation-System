package org.it21902;


public class ElderTraveller extends Traveller{

	@Override
	public double calculateSimilarity(City c) {
		double p = 0.2; 
	
		return p * similarityTermsVector(c) + (1-p) * similarityGeodesicVector(c);
	}
	
	/**
	 * Similarity between the young traveller and the city
 	 * based on Jaccard distance.
 	 * @return The similarity between the city and the traveler.
	 */
	private double similarityTermsVector(City c) {
		/*Find the section of the two vectors.*/
		int intersection = 0;
		for (int i=0; i<super.getTermsVector().size(); ++i) {
			if (super.getTermsVector().get(i) > 0 && c.getTermsVector().get(i) > 0) {
				++intersection;
			}
		}
		
		return intersection/10.0;
	}
}
