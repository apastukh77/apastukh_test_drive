/**
 * Created by apastukh.
 * Date: 07.10.2023
 * Project Name: apastukh_test_drive
 */

package pages;

import org.openqa.selenium.WebDriver;

import static libs.Constants.Urls.URL_WIKI;

public class SeleniumPage {

    WebDriver driver;

    public SeleniumPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean checkUrl() {
        boolean result = false;
        String currentUrl = driver.getCurrentUrl();
        if (!currentUrl.isEmpty() && currentUrl.equals(URL_WIKI+"wiki/Selenium")) {
            System.out.println("SeleniumPage. \nIt is the Selenium Page.");
            result = true;
            return result;
        }
        return result;
    }
}
