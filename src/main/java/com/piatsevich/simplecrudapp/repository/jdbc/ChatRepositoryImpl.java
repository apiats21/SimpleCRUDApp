package com.piatsevich.simplecrudapp.repository.jdbc;

import com.piatsevich.simplecrudapp.config.JdbcUtils;
import com.piatsevich.simplecrudapp.models.Employee;
import com.piatsevich.simplecrudapp.repository.ChatRepository;
import com.piatsevich.simplecrudapp.models.Chat;
import org.springframework.stereotype.Component;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChatRepositoryImpl implements ChatRepository {

    private static final String GET_ALL_CHATS = "SELECT chats.*, employees.* " +
            "FROM chats INNER JOIN employees ON chats.creator_id = employees.employee_id;";
    private static final String SAVE_CHAT =
            "INSERT INTO chats (chat_name, creator_id) VALUES(?,?)";
    private static final String GET_CHAT_BY_ID = "SELECT chats.*, employees.* " +
            "FROM chats INNER JOIN employees ON chats.creator_id = employees.employee_id WHERE creator_id=?";
    private static final String UPDATE_CHAT =
            "UPDATE chats SET chat_name=?, creator_id=? WHERE chat_id=?";
    private static final String DELETE_CHAT_BY_ID =
            "DELETE FROM chats WHERE chat_id=?";

    @Override
    public Chat getById(Integer id) {
        Chat chat = null;
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(GET_CHAT_BY_ID);

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();
            chat = new Chat();

            chat.setId(resultSet.getInt("chat_id"));
            chat.setName(resultSet.getString("chat_name"));

            Employee employee = new Employee();
            employee.setId(resultSet.getInt("employee_id"));
            employee.setUsername(resultSet.getString("username"));
            employee.setEmail(resultSet.getString("email"));
            chat.setEmployee(employee);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return chat;
    }


    @Override
    public Chat save(Chat chat) {
        PreparedStatement stmt = null;
        try {
            stmt = JdbcUtils.getConnection().prepareStatement(SAVE_CHAT);
            stmt.setString(1, chat.getName());
            stmt.setInt(2, chat.getEmployee().getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return chat;
    }

    @Override
    public List<Chat> getAll() {
        List<Chat> chats = new ArrayList<>();

        try {
            PreparedStatement stmt =JdbcUtils.getConnection().prepareStatement(GET_ALL_CHATS);
            ResultSet resultSet = stmt.executeQuery();

            while(resultSet.next()) {
                Chat chat = new Chat();
                chat.setId(resultSet.getInt("chat_id"));
                chat.setName(resultSet.getString("chat_name"));

                Employee employee = new Employee();
                employee.setId(resultSet.getInt("employee_id"));
                employee.setUsername(resultSet.getString("username"));
                employee.setEmail(resultSet.getString("email"));
                chat.setEmployee(employee);

                chats.add(chat);
            }
            return chats;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Chat update(Chat updatedChat) {
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(UPDATE_CHAT);

            preparedStatement.setString(1, updatedChat.getName());
            preparedStatement.setInt(2, updatedChat.getEmployee().getId());
            preparedStatement.setInt(3,updatedChat.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return updatedChat;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            PreparedStatement preparedStatement =
                    JdbcUtils.getConnection().prepareStatement(DELETE_CHAT_BY_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
