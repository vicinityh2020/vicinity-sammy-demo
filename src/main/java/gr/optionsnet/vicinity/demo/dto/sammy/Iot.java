package gr.optionsnet.vicinity.demo.dto.sammy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "name",
        "value",
        "location"
})
public class Iot {

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private Float value;

    @JsonProperty("location")
    private Location location;

}
