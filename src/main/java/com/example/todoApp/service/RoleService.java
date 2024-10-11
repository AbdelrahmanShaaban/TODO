package com.example.todoApp.service;

import com.example.todoApp.model.entities.Role;
import com.example.todoApp.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;


    public List<Role> findAllRoles(){
        return roleRepository.findAll();
    }

    public Role findAllRolesById(Long id){
        return roleRepository.findById(id).orElseThrow();
    }

    public Role insertUser (Role role) {
        return roleRepository.save(role);

    }

    public Role UpdateUser(Role role) {
        Role currentRole = roleRepository.findById(role.getId()).orElseThrow();
        currentRole.setRoleName(role.getRoleName());
        return roleRepository.save(currentRole);

    }
}
