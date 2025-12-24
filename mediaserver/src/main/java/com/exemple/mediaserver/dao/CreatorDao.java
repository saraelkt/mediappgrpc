package com.exemple.mediaserver.dao;

import com.exemple.mediaserver.model.CreatorModel;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CreatorDao {
    private final Map<String, CreatorModel> creators = new ConcurrentHashMap<>();

    public CreatorModel save(CreatorModel creator) {
        if (creator.getId() == null) {
            creator.setId(UUID.randomUUID().toString());
        }
        creators.put(creator.getId(), creator);
        return creator;
    }

    public Optional<CreatorModel> findById(String id) {
        return Optional.ofNullable(creators.get(id));
    }

    public List<CreatorModel> findAll() {
        return new ArrayList<>(creators.values());
    }
}