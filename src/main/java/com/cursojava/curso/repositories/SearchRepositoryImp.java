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

    @Override
    public WebPage getByUrl(String url) {
        String query = "FROM WebPage WHERE url = :url";
        List<WebPage> list = entityManager.createQuery(query)
                .setParameter("url", url)
                .getResultList();
        return list.size() == 0 ? null : list.get(0);
    }

    @Override
    public List<WebPage> getLinksToIndex() {
        String query = "FROM WebPage WHERE title is null AND description is null"; //no es sql si no la sintaxis de hibernate sql
        return entityManager.createQuery(query)
                .setMaxResults(100)
                .getResultList();
    }

    @Transactional
    @Override
    public List<WebPage> search(String textSearch) {
        String query = "From WebPage WHERE description like : textSearch"; //no es sql si no la sintaxis de hibernate sql
        return entityManager.createQuery(query)
                .setParameter("textSearch", "%"+textSearch+"%")
                .getResultList();
    }

    @Transactional
    @Override
    public void save(WebPage webPage) {
        entityManager.merge(webPage);
    }

    @Override
    public boolean exist(String url) {
        return getByUrl(url) != null;
    }
}
