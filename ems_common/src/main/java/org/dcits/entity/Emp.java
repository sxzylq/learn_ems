package org.dcits.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
public class Emp {
    private Integer id;
    private String name;
    private String path;
    private String salary;
    private Integer age;
}
