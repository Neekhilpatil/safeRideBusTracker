package com.saferidetracker.bootstrap;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import com.saferidetracker.dtos.RegisterUserDto;
import com.saferidetracker.model.Role;
import com.saferidetracker.model.RoleEnum;
import com.saferidetracker.model.User;
import com.saferidetracker.repository.RoleRepository;
import com.saferidetracker.repository.UserRepository;

import java.util.Optional;
@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public AdminSeeder(
        RoleRepository roleRepository,
        UserRepository  userRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.createSuperAdministrator();
    }

    private void createSuperAdministrator() {
        RegisterUserDto userDto = new RegisterUserDto();
        userDto.setFullName("Super Admin").setEmail("super.admin@email.com").setPassword("123456");

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if (optionalRole.isEmpty() || optionalUser.isPresent()) {
            return;
        }

        var user = new User()
            .setFullName(userDto.getFullName())
            .setEmail(userDto.getEmail())
            .setPassword(passwordEncoder.encode(userDto.getPassword()))
            .setRole(optionalRole.get());

        userRepository.save(user);
    }
}
