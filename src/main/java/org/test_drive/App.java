package org.test_drive;

import libs.TestsHelper;
import org.openqa.selenium.WebDriver;
import pages.SeleniumPage;
import pages.WikiMainPage;

import java.time.Duration;

import static libs.Constants.TimeoutVariables.IMPLICITY_WAIT;


public class App {
    public static void main(String[] args) {

        WebDriver driver = TestsHelper.webDriver;
        String value = "selenium";

        WikiMainPage wikiMainPage = new WikiMainPage(driver);
        wikiMainPage.openWikiMainPage();
        wikiMainPage.waitWikiSerchInput();
        wikiMainPage.setValueToSearchInput(value);
        wikiMainPage.chooseEnglishLanguage("en");

        try {
            SeleniumPage seleniumPage = wikiMainPage.clickOnSearchBtn();
            seleniumPage.checkUrl();
        } catch (Exception e) {
            System.out.println("Test Failed");
            return;
        }

        System.out.println("Test Passed");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITY_WAIT));
        TestsHelper.toBeCleared();
        TestsHelper.closeBrowser();
    }
}
