package org.it21902;


public class ElderTraveller extends Traveller{

	public ElderTraveller() 
		{super();}
	
	public ElderTraveller(String name, String currentCity) {
		super(name, currentCity);
	}
	
	@Override
	public double calculateSimilarity(City c) {
		double p = 0.03; 
	
		return p * similarityTermsVector(c) + (1-p) * similarityGeodesicVector(c);
	}
	
	/**
	 * Similarity between the young traveller and the city
 	 * based on Jaccard distance.
 	 * @return The similarity between the city and the traveler.
	 */
	private double similarityTermsVector(City c) {
		/*Find the intersection of the two vectors.*/
		int intersection = 0;
		for (int i=0; i<super.getTermsVector().size(); ++i) {
			if (super.getTermsVector().get(i) > 0 && c.getTermsVector().get(i) > 0) {
				++intersection;
			}
		}
		
		return intersection/10.0;
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