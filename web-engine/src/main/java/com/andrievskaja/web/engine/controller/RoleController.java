/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.web.engine.controller;

/**
 *
 * @author Людмила
 */
import com.andrievskaja.business.model.UserProfile;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

    /**
     *
     *This controller accept request and redirect to controller by user Role 
     * AdminConroller
     * AdopsController
     * PublisherController
     * It have only one method
     */
@Controller
@RequestMapping("/portal/user")
@Secured({"ROLE_ADOPS", "ROLE_ADMIN", "ROLE_PUBLISHER"})
public class RoleController {

    @RequestMapping(method = RequestMethod.GET)
    public String getRecord(Model model, HttpServletRequest request) {
        UserProfile user = (UserProfile) SecurityContextHolder.getContext().getAuthentication().getPrincipal();        
            if (user.isAdmin()) {
            return "redirect:/portal/admin/admin";
        }
            if (user.isPublisher()) {
            return "redirect:/portal/publisher/publisher";
        }
            if (user.isAdops()) {
            return "redirect:/portal/adops/adops";
        }
        return "portal/bloknot";
    }
}
