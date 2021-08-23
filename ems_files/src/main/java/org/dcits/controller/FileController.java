package org.dcits.controller;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@Slf4j
public class FileController {

    @Value("${upload.dir}")
    private String realPath;

    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/file/upload",consumes = "multipart/form-data")
    public Map<String,Object> upload(@RequestPart("photo") MultipartFile photo) {
        HashMap<String, Object> map = new HashMap<>();
        log.info("接受到的文件名称为：" +photo.getOriginalFilename());

        try {
            //1.修改文件名称
            String extension = FilenameUtils.getExtension(photo.getOriginalFilename());
            String newFullName = UUID.randomUUID().toString() + "." + extension;
            //2.文件上传
            photo.transferTo(new File(realPath,newFullName));
            //3.返回响应地址
            String url = "http://loaclhost:" + port + "/" + newFullName;
            map.put("url",url);
            map.put("state","true");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg",e.getMessage());
            map.put("state","false");
        }
        return map;
    }
}