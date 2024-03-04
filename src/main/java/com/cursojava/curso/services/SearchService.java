package com.cursojava.curso.services;

import com.cursojava.curso.entities.WebPage;
import com.cursojava.curso.repositories.SearchRepositoryImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    @Autowired
    private SearchRepositoryImp repository;

    public List<WebPage> search(String textSearch){
        /*
        List<WebPage> result = new ArrayList<>();
        WebPage page = new WebPage();
        page.setTitle("test");
        page.setDescription("test");
        result.add(page);
        return result;
        */
        return repository.search(textSearch);
    }

    public void save(WebPage  webPage){
        repository.save(webPage);
    }

    public boolean exist(String link) {
        return repository.exist(link);
    }
}
