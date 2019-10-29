package org.mytests.tests.example;

import com.applitools.eyes.Region;
import org.mytests.tests.TestsInit;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static com.epam.jdi.light.driver.WebDriverFactory.getDriver;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.mytests.uiobjects.example.entities.Defaults.DEFAULT_USER;
import static org.mytests.uiobjects.example.entities.Defaults.SPIDER_MAN;
import static org.mytests.uiobjects.example.site.SiteJdi.*;
import static org.mytests.uiobjects.example.site.pages.MarvelousPage.userTable;

public class FormTests extends TestsInit {
    @Test
    public void verifyUsersTest() {
        eyes.checkWindow(homePage.getName());
        userIcon.click();
        loginForm.loginAs(DEFAULT_USER);
        //eyes.checkElement(userName.core().get());
        WebElement userName = getDriver().findElement(By.id("user-name"));
        eyes.checkElement(userName, "Validation Element");
        eyes.checkRegion(userName, "Validation Region from Element");
        //left, top, width, height
        Rectangle region = userName.getRect();
        eyes.checkRegion(new Region(region.x, region.y, region.width, region.height),
        -1, "Validation Region");
        eyes.checkRegion(By.id("user-name"), "Validation Locator");

        marvelousPage.open();
        eyes.checkWindow(marvelousPage.getName());

        userTable.assertThat()
            .all().rows(d -> d.user.length() > 4)
            .no().rows(d -> isBlank(d.user))
            .atLeast(3).rows(d -> d.type.contains("User"))
            .exact(2).rows(d -> d.user.contains("R"))
            .exact(1).rows(SPIDER_MAN);
    }
}
