package com.xupy.spring_cloud.service1.service;

import com.alibaba.da.coin.ide.spi.meta.Action;
import com.alibaba.da.coin.ide.spi.meta.ExecuteCode;
import com.alibaba.da.coin.ide.spi.meta.ResultType;
import com.alibaba.da.coin.ide.spi.meta.SlotEntity;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
@Component
@Slf4j
public class StoryHandleImpl implements StoryHandle {
    @Override
    public TaskResult execute(TaskQuery taskQuery) {
        log.info("WeatherHandleImpl execute...");
        //从请求中获取意图参数以及参数值
        Map<String, String> paramMap = taskQuery
                .getSlotEntities()
                .stream()
                .collect(Collectors.toMap(
                        SlotEntity::getIntentParameterName,
                        SlotEntity::getStandardValue));

        log.info("paramMap ：" + paramMap.toString());
       if ("故事".equals(taskQuery.getIntentName())) {
           return whatStory();
        } else if ("红卡讲故事".equals(taskQuery.getIntentName())){
           return baseQuery(paramMap);
        }
        return null;
    }

    private TaskResult baseQuery(Map<String, String> paramMap) {
        TaskResult result = new TaskResult();
        try {
            String story = paramMap.get("story");
            if (StringUtils.isBlank(story)) {
                result.setReply("想听怪蜀黍给你讲什么故事？");
                result.setExecuteCode(ExecuteCode.SUCCESS);
                result.setResultType(ResultType.RESULT);
            }else{
                if ("雷锋精神".equals(story)) {
                    result.setReply("听怪蜀黍给你讲" + story + "。");
                    result.setExecuteCode(ExecuteCode.SUCCESS);
                    result.setResultType(ResultType.RESULT);
                    Action action = new Action("audioPlayGenieSource");
                    action.addProperty("audioGenieId", "21523");
                    result.addAction(action);
                }else{
                    result.setReply("怪蜀黍还没准备好" + story + "这个故事哦～");
                    result.setExecuteCode(ExecuteCode.SUCCESS);
                    result.setResultType(ResultType.RESULT);
                }
            }
        } catch (Exception e) {
            log.info("query exception", e);
            result.setExecuteCode(ExecuteCode.EXECUTE_ERROR);
        }
        return result;
    }

    private TaskResult whatStory() {
        TaskResult result = new TaskResult();
        try {
            result.setReply("需要怪蜀黍给你讲什么故事 ？");
            result.setExecuteCode(ExecuteCode.SUCCESS);
            result.setResultType(ResultType.RESULT);
        } catch (Exception e) {
            log.info("query exception", e);
            result.setExecuteCode(ExecuteCode.EXECUTE_ERROR);
        }

        return result;
    }
}