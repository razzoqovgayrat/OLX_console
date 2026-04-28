package com.company.controller;

import com.company.dto.AddressDTO;
import com.company.dto.FilterDTO;
import com.company.dto.PostDTO;
import com.company.enums.HomeType;
import com.company.enums.PostType;
import com.company.service.PostService;

import static com.company.utils.Utils.*;

public class MainController {

    private final PostService postService = PostService.getInstance();

    public void menu() {
        while (true) {
            System.out.println("""
                    1. Create a new post
                    2. My posts
                    3. Posts
                    4. Search
                    0. Log out
                    """);
            switch (getNum("Choose one")) {
                case 1 -> createPost();
                case 2 -> myPosts();
                case 3 -> allPosts();
                case 4 -> search();
                case 0 -> { return; }
                default -> System.out.println("wrong input");
            }
        }
    }

    private void createPost() {
        HomeType[] homeTypes = HomeType.values();
        for (int i = 0; i < homeTypes.length; i++) {
            System.out.println(i + ". " + homeTypes[i]);
        }
        int index = getNum("choose one");
        HomeType homeType = homeTypes[index];

        String city = getStr("City name");
        String street = getStr("Street name");
        int apartNumber = getNum("Apart number");
        double field = getDouble("Apart space kv.m");
        int roomCount = getNum("Room count");
        long price = getNum("Price");

        System.out.println("""
                0. Sell
                1. Rent""");
        PostType postType = PostType.values()[getNum("Choose one")];
        String description = getStr("Description");

        PostDTO postDTO = new PostDTO(homeType, new AddressDTO(city, street, apartNumber), field, roomCount, price, postType, description);
        postService.createPost(postDTO);
        System.out.println("successfully created");
    }

    private void myPosts() {
        postService.myPosts().forEach(System.out::println);
    }

    private void allPosts() {
        postService.getAllPosts().forEach(System.out::println);
    }

    private void search() {
        while (true) {
            System.out.println("""
                What are you searching for?
                1. Home Type
                2. Address
                3. Field
                4. Room count
                5. Price
                6. Post Type
                7. All
                0. Back""");
            switch (getNum("choose one")) {
                case 1 -> {
                    for (int i = 0; i < HomeType.values().length; i++) {
                        System.out.println(i + ". " + HomeType.values()[i]);
                    }
                    postService.filter(new FilterDTO(HomeType.values()[getNum("Choose one")], null, 0, 0, 0, null)).forEach(System.out::println);
                }
                case 2 -> postService.filter(new FilterDTO(null, getStr("Enter city name"), 0, 0, 0, null)).forEach(System.out::println);
                case 3 -> postService.filter(new FilterDTO(null, null, getDouble("Enter field"), 0, 0, null)).forEach(System.out::println);
                case 4 -> postService.filter(new FilterDTO(null, null, 0, getNum("Enter room count"), 0, null)).forEach(System.out::println);
                case 5 -> postService.filter(new FilterDTO(null, null, 0, 0, getNum("Enter price"), null)).forEach(System.out::println);
                case 6 -> {
                    for (int i = 0; i < PostType.values().length; i++) {
                        System.out.println(i + ". " + PostType.values()[i]);
                    }
                    postService.filter(new FilterDTO(null, null, 0, 0, 0, PostType.values()[getNum("Choose one")])).forEach(System.out::println);
                }
                case 7 -> {
                    for (int i = 0; i < HomeType.values().length; i++) {
                        System.out.println(i + ". " + HomeType.values()[i]);
                    }
                    HomeType homeType = HomeType.values()[getNum("Choose one")];
                    String address = getStr("Enter city name");
                    double field = getDouble("Enter field");
                    int roomCount = getNum("Enter room count");
                    long price = getNum("Enter price");
                    for (int i = 0; i < PostType.values().length; i++) {
                        System.out.println(i + ". " + PostType.values()[i]);
                    }
                    PostType postType = PostType.values()[getNum("Choose one")];
                    FilterDTO filterDTO = new FilterDTO(homeType, address, field, roomCount, price, postType);
                    postService.filterAll(filterDTO).forEach(System.out::println);
                }
                case 0 -> { return; }
                default -> System.out.println("wrong input");
            }
        }
    }
}
