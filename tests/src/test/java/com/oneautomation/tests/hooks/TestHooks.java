package com.oneautomation.tests.hooks;

import com.oneautomation.framework.logging.TestNGLogUtility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class TestHooks {

    @Before(order = 1)
    public void beforeScenario(Scenario scenario) {
        TestNGLogUtility.info("=== Scenario Started: {} ===", scenario.getName());
        TestNGLogUtility.info("Tags: {}", scenario.getSourceTagNames());
    }

    @After(order = 1)
    public void afterScenario(Scenario scenario) {
        TestNGLogUtility.info("=== Scenario Finished: {} - Status: {} ===",
                scenario.getName(), scenario.getStatus());
    }
}
