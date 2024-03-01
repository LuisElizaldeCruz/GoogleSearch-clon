package com.cursojava.curso.services;

import com.cursojava.curso.entities.WebPage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    public List<WebPage> search(String textSearch){
        List<WebPage> result = new ArrayList<>();
        WebPage page = new WebPage();
        page.setTitle("test");
        page.setDescription("test");
        result.add(page);
        return result;
    }
}
