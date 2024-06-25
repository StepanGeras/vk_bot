package org.example.vk_bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Request {

    @JsonProperty("type")
    private String type;

    @JsonProperty("object")
    private MessageObj object;

    @JsonProperty("group_id")
    private int groupId;

    @JsonProperty("secret")
    private String secret;
}
