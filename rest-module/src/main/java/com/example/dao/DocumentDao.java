package com.example.dao;

import com.example.dto.DocumentDto;
import com.example.model.Document;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
public class DocumentDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public DocumentDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Document document) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(document);
    }

    @Transactional
    public Optional<Document> show(DocumentDto document) {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("select d from Document d where series = :series and number = :number and type = :type")
                .setParameter("series", document.getSeries())
                .setParameter("number", document.getNumber())
                .setParameter("type", document.getType())
                .stream().findAny();
    }
}
