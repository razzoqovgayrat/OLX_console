package com.company.controller;

import com.company.dto.AddressDTO;
import com.company.dto.PostDTO;
import com.company.entity.Address;
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
                case 2 -> {  }
                case 3 -> {  }
                case 4 -> {  }
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
                1. Sell
                2. Rent""");
        int num = getNum("Choose one");
        PostType postType = PostType.values()[num];
        String description = getStr("Description");

        PostDTO postDTO = new PostDTO(homeType, new AddressDTO(city, street, apartNumber), field, roomCount, price, postType, description);
        postService.createPost(postDTO);
        System.out.println("successfully created");
    }
}
