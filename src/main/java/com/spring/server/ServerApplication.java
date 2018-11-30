package com.spring.server;

import com.spring.server.fetch.FetchHandler;
import com.spring.server.fetch.impl.FetchDetector;
import com.spring.server.util.Base64Util;
import com.spring.server.util.FileUtil;

import java.io.File;
import java.net.URL;

public class ServerApplication {
    public static void main(String[] args) {
        //SpringApplication.run(ServerApplication.class, args);
        FetchHandler fetchHandler = new FetchDetector();
        fetchHandler.fetch();
    }
}
