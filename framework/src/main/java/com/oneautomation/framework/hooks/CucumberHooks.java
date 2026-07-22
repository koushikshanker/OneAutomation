package com.oneautomation.framework.hooks;

import com.oneautomation.framework.context.SessionInitiator;
import com.oneautomation.framework.logging.TestNGLogUtility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class CucumberHooks {

    private final SessionInitiator session = SessionInitiator.getInstance();

    @Before(value = "@ui", order = 0)
    public void beforeUIScenario(Scenario scenario) {
        TestNGLogUtility.info("Starting UI scenario: {}", scenario.getName());
        session.initializeSession();
        session.initializeDriver();
    }

    @Before(value = "@api", order = 0)
    public void beforeAPIScenario(Scenario scenario) {
        TestNGLogUtility.info("Starting API scenario: {}", scenario.getName());
        session.initializeSession();
    }

    @After(value = "@ui", order = 0)
    public void afterUIScenario(Scenario scenario) {
        TestNGLogUtility.info("Finishing UI scenario: {} - Status: {}", scenario.getName(), scenario.getStatus());
        session.saveScreenshot(scenario);
        session.closeDriverSession();
    }

    @After(value = "@api", order = 0)
    public void afterAPIScenario(Scenario scenario) {
        TestNGLogUtility.info("Finishing API scenario: {} - Status: {}", scenario.getName(), scenario.getStatus());
    }
}
