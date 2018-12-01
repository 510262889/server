/*
 *  Copyright (c) 2017. 福建亿立方网络科技有限公司，版权所有。
 *  本项目所有的源代码、配置以及业务逻辑设计均属福建亿立方网络科技有限公司所有。
 *  未经许可对本项目的所有内容的不论任何形式的使用、扩散和传播都将视为侵权。
 */

package com.spring.server.service.impl;

import com.spring.server.fetch.impl.FetchDetector;
import com.spring.server.service.FetchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 采集服务
 *
 * @author ykzhuo
 * @since 2018-12-01 10:03
 */
@Service
public class FetchServiceImpl implements FetchService {

    @Autowired
    private FetchDetector fetchDetector;

    /**
     * 检测体采集
     */
    @Override
    public void fetchDetector() {
        fetchDetector.fetch();
    }
}
