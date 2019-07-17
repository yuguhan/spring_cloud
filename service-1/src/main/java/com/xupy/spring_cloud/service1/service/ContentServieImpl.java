package com.xupy.spring_cloud.service1.service;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;

import org.springframework.stereotype.Service;

@Service
public class ContentServieImpl implements ContentService {
    private String url = "http://gw.api.tbsandbox.com/router/rest";
    private String appKey = "";
    private String secret = "";
    private long skillId = 0L;
    public void execute() {
        TaobaoClient client = new DefaultTaobaoClient(url, appKey, secret);

    }
}
