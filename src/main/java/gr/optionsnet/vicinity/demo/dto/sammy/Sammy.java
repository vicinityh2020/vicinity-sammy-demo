package gr.optionsnet.vicinity.demo.dto.sammy;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "iot",
        "attr"
})
public class Sammy {

    @JsonProperty("iot")
    private List<Iot> iot = null;

    @JsonProperty("attr")
    private Attr attr;
}
