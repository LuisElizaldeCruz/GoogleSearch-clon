package com.cursojava.curso.controllers;

import com.cursojava.curso.entities.WebPage;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import java.util.ArrayList;
import java.util.List;

@RestController
public class SearchController {

    @RequestMapping(value = "api/search", method = RequestMethod.GET)
    public List<WebPage> search(){
        List<WebPage> result = new ArrayList<>();
        WebPage page = new WebPage();
        page.setTitle("test");
        page.setDescription("test");
        return result;
    }
}
