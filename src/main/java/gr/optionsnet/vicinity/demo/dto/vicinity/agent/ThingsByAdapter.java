package gr.optionsnet.vicinity.demo.dto.vicinity.agent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "for-adapter",
        "adapter-things"
})
public class ThingsByAdapter {

    @JsonProperty("for-adapter")
    private String forAdapter;

    @JsonProperty("adapter-things")
    private List<Thing> adapterThings = null;

}
