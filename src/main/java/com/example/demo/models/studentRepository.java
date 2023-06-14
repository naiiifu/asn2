package com.example.demo.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface studentRepository extends JpaRepository<student,Integer> {
    List<student> findBySid(String sid);
}
