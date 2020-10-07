package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.models.Movie;
import com.dev.cinema.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);

        movieService.getAll().forEach(System.out::println);
    }
}
