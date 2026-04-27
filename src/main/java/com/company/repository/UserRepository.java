package com.company.repository;

import com.company.entity.User;

import java.io.*;
import java.util.*;

public class UserRepository {
    private static UserRepository userRepository;
    private UserRepository() {}
    public static UserRepository getInstance() {
        if (Objects.isNull(userRepository))
            userRepository = new UserRepository();
        return userRepository;
    }

    public void saveUser(User user) {
        List<User> list = getList();
        list.add(user);
        saveList(list);
    }

    public void saveList(List<User> list) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/main/java/com/company/repository/user.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            fileOutputStream.close();
        } catch (IOException ignored) {}
    }

    public List<User> getList() {
        try {
            FileInputStream fileInputStream = new FileInputStream("src/main/java/com/company/repository/user.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            return (List<User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException ignored) {}
        return Collections.emptyList();
    }

    public Optional<User> getUserByPhone(String phoneNumber) {
        List<User> list = getList();
        for (User user : list) {
            if (user.getPhoneNumber().equals(phoneNumber)) return Optional.of(user);
        }
        return Optional.empty();
    }

    public Optional<User> getUserByEmail(String email) {
        List<User> list = getList();
        for (User user : list) {
            if (user.getEmail().equals(email)) return Optional.of(user);
        }
        return Optional.empty();
    }

    {
        List<User> list = getList();
        if (list.isEmpty()) {
            List<User> users = new ArrayList<>();
            users.add(new User(UUID.randomUUID().toString(), "Ali Aliyev", "934445566", "Cat", "alibek@gmail.com"));
            saveList(users);
        }
    }
}
