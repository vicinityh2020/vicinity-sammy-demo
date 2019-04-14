package gr.optionsnet.vicinity.demo.dto.sammy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "weatherTime",
        "waterTime",
        "lastonline",
        "onlineMinutes",
        "offlineMinutes"
})
public class Attr {

    @JsonProperty("weatherTime")
    private Integer weatherTime;

    @JsonProperty("waterTime")
    private Integer waterTime;

    @JsonProperty("lastonline")
    private Integer lastonline;

    @JsonProperty("onlineMinutes")
    private Integer onlineMinutes;

    @JsonProperty("offlineMinutes")
    private Integer offlineMinutes;
}
