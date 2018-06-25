package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_NAME_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        ARTICLE_ELEMENT = "xpath://XCUIElementTypeCell";
    }

    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
