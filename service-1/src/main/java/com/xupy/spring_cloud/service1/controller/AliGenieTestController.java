package com.xupy.spring_cloud.service1.controller;

import com.alibaba.da.coin.ide.spi.standard.ResultModel;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import com.alibaba.da.coin.ide.spi.trans.MetaFormat;
import com.xupy.spring_cloud.service1.service.StoryHandle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/skill")
@Slf4j
public class AliGenieTestController {
    @Autowired
    private StoryHandle weatherHandle;
    @PostMapping("/story")
    public ResultModel<TaskResult> story(@RequestBody String taskQuery) {
        /*将开发者平台识别到的语义理解的结果（json字符串格式）转换成TaskQuery*/
        log.info("TaskQuery:{}", taskQuery);
        TaskQuery query = MetaFormat.parseToQuery(taskQuery);
        /* 构建服务返回结果 */
        ResultModel<TaskResult> resultModel = new ResultModel<>();
        try {
            /*调用天气服务执行并构建回复内容 */
            TaskResult result = weatherHandle.execute(query);
            resultModel.setReturnCode("0");
            resultModel.setReturnValue(result);
        } catch (Exception e) {
            resultModel.setReturnCode("-1");
            resultModel.setReturnErrorSolution(e.getMessage());
        }
        /*直接返回ResultModel<TaskResult>对象就ok */
        return resultModel;
    }



}
