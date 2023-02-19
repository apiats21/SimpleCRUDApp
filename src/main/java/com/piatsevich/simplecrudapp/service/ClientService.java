package com.piatsevich.simplecrudapp.service;

import com.piatsevich.simplecrudapp.models.Client;

import java.util.List;

public interface ClientService {
    public Client getById(Integer id);

    public Client create(Client client);

    public List<Client> getAll();

    public Client update(Client client);

    public void delete(Integer id);
}
