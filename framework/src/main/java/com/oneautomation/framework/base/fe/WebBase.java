package com.oneautomation.framework.base.fe;

import com.oneautomation.framework.base.CommonAutomation;
import com.oneautomation.framework.driver.DriverManager;
import com.oneautomation.framework.util.fe.SeleniumElementActions;
import com.oneautomation.framework.util.fe.SeleniumInteractionHandler;
import com.oneautomation.framework.util.fe.SeleniumJavaScriptSupport;
import com.oneautomation.framework.util.fe.SeleniumWaitStrategy;
import com.oneautomation.framework.util.wrapper.ElementActions;
import com.oneautomation.framework.util.wrapper.InteractionHandler;
import com.oneautomation.framework.util.wrapper.JavaScriptSupport;
import com.oneautomation.framework.util.wrapper.WaitStrategy;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WebBase extends CommonAutomation {

    public RemoteWebDriver driver = DriverManager.getInstance().getDriver();
    public ElementActions genericMethod = new SeleniumElementActions();
    public WaitStrategy wait = new SeleniumWaitStrategy();
    public JavaScriptSupport jsUtil = new SeleniumJavaScriptSupport();
    public InteractionHandler actionBuilderUtility = new SeleniumInteractionHandler();
}
