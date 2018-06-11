package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
        TITLE = "org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        MORE_OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
        ADD_TO_READING_LIST = "//*[@text='Add to reading list']",
        GOT_IT_BUTTON = "org.wikipedia:id/onboarding_button",
        OK_BUTTON = "//*[@text='OK']",
        CLOSE_X_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
        SEARCH_BUTTON = "org.wikipedia:id/menu_page_search",
        TABS_BUTTON = "org.wikipedia:id/menu_page_show_tabs",
        PAGE_TOOLBAR = "org.wikipedia:id/page_toolbar",
        FOLDER_NAME_INPUT = "org.wikipedia:id/text_input",
        FOLDER_NAME_SELECT_TPL = "//*[@text='{SUBSTRING}']";

    /* TEMPLATES METHODS */
    private static String getFolderElement(String substring){
        return FOLDER_NAME_SELECT_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE),"Cannot find article title on the page",15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),"Cannot find the end of the article",20);
    }

    public void clickMoreOptions() {
        this.waitForElementAndClick(By.xpath(MORE_OPTIONS_BUTTON),"Cannot find button to open article options",5);
    }

    public void clickAddToReadingList() {
        this.waitForElementAndClick(By.xpath(ADD_TO_READING_LIST),"Cannot find option to add article to reading list",5);
    }

    public void clickGotItButton() {
        this.waitForElementAndClick(By.id(GOT_IT_BUTTON),"Cannot find 'Got it' button",5);
    }

    public void clickOnFolderName(String substring) {
        String folder_select_xpath = getFolderElement(substring);
        this.waitForElementAndClick(By.xpath(folder_select_xpath),"Cannot find folder " + substring,5);
    }

    public void createArticlesFolder(String name_of_folder) {
        this.waitForElementAndClear(By.id(FOLDER_NAME_INPUT),"Cannot find input to set name of articles folder",5);
        this.waitForElementAndSendKeys(By.id(FOLDER_NAME_INPUT), name_of_folder, "Cannot put text into articles folder input",5);
        this.waitForElementAndClick(By.xpath(OK_BUTTON), "Cannot press OK button",5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(By.xpath(CLOSE_X_BUTTON),"Cannot close article, cannot find X link",5);
    }

    public void waitForNavBarLoad() {
        this.waitForElementPresent(By.id(SEARCH_BUTTON),"Cannot find search icon",5);
        this.waitForElementPresent(By.id(TABS_BUTTON),"Cannot find tabs icon",5);
        this.waitForElementPresent(By.id(PAGE_TOOLBAR),"Cannot find top nav menu",10);
    }
}
