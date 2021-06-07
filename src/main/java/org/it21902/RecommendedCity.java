package org.it21902;

public class RecommendedCity {
	String City;
	int rank;

	public RecommendedCity(String city, int rank) {
		super();
		City = city;
		this.rank = rank;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
}