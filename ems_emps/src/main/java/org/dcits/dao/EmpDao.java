package org.dcits.dao;

import org.dcits.entity.Emp;

import java.util.List;


public interface EmpDao {

    List<Emp> findAll();

    void save(Emp emp);

    void delete(Integer id);

    Emp findOne(Integer id);

    void update(Emp emp);
}
