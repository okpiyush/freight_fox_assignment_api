package com.freightfoxmap.freightfoxassignment.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.lang.reflect.Array;

public class GetLocation {
    private String url ="https://api.distancematrix.ai/maps/api/geocode/json?";
    private int pincode;

    public GetLocation(int pincode) {
        this.pincode = pincode;
    }

    private String getUrl(){
        String curr_url =  url+"address="+pincode+"&key=1AfS433UnfE9GHe7cS4SFQBa2S31ktioxZZIqkXbk8dim5eyWjPyQ4fL4hL9KPG8";
        return curr_url;
    }

    public JsonNode getLatLong() {
        String url = getUrl();
        ObjectMapper mapper = new ObjectMapper();  // Initialize ObjectMapper
        try {
            WebClient webClient = WebClient.builder().build();
            JsonNode response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();

            System.out.println("---------------------------------------");
            System.out.println(response);
            System.out.println("---------------------------------------");

            if (response != null && response.has("result") && response.get("result").isArray() && response.get("result").size() > 0) {
                JsonNode location = response.get("result").get(0).get("geometry").get("location");
                double latitude = location.get("lat").asDouble();
                double longitude = location.get("lng").asDouble();

                ObjectNode latLongNode = mapper.createObjectNode();
                latLongNode.put("latitude", latitude);
                latLongNode.put("longitude", longitude);
                return latLongNode;
            } else {
                System.out.println("No location data found for the provided pincode.");
                return null;
            }
        } catch (WebClientResponseException.Forbidden e) {
            System.out.println("403 Forbidden - Access denied by the server.");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
