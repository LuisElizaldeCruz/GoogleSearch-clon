package com.cursojava.curso.repositories;
import com.cursojava.curso.entities.WebPage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SearchRepositoryImp implements SearchRepository{

    @PersistenceContext
    EntityManager entityManager;
    @Transactional
    @Override
    public List<WebPage> search(String textSearch) {
        String query = "From WebPage WHERE description like : textSearch"; //no es sql si no la sintaxis de hibernate sql
        return entityManager.createQuery(query)
                .setParameter("textSearch", textSearch)
                .getResultList();
    }
}
