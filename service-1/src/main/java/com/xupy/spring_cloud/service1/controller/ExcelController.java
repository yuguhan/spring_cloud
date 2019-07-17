package com.xupy.spring_cloud.service1.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.google.common.collect.Lists;
import com.xupy.spring_cloud.service1.model.StudentEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/api/service-1")
public class ExcelController {

    @GetMapping("/download")
    public Mono<Void> downExcel(ServerHttpResponse response) throws IOException {
        File file = new File("学生.xlsx");
        FileOutputStream fileOutputStream = null;
        try {
            ZeroCopyHttpOutputMessage zeroCopyHttpOutputMessage = (ZeroCopyHttpOutputMessage) response;
            response.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=学生信息.xlsx");
            response.getHeaders().setContentType(MediaType.IMAGE_PNG);

            ExportParams exportParams = new ExportParams("学生信息", "sheet1");
            exportParams.setType(ExcelType.XSSF);
            List<StudentEntity> students = getStudents();
            Workbook workbook = ExcelExportUtil.exportExcel(exportParams, StudentEntity.class, students);
            fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            return zeroCopyHttpOutputMessage.writeWith(file, 0, file.length());
        } finally {
            if (file.exists()) {
                file.delete();
            }
        }


    }

    private List<StudentEntity> getStudents(){
        StudentEntity studentEntity = new StudentEntity();
        studentEntity.setBirthday("2018-10-11");
        studentEntity.setName("小虎");
        studentEntity.setSex(1);

        StudentEntity studentEntity1 = new StudentEntity();
        studentEntity1.setBirthday("2018-10-11");
        studentEntity1.setName("小虎");
        studentEntity1.setSex(1);

        StudentEntity studentEntity2 = new StudentEntity();
        studentEntity2.setBirthday("2018-10-11");
        studentEntity2.setName("小虎");
        studentEntity2.setSex(1);
        return Lists.newArrayList(studentEntity, studentEntity1, studentEntity2);
    }
}
