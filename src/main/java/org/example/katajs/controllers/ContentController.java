package org.example.katajs.controllers;

import org.example.katajs.DTO.UserDTO;
import org.example.katajs.models.User;
import org.example.katajs.service.UserService;
import jakarta.validation.Valid;
import org.example.katajs.util.UserIncorrectData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping()
public class ContentController {

    private final UserService userService;


    @Autowired
    public ContentController( UserService userService) {
        this.userService = userService;

    }

    @GetMapping("api/user")
    public ResponseEntity <User> userHome(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(user);
    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(NoSuchElementException e){
    UserIncorrectData data = new UserIncorrectData();
    data.setInfo(e.getMessage());
    return  new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }


    @GetMapping("api/admin")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }



    @PostMapping("api/admin")
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserDTO user) {
        userService.saveUserWithRoles(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }


    @PutMapping("api/admin")
    public ResponseEntity<HttpStatus> editUser(@RequestBody @Valid UserDTO user){
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("api/admin/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id) {
       userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("api/admin/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable long id) {
        if(userService.getUserById(id)==null){
            throw  new UsernameNotFoundException("there is no user with id "+id);
        }
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }
}
