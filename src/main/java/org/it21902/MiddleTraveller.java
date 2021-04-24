package org.it21902;

public class MiddleTraveller extends Traveller{

	public MiddleTraveller()
		{super();}
	
	public MiddleTraveller(String name, String currentCity) {
		super(name, currentCity);
	}
	
	
	@Override
	public double calculateSimilarity(City c) {
		double p = 0.6; 
	
		return p * similarityTermsVector(c) + (1-p) * similarityGeodesicVector(c);
	}

	/**
	 * Similarity between the young traveller and the city
 	 * based on Cosine similarity .
 	 * @return The similarity between the city and the traveler.
	 */
	private double similarityTermsVector(City c) {
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
	
	@Override
	public int compareTo(Traveller trav) {
		if (this.getTimestamp() == trav.getTimestamp())
			return 0;
		else if (this.getTimestamp() > trav.getTimestamp())
			return 1;
		return -1;
	}
	
}
