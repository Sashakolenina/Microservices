package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.itmentor.spring.boot_security.demo.model.User;

@Service
public class UserService {
    private static final String API_URL = "http://94.198.50.185:7081/api/users";
    private static String sessionId;
    private final RestTemplate restTemplate;

    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void getAllUsers() {
        ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);
        sessionId = response.getHeaders().getFirst("Set-Cookie");
        System.out.println("Session ID: " + sessionId);
        System.out.println("User list" + response.getBody());
    }

    public String addUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", sessionId);
        User newUser = new User(3L, "James", "Brown", (byte) 30);
        HttpEntity<User> request = new HttpEntity<>(newUser, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(API_URL, request, String.class);
        System.out.println("User added: " + response.getBody());
        return response.getBody();

    }

    public String updateUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", sessionId);
        User newUser = new User(3L, "Thomas", "Shelby", (byte) 30);
        HttpEntity<User> request = new HttpEntity<>(newUser, headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL, HttpMethod.PUT, request, String.class);
        System.out.println("User updated: " + response.getBody());
        return response.getBody();
    }

    public String deleteUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Cookie", sessionId);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(API_URL + "/3", HttpMethod.DELETE, request, String.class);
        System.out.println("User deleted: " + response.getBody());
        return response.getBody();
    }

    public void runMethods() {
        getAllUsers();
        String addUser = addUser();
        String update = updateUser();
        String delete = deleteUser();
        String resault = addUser + update + delete;
        System.out.println(resault);
    }
}
