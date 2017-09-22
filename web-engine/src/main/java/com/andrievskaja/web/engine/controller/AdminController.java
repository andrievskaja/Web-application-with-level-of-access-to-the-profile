/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.web.engine.controller;

import com.andrievskaja.business.service.UserService;
import com.andrievskaja.business.service.model.form.RegisteForm;
import com.andrievskaja.business.service.model.view.UserView;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Людмила
 */
@Controller
@RequestMapping("/portal/admin")
@Secured({"ROLE_ADMIN"})
public class AdminController {

    @Autowired
    private UserService userService;

    /**
     *
     * return start page with list users
     */
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("users", userService.getAll());
        return "portal/admin/admin";
    }

    /**
     *
     * create new user
     *
     * @param registeForm
     * @return admin page
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String add(@Valid RegisteForm registeForm, Model model) {
        userService.createUSer(registeForm);
         return "redirect:/portal/admin/admin";
    }

    /**
     *
     * return page form for creating new user
     */
    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public String formPage() {
        return "portal/admin/add-user";
    }

    /**
     * It is jquery request from portal/admin/admin.jsp.
     *
     * @param id user for deleting.
     * @return If user delete, return true else false
     * if Role user equal ROLE_ADMIN, user don't be delete
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public boolean delete(Long id) {
        return userService.delete(id);
    }
 
        /**
     * It is jquery request from portal/admin/admin.jsp.
     *
     * @param id user for editing.
     * @return userView
     * 
     */
    @ResponseBody
    @RequestMapping(value = "/getOne", method = RequestMethod.POST)
    public UserView get(Long id) {
        return userService.getUser(id);
    }

 
        /**
     * It is jquery request from portal/admin/admin.jsp.
     *
     * @param registeForm
     * @param id user for editing.
     * @return userView
     * 
     */
    @ResponseBody
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public UserView editUser(@Valid RegisteForm registeForm, Long userId) {
        return userService.editUser(registeForm,userId);
    }
}
