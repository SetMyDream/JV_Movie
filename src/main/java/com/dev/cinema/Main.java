package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.models.CinemaHall;
import com.dev.cinema.models.Movie;
import com.dev.cinema.models.MovieSession;
import com.dev.cinema.models.ShoppingCart;
import com.dev.cinema.models.User;
import com.dev.cinema.service.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws AuthenticationException {
        Movie movie = new Movie();
        movie.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);

        Movie groundhogDay = new Movie();
        groundhogDay.setTitle("Groundhog Day");
        movieService.add(groundhogDay);

        Movie bobroPozhalovat = new Movie();
        bobroPozhalovat.setTitle("Bienvenue chez les Ch'tis");
        movieService.add(bobroPozhalovat);

        Movie gift = new Movie();
        gift.setTitle("Gift");
        movieService.add(gift);

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall blueHall = new CinemaHall();
        blueHall.setCapacity(100);
        cinemaHallService.add(blueHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession giftManSession = new MovieSession();
        giftManSession.setCinemaHall(blueHall);
        giftManSession.setMovie(gift);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        giftManSession.setShowTime(LocalDateTime.now().plusMonths(5));
        movieSessionService.add(giftManSession);

        MovieSession groundHogDayManSession = new MovieSession();
        groundHogDayManSession.setCinemaHall(blueHall);
        groundHogDayManSession.setMovie(groundhogDay);
        groundHogDayManSession.setShowTime(LocalDateTime.now().plusMonths(3));
        movieSessionService.add(groundHogDayManSession);

        MovieSession bobroPozhalovatSession = new MovieSession();
        bobroPozhalovatSession.setCinemaHall(blueHall);
        bobroPozhalovatSession.setMovie(bobroPozhalovat);
        bobroPozhalovatSession.setShowTime(LocalDateTime.now().plusMonths(2));
        movieSessionService.add(bobroPozhalovatSession);

        movieSessionService.findAvailableSessions(gift.getId(), LocalDate.now())
                .forEach(System.out::println);
        movieSessionService.findAvailableSessions(groundhogDay.getId(),LocalDate.now())
                .forEach(System.out::println);
        movieSessionService.findAvailableSessions(bobroPozhalovat.getId(),LocalDate.now())
                .forEach(System.out::println);

        movieService.getAll().forEach(System.out::println);

        UserService userService = (UserService) injector.getInstance(UserService.class);
        User den = new User();
        den.setEmail("den@somemail.com");
        den.setPassword("dent");
        userService.add(den);

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User johny = new User();
        johny.setEmail("johnydepp@amail.com");
        johny.setPassword("CaptainJackSparrow");
        authenticationService.register(johny.getEmail(), johny.getPassword());
        authenticationService.login(johny.getEmail(), johny.getPassword());
        System.out.println(userService.findByEmail(johny.getEmail()).get());

        ShoppingCartService cartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        cartService.addSession(g, den);
        cartService.addSession(groundHogDayManSession, johny);
        System.out.println("Den's shopping cart :" + cartService.getByUser(den).toString());
        System.out.println("Johny's shopping cart :" + cartService.getByUser(johny).toString());
        ShoppingCart johnyShoppingCart = cartService.getByUser(johny);
        cartService.clear(johnyShoppingCart);
        System.out.println("Roman's shoppingCart :" + cartService.getByUser(johny));
    }
}
