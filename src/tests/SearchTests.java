package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchTests extends CoreTestCase {

    @Test
    public void testSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    public void testCancelSearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testCompareSearchPlaceholderText() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        String placeholder_text = SearchPageObject.getSearchInputPlaceholderText();

        assertEquals(
                "We see '" + placeholder_text + "' instead of 'Search…'",
                "Search…",
                placeholder_text
        );
    }

    @Test
    public void testCancelSearchAfterArticlesFound() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("country");
        int amount_of_found_articles = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "Cannot find > 1 articles",
                amount_of_found_articles > 1
        );

        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }

    @Test
    public void testSearchResultsContainSearchWord() {
        String search_word = "Country";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_word);
        List<WebElement> search_results = SearchPageObject.getArticleTitles();

        for (WebElement element : search_results) {
            String article_title = element.getText();
            String position = Integer.toString(search_results.indexOf(element));
            assertEquals(
                    "Cannot find '" + search_word + "' in article title '" + article_title + "'. Article index " + position,
                    true,
                    SearchPageObject.isContainsSubstr(article_title, search_word)
            );
        }
    }

    @Test
    public void testSearchResultsContainTitleAndDescription() {
        String search_word = "Android";
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_word);

        SearchPageObject.waitForElementByTitleAndDescription(
                "Android",
                "Wikimedia disambiguation page");
        SearchPageObject.waitForElementByTitleAndDescription(
                "Android (operating system)",
                "An open source operating system for mobile devices created by Google");
        SearchPageObject.waitForElementByTitleAndDescription(
                "Android version history",
                "Wikimedia list article");
    }


    @Test
    public void testAmountOfNotEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );
    }

    @Test
    public void testAmountOfEmptySearch() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("hsefdstrew77ogf");
        SearchPageObject.waitForEmptyResultsLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();
    }
}
