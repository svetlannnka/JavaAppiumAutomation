package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    private static final String
            FOLDER_NAME_TPL = "//android.widget.TextView[@text='{SUBSTRING}']",
            ARTICLE_NAME_TPL = "//*[@text='{SUBSTRING}']",
            ARTICLE_ELEMENT = "org.wikipedia:id/page_list_item_container";

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
        this.waitForElementAndClick(By.xpath(folder_name_xpath), "Cannot find '" + substring + "' folder", 10);
    }

    public void swipeArticleToRemove(String substring) {
        String article_name_xpath = getArticleElement(substring);
        this.swipeElementToLeft(By.xpath(article_name_xpath), "Cannot find saved article");
        this.waitForElementNotPresent(By.xpath(article_name_xpath), "Cannot delete saved article", 5);
    }

    public void clickArticleName(String substring) {
        String article_name_xpath = getArticleElement(substring);
        this.waitForElementAndClick(By.xpath(article_name_xpath), "Cannot tap on a saved article title", 15);
    }

    public int getAmountOfListedArticles() {
        this.waitForElementPresent(By.id(ARTICLE_ELEMENT), "Cannot find saved articles", 15);
        return this.getAmountOfElements(By.id(ARTICLE_ELEMENT));
    }
}
