package com.example.moviespring.controllers;

import com.example.moviespring.models.Movie;
import com.example.moviespring.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class MovieController {
    private MovieService service = new MovieService();

    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("name","Nicklas");
        return mav;
    }

    //Return how many movies are in the data file
    @GetMapping("/count")
    public ModelAndView count(){
        ModelAndView mav = new ModelAndView("examples/count");
        int count = service.getCount();
        mav.addObject("count", count);
        return mav;
    }

    @GetMapping("/first")
    public ModelAndView getFirst(){
        ModelAndView mav = new ModelAndView("first");
        Movie first = service.getFirst();
        mav.addObject("firstMovie",first);
        return mav;
    }

    @GetMapping("/average-length")
    public ModelAndView averageLength(){
        ModelAndView mav = new ModelAndView("averageLength");
        int length = service.getAverageLength();
        mav.addObject("averageLength",length);
        return mav;
    }

    @GetMapping("/averageAwardsAge")
    public ModelAndView averageAwardsAge(){
        ModelAndView mav = new ModelAndView("awards-age");
        int age = service.getAwardedMovieAge();
        mav.addObject("averageAwardMovieAge", age);
        return mav;
    }

    @GetMapping("/mostPopular")
    public ModelAndView mostPopular(){
        ModelAndView mav = new ModelAndView("most-popular");
        String popular = service.getMostPopular();
        mav.addObject("mostPopular", popular);
        return mav;
    }

    @GetMapping("/howManyPerYear")
    public ModelAndView howManyPerYear(@RequestParam String year){
        ModelAndView mav = new ModelAndView("howManyPerYear");
        int number = service.getAmountByYear(year);
        if(number > 0){
            mav.addObject("number", number);
        } else{
            mav.setStatus(HttpStatus.NOT_FOUND);
        }
        return mav;
    }

    @GetMapping("/first-three")
    public ModelAndView getFirstThree(){
    ModelAndView mav = new ModelAndView("examples/example-first-three");
    List<Movie> firstThree = service.getFirstThree();
    mav.addObject("movies", firstThree);
    return mav;
    }

    @GetMapping("/adventure")
    public ModelAndView getAdventureMovies(){
        ModelAndView mav = new ModelAndView("application/list");
        List<Movie> movies = service.getAdventureMovies();
        mav.addObject("movies",movies);
        return mav;
    }

    @GetMapping("/10-first")
    public ModelAndView firstTenAwarded(){
        ModelAndView mav = new ModelAndView("application/list");
        List<Movie> movies = service.getFirstTenAwarded();
        mav.addObject("movies",movies);
        return mav;
    }
}
