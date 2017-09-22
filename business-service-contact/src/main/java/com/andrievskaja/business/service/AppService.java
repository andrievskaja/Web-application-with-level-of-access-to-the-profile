/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service;

import com.andrievskaja.business.service.model.form.AppForm;
import com.andrievskaja.business.service.model.view.AppView;
import java.util.List;

/**
 *
 * @author Людмила
 */
public interface AppService {

    public AppView create(AppForm appform);

    public AppView getApp(Long id, Long userId);

    public List<AppView> getAll();

    public AppView editApp(AppForm appForm);

    public boolean appDelete(Long id, Long userId);
}
