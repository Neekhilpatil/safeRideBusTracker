//package com.saferidetracker.service;
//
//import java.util.Optional;
//
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import com.saferidetracker.dtos.LoginUserDto;
//import com.saferidetracker.dtos.RegisterUserDto;
//import com.saferidetracker.model.Role;
//import com.saferidetracker.model.RoleEnum;
//import com.saferidetracker.model.User;
//import com.saferidetracker.repository.RoleRepository;
//import com.saferidetracker.repository.UserRepository;
//
//@Service
//public class AuthenticationService {
//  private final UserRepository userRepository;
//  
//  private final PasswordEncoder passwordEncoder;
//  
//  private final AuthenticationManager authenticationManager;
//
//  public AuthenticationService(
//      UserRepository userRepository,
//      AuthenticationManager authenticationManager,
//      PasswordEncoder passwordEncoder
//  ) {
//      this.authenticationManager = authenticationManager;
//      this.userRepository = userRepository;
//      this.passwordEncoder = passwordEncoder;
//  }
//
////  public User signup(RegisterUserDto input) {
////      User user = new User()
////              .setFullName(input.getFullName())
////              .setEmail(input.getEmail())
////              .setPassword(passwordEncoder.encode(input.getPassword()));
////
////      return userRepository.save(user);
////  }
//
////  public User signup(RegisterUserDto input) {
////      User user = new User().
////              setFullName(input.getFullName())  // Chaining setters
////              .setEmail(input.getEmail())
////              .setPassword(passwordEncoder.encode(input.getPassword()));
////
////      return userRepository.save(user);
////  }
//  
//  public User signup(RegisterUserDto input) {
//	    Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
//	        
//	    if (optionalRole.isEmpty()) {
//	        return null;
//	    }
//	        
//	    var user = new User()
//	        .setFullName(input.getFullName())
//	        .setEmail(input.getEmail())
//	        .setPassword(passwordEncoder.encode(input.getPassword()))
//	        .setRole(optionalRole.get());
//
//	    return userRepository.save(user);
//	}
//  
//  public User authenticate(LoginUserDto input) {
//      authenticationManager.authenticate(
//              new UsernamePasswordAuthenticationToken(
//                      input.getEmail(),
//                      input.getPassword()
//              )
//      );
//
//      return userRepository.findByEmail(input.getEmail())
//              .orElseThrow();
//  }
//}

package com.saferidetracker.service;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.saferidetracker.dtos.LoginUserDto;
import com.saferidetracker.dtos.RegisterUserDto;
import com.saferidetracker.model.Role;
import com.saferidetracker.model.RoleEnum;
import com.saferidetracker.model.User;
import com.saferidetracker.repository.RoleRepository;
import com.saferidetracker.repository.UserRepository;

@Service
public class AuthenticationService {
  private final UserRepository userRepository;
  private final RoleRepository roleRepository;  // Inject RoleRepository
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  public AuthenticationService(
      UserRepository userRepository,
      RoleRepository roleRepository,  // Add RoleRepository to the constructor
      AuthenticationManager authenticationManager,
      PasswordEncoder passwordEncoder
  ) {
      this.authenticationManager = authenticationManager;
      this.userRepository = userRepository;
      this.roleRepository = roleRepository;
      this.passwordEncoder = passwordEncoder;
  }

  public User signup(RegisterUserDto input) {
      Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);
      
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

  public User authenticate(LoginUserDto input) {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      input.getEmail(),
                      input.getPassword()
              )
      );

      return userRepository.findByEmail(input.getEmail())
              .orElseThrow();
  }
}

