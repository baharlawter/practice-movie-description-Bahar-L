package com.example.movie_management.controller;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.movie_management.model.Movie;
import com.example.movie_management.service.MovieService;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService svc;
    public MovieController(MovieService svc){ this.svc = svc; }
    @PostMapping
    public Movie create(@RequestParam String title, @RequestParam int rating) throws HttpException, IOException {
        return svc.addMovie(title,rating);
    }
    @GetMapping
    public List<Movie> list(){ return svc.getAll(); }
}

