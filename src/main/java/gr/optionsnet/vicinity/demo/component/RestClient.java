package gr.optionsnet.vicinity.demo.component;

import gr.optionsnet.vicinity.demo.dto.sammy.Sammy;
import gr.optionsnet.vicinity.demo.dto.vicinity.adapter.Objects;

import gr.optionsnet.vicinity.demo.dto.vicinity.agent.Configuration;
import gr.optionsnet.vicinity.demo.dto.vicinity.api.Login;
import gr.optionsnet.vicinity.demo.dto.vicinity.api.Property;
import gr.optionsnet.vicinity.demo.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class RestClient {

    private Logger logger = LoggerFactory.getLogger(RestClient.class);

    //SaMMY context
    @Value("${sammy.protocol}")
    private String sammyProtocol;
    @Value("${sammy.host}")
    private String sammyHost;
    @Value("${sammy.port}")
    private Integer sammyPort;
    @Value("${sammy.path}")
    private String sammyPath;
    @Value("${sammy.user}")
    private String sammyUsername;
    @Value("${sammy.pass}")
    private String sammyPassword;
    @Value("${sammy.marinaId}")
    private String sammyMarinaId;

    //Vicinity Agent context
    @Value("${agent.protocol}")
    private String agentProtocol;
    @Value("${agent.host}")
    private String agentHost;
    @Value("${agent.port}")
    private Integer agentPort;
    @Value("${agent.path}")
    private String agentPath;
    @Value("${agent.user}")
    private String agentUsername;
    @Value("${agent.pass}")
    private String agentPassword;

    //Vicinity Adapter context
    @Value("${adapter.protocol}")
    private String adapterProtocol;
    @Value("${adapter.host}")
    private String adapterHost;
    @Value("${adapter.port}")
    private Integer adapterPort;
    @Value("${adapter.path}")
    private String adapterPath;
    @Value("${adapter.user}")
    private String adapterUsername;
    @Value("${adapter.pass}")
    private String adapterPassword;

    //Vicinity Gateway API
    @Value("${gateway.protocol}")
    private String gatewayProtocol;
    @Value("${gateway.host}")
    private String gatewayHost;
    @Value("${gateway.port}")
    private Integer gatewayPort;
    @Value("${gateway.path}")
    private String gatewayPath;
    @Value("${gateway.user}")
    private String gatewayUsername;
    @Value("${gateway.pass}")
    private String gatewayPassword;




    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public RestClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public ResponseEntity<Sammy> sammyData() throws RestClientException {
        String uri = Utils.getUri(sammyProtocol, sammyHost, sammyPort, sammyPath);
        logger.info("Requesting SaMMY original data from: " + uri);

        //initialize headers
        HttpHeaders headers = Utils.basicAuth(sammyUsername, sammyPassword);

        //add request parameters to headers
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("idm", sammyMarinaId);

        //set request with headers abd parameters
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);


        ResponseEntity<Sammy> entity = restTemplate.postForEntity(uri, request, Sammy.class);
        return entity;
    }


    public ResponseEntity<Configuration> agentConfiguration() throws RestClientException {
        String uri = Utils.getUri(agentProtocol, agentHost, agentPort, agentPath + "configuration");
        logger.info("Requesting agent configuration from: " + uri);

        ResponseEntity<Configuration> entity = restTemplate.getForEntity(uri, Configuration.class);
        return entity;
    }


    public ResponseEntity<Objects> adapterObjects() throws RestClientException {
        String uri = Utils.getUri(adapterProtocol, adapterHost, adapterPort, adapterPath + "objects");
        logger.info("Requesting adapter objects from: " + uri);

        ResponseEntity<Objects> entity = restTemplate.getForEntity(uri, Objects.class);
        return entity;
    }

    public ResponseEntity<Login> apiLogin(String oid, String pass) throws RestClientException {
        String uri = Utils.getUri(gatewayProtocol, gatewayHost, gatewayPort, gatewayPath + "objects/login");
        logger.info("Requesting gateway API object login: " + uri);

        //initialize headers
        HttpHeaders headers = Utils.basicAuth(oid, pass);

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Login> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Login.class);
        return response;
    }

    public ResponseEntity<Property> apiProperty(String oid, String pid, String user, String pass) throws RestClientException {
        String uri = Utils.getUri(gatewayProtocol, gatewayHost, gatewayPort, gatewayPath + "objects/" + oid + "/properties/" + pid);
        logger.info("Requesting gateway API object property: " + uri);

        //initialize headers
        HttpHeaders headers = Utils.basicAuth(user, pass);

        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<Property> response = restTemplate.exchange(uri, HttpMethod.GET, entity, Property.class);
        return response;
    }

}
