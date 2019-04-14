package gr.optionsnet.vicinity.demo.dto.vicinity.agent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "agent-id",
        "adapters"
})
public class Agent {

    @JsonProperty("agent-id")
    private String agentId;

    @JsonProperty("adapters")
    private List<Adapter> adapters = null;

}
