package com.example.dao;

import com.example.model.Document;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


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
}
