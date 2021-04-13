package weather;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"1h"
})
public class Rain {
	@JsonProperty("1h")
	private double h1;
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	
	public Rain()
		{}

	public Rain(Double h1) {
		this.h1 = h1;
	}


	@JsonProperty("1h")
	public double getH1() {
		return h1;
	}

	@JsonProperty("1h")
	public void setH1(double h1) {
		this.h1 = h1;
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
