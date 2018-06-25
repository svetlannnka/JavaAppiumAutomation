package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE ="id:org.wikipedia:id/view_page_title_text";
        TITLE_TPL = "xpath://*[contains(@text,'{TITLE}') and @resource-id='org.wikipedia:id/view_page_title_text']";
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']";
        MORE_OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']";
        ADD_TO_READING_LIST = "xpath://*[@text='Add to reading list']";
        GOT_IT_BUTTON = "id:org.wikipedia:id/onboarding_button";
        OK_BUTTON = "xpath://*[@text='OK']";
        CLOSE_X_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        SEARCH_BUTTON = "id:org.wikipedia:id/menu_page_search";
        TABS_BUTTON = "id:org.wikipedia:id/menu_page_show_tabs";
        PAGE_TOOLBAR = "id:org.wikipedia:id/page_toolbar";
        FOLDER_NAME_INPUT = "id:org.wikipedia:id/text_input";
        FOLDER_NAME_SELECT_TPL = "xpath://*[@text='{SUBSTRING}']";
        REMOVE_FROM_LIST = "xpath://*[contains(@text,'Remove from')]";
    }

    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
