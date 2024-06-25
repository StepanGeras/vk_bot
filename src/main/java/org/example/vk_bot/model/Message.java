package org.example.vk_bot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Message {

    @JsonProperty("date")
    private int date;

    @JsonProperty("from_id")
    private int fromId;

    @JsonProperty("id")
    private int id;

    @JsonProperty("out")
    private int out;

    @JsonProperty("peer_id")
    private int peerId;

    @JsonProperty("text")
    private String text;
}
