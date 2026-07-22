package com.oneautomation.framework.util.wrapper;

import com.oneautomation.framework.locator.Locator;

public interface InteractionHandler {

    void clickByAction(Locator locator);

    void doubleClick(Locator locator);

    void contextClickByAction(Locator locator);

    void hoverByAction(Locator locator);

    void sendKeysByAction(Locator locator, String text);

    void moveByOffset(int xOffset, int yOffset);
}
