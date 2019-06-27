/*
 * Copyright © 2019 OptionsNet/ All rights reserved.
 *
 * This file is part of SaMMY@VICINITY integration demo.
 *
 * SaMMY@VICINITY integration demo is free software: you can redistribute it
 * and/or modify it under the terms of GNU GPL.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT ANY WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT, IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * See README file for the full disclaimer information and LICENSE file for full license information in the project root.
 */

package gr.optionsnet.vicinity.demo.controller;

import gr.optionsnet.vicinity.demo.component.RestClient;
import gr.optionsnet.vicinity.demo.config.ContextURL;
import gr.optionsnet.vicinity.demo.domain.Item;
import gr.optionsnet.vicinity.demo.dto.vicinity.api.Login;
import gr.optionsnet.vicinity.demo.dto.vicinity.api.Property;
import gr.optionsnet.vicinity.demo.repository.ItemRepository;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(value = ContextURL.REST_ROOT, produces = {APPLICATION_JSON_UTF8_VALUE})
public class ApiController implements ContextURL {

    private Logger logger = LoggerFactory.getLogger(ApiController.class);

    @Value("${log.refreshInterval}")
    private Integer refreshInterval;
    @Value("${log.lines}")
    private Integer lines;
    @Value("${log.demo}")
    private String demoLog;
    @Value("${log.agent}")
    private String agentLog;
    @Value("${log.adapter}")
    private String adapterLog;
    @Value("${log.gateway}")
    private String gatewayLog;

    @Autowired
    private RestClient restClient;
    @Autowired
    private ItemRepository itemRepository;



    @RequestMapping(value = ContextURL.REST_SAMMY, method = RequestMethod.GET)
    public @ResponseBody ResponseEntity sammy() {
        return new ResponseEntity<>(restClient.sammyData().getBody(), HttpStatus.OK);
    }

    @RequestMapping(value = ContextURL.REST_ITEMS, method = RequestMethod.GET)
    public @ResponseBody ResponseEntity items() {
        return new ResponseEntity<>(itemRepository.findAll(new Sort(Sort.Direction.ASC, "pid")), HttpStatus.OK);
    }

    @RequestMapping(value = ContextURL.REST_ITEM_LOGIN, method = RequestMethod.GET)
    public @ResponseBody ResponseEntity itemLogin(@RequestParam(value = "oid", required = true) String oid) {
        Item item = itemRepository.findByOid(oid).get();

        ResponseEntity<Login> response = restClient.apiLogin(oid, item.getPassword());
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @RequestMapping(value = ContextURL.REST_ITEM_QUERY, method = RequestMethod.GET)
    public @ResponseBody ResponseEntity itemQuery(@RequestParam(value = "oid", required = true) String oid) {
        Item destination = itemRepository.findByOid(oid).get();
        Item source = itemRepository.findByOidNot(oid).get(0);

        ResponseEntity<Property> response = restClient.apiProperty(oid, destination.getPid(), source.getOid(), source.getPassword());
        return new ResponseEntity<>(response.getBody(), response.getStatusCode());
    }

    @RequestMapping(value = ContextURL.REST_LOG, method = RequestMethod.GET)
    public @ResponseBody List<String> log(@RequestParam(value = "type", required = true) String type) throws IOException {
        List<String> list = new ArrayList<>();

        HashMap<String, String> logfiles = new HashMap<>();
        logfiles.put("gateway", gatewayLog);
        logfiles.put("adapter", adapterLog);
        logfiles.put("agent", agentLog);
        logfiles.put("demo", demoLog);

        File file = new File(logfiles.get(type));
        int counter = 0;
        ReversedLinesFileReader object = new ReversedLinesFileReader(file);
        while(counter < lines) {
            list.add(object.readLine());
            counter++;
        }

        return list;
    }

}
