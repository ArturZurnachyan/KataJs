package org.example.katajs.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.example.katajs.models.Role;


import java.util.Set;

public class UserDTO {

    private Long id;

    @Pattern(message = "Bad formed user name: ${validatedValue} \n" +
                       "Name should starts with a capital letter and not contain symbols or numbers",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2 , max = 30, message = "Name should be more than 2 and less then 30 characters")
    private String name;

    @Pattern(message = "Bad formed user surname: ${validatedValue} \n" +
                       "Surname should starts with a capital letter and not contain symbols or numbers",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$")
    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 2, max = 40, message = "Surname should be between 2 and 40 characters")
    private String surname;


    @Email
    @NotEmpty(message = "Email should not be empty")
    private String email;

    @Size(min = 4, message = "Password should be between 4 and 20 characters")
    private String password;


    private Set <Role> roleList;

    public UserDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Pattern(message = "Bad formed user name: ${validatedValue} \n" +
                              "Name should starts with a capital letter and not contain symbols or numbers",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$") @NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 30, message = "Name should be more than 2 and less then 30 characters") String getName() {
        return name;
    }

    public void setName(@Pattern(message = "Bad formed user name: ${validatedValue} \n" +
                                           "Name should starts with a capital letter and not contain symbols or numbers",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$") @NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 30, message = "Name should be more than 2 and less then 30 characters") String name) {
        this.name = name;
    }

    public @Pattern(message = "Bad formed user surname: ${validatedValue} \n" +
                              "Surname should starts with a capital letter and not contain symbols or numbers",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$") @NotEmpty(message = "Surname should not be empty") @Size(min = 2, max = 40, message = "Surname should be between 2 and 40 characters") String getSurname() {
        return surname;
    }

    public void setSurname(@Pattern(message = "Bad formed user surname: ${validatedValue} \n" +
                                              "Surname should starts with a capital letter and not contain symbols or numbers",
            regexp = "^[A-Z][a-z]*(\\s(([a-z]{1,3})|(([a-z]+\\')?[A-Z][a-z]*)))*$") @NotEmpty(message = "Surname should not be empty") @Size(min = 2, max = 40, message = "Surname should be between 2 and 40 characters") String surname) {
        this.surname = surname;
    }

    public @Email @NotEmpty(message = "Email should not be empty") String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotEmpty(message = "Email should not be empty") String email) {
        this.email = email;
    }

    public @Size(min = 4, message = "Password should be between 4 and 20 characters") String getPassword() {
        return password;
    }

    public void setPassword(@Size(min = 4, message = "Password should be between 4 and 20 characters") String password) {
        this.password = password;
    }

    public Set<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(Set<Role> roleList) {
        this.roleList = roleList;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", surname='" + surname + '\'' +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", roleList=" + roleList +
               '}';
    }
}
