/**
 * Created by apastukh.
 * Date: 07.10.2023
 * Project Name: apastukh_test_drive
 */

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static libs.Constants.TimeoutVariables.EXPLICITY_WAIT;
import static libs.Constants.TimeoutVariables.IMPLICITY_WAIT;
import static libs.Constants.Urls.URL_WIKI;

public class WikiMainPage {
    private final WebDriver driver;
    WebElement wikiSearchInput;
    WebElement searchBtn;
    List<WebElement> chooseLanguageList;
    WebElement chosenLanguage = null;
    By searchInputByLocator = By.xpath("//*[@id=\"searchInput\"]");
    By searchBtnByLocator = By.xpath("//*[@id=\"search-form\"]/fieldset/button");
    By language = By.xpath("//*[@id='searchLanguage']");
    By chooseLanguageListByLocator = By.xpath("//*[@id=\"searchLanguage\"]/option");

    public WikiMainPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void openWikiMainPage() {
        driver.get(URL_WIKI);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITY_WAIT));
    }

    public void waitWikiSerchInput() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_WAIT));
        wikiSearchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInputByLocator));
    }

    public void setValueToSearchInput(String value) {
        wikiSearchInput.clear();
        wikiSearchInput.sendKeys(value);
    }

    public SeleniumPage clickOnSearchBtn() {
        searchBtn = driver.findElement(searchBtnByLocator);
        searchBtn.click();
        return new SeleniumPage(driver);
    }

    public void chooseEnglishLanguage(String lang) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICITY_WAIT));
        chooseLanguageList = getLanguageList();

        for (WebElement e : chooseLanguageList) {
            String elementValue = e.getAttribute("value");
            if (elementValue != null && elementValue.equals(lang)) {
                chosenLanguage = e;
                break;
            }
        }

        if (chosenLanguage != null) {
             wait.until(ExpectedConditions.visibilityOf(chosenLanguage)).click();
        } else {
            System.out.println("No such element");
        }

    }

    private List<WebElement> getLanguageList() {
        driver.findElement(language).click();
        return driver.findElements(chooseLanguageListByLocator);
    }
}
