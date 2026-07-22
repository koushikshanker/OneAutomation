package com.oneautomation.framework.hooks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {

    private static final Logger log = LoggerFactory.getLogger(TestNGListener.class);

    @Override
    public void onStart(ITestContext context) {
        log.info("=== Test Suite Started: {} ===", context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info("=== Test Suite Finished: {} ===", context.getName());
        log.info("Passed: {}, Failed: {}, Skipped: {}",
                context.getPassedTests().size(),
                context.getFailedTests().size(),
                context.getSkippedTests().size());
    }

    @Override
    public void onTestStart(ITestResult result) {
        log.info("Test Started: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test Passed: {}", result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.error("Test Failed: {}", result.getMethod().getMethodName());
        log.error("Failure reason: ", result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.warn("Test Skipped: {}", result.getMethod().getMethodName());
    }
}
