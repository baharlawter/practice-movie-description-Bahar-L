package controller;

import model.Movie;
import org.apache.http.HttpException;
import org.springframework.web.bind.annotation.*;
import service.MovieService;

import java.io.IOException;
import java.util.List;

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

