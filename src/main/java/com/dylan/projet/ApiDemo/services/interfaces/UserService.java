package com.dylan.projet.ApiDemo.services.interfaces;

import com.dylan.projet.ApiDemo.models.User;
import com.dylan.projet.ApiDemo.services.interfaces.parent.ParentService;

public interface UserService extends ParentService<User> {

   Integer validateAccount(Integer id);

   Integer invalidateAccount(Integer id);
}
