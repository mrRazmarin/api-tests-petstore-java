package com.example.restframework.utils.extension;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.concurrent.atomic.AtomicInteger;

public class RunnerExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    private static final AtomicInteger testCaseCount = new AtomicInteger(1);
    private static final Logger logger = LogManager.getLogger(RunnerExtension.class);


    @Override
    public void beforeTestExecution(ExtensionContext context) {
        logger.info("Start test id: {} | Name: '{}' - '{}' ", testCaseCount,
                context.getRequiredTestMethod().getName(), context.getDisplayName());
    }

    @Override
    public void afterTestExecution(ExtensionContext context) {
        logger.info("Finish test id: {} | Name: '{}' - '{}' | Status: '{}'",
                testCaseCount.getAndIncrement(), context.getRequiredTestMethod().getName(),
                context.getDisplayName(), checkStatus(context.getExecutionException().isPresent()));
        logger.info("==================");
    }

    private String checkStatus(boolean status) {
        if (status) {
            return "Failed";
        } else {
            return "Success";
        }
    }
}
