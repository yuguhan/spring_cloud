package com.xupy.spring_cloud.service1;

import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;

public interface WeatherHandle {
    TaskResult execute(TaskQuery taskQuery);
}
