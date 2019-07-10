package com.xupy.spring_cloud.service1;

import com.alibaba.da.coin.ide.spi.meta.ExecuteCode;
import com.alibaba.da.coin.ide.spi.meta.ResultType;
import com.alibaba.da.coin.ide.spi.meta.SlotEntity;
import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;

// 天气服务执行，根据NLU理解的结果做相应处理并返回回复语句
@Component
@Slf4j
public class WeatherHandleImpl implements WeatherHandle {

    @Override
    public TaskResult execute(TaskQuery taskQuery) {
        log.info("WeatherHandleImpl execute...");

        //从请求中获取意图参数以及参数值
        Map<String, String> paramMap = taskQuery
                .getSlotEntities()
                .stream()
                .collect(
                        Collectors.toMap(SlotEntity::getIntentParameterName,
                                SlotEntity::getStandardValue));
        log.info("paramMap ：" + paramMap.toString());
        //如果意图是询问空气质量，则执行空气质量逻辑
       if (taskQuery.getIntentName().equals("天气查询")) {
            return baseQuery(taskQuery, paramMap);
        } else {
            return null;
        }
    }

    private TaskResult baseQuery(TaskQuery taskQuery, Map<String, String> paramMap) {
        TaskResult result = new TaskResult();
        try {
            String story = paramMap.get("story");
            result.setReply("听怪蜀黍给你讲" + story + "。");
            result.setExecuteCode(ExecuteCode.SUCCESS);
            result.setResultType(ResultType.RESULT);
        } catch (Exception e) {
            log.info("query exception", e);
            result.setExecuteCode(ExecuteCode.EXECUTE_ERROR);
        }

        return result;
    }
}