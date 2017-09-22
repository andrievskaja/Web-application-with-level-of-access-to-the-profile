/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service.model.view;

import java.util.List;

/**
 *
 * @author Людмила
 */
public class PublishAndApp {
    List<AppView> listApp;
    List<UserView>listUser;

    public PublishAndApp() {
    }

    
    public PublishAndApp(List<AppView> listApp, List<UserView> listUser) {
        this.listApp = listApp;
        this.listUser = listUser;
    }

    public List<AppView> getListApp() {
        return listApp;
    }

    public void setListApp(List<AppView> listApp) {
        this.listApp = listApp;
    }

    public List<UserView> getListUser() {
        return listUser;
    }

    public void setListUser(List<UserView> listUser) {
        this.listUser = listUser;
    }
}
