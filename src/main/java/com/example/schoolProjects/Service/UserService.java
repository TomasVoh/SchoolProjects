package com.example.schoolProjects.Service;

import com.example.schoolProjects.Dto.RegistrationDto;
import com.example.schoolProjects.Model.Role;
import com.example.schoolProjects.Model.UserEntity;
import com.example.schoolProjects.Repository.RoleRepository;
import com.example.schoolProjects.Repository.UserEntityRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    private RoleRepository roleRepository;
    private UserEntityRepository userEntityRepository;

    public UserService(RoleRepository roleRepository, UserEntityRepository userEntityRepository) {
        this.roleRepository = roleRepository;
        this.userEntityRepository = userEntityRepository;
    }

    public void saveUser(RegistrationDto registrationDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registrationDto.getUsername());
        userEntity.setPassword(registrationDto.getPassword());
        Role role = roleRepository.findByName("USER");
        userEntity.setRoles(Arrays.asList(role));
        userEntityRepository.save(userEntity);
    }
}
