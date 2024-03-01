package com.cursojava.curso.repositories;

import com.cursojava.curso.entities.WebPage;

import java.util.List;

public interface SearchRepository {
    public List<WebPage> search(String textSearch);
}
