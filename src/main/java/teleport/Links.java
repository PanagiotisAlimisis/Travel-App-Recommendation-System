package teleport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"curies",
"self"
})
@Generated("jsonschema2pojo")
public class Links {

@JsonProperty("curies")
private List<Cury> curies = null;
@JsonProperty("self")
private Self self;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("curies")
public List<Cury> getCuries() {
return curies;
}

@JsonProperty("curies")
public void setCuries(List<Cury> curies) {
this.curies = curies;
}

@JsonProperty("self")
public Self getSelf() {
return self;
}

@JsonProperty("self")
public void setSelf(Self self) {
this.self = self;
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
