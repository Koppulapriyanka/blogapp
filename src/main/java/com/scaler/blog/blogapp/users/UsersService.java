package com.scaler.blog.blogapp.users;

import com.scaler.blog.blogapp.security.jwt.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTService jwtService;

    public List<Profile> getProfiles() {
        List<UserEntity> users = usersRepository.findAll();
        List<Profile> profiles = users.stream().map((user) -> {
            return modelMapper.map(user, Profile.class);
        }).toList();
        return  profiles;
    }

    public UserResponseDto createUser(UserEntity userEntity) {
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        UserEntity savedUserEntity = usersRepository.save(userEntity);
        UserResponseDto user = modelMapper.map(savedUserEntity, UserResponseDto.class);
        user.setToken(jwtService.createJwt(user.getUserName()));
        return user;
    }

    public Profile getProfile(String userName) {
        UserEntity userEntity = usersRepository.findByUserName(userName);
        return modelMapper.map(userEntity, Profile.class);
    }

    public UserResponseDto verifyUser(UserLoginDto userLoginDto) {
        UserEntity userEntity = usersRepository.findByUserName(userLoginDto.getUserName());
        if(passwordEncoder.matches(userLoginDto.getPassword(), userEntity.getPassword())){
            UserResponseDto validUser = modelMapper.map(userEntity, UserResponseDto.class);
            validUser.setToken(jwtService.createJwt(validUser.getUserName()));
            return validUser;
        }
        return null;
    }

    public UserResponseDto getUserByUserName(String userName) {
        UserEntity userEntity = usersRepository.findByUserName(userName);
        return modelMapper.map(userEntity, UserResponseDto.class);
    }
}
