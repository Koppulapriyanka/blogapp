package com.scaler.blog.blogapp.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    public UsersService usersService;

    @GetMapping(path = "/profiles", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Profile>> getProfiles() {
        List<Profile> profiles = usersService.getProfiles();
        return ResponseEntity.ok(profiles);
    }
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponseDto> createUser(@RequestBody UserEntity userEntity) {
        UserResponseDto user = usersService.createUser(userEntity);
        return ResponseEntity.created(URI.create("/users/"+user.getUserId())).body(user);
    }

    @GetMapping(path = "/profiles/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Profile> getProfile(@PathVariable("username") String userName) {
        Profile profile = usersService.getProfile(userName);
        return ResponseEntity.ok(profile);
    }

    @PostMapping(path = "/login", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponseDto> login(@RequestBody UserLoginDto userLoginDto) {
        UserResponseDto userResponseDto = usersService.verifyUser(userLoginDto);
        return ResponseEntity.ok(userResponseDto);
    }
}
