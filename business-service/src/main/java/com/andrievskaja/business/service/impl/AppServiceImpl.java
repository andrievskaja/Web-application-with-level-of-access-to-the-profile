/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andrievskaja.business.service.impl;

import com.andrievskaja.business.model.App;
import com.andrievskaja.business.model.AppType;
import com.andrievskaja.business.model.ContentType;
import com.andrievskaja.business.model.User;
import com.andrievskaja.business.service.AppService;
import com.andrievskaja.business.service.model.form.AppForm;
import com.andrievskaja.business.service.model.view.AppView;
import com.andrievskaja.dao.AppRepository;
import com.andrievskaja.dao.UserRepository;
import static com.andrievskaja.specification.AppSpecifications.app_userId;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.data.jpa.domain.Specifications.where;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Людмила
 */
@Service("appService")
public class AppServiceImpl implements AppService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppRepository appRepository;
    private final ModelMapper mapper = new ModelMapper();

    /**
     *
     * create new App
     *
     * @param appform
     * @return AppView
     */
    @Transactional
    @Override
    public AppView create(AppForm appform) {
        App app = this.newApp(appform);
        if (app == null) {
            return null;
        }
        User user = userRepository.findOne(appform.getUserId());
        if (user == null) {
            return null;
        }
        app.setUser(user);
        appRepository.save(app);
        return mapper.map(app, AppView.class);
    }

    /**
     *
     * Look off App by userId
     *
     * @param userId
     * @return AppView
     */
    @Transactional(readOnly = true)
    @Override
    public AppView getApp(Long id, Long userId) {
//        App app = appRepository.findOne(where(app_userId(userId)));
        App app = appRepository.findByIdAndUserId(id, userId);
        if (app == null) {
            return null;
        }
        return mapper.map(app, AppView.class);
    }

    private App newApp(AppForm appform) {
        App app = new App();

        app.setAppType(AppType.valueOf(appform.getAppType()));
        app.setContentType(ContentType.valueOf(appform.getContentType()));
        app.setName(appform.getName());;
        return app;
    }

    @Override
    public List<AppView> getAll() {
        List<AppView> list = new ArrayList<>();
        appRepository.findAll().stream().forEach((app) -> {
            list.add(mapper.map(app, AppView.class));
        });
        return list;
    }

    /**
     * Edit app by id and userId
     *
     * @param appForm
     * @return AppView
     */
    @Override
    public AppView editApp(AppForm appForm) {
        App app = appRepository.findByIdAndUserId(appForm.getId(), appForm.getUserId());
        if (app == null) {
            return null;
        }
        app.setAppType(AppType.valueOf(appForm.getAppType()));
        app.setContentType(ContentType.valueOf(appForm.getContentType()));
        app.setName(appForm.getName());
        appRepository.save(app);
        return mapper.map(app, AppView.class);
    }

    /**
     * Delete app by id and userId
     *
     * @param id
     * @param userId
     * @return boolean
     */
    @Override
    public boolean appDelete(Long id, Long userId) {
        App app = appRepository.findByIdAndUserId(id, userId);
        if (app == null) {
            return false;
        }
        appRepository.delete(app);
        return true;
    }
}
