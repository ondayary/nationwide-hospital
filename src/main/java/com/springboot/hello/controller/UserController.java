package com.springboot.hello.controller;

import com.springboot.hello.dao.UserDao;
import com.springboot.hello.domain.User;
import com.springboot.hello.domain.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UserDao userDao;

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/")
    public String hello() {
        return "Hello World";
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable String id) throws SQLException, ClassNotFoundException {
        return ResponseEntity.ok().body(this.userDao.findById(id));

    }

    @GetMapping("/user")
    public User addAndGet() throws SQLException, ClassNotFoundException {
        userDao.add(new User("1","name1","password1"));
        return userDao.findById("1");
    }

    /*
    @PostMapping("/user")
    public ResponseEntity<Integer> add(@RequestBody UserDto userDto) throws SQLException, ClassNotFoundException {
        User user = new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getPassword());
        return ResponseEntity.ok().body(user.add(user));
    }*/

    @DeleteMapping("/user")
    public ResponseEntity<Integer> deleteAll() throws SQLException, ClassNotFoundException {
        return ResponseEntity.ok().body(userDao.deleteAll());
    }

}
