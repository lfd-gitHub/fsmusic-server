package com.lfd.fsmusic.controller;

import com.lfd.fsmusic.repository.RoleRepository;
import com.lfd.fsmusic.repository.UserRepository;
import com.lfd.fsmusic.repository.entity.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/hello")
public class DefaultController {
    
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @GetMapping
    public String install(){
        // User user = new User();
        // user.setUsername("username"+LocalDateTime.now()
        // .atZone(ZoneOffset.systemDefault())
        // .toInstant().toEpochMilli());
        // user.setPassword("password");
        // user.setNickname("nickname");
        // user.setEnabled(true);
        // user.setLocked(false);
        // user.setLastLoginIp("127.0.0.1");
        // user.setLastLoginTime(LocalDateTime.now());

    
        Role roleAdmin = new Role();
        roleAdmin.setName("Admin");
        roleAdmin.setTitle("管理员");

        Role roleUser = new Role();
        roleUser.setName("User");
        roleUser.setTitle("管理员");
        
        // user.getRoles().add(role);

        // userRepository.save(user);
        Role savedAdmin = roleRepository.findByName("Admin");
        System.out.println("admin-role:" + savedAdmin);
        if(savedAdmin == null) roleRepository.save(roleAdmin);
        Role savedUser = roleRepository.findByName("User");
        System.out.println("user-role:" + savedUser);
        if(savedUser == null) roleRepository.save(roleUser);

        //user.setRoles(Arrays.asList(new Role[]{role}));
        return "success:" + userRepository.findAll();
    }
}
