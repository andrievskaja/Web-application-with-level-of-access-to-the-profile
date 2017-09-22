/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.web.engine.controller;

import com.andrievskaja.business.service.AppService;
import com.andrievskaja.business.service.UserService;
import com.andrievskaja.business.service.model.form.AppForm;
import com.andrievskaja.business.service.model.form.RegisteForm;
import com.andrievskaja.business.service.model.view.AppView;
import com.andrievskaja.business.service.model.view.UserView;
import com.andrievskaja.dao.AppRepository;
import com.google.gson.Gson;
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
@RequestMapping("/portal/adops")
@Secured({"ROLE_ADOPS"})
public class AdopsController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private AppService appService;

    /**
     *
     * return start page with list users
     */
    @RequestMapping(value = "/adops", method = RequestMethod.GET)
    public String adopsPage(Model model) {
        model.addAttribute("users", userService.getPublisherUSer());
        model.addAttribute("apps", appService.getAll());
        
        return "portal/adops/adops";
    }

    /**
     * It is jquery request from portal/adops/adops.jsp.
     *
     * @param id user for editing.
     * @return userView
     *
     */
    @ResponseBody
    @RequestMapping(value = "/getOne", method = RequestMethod.POST)
    public UserView edit(Long id) {
        return userService.getUser(id);
    }

    @ResponseBody
    @RequestMapping(value = "/getApp", method = RequestMethod.POST)
    public  AppView editApp(Long id, Long userId) {
        
        return appService.getApp(id,userId);
    }

    /**
     * It is jquery request from portal/adops/adops.jsp.
     *
     * @param id user for deleting.
     * @return If user deleted, return true
     */
    @ResponseBody
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public boolean delete(Long id) {
        return userService.delete(id);
    }
    @ResponseBody
    @RequestMapping(value = "/deleteApp", method = RequestMethod.POST)
    public boolean deleteApp(Long id, Long userId) {
        return appService.appDelete(id, userId);
    }

    /**
     *
     * create new user
     *
     * @param registeForm
     * @return admin page
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String add(@Valid RegisteForm registeForm) {
        registeForm.setRole("ROLE_PUBLISHER");
        userService.createUSer(registeForm);
        return "redirect:/portal/adops/adops";
    }

    /**
     *
     * return page form for creating new user
     */
    @RequestMapping(value = "/add-user", method = RequestMethod.GET)
    public String formPage() {
        return "portal/adops/add-user";
    }
    
    @ResponseBody
    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public UserView editUser(@Valid RegisteForm registeForm, Long userId) {
        return userService.editUser(registeForm, userId);
    }
    @ResponseBody
    @RequestMapping(value = "/editApp", method = RequestMethod.POST)
    public AppView editApp(@Valid AppForm appform, Long userId) {
        return appService.editApp(appform);
    }
    
    @RequestMapping(value = "/add-app-page", method = RequestMethod.GET)
    public String addAppPage(Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "portal/adops/add-app";
    }
    
    @RequestMapping(value = "/createApp", method = RequestMethod.POST)
    public String createApp(@Valid AppForm appForm, Model model) {
        appService.create(appForm);
        return "redirect:/portal/adops/adops";
    }
}
