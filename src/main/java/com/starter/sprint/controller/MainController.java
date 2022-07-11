package com.starter.sprint.controller;

import com.starter.sprint.entity.Doc;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class MainController {

    List<Doc> list = new ArrayList<Doc>();

    private static final Logger LOGGER = LogManager.getLogger(MainController.class);

    @GetMapping("/test/{message}")
    public HttpEntity test(@PathVariable("message") String msg) {
        return new HttpEntity("Testing Successsfull, "+msg, null);
    }

    @PostMapping("doc")
    public HttpEntity createDocument(@RequestParam("name") String name, @RequestParam("size") int size){
        Doc newDoc = new Doc(name, size, LocalDateTime.now());
        LOGGER.info("Created a new Document: "+newDoc);
        list.add(newDoc);
        return new HttpEntity(newDoc, null);
    }

    @GetMapping("doc")
    public HttpEntity getDocument(){
        LOGGER.info("Sending Data: " + list );
        return new HttpEntity(list, null);
    }

    @GetMapping("/kafka/produce/{message}")
    public HttpEntity produce(@PathVariable("message") String message) {
        return new HttpEntity("Produced", null);
    }

}
