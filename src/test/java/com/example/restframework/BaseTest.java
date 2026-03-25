package com.example.restframework;

import com.example.restframework.utils.extension.RunnerExtension;
import com.example.restframework.utils.extension.TestSuiteExtension;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith({RunnerExtension.class, TestSuiteExtension.class})
public abstract class BaseTest {
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
}
