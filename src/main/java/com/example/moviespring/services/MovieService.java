package com.example.moviespring.services;

import com.example.moviespring.models.Movie;
import com.example.moviespring.repositories.MovieRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MovieService {
    private MovieRepository movieRepo = new MovieRepository();
    private List<Movie> allMovies = movieRepo.getAllMovies();

    public Movie getFirst() {
        return allMovies.get(0);
    }

    public int getCount() {
        return allMovies.size();
    }

    public int getAverageLength() {
        int time = 0;
        for (Movie movie : allMovies) {
            time += movie.getLength();
        }
        time = time / getCount();
        return time;
    }

    public int getAwardedMovieAge() {
        int age = 0;
        int avgAge = 0;
        int currentYear = 2026;
        for (Movie movie : allMovies) {
            if (movie.isAwards()) {
                age = movie.getYear();
            }
        }
        avgAge = age / allMovies.size();
        return avgAge;
    }

    public String getMostPopular() {
        String mostPopular;
        HashMap<String, Integer> subjects = new HashMap<>();
        for (Movie movie : allMovies) {
            if (subjects.containsKey(movie.getSubject()))
                subjects.put(movie.getSubject(), subjects.get(movie.getSubject()) + 1);
            else {
                subjects.put(movie.getSubject(), 1);
            }
        }
        mostPopular = subjects.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
        return mostPopular;
    }

    public int getAmountByYear(String inp) {
        int year = Integer.parseInt(inp);
        int amount = 0;
        for (Movie movie : allMovies) {
            if (movie.getYear() == year) {
                amount++;
            }
        }
        return amount;
    }

    public List<Movie> getFirstThree() {
        ArrayList<Movie> firstThree = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            firstThree.add(allMovies.get(i));
        }
        return firstThree;
    }

    public List<Movie> getAdventureMovies() {
        ArrayList<Movie> adventure = new ArrayList<>();
        for (Movie movie : allMovies) {
            if (movie.getSubject().equals("Adventure")) {
                adventure.add(movie);
            }
        }
        return adventure;
    }

    public List<Movie> getFirstTenAwarded() {
        ArrayList<Movie> firstTen = new ArrayList<>();
        for (Movie movie : allMovies) {
            if (movie.isAwards() && firstTen.size() < 10) {
                firstTen.add(movie);
            }
        }
        return firstTen;
    }
}
