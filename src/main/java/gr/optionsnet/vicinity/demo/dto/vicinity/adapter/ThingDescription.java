package gr.optionsnet.vicinity.demo.dto.vicinity.adapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "oid",
        "name",
        "type",
        "properties",
        "actions",
        "events"
})
public class ThingDescription {

    @JsonProperty("oid")
    private String oid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("properties")
    private List<Property> properties = null;

    @JsonProperty("actions")
    private List<Object> actions = null;

    @JsonProperty("events")
    private List<Object> events = null;

}
