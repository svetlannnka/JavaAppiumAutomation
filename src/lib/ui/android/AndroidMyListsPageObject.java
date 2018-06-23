package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class AndroidMyListsPageObject extends MyListsPageObject {
    static {
        FOLDER_NAME_TPL = "xpath://android.widget.TextView[@text='{SUBSTRING}']";
        ARTICLE_NAME_TPL = "xpath://*[@text='{SUBSTRING}']";
        ARTICLE_ELEMENT = "id:org.wikipedia:id/page_list_item_container";
    }

    public AndroidMyListsPageObject(AppiumDriver driver) {
        super(driver);
    }
}