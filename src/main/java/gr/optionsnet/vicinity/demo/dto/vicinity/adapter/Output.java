package gr.optionsnet.vicinity.demo.dto.vicinity.adapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "field",
        "type"
})
public class Output {

    @JsonProperty("field")
    private List<Field> field = null;

    @JsonProperty("type")
    private String type;

}
