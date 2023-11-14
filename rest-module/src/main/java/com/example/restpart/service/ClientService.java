package com.example.restpart.service;

import com.example.restpart.model.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional(readOnly = true)
public class ClientService {

    private final SessionFactory sessionFactory;

    @Autowired
    public ClientService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Transactional
    public void save(Client client) {
        sessionFactory.getCurrentSession().persist(client);
    }
}
