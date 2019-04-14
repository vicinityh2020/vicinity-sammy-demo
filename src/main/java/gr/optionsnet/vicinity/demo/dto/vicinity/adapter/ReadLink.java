package gr.optionsnet.vicinity.demo.dto.vicinity.adapter;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "href",
        "output"
})
public class ReadLink {

    @JsonProperty("href")
    private String href;

    @JsonProperty("output")
    private Output output;

}
