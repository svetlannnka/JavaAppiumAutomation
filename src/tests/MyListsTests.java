package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList() {
        String article_name = "Java (programming language)";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.clickFolderName(name_of_folder);
        }
        MyListsPageObject.swipeArticleToRemove(article_name);
    }

    @Test
    public void testSaveTwoArticlesToFolder() {
        String name_of_folder = "Learning programming";
        String article_name1 = "Java (programming language)";
        String article_name2 = "Scala (programming language)";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElementByTitle(article_name1);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        if (Platform.getInstance().isIOS()) {
            SearchPageObject.waitAndClickCLearSearchButton();
        }
        SearchPageObject.typeSearchLine("Scala");
        SearchPageObject.clickByArticleWithSubstring("Programming language");

        ArticlePageObject.waitForTitleElementByTitle(article_name2);
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.clickMoreOptions();
            ArticlePageObject.clickAddToReadingList();
            ArticlePageObject.clickOnFolderName(name_of_folder);
            // added waitForNavBarLoad for menu and closing X to make the test stable before click
            ArticlePageObject.waitForNavBarLoad();
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.clickFolderName(name_of_folder);
        }

        int amount_of_articles_before = MyListsPageObject.getAmountOfListedArticles();
        MyListsPageObject.swipeArticleToRemove(article_name2);
        int amount_of_articles_after = MyListsPageObject.getAmountOfListedArticles();
        assertEquals(
                "We found wrong amount of articles on the list",
                amount_of_articles_before - 1,
                amount_of_articles_after
        );

        MyListsPageObject.clickArticleName(article_name1);
        String actual_title = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Article title does not match",
                article_name1,
                actual_title
        );
        ArticlePageObject.checkArticleSaved();
    }
}
