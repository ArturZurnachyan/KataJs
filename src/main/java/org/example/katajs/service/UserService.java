package org.example.katajs.service;

import org.example.katajs.DTO.UserDTO;
import org.example.katajs.models.Role;
import org.example.katajs.models.User;
import org.example.katajs.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
                       RoleService roleService,ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
        this.modelMapper = modelMapper;
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional <User> findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user;
    }

    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void updateUser(UserDTO user) {
        if(user.getRoleList()==null){
            user.setRoleList(Collections.singleton(new Role(1L,"ROLE_USER")));
        }
        user.setRoleList(user.getRoleList().stream()
                .map(role -> roleService.getByRole(role.getRole()))
                .collect(Collectors.toSet()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(convertingFromUserDTOToUser(user));
    }

    @Transactional
    public void saveUserWithRoles(UserDTO user) {
        if(user.getRoleList()==null){
            user.setRoleList(Collections.singleton(new Role(1,"Role_USER")));
        }
        user.setRoleList(user.getRoleList().stream()
                .map(role->roleService.getByRole(role.getRole()))
                .collect(Collectors.toSet()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(convertingFromUserDTOToUser(user));
    }

    @Transactional
    public void saveUserWithRoles(User user, Set<Role> roles) {
        user.setRoleList(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }


    public UserDTO getUserById(Long id) {
        return convertingFromUserToUserDTO(userRepository.findById(id).orElse(null));
    }

    private User convertingFromUserDTOToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRoleList(userDTO.getRoleList());
        return user;
    }

    private UserDTO convertingFromUserToUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }


}
