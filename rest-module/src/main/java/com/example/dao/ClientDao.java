package com.example.dao;

import com.example.model.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public ClientDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(Client client) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(client);
    }
}
