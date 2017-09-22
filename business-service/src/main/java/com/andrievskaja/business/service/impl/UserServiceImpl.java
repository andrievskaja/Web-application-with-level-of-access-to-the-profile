/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service.impl;

import com.andrievskaja.business.model.Role;
import com.andrievskaja.business.model.User;
import com.andrievskaja.business.service.UserService;
import com.andrievskaja.business.service.model.form.RegisteForm;
import com.andrievskaja.business.service.model.view.UserView;
import com.andrievskaja.dao.UserRepository;
import static com.andrievskaja.specification.UserSpecifications.user_role;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.Specifications.where;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Людмила
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();

    /**
     *
     * create new user
     */
    @Override
    @Transactional
    public UserView createUSer(RegisteForm registeForm) {
        User user = new User();
        user.setEmail(registeForm.getEmail());
        user.setName(registeForm.getName());
        user.setPassword(passwordEncoder.encode(registeForm.getPassword()));
        user.setRole(Role.valueOf(registeForm.getRole()));
        userRepository.save(user);
        return mapper.map(user, UserView.class);
    }

    /**
     *
     * get all users
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserView> getAll() {
        List<UserView> userViews = new ArrayList<>();
        userRepository.findAll().stream().forEach((user) -> {
            userViews.add(mapper.map(user, UserView.class));
        });
        return userViews;
    }

    /**
     *
     * delete user by id
     */
    @Override
    @Transactional
    public boolean delete(Long id) {
        User user = userRepository.findOne(id);
        if (user == null || user.getRole().equals(Role.ROLE_ADMIN)) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    /**
     *
     * get user by id
     */
    @Override
    @Transactional(readOnly = true)
    public UserView getUser(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            return null;
        }
        return mapper.map(user, UserView.class);
    }

    /**
     * get user where role equal ROLE_PUBLISHER return start page for ROLE_ADOPS
     */
    @Override
    @Transactional(readOnly = true)
    public List<UserView> getPublisherUSer() {
        List<UserView> userViews = new ArrayList<>();
        List<User> users = userRepository.findAll(where(user_role(Role.ROLE_PUBLISHER)));
        if (users.isEmpty()) {
            return null;
        }
        users.stream().forEach((user) -> {
            userViews.add(mapper.map(user, UserView.class));
        });
        return userViews;
    }

    @Override
    @Transactional
    public UserView editUser(RegisteForm registeForm, Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            return null;
        }
        user.setEmail(registeForm.getEmail());
        user.setName(registeForm.getName());
        user.setRole(Role.valueOf(registeForm.getRole()));
        userRepository.save(user);
        return mapper.map(user, UserView.class);
    }
}
