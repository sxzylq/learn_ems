package org.dcits.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String realname;
    private String password;
    private String sex;
    private String status;
    private Date registerTime;
}
