/*
 * Copyright © 2019 OptionsNet/ All rights reserved.
 *
 * This file is part of SaMMY@VICINITY integration demo.
 *
 * SaMMY@VICINITY integration demo is free software: you can redistribute it
 * and/or modify it under the terms of GNU GPL.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT ANY WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT, IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * See README file for the full disclaimer information and LICENSE file for full license information in the project root.
 */

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
