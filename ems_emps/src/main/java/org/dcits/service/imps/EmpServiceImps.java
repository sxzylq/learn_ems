package org.dcits.service.imps;

import org.dcits.dao.EmpDao;
import org.dcits.entity.Emp;
import org.dcits.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpServiceImps implements EmpService {

    @Autowired
    private EmpDao empsDao;

    @Override
    public List<Emp> findAll() {
        return empsDao.findAll();
    }

    @Override
    public void save(Emp emp) {
        empsDao.save(emp);
    }

    @Override
    public void delete(Integer id) {
        empsDao.delete(id);
    }

    @Override
    public Emp findOne(Integer id) {
        return empsDao.findOne(id);
    }

    @Override
    public void update(Emp emp) {
        empsDao.update(emp);
    }
}
