package com.company.repository;

import com.company.entity.Address;
import com.company.entity.Post;
import com.company.enums.HomeType;
import com.company.enums.PostType;

import java.io.*;
import java.util.*;

public class PostRepository {

    private static PostRepository postRepository;
    private PostRepository() {}
    public static PostRepository getInstance() {
        if (Objects.isNull(postRepository)) postRepository = new PostRepository();
        return postRepository;
    }

    public void savePost(Post post) {
        List<Post> list = getList();
        list.add(post);
        saveList(list);
    }

    public void saveList(List<Post> list) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/com/company/repository/post.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            fileOutputStream.close();
        } catch (IOException ignored) {}
    }

    public List<Post> getList() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/com/company/repository/post.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (List<Post>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ignored) {}
        return Collections.emptyList();
    }

    {
        List<Post> list = getList();
        if (list.isEmpty()) {
            Address address = new Address("Tashkent", "Alisher Navoiy", 45);
            list.add(new Post(UUID.randomUUID().toString(), "abc", HomeType.KVARTIRA, address, 80.7, 3, 500, PostType.RENT, "Only boys"));
            saveList(list);
        }
    }

}
