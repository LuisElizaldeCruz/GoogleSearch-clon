package com.cursojava.curso.controllers;

import com.cursojava.curso.entities.WebPage;
import com.cursojava.curso.services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private SearchService service;
    @RequestMapping(value = "api/search", method = RequestMethod.GET)

    public List<WebPage> search(String textSearch){
        /*
        List<WebPage> result = new ArrayList<>();
        WebPage page = new WebPage();
        page.setTitle("test");
        page.setDescription("test");
        result.add(page);
        return result;
        */
        return  service.search(textSearch);
    }
}
