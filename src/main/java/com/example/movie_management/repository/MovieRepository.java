package com.example.movie_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.movie_management.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}


