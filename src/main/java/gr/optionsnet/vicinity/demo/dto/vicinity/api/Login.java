package gr.optionsnet.vicinity.demo.dto.vicinity.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "error",
        "statusCode",
        "statusCodeReason",
        "contentType",
        "message"
})
public class Login {

    @JsonProperty("error")
    public Boolean error;

    @JsonProperty("statusCode")
    public Integer statusCode;

    @JsonProperty("statusCodeReason")
    public String statusCodeReason;

    @JsonProperty("contentType")
    public String contentType;

    @JsonProperty("message")
    public List<Object> message = null;

}
