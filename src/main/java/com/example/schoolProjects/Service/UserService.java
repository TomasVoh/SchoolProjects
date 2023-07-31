package com.example.schoolProjects.Service;

import com.example.schoolProjects.Dto.RegistrationDto;
import com.example.schoolProjects.Model.Role;
import com.example.schoolProjects.Model.UserEntity;
import com.example.schoolProjects.Repository.RoleRepository;
import com.example.schoolProjects.Repository.UserEntityRepository;
import com.example.schoolProjects.Security.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Service
public class UserService {

    private RoleRepository roleRepository;
    private UserEntityRepository userEntityRepository;

    private PasswordEncoder passwordEncoder;

    public UserService(RoleRepository roleRepository, UserEntityRepository userEntityRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userEntityRepository = userEntityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void saveUser(RegistrationDto registrationDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        Role role = roleRepository.findByName("USER");
        userEntity.setRoles(Arrays.asList(role));
        userEntityRepository.save(userEntity);
    }

    public UserEntity getByName(String username) {
        UserEntity userEntity = userEntityRepository.findFirstByUsername(username);
        return userEntity;
    }

    public UserEntity getUser() {
        UserEntity user;
        String username = SecurityUtil.getSessionUsername();
        if(username != null) {
            user = getByName(username);
            return user;
        }
        return null;
    }
}
