package com.piatsevich.simplecrudapp.service.impl;

import com.piatsevich.simplecrudapp.models.Client;
import com.piatsevich.simplecrudapp.repository.ClientRepository;
import com.piatsevich.simplecrudapp.repository.jdbc.ClientRepositoryImpl;
import com.piatsevich.simplecrudapp.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository = new ClientRepositoryImpl();

    @Override
    public Client getById(Integer id) {
        return clientRepository.getById(id);
    }

    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    @Override
    public Client update(Client client) {
        return clientRepository.update(client);
    }

    @Override
    public void delete(Integer id) {
        clientRepository.deleteById(id);

    }
}
