package com.piatsevich.simplecrudapp.repository.jdbc;

import com.piatsevich.simplecrudapp.config.JdbcUtils;
import com.piatsevich.simplecrudapp.repository.ChatRepository;
import com.piatsevich.simplecrudapp.models.Chat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ChatRepositoryImpl implements ChatRepository {

    private Connection connection;

    public ChatRepositoryImpl() {
        this.connection = JdbcUtils.getConnection();
    }

    @Override
    public Chat getById(Integer integer) {
        return null;
    }

    @Override
    public Chat save(Chat chat) {
        String sql = "INSERT INTO chats (chat_name, creator_id) VALUES (?,?)";

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);
            preparedStatement.setString(1,chat.getName());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return chat;
    }

    @Override
    public List<Chat> getAll() {
        return null;
    }

    @Override
    public Chat update(Chat chat) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
