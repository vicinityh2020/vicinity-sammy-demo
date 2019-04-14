package gr.optionsnet.vicinity.demo.dto.vicinity.adapter;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;


@Data
@JsonInclude(JsonInclude.Include.ALWAYS)
@JsonPropertyOrder({
        "units",
        "type"
})
public class Schema {

    @JsonProperty("units")
    private String units = "";

    @JsonProperty("type")
    private String type;

}
