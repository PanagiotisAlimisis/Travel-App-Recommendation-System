package weather;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"lon", 
	"lat"
})

public class Coord {
	@JsonProperty("lon")
	private Double lon;
	@JsonProperty("lat")
	private Double lat;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public Coord()
		{}
	
	public Coord(Double lon, Double lat) {
		super();
		this.lon = lon;
		this.lat = lat;
	}
	
	@JsonProperty("lon")
	public Double getLon() {
		return lon;
	}
	
	@JsonProperty("lon")
	public void setLon() {
		this.lon = lon;
	}
	
	@JsonProperty("lat")
	public Double getLat() {
		return lat;
	}
	
	@JsonProperty("lat")
	public void setLat() {
		this.lat = lat;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}
	
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
}
