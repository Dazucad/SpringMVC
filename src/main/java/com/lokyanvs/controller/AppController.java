package com.lokyanvs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lokyanvs.daoClass.DaoClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppController {

    @Autowired
    private DaoClass daoClass;

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    @ResponseBody
    public String helloWorld() {
        return "HelloWorld!";
    }

    @RequestMapping(path = "/models", method = RequestMethod.GET, params = {"id", "name"})
    public String getUser(@RequestParam("id") int id, @RequestParam("name") String name) {
        return id + " " + name;
    }

    @RequestMapping("/users")
    public String users() {
        return "users list is empty";
    }

    @RequestMapping(path = "/pc")
    public String pc() {
        return objectAsJsonString(daoClass.findAllPC());
    }

    @RequestMapping(value = "/pc", params = {"id"})
    public String pcById(@RequestParam("id") int id) {
        return objectAsJsonString(daoClass.findPCbyId(id));
    }

    @RequestMapping(value = "/pc", params = {"model"})
    public String pcByModel(@RequestParam("model") String model) {
        return objectAsJsonString(daoClass.findPcByModel(model));
    }

    private String objectAsJsonString(Object obj){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
