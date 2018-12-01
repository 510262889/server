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
