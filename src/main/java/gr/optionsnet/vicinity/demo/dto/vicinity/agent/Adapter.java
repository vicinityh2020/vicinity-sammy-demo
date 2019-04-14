package gr.optionsnet.vicinity.demo.dto.vicinity.agent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "adapter-id",
        "things",
        "subscribe-channels",
        "open-channels"
})
public class Adapter {

    @JsonProperty("adapter-id")
    private String adapterId;

    @JsonProperty("things")
    private List<Thing> things = null;

    @JsonProperty("subscribe-channels")
    private List<Object> subscribeChannels = null;

    @JsonProperty("open-channels")
    private List<Object> openChannels = null;

}
