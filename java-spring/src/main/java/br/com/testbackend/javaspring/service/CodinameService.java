package br.com.testbackend.javaspring.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import reactor.core.publisher.Mono;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
// TODO: ESTUDAR ESSA CLASSE.
public class CodinameService {

    private Environment env;
    private WebClient webClient;


    // Construtor usando WebClient
    public CodinameService(Environment environment, ObjectMapper objectMapper) {
        this.env = environment;
        this.objectMapper = objectMapper;
        this.webClient = WebClient.builder()
                .baseUrl(env.getProperty("avengers")) // Preciso dessa linha?
                .build();
    }

    private final List<String> avengersCodinameList = new ArrayList<>();
    private final List<String> justiceLeagueCodinameList = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();


    @PostConstruct
    public void loadJsonDataWebClient(){
        try {
            Mono<String> responseMono = webClient.get() // Faz uma solictação http no webClient
                    .retrieve() // usada para finalizar a execução e começar a construção
                    .bodyToMono(String.class); // converter o tipo de resposta em um Objeto Mono,

            String codinameResponseWebClient = responseMono.block(); // Bloqueia ate q a resposta seja recebida

            JsonNode jsonNode = objectMapper.readTree(codinameResponseWebClient);
            ArrayNode avengersWebClient = (ArrayNode) jsonNode.get("vingadores");

            for(JsonNode item: avengersWebClient){
                avengersCodinameList.add(item.get("codinome").asText());
            }


        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @PostConstruct
    public void loadXmlDataWebClient(){
        try {
            Mono<String> responseMono = webClient.get()
                    .retrieve()
                    .bodyToMono(String.class);

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(env.getProperty("justice_league"));

            NodeList codinomeList = document.getElementsByTagName("codinome");

            for(int i = 1; i < codinomeList.getLength(); i++){
                Element codinomeElement = (Element) codinomeList.item(i);
                String codinome = codinomeElement.getTextContent();
                justiceLeagueCodinameList.add(codinome);
            }


        } catch (Exception e){
            e.printStackTrace();
        }
    }


}
