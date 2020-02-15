package com.pw486.springrest.dao;

import java.io.Serializable;
import java.util.List;

public interface PostDao<T> extends Serializable {

  List<T> getAll();

  T findById(long id);

}
