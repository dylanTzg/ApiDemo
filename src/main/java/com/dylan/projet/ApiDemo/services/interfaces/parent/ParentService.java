package com.dylan.projet.ApiDemo.services.interfaces.parent;

import java.util.List;

public interface ParentService <T>{

    Integer save(T model);

    List<T> findAll();

    T findById(Integer id);

    void deleteById(Integer id);
}
