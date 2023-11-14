package com.example.service;

import com.example.model.Document;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class DocumentService {

    private final SessionFactory sessionFactory;

    @Autowired
    public DocumentService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Document document) {
        sessionFactory.getCurrentSession().persist(document);
    }
}
