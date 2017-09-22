package com.andrievskaja.business.service;

import com.andrievskaja.business.service.model.form.RegisteForm;
import com.andrievskaja.business.service.model.view.UserView;
import java.util.List;

/**
 *
 * @author Людмила
 */
public interface UserService {

    public UserView createUSer(RegisteForm registeForm);

    public UserView editUser(RegisteForm registeForm, Long id);

    public UserView getUser(Long id);

    public List<UserView> getAll();

    public boolean delete(Long id);

    public List<UserView> getPublisherUSer();

}
