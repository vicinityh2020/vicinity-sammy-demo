package gr.optionsnet.vicinity.demo.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

public class Utils {


    public static String getUri(String protocol, String host, Integer port, String path) {
        return protocol + "://" + host + ":" + port + (path.substring(0,1).equals("/") ? path : "/" + path);
    }

    public static HttpHeaders basicAuth(String username, String password) {

        //initialize headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        //set basic authentication
        String plainCreds = username + ":" + password;
        String base64Creds = new String(Base64.encodeBase64(plainCreds.getBytes()));
        headers.add("Authorization", "Basic " + base64Creds);

        return headers;
    }

}
