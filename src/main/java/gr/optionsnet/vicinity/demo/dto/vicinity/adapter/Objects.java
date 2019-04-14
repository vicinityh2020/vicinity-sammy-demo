package gr.optionsnet.vicinity.demo.dto.vicinity.adapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "adapter-id",
        "thing-descriptions"
})
public class Objects {

    @JsonProperty("adapter-id")
    private String adapterId;

    @JsonProperty("thing-descriptions")
    private List<ThingDescription> thingDescriptions = null;

}
