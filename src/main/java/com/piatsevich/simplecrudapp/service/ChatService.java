package com.piatsevich.simplecrudapp.service;

import com.piatsevich.simplecrudapp.models.Chat;

import java.util.List;

public interface ChatService {
    public Chat getById(Integer id);

    public Chat create(Chat chat);

    public List<Chat> getAll();

    public Chat update(Chat chat);

    public void delete(Integer id);
}
