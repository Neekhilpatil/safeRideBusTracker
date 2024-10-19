//	package com.saferidetracker.bootstrap;
//
//import java.util.Map;
//
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//import com.saferidetracker.model.RoleEnum;
//import com.saferidetracker.repository.RoleRepository;
//import io.jsonwebtoken.lang.Arrays;
//
//	@Component
//	public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
//	    private final RoleRepository roleRepository;
//
//
//	    public RoleSeeder(RoleRepository roleRepository) {
//	        this.roleRepository = roleRepository;
//	    }
//
//	    @Override
//	    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
//	        this.loadRoles();
//	    }
//
//	    private void loadRoles() {
//	        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPER_ADMIN };
//	        Map<RoleEnum, String> roleDescriptionMap = Map.of(
//	            RoleEnum.USER, "Default user role",
//	            RoleEnum.ADMIN, "Administrator role",
//	            RoleEnum.SUPER_ADMIN, "Super Administrator role"
//	        );
//
//	        Arrays.stream(roleNames).forEach((roleName) -> {
//	            Optional<Role> optionalRole = roleRepository.findByName(roleName);
//
//	            optionalRole.ifPresentOrElse(System.out::println, () -> {
//	                Role roleToCreate = new Role();
//
//	                roleToCreate.setName(roleName)
//	                        .setDescription(roleDescriptionMap.get(roleName));
//
//	                roleRepository.save(roleToCreate);
//	            });
//	        });
//	    }
//	}

package com.saferidetracker.bootstrap;

import java.util.Map;
import java.util.Optional;
import java.util.Arrays; // Correct import

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.saferidetracker.model.Role;
import com.saferidetracker.model.RoleEnum;
import com.saferidetracker.repository.RoleRepository;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {
    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        this.loadRoles();
    }

    private void loadRoles() {
        RoleEnum[] roleNames = new RoleEnum[] { RoleEnum.USER, RoleEnum.ADMIN, RoleEnum.SUPER_ADMIN };
        Map<RoleEnum, String> roleDescriptionMap = Map.of(
            RoleEnum.USER, "Default user role",
            RoleEnum.ADMIN, "Administrator role",
            RoleEnum.SUPER_ADMIN, "Super Administrator role"
        );

        Arrays.stream(roleNames).forEach((roleName) -> {
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println, () -> {
                Role roleToCreate = new Role();

                roleToCreate.setName(roleName)
                        .setDescription(roleDescriptionMap.get(roleName));

                roleRepository.save(roleToCreate);
            });
        });
    }
}
