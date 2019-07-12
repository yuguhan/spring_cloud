package com.xupy.spring_cloud.service1.service;

import com.alibaba.da.coin.ide.spi.standard.TaskQuery;
import com.alibaba.da.coin.ide.spi.standard.TaskResult;

public interface StoryHandle {
    TaskResult execute(TaskQuery taskQuery);
}
