package com.dylan.projet.ApiDemo.services.interfaces;

import java.util.List;

public interface ParentService <T>{

    Integer save(T object);

    List<T> findAll();

    T findById(Integer id);

    void deleteById(Integer id);
}
