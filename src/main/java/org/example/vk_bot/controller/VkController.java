package org.example.vk_bot.controller;

import org.example.vk_bot.model.Request;
import org.example.vk_bot.service.VkService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vk")
public class VkController {

    @Value("${vk.confirmation.code}")
    private String confirmationCode;

    @Value("${vk.secret.key}")
    private String secretKey;

    private final VkService vkService;

    public VkController(VkService vkService) {
        this.vkService = vkService;
    }

    @PostMapping("/callback")
    public ResponseEntity<String> handleCallback(@RequestBody Request request) {
        if (!secretKey.equals(request.getSecret())) {
            return ResponseEntity.status(403).body("Forbidden");
        }

        return switch (request.getType()) {
            case "confirmation" -> ResponseEntity.ok(confirmationCode);
            case "message_new" -> {
                vkService.handleNewMessage(request.getObject().getMessage().getText(), request.getObject().getMessage().getPeerId());
                yield ResponseEntity.ok("ok");
            }
            default -> ResponseEntity.ok("ok");
        };
    }

}
