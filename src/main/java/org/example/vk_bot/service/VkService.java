package org.example.vk_bot.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Random;

@Service
public class VkService {

    @Value("${vk.access.token}")
    private String accessToken;

    public void handleNewMessage(String message, int peerId) {
        sendMessage(peerId, "Вы сказали: " + message);
    }

    public void sendMessage(int peerId, String message) {
        String url = "https://api.vk.com/method/messages.send";

        int randomId = new Random().nextInt(Integer.MAX_VALUE);

        URI uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("peer_id", peerId)
                .queryParam("message", message)
                .queryParam("v", "5.131")
                .queryParam("access_token", accessToken)
                .queryParam("random_id", randomId)
                .build().toUri();

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getForObject(uri, String.class);

    }

}
