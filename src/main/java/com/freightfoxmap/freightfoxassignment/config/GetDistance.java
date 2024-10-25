package com.freightfoxmap.freightfoxassignment.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.reactive.function.client.WebClient;

public class GetDistance {
    private String url = "https://api.distancematrix.ai/maps/api/distancematrix/json?";
    private String xlat;
    private String xlon;
    private String ylat;
    private String ylon;

    private String key;

    private String getUrl() {
        if (this.xlat == null || this.xlon == null || this.ylat == null || this.ylon == null){
            System.out.println("The following request was denied due to lack of data");
            return null;
        }
        String curr_url = url + "origins="+xlat+", "+xlon+"&"+"destinations="+ylat+", "+ylon+"&"+"key="+key;
        return curr_url;
    }

    public GetDistance(String xlat, String xlon, String ylat, String ylon, String key) {
        this.xlat = xlat;
        this.xlon = xlon;
        this.ylat = ylat;
        this.ylon = ylon;
        this.key = key;
    }

    public JsonNode getDistance() {
        String url = getUrl();
        ObjectMapper mapper = new ObjectMapper();

        if (url == null || url.isEmpty()) {
            // Create a JSON error response for a missing URL
            ObjectNode errorResponse = mapper.createObjectNode();
            errorResponse.put("error", "Error due to missing URL");
            return errorResponse;
        }

        try {
            WebClient.Builder builder = WebClient.builder();
            return builder.build()
                    .get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();
        } catch (Exception e) {
            e.printStackTrace();
            // Create a JSON error response for exceptions
            ObjectNode errorResponse = mapper.createObjectNode();
            errorResponse.put("error", "Exception found in the code, please check it again");
            errorResponse.put("message", e.getMessage());
            return errorResponse;
        }
    }
}
