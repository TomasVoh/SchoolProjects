package com.example.schoolProjects.Security;

import com.example.schoolProjects.Model.UserEntity;
import com.example.schoolProjects.Repository.UserEntityRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserService implements UserDetailsService {

    private UserEntityRepository userEntityRepository;

    public CustomUserService(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findFirstByUsername(username);
        if(userEntity != null) {
            User user = new User(
                    userEntity.getUsername(),
                    userEntity.getPassword(),
                    userEntity.getRoles().stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList())
            );
            return user;
        } else {
            throw new UsernameNotFoundException("Uživatel nebyl nalezen");
        }
    }
}
