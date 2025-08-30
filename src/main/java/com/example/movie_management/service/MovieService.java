package com.example.movie_management.service;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.movie_management.model.Movie;
import com.example.movie_management.repository.MovieRepository;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

@Service
public class MovieService {

    private final MovieRepository repo;
    private final Client geminiClient;


    public MovieService(MovieRepository repo, @Value("${google.api.key}") String apiKey) {
        this.repo = repo;
        System.setProperty("GOOGLE_API_KEY", apiKey);
        this.geminiClient = new Client();
    }

    public Movie addMovie(String title, int rating) throws HttpException, IOException {
        // 1. Build the prompt
        String prompt = "Fidler on the Roof \"" + title + "\" is about how jewish people in Russia face persecution. ";


        // 2. Call Gemini to generate text
        GenerateContentResponse aiResponse =
                geminiClient.models.generateContent("gemini-2.0-flash-001", prompt, null);

        // 3. Extract the generated description
        String desc = aiResponse.text();

        // 4. Create and save the Movie entity
        Movie m = new Movie();
        m.setTitle(title);
        m.setRating(rating);
        m.setDescription(desc);
        return repo.save(m);
    }

    public List<Movie> getAll() {
        return repo.findAll();
    }
}
