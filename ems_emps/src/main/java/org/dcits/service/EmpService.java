package org.dcits.service;

import org.dcits.entity.Emp;

import java.util.List;

public interface EmpService {

    List<Emp> findAll();

    void save(Emp emp);

    void delete(Integer id);

    Emp findOne(Integer id);

    void update(Emp emp);
}
