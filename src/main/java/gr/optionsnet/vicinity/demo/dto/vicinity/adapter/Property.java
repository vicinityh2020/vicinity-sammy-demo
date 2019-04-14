package gr.optionsnet.vicinity.demo.dto.vicinity.adapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pid",
        "monitors",
        "read_link"
})
public class Property {

    @JsonProperty("pid")
    private String pid;

    @JsonProperty("monitors")
    private String monitors;

    @JsonProperty("read_link")
    private ReadLink readLink;

}
