package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        String name_of_folder = "Learning programming";
        String article_name = "Java (programming language)";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.clickMoreOptions();
        ArticlePageObject.clickAddToReadingList();
        ArticlePageObject.clickGotItButton();
        ArticlePageObject.createArticlesFolder(name_of_folder);
        ArticlePageObject.closeArticle();
        SearchPageObject.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.clickFolderName(name_of_folder);
        MyListsPageObject.swipeArticleToRemove(article_name);
    }

    @Test
    public void testSaveTwoArticlesToFolder() {
        String name_of_folder = "Learning programming";
        String article_name = "Java (programming language)";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.clickMoreOptions();
        ArticlePageObject.clickAddToReadingList();
        ArticlePageObject.clickGotItButton();
        ArticlePageObject.createArticlesFolder(name_of_folder);
        ArticlePageObject.closeArticle();

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Scala");
        SearchPageObject.clickByArticleWithSubstring("Programming language");

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.clickMoreOptions();
        ArticlePageObject.clickAddToReadingList();
        ArticlePageObject.clickOnFolderName(name_of_folder);

        // added waitForNavBarLoad for menu and closing X to make the test stable before click
        ArticlePageObject.waitForNavBarLoad();
        ArticlePageObject.closeArticle();
        SearchPageObject.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.clickFolderName(name_of_folder);
        int amount_of_articles_before = MyListsPageObject.getAmountOfListedArticles();
        MyListsPageObject.swipeArticleToRemove(article_name);
        int amount_of_articles_after = MyListsPageObject.getAmountOfListedArticles();

        assertEquals(
                "We found wrong amount of articles on the list",
                amount_of_articles_before - 1,
                amount_of_articles_after
        );

        String expected_article_title = "Scala (programming language)";
        MyListsPageObject.clickArticleName(expected_article_title);
        String actual_title = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article title has been changed after screen rotation",
                expected_article_title,
                actual_title
        );
    }

}
