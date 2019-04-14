package gr.optionsnet.vicinity.demo.dto.vicinity.agent;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "adapter-infra-id",
        "infra-id",
        "password",
        "agent-id",
        "adapter-id",
        "oid",
        "adapter-oid"
})
public class Thing {

    @JsonProperty("adapter-infra-id")
    private String adapterInfraId;

    @JsonProperty("infra-id")
    private String infraId;

    @JsonProperty("password")
    private String password;

    @JsonProperty("agent-id")
    private String agentId;

    @JsonProperty("adapter-id")
    private String adapterId;

    @JsonProperty("oid")
    private String oid;

    @JsonProperty("adapter-oid")
    private String adapterOid;

}
