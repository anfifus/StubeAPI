package com.example.stubeapi;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface VideoRepository extends CrudRepository<Video,Long> {
    List<Video> findAllByUser(User user);
}
