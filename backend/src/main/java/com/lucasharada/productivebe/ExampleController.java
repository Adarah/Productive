package com.lucasharada.productivebe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    Logger logger = LoggerFactory.getLogger(ExampleController.class);

    @GetMapping
    public String hello() {
        logger.trace("here's some trace log");
        logger.debug("here's some debug log");
        logger.info("here's some info log");
        logger.warn("here's some warn log");
        logger.error("here's some error log");
        return "World!";
    }

}
