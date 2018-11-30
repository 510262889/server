package com.spring.server;

import com.spring.server.fetch.FetchHandler;
import com.spring.server.fetch.impl.FetchDetector;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        //SpringApplication.run(ServerApplication.class, args);
        FetchHandler fetchHandler = new FetchDetector();
        fetchHandler.fetch();
    }
}
