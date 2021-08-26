package org.dcits.controller;

import lombok.extern.slf4j.Slf4j;
import org.dcits.clients.FileClients;
import org.dcits.entity.Emp;
import org.dcits.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class EmpsController {

    @Autowired
    private EmpService empsService;

    @Autowired
    private FileClients fileClients;

    @PostMapping("/emp/save")
    public Map<String,Object> save(Emp emp , MultipartFile photo){
        Map<String , Object> retrunMap = new HashMap<>();

        try {
            //1.文件上传
            Map<String, Object> fileResult = fileClients.upload(photo);
            if (fileResult.isEmpty() &&fileResult.get("stats").equals("false")) {
                throw new RuntimeException("头像保存失败");
            }
            log.info("头像地址为："+ fileResult.get("url").toString());
            //2.保存员工信息
            emp.setPath(fileResult.get("url").toString());
            empsService.save(emp);
            retrunMap.put("msg","提示:员工信息保存成功!");
            retrunMap.put("state",true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            retrunMap.put("msg","提示:员工信息保存失败!");
            retrunMap.put("state",false);
        }
        return retrunMap;
    }

    @GetMapping("emp/findAll")
    public Map<String , Object> findAll(){
        Map<String , Object> retrunMap = new HashMap<>();
        List<Emp> allEmp = empsService.findAll();
        retrunMap.put("empList",allEmp);
        retrunMap.put("msg","信息获取成功");
        retrunMap.put("state","ture");
        return retrunMap;
    }

    @GetMapping("emp/findOne")
    public Map<String , Object> findOne(@RequestParam Integer id){
        Map<String , Object> retrunMap = new HashMap<>();
        Emp emp = empsService.findOne(id);
        retrunMap.put("emp",emp);
        retrunMap.put("msg","信息获取成功");
        retrunMap.put("state","ture");
        return retrunMap;
    }

    @GetMapping("emp/delete")
    public Map<String , Object> delete(@RequestParam Integer id){
        Map<String , Object> retrunMap = new HashMap<>();
        empsService.delete(id);
        retrunMap.put("msg","删除员工信息成功!");
        retrunMap.put("state","ture");
        return retrunMap;
    }

    @PostMapping("emp/update")
    public Map<String , Object> update(Emp emp , MultipartFile photo){
        Map<String , Object> retrunMap = new HashMap<>();

        try {
            //1.文件上传
            Map<String, Object> fileResult = fileClients.upload(photo);
            if (fileResult.isEmpty() &&fileResult.get("stats").equals("false")) {
                throw new RuntimeException("头像保存失败");
            }
            log.info("头像地址为："+ fileResult.get("url").toString());
            //2.更新员工信息
            emp.setPath(fileResult.get("url").toString());
            empsService.update(emp);
            retrunMap.put("msg","提示:员工信息更新成功!");
            retrunMap.put("state",true);
        } catch (RuntimeException e) {
            e.printStackTrace();
            retrunMap.put("msg","提示:员工信息更新失败!");
            retrunMap.put("state",false);
        }
        return retrunMap;
    }
}
