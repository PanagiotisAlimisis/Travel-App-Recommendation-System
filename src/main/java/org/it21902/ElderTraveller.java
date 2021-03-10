package org.it21902;

import java.util.HashMap;
import java.util.Map;

public class ElderTraveller extends Traveller{

	@Override
	public double calculateSimilarity(City c) {
		double p = 0.5; /*p must be in [0, 1]. Find out how it gets its value.????????!!!!!*/  
	
		return p * similarityTermsVector(c) + (1-p) * similarityGeodesicVector();
	}


	private double similarityGeodesicVector() {
		return 0;		
	}
	
	/**
	 * Similarity between the young traveller and the city
 	 * based on Jaccard distance.
 	 * @return The similarity between the city and the traveler.
	 */
	private double similarityTermsVector(City c) {
		/*Find the section of the two vectors.*/
		int intersection = 0;
		for (int i=0; i<this.getTermsVector().size(); ++i) {
			if (this.getTermsVector().get(i) == 0)
				continue;
			if (c.getTermsVector().contains(this.getTermsVector().get(i))) {
				++intersection;
			}
		}
		
		/*Find the union of the two vectors.*/
		Map<Integer, Integer> m = new HashMap();
		for (int i=0; i<this.getTermsVector().size(); ++i) {
			if (this.getTermsVector().get(i) == 0)
				continue;
			m.put(this.getTermsVector().get(i), i);
		}
		for (int i=0; i<c.getTermsVector().size(); ++i) {
			if (c.getTermsVector().get(i) == 0)
				continue;
			m.put(c.getTermsVector().get(i), i);
		}
		int union = m.size();
		
		return intersection / union;
	}
}
