package com.piatsevich.simplecrudapp.repository.jdbc;

import com.piatsevich.simplecrudapp.config.JdbcUtils;
import com.piatsevich.simplecrudapp.repository.ClientRepository;
import com.piatsevich.simplecrudapp.models.Client;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ClientRepositoryImpl implements ClientRepository {
    private static final String GET_ALL_CLIENTS = "SELECT * FROM clients";
    private static final String SAVE_CLIENT =  "INSERT INTO clients (client_name, client_email) VALUES(?,?)";
    private static final String GET_CLIENT_BY_ID = "SELECT * FROM clients WHERE client_id=?";
    private static final String UPDATE_CLIENT = "UPDATE clients SET client_name=?, client_email=? WHERE client_id=?";
    private static final String DELETE_CLIENT_BY_ID = "DELETE FROM clients WHERE client_id=?";

    @Override
    public Client getById(Integer id) {
        Client client = null;
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(GET_CLIENT_BY_ID);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            client = new Client();

            client.setId(resultSet.getInt("client_id"));
            client.setName(resultSet.getString("client_name"));
            client.setEmail(resultSet.getString("client_email"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return client;
    }


    @Override
    public Client save(Client client) {
        PreparedStatement stmt = null;
        try {
            stmt = JdbcUtils.getConnection().prepareStatement(SAVE_CLIENT);
            stmt.setString(1, client.getName());
            stmt.setString(2, client.getEmail());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return client;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();

        try {
            PreparedStatement stmt =JdbcUtils.getConnection().prepareStatement(GET_ALL_CLIENTS);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                Client client = new Client();

                client.setId(resultSet.getInt("client_id"));
                client.setName(resultSet.getString("client_name"));
                client.setEmail(resultSet.getString("client_email"));
                clients.add(client);

            }
            return clients;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Client update(Client updatedClient) {
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(UPDATE_CLIENT);

            preparedStatement.setString(1, updatedClient.getName());
            preparedStatement.setString(2, updatedClient.getEmail());
            preparedStatement.setInt(3, updatedClient.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedClient;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(DELETE_CLIENT_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
