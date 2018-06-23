package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        FOOTER_ELEMENT = "id:View article in browser";
        ADD_TO_READING_LIST = "xpath://XCUIElementTypeButton[@name='Save for later']";
        CLOSE_X_BUTTON = "id:Back";
        SEARCH_BUTTON = "id:Search Wikipedia";

        TABS_BUTTON = "id:org.wikipedia:id/menu_page_show_tabs";
        PAGE_TOOLBAR = "id:org.wikipedia:id/page_toolbar";
        FOLDER_NAME_INPUT = "id:org.wikipedia:id/text_input";
        FOLDER_NAME_SELECT_TPL = "xpath://*[@text='{SUBSTRING}']";
    }

    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}