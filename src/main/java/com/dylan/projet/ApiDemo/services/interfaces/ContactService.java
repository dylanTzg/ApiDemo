package com.dylan.projet.ApiDemo.services.interfaces;

import com.dylan.projet.ApiDemo.models.Contact;
import com.dylan.projet.ApiDemo.services.interfaces.parent.ParentService;

import java.util.List;

public interface ContactService extends ParentService<Contact>{

    List<Contact> findAllByUserId(Integer userId);
}
