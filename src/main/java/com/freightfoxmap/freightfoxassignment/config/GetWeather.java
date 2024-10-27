package com.freightfoxmap.freightfoxassignment.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

public class GetWeather {


    private String url="https://api.weatherbit.io/v2.0/current?";
    private double lat;
    private double lon;
    private String key ="db477959161847a99b97d79d8c4a256d";

    private String getUrl(){
        return url+"lat="+lat+"&lon="+lon+"&key="+key;
    }

    public GetWeather(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }


    public JsonNode getWeather(){
        String url = getUrl();
        ObjectMapper mapper = new ObjectMapper();  // Initialize ObjectMapper
        try {
            WebClient webClient = WebClient.builder().build();
            JsonNode response = webClient.get()
                    .uri(url)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .block();
            System.out.println(response);
            return response;
        } catch (WebClientResponseException.Forbidden e) {
            System.out.println("403 Forbidden - Access denied by the server.");
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
