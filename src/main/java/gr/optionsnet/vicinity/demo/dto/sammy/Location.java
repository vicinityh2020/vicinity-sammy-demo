package gr.optionsnet.vicinity.demo.dto.sammy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "lat",
        "lng",
        "alt",
        "name",
        "description"
})
public class Location {

    @JsonProperty("lat")
    private Float lat;

    @JsonProperty("lng")
    private Float lng;

    @JsonProperty("alt")
    private Float alt;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private List<String> description = null;

}
