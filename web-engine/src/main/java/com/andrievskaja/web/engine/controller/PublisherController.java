/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.web.engine.controller;

import com.andrievskaja.business.model.UserProfile;
import com.andrievskaja.business.service.AppService;
import com.andrievskaja.business.service.model.form.AppForm;
import com.andrievskaja.business.service.model.view.AppView;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/portal/publisher")
@Secured({"ROLE_PUBLISHER"})
public class PublisherController {

    @Autowired
    private AppService appService;

    /**
     *
     * return start page with list users
     */
    @RequestMapping(value = "/publisher", method = RequestMethod.GET)
    public String adopsPage(Model model) {
        model.addAttribute("apps", appService.getAll());

        return "portal/publisher/publisher";
    }

    /**
     *find one app for edit
     * return app
     */

    @ResponseBody
    @RequestMapping(value = "/getApp", method = RequestMethod.POST)
    public AppView editApp(Long id) {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return appService.getApp(id, user.getUserId());
    }

    /**
     * It is jquery request from portal/publisher/publisher.jsp.
     *
     * @param id user for deleting.
     * @param userId
     * @return If app deleted, return true else false
     */
    @ResponseBody
    @RequestMapping(value = "/deleteApp", method = RequestMethod.POST)
    public boolean deleteApp(Long id) {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return appService.appDelete(id, user.getUserId());
    }

    @ResponseBody
    @RequestMapping(value = "/editApp", method = RequestMethod.POST)
    public AppView editApp(@Valid AppForm appform, Long userId) {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        appform.setUserId(user.getUserId());
        return appService.editApp(appform);
    }

    @RequestMapping(value = "/add-app", method = RequestMethod.GET)
    public String addAppPage() {
        return "portal/publisher/add-app";
    }

    @RequestMapping(value = "/createApp", method = RequestMethod.POST)
    public String createApp(@Valid AppForm appForm, Model model) {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        appForm.setUserId(user.getUserId());
        appService.create(appForm);
        return "redirect:/portal/publisher/publisher";
    }
}
