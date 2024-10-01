package com.codgym.user_management.loging;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Component
public class DiscordLogger {
    @Value("${discord.webhook.url}")
    private String webhookUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendLog(String message) {
        Map<String, String> json = new HashMap<>();
        json.put("content", message);
        restTemplate.postForEntity(webhookUrl, json, String.class);
    }
}
