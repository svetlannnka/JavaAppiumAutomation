package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;

abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_NAME_TPL,
            ARTICLE_NAME_TPL,
            ARTICLE_ELEMENT;

    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    /* TEMPLATES METHODS */
    private static String getFolderNameElement(String substring) {
        return FOLDER_NAME_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getArticleElement(String substring) {
        return ARTICLE_NAME_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public void clickFolderName(String substring) {
        String folder_name_xpath = getFolderNameElement(substring);
        this.waitForElementAndClick(folder_name_xpath, "Cannot find '" + substring + "' folder", 10);
    }

    public void swipeArticleToRemove(String substring) {
        String article_name_xpath = getArticleElement(substring);
        this.swipeElementToLeft(article_name_xpath, "Cannot find saved article");
        if (Platform.getInstance().isIOS()) {
            this.clickElementToTheRightUpperCorner(article_name_xpath, "Cannot find saved article");
        }
        this.waitForElementNotPresent(article_name_xpath, "Cannot delete saved article", 5);
    }

    public void clickArticleName(String substring) {
        String article_name_xpath = getArticleElement(substring);
        this.waitForElementAndClick(article_name_xpath, "Cannot tap on a saved article title", 15);
    }

    public int getAmountOfListedArticles() {
        this.waitForElementPresent(ARTICLE_ELEMENT, "Cannot find saved articles", 15);
        return this.getAmountOfElements(ARTICLE_ELEMENT);
    }
}
