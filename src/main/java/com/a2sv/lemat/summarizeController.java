package com.a2sv.lemat;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/summarize")
public class summarizeController {

    private static final String API_URL = "https://api-inference.huggingface.co/models/facebook/bart-large-cnn";
    private static final String TOKEN = "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";

    @PostMapping("/query")
    public ResponseEntity<String> queryBart(@RequestBody String inputText) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(TOKEN);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>("{\"inputs\": \"" + inputText + "\"}", headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(API_URL, HttpMethod.POST, requestEntity, String.class);
        String responseBody = responseEntity.getBody();

        return ResponseEntity.ok(responseBody);
    }
}