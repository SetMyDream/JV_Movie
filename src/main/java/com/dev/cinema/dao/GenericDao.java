package com.dev.cinema.dao;

import java.util.List;

public interface GenericDao<T> {
    T add(T entity);

    List<T> getAll(Class<T> clazz);
}
