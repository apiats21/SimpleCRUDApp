package com.piatsevich.simplecrudapp.service.impl;

import com.piatsevich.simplecrudapp.models.Chat;
import com.piatsevich.simplecrudapp.repository.ChatRepository;
import com.piatsevich.simplecrudapp.repository.jdbc.ChatRepositoryImpl;
import com.piatsevich.simplecrudapp.service.ChatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository = new ChatRepositoryImpl();

    @Override
    public Chat getById(Integer id) {
        return chatRepository.getById(id);
    }

    @Override
    public Chat create(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public List<Chat> getAll() {
        return chatRepository.getAll();
    }

    @Override
    public Chat update(Chat chat) {
        return chatRepository.update(chat);
    }

    @Override
    public void delete(Integer id) {
        chatRepository.deleteById(id);
    }
}
