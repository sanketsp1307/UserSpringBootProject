package com.example.demo.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.UserService;
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private Random random = new Random();

    public User createUserWithGeneratedPassword(String firstName, String middleName, String lastName) {
        String generatedPassword = generatePassword(firstName, middleName, lastName);
        
        User user = new User();
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setLastName(lastName);
        user.setPassword(generatedPassword);
        
        return userRepository.save(user);
    }

    private String generatePassword(String firstName, String middleName, String lastName) {
        String password = firstName.substring(0, 2) + middleName.substring(0, 2) 
        + lastName.substring(0, 2) + String.format("%03d", random.nextInt(1000));  
        return password;
    }
}
