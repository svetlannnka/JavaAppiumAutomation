package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class iOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE = "id:Java (programming language)";
        TITLE_TPL = "id:{TITLE}";
        FOOTER_ELEMENT = "id:View article in browser";
        ADD_TO_READING_LIST = "xpath://XCUIElementTypeButton[@name='Save for later']";
        CLOSE_X_BUTTON = "id:Back";
        SEARCH_BUTTON = "id:Search Wikipedia";
        ARTICLE_SAVED = "id:Saved. Activate to unsave.";
    }

    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}