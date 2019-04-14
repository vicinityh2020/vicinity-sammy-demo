package gr.optionsnet.vicinity.demo.dto.vicinity.agent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "adapters",
        "things-by-adapter",
        "things-by-oid",
        "agents"
})
public class Configuration {

    @JsonProperty("adapters")
    private List<Adapter> adapters = null;

    @JsonProperty("things-by-adapter")
    private List<ThingsByAdapter> thingsByAdapter = null;

    @JsonProperty("things-by-oid")
    private List<Thing> thingsByOid = null;

    @JsonProperty("agents")
    private List<Agent> agents = null;

}
