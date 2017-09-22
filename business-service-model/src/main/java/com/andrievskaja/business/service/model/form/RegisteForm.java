/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service.model.form;

import org.hibernate.validator.constraints.SafeHtml;

/**
 *
 * @author Людмила
 */
public class RegisteForm {

    private Long userId;
    private String email;
    private String password;
    private String name;
    private String role;

    public RegisteForm(String email, String password, String role, String name) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.name = name;
    }

    public RegisteForm() {
    }

    @SafeHtml
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @SafeHtml
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @SafeHtml
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    
}
