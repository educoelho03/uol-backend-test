package br.com.testbackend.javaspring.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.core.env.Environment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

@Service
@Getter
// TODO: ESTUDAR ESSA CLASSE.
public class CodinameService {

    private RestTemplate restTemplate; // faz a comunicação com o mundo externo
    private Environment env;

    public CodinameService(RestTemplate restTemplate, Environment  environment) {
        this.restTemplate = restTemplate;
        this.env = environment;
    }

    private final List<String> avengersCodinameList = new ArrayList<>();
    private final List<String> justiceLeagueCodinameList = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();


    // Esta indo no NODE do Json e pegando o codiname e salvando esse codiname dentro da lista criada acima
    @PostConstruct // Vai subir junto com a aplicação
    public void loadJsonData(){
        try {
            // Vai no aplication properties e pegar a url do avengers;
            String codinameResponse = restTemplate.getForObject(env.getProperty("avengers"), String.class);
            JsonNode jsonNode = objectMapper.readTree(codinameResponse);

            ArrayNode avengers = (ArrayNode) jsonNode.get("vingadores");

            for(JsonNode item: avengers){
                avengersCodinameList.add(item.get("codiname").asText());
            }


        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @PostConstruct
    public void loadXmlData(){

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(env.getProperty("justice.league"));

            NodeList codinameList = document.getElementsByTagName("codiname");

            for(int i = 1; i < codinameList.getLength(); i++){
                Element codinameElement = (Element) codinameList.item(i);
                String codiname = codinameElement.getTextContent();
                justiceLeagueCodinameList.add(codiname);
            }


        } catch (Exception e){
            e.printStackTrace();
        }

    }


}
