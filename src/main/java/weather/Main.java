package weather;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "temp",
    "feels_like",
    "temp_min",
    "temp_max",
    "pressure",
    "humidity"
})
public class Main {
	@JsonProperty("temp")
	private Double temp;
	@JsonProperty("feels_like")
	private Double feels_like;
	@JsonProperty("temp_min")
	private Double temp_min;
	@JsonProperty("temp_max")
	private Double temp_max;
	@JsonProperty("pressure")
	private Double pressure;
	@JsonProperty("humidity")
	private Double humidity;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	public Main()
		{}

	public Main(Double temp, Double feels_like, Double temp_min, Double temp_max, Double pressure, Double humidity) {
		super();
		this.temp = temp;
		this.feels_like = feels_like;
		this.temp_min = temp_min;
		this.temp_max = temp_max;
		this.pressure = pressure;
		this.humidity = humidity;
	}

	@JsonProperty("temp")
	public Double getTemp() {
		return temp;
	}

	@JsonProperty("temp")
	public void setTemp(Double temp) {
		this.temp = temp;
	}

	@JsonProperty("feels_like")
	public Double getFeels_like() {
		return feels_like;
	}

	@JsonProperty("feels_like")
	public void setFeels_like(Double feels_like) {
		this.feels_like = feels_like;
	}

	@JsonProperty("temp_min")
	public Double getTemp_min() {
		return temp_min;
	}

	@JsonProperty("temp_min")
	public void setTemp_min(Double temp_min) {
		this.temp_min = temp_min;
	}

	@JsonProperty("temp_max")
	public Double getTemp_max() {
		return temp_max;
	}

	@JsonProperty("temp_max")
	public void setTemp_max(Double temp_max) {
		this.temp_max = temp_max;
	}

	@JsonProperty("pressure")
	public Double getPressure() {
		return pressure;
	}

	@JsonProperty("pressure")
	public void setPressure(Double pressure) {
		this.pressure = pressure;
	}

	@JsonProperty("humidity")
	public Double getHumidity() {
		return humidity;
	}

	@JsonProperty("humidity")
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
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
