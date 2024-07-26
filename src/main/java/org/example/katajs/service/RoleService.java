package org.example.katajs.service;

import org.example.katajs.models.Role;
import org.example.katajs.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;


    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role getByRole(String role) {
        return roleRepository.getByRole(role);
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
