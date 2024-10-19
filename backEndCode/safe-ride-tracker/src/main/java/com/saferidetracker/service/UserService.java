package com.saferidetracker.service;

//import org.springframework.stereotype.Service;
//import com.saferidetracker.model.User;
//import com.saferidetracker.repository.UserRepository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//public class UserService {
//    private final UserRepository userRepository;
//
//    public UserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    public List<User> allUsers() {
//        List<User> users = new ArrayList<>();
//
//        userRepository.findAll().forEach(users::add);
//
//        return users;
//    }
//}


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.saferidetracker.dtos.RegisterUserDto;
import com.saferidetracker.model.Role;
import com.saferidetracker.model.RoleEnum;
import com.saferidetracker.model.User;
import com.saferidetracker.repository.RoleRepository;
import com.saferidetracker.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    public User createAdministrator(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User()
                .setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPassword(passwordEncoder.encode(input.getPassword()))
                .setRole(optionalRole.get());

        return userRepository.save(user);
    }
}
