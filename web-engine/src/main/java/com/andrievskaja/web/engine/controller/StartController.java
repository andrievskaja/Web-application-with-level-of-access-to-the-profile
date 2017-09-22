/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.web.engine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Людмила
 */
@Controller
@RequestMapping("/")
public class StartController {

    @RequestMapping("/")
    public String startPage(Model model) {
        return "/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        return "/register";
    }

    @RequestMapping(value = "/portal/user/authorization_error")
    public String authorizationError(ModelMap model
    ) {
        return "redirect:/login";

    }
}
