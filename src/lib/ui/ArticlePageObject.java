package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject {

    protected static String
            TITLE,
            FOOTER_ELEMENT,
            MORE_OPTIONS_BUTTON,
            ADD_TO_READING_LIST,
            GOT_IT_BUTTON,
            OK_BUTTON,
            CLOSE_X_BUTTON,
            SEARCH_BUTTON,
            TABS_BUTTON,
            PAGE_TOOLBAR,
            FOLDER_NAME_INPUT,
            FOLDER_NAME_SELECT_TPL;

    /* TEMPLATES METHODS */
    private static String getFolderElement(String substring) {
        return FOLDER_NAME_SELECT_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATES METHODS */

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(TITLE, "Cannot find article title on the page", 15);
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        if (lib.Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void assertArticleTitlePresent() {
        this.assertElementPresent(TITLE, "Title not found.");
    }

    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of the article", 40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of the article", 40);
        }
    }

    public void clickMoreOptions() {
        this.waitForElementAndClick(MORE_OPTIONS_BUTTON, "Cannot find button to open article options", 5);
    }

    public void clickAddToReadingList() {
        this.waitForElementAndClick(ADD_TO_READING_LIST, "Cannot find option to add article to reading list", 5);
    }

    public void clickGotItButton() {
        this.waitForElementAndClick(GOT_IT_BUTTON, "Cannot find 'Got it' button", 5);
    }

    // for Android only
    public void addArticleToMyList(String name_of_folder) {
        this.clickMoreOptions();
        this.clickAddToReadingList();
        this.clickGotItButton();
        this.createArticlesFolder(name_of_folder);
    }

    // for iOS only
    public void addArticlesToMySaved() {
        this.waitForElementAndClick(ADD_TO_READING_LIST, "Cannot find option to add article to reading list", 5);
    }

    public void clickOnFolderName(String substring) {
        String folder_select_xpath = getFolderElement(substring);
        this.waitForElementAndClick(folder_select_xpath, "Cannot find folder " + substring, 5);
    }

    public void createArticlesFolder(String name_of_folder) {
        this.waitForElementAndClear(FOLDER_NAME_INPUT, "Cannot find input to set name of articles folder", 5);
        this.waitForElementAndSendKeys(FOLDER_NAME_INPUT, name_of_folder, "Cannot put text into articles folder input", 5);
        this.waitForElementAndClick(OK_BUTTON, "Cannot press OK button", 5);
    }

    public void closeArticle() {
        this.waitForElementAndClick(CLOSE_X_BUTTON, "Cannot close article, cannot find X link", 5);
    }

    public void waitForNavBarLoad() {
        this.waitForElementPresent(SEARCH_BUTTON, "Cannot find search icon", 5);
        this.waitForElementPresent(TABS_BUTTON, "Cannot find tabs icon", 5);
        this.waitForElementPresent(PAGE_TOOLBAR, "Cannot find top nav menu", 10);
    }
}
