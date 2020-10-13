package com.dev.cinema;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Injector;
import com.dev.cinema.models.CinemaHall;
import com.dev.cinema.models.Movie;
import com.dev.cinema.models.MovieSession;
import com.dev.cinema.models.ShoppingCart;
import com.dev.cinema.models.User;
import com.dev.cinema.service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) throws AuthenticationException {
        Movie fastAndFurious = new Movie();
        fastAndFurious.setTitle("Fast and Furious");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(fastAndFurious);

        Movie groundhogDay = new Movie();
        groundhogDay.setTitle("Groundhog Day");
        movieService.add(groundhogDay);

        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        CinemaHall blueHall = new CinemaHall();
        blueHall.setCapacity(100);
        cinemaHallService.add(blueHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);

        MovieSession groundHogDayManSession = new MovieSession();
        groundHogDayManSession.setCinemaHall(blueHall);
        groundHogDayManSession.setMovie(groundhogDay);
        groundHogDayManSession.setShowTime(LocalDateTime.now().plusMonths(3));
        movieSessionService.add(groundHogDayManSession);

        MovieSession fastSession = new MovieSession();
        fastSession.setCinemaHall(blueHall);
        fastSession.setMovie(fastAndFurious);
        fastSession.setShowTime(LocalDateTime.now().plusMonths(2));
        movieSessionService.add(fastSession);

        movieSessionService.findAvailableSessions(groundhogDay.getId(),LocalDate.now())
                .forEach(System.out::println);
        movieSessionService.findAvailableSessions(fastAndFurious.getId(),LocalDate.now())
                .forEach(System.out::println);

        movieService.getAll().forEach(System.out::println);

        UserService userService = (UserService) injector.getInstance(UserService.class);

        AuthenticationService authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
        User johny = new User();
        johny.setEmail("johnydepp@amail.com");
        johny.setPassword("CaptainJackSparrow");
        authenticationService.register(johny.getEmail(), johny.getPassword());
        johny = authenticationService.login(johny.getEmail(), johny.getPassword());
        System.out.println(userService.findByEmail(johny.getEmail()).get());

        User den = new User();
        den.setEmail("den@somemail.com");
        den.setPassword("dent");
        authenticationService.register(den.getEmail(), den.getPassword());
        den = authenticationService.login(den.getEmail(), den.getPassword());
        System.out.println(userService.findByEmail(johny.getEmail()).get());

        ShoppingCartService cartService = (ShoppingCartService) injector
                .getInstance(ShoppingCartService.class);
        System.out.println(cartService.getByUser(johny));

        cartService.addSession(fastSession, den);
        cartService.addSession(groundHogDayManSession, johny);

        System.out.println("Johny's shopping cart :" + cartService.getByUser(johny).toString());
        System.out.println("Den's shopping cart :" + cartService.getByUser(den).toString());
        ShoppingCart johnyShoppingCart = cartService.getByUser(johny);
        cartService.clear(johnyShoppingCart);
        System.out.println("Johny's shoppingCart :" + cartService.getByUser(johny));

        OrderService orderService = (OrderService) injector
                .getInstance(OrderService.class);
        orderService.completeOrder(johnyShoppingCart.getTickets(), johnyShoppingCart.getUser());
        System.out.println(orderService.getOrderHistory(johnyShoppingCart.getUser()));
    }
}
