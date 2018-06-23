package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObject extends MyListsPageObject {
    static {
        ARTICLE_NAME_TPL = "xpath://XCUIElementTypeLink[contains(@name,'{SUBSTRING}')]";
        //ARTICLE_ELEMENT = "id:org.wikipedia:id/page_list_item_container";
        //XCUIElementTypeLink[@name="Java (programming language) Object-oriented programming language"]
    }

    public iOSMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}
