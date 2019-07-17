package com.xupy.spring_cloud.service1.model;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

@Data
public class StudentEntity {
    @Excel(name = "姓名")
    private String name;
    @Excel(name = "生日")
    private String birthday;
    @Excel(name = "性别", type = 10)
    private int sex;
}
