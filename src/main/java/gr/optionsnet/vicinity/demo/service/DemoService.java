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

package gr.optionsnet.vicinity.demo.service;

import gr.optionsnet.vicinity.demo.component.RestClient;
import gr.optionsnet.vicinity.demo.domain.Item;
import gr.optionsnet.vicinity.demo.dto.vicinity.adapter.Objects;
import gr.optionsnet.vicinity.demo.dto.vicinity.adapter.ThingDescription;
import gr.optionsnet.vicinity.demo.dto.vicinity.agent.Configuration;
import gr.optionsnet.vicinity.demo.dto.vicinity.agent.Thing;
import gr.optionsnet.vicinity.demo.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemoService {

    private Logger logger = LoggerFactory.getLogger(DemoService.class);

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private RestClient restClient;

    public void initEnv() {
        logger.info("Application will initialize environment");

        //Agent configuration
        Configuration configuration = restClient.agentConfiguration().getBody();
        logger.info("Agent configuration received");
        logger.debug(configuration.toString());

        itemRepository.deleteAll();

        for (Thing thing : configuration.getThingsByOid()) {
            Item item = new Item();
            item.setOid(thing.getOid());
            item.setInfraId(thing.getInfraId());
            item.setPassword(thing.getPassword());
            itemRepository.save(item);
        }


        //Adapter objects
        Objects objects = restClient.adapterObjects().getBody();
        logger.info("Adapter objects received");
        logger.debug(objects.toString());

        for (ThingDescription td : objects.getThingDescriptions()) {
            Item item = itemRepository.findByInfraId(td.getOid()).get();

            item.setName(td.getName());
            item.setType(td.getType());
            item.setPid(td.getProperties().get(0).getPid());
            item.setMonitors(td.getProperties().get(0).getMonitors());
            item.setUnits(td.getProperties().get(0).getReadLink().getOutput().getField().get(0).getSchema().getUnits());
            itemRepository.save(item);
        }


    }
}
