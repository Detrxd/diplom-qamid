package ru.iteco.fmhandroid.ui.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.utils.Utils.getCurrentDate;
import static ru.iteco.fmhandroid.ui.utils.Utils.getCurrentTime;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

public class MainPageTest extends BasicTest {
    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    MainSteps MainSteps = new MainSteps();
    NewsSteps NewsSteps = new NewsSteps();
    CommonSteps CommonSteps = new CommonSteps();
    ControlPanelSteps ControlPanelSteps = new ControlPanelSteps();

    public static String newsTitleString = "Тест тест " + getCurrentDate() + "T" + getCurrentTime();
    public static String newsDescriptionString = "Тестовое описание " + getCurrentDate() + "T" + getCurrentTime();
    public static String newNewsTitle = "Тестовый заголовок " + getCurrentDate() + "T" + getCurrentTime();


    @Before
    public void loginCheck() {
        SystemClock.sleep(7000);
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationSteps.enterLogin("login2");
        AuthorizationSteps.enterPassword("password2");
        AuthorizationSteps.signIn();
        SystemClock.sleep(2000);
    }

    @Test
    @DisplayName("Разворачивание и сворачивание блока новостей и претензий")
    public void expandAll() {
        MainSteps.expandAllNews();
        MainSteps.allNewsNotDisplayed();
        MainSteps.expandAllClaims();
        MainSteps.allClaimsNotDisplayed();

        MainSteps.expandAllNews();
        MainSteps.allNewsDisplayed();
        MainSteps.expandAllClaims();
        MainSteps.allClaimsDisplayed();
    }

    @Test
    @DisplayName("Сортировка новостей на экране управления")
    public void controlPanelSorting() {

        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();

        NewsSteps.goToControlPanel();

        String firstNews = NewsSteps.getFirstNewsTitle();
        String firstPublicationDate = ControlPanelSteps.getFirstNewsPublicationDate();
        String firstCreationDate = ControlPanelSteps.getFirstNewsCreationDate();
        NewsSteps.clickSortButton();
        String lastPublicationDate = ControlPanelSteps.getLastNewsPublicationDate();

        NewsSteps.clickSortButton();
        NewsSteps.clickSortButton();
        String firstNewsAgain = NewsSteps.getFirstNewsAgainTitle();

        String firstPublicationDateAgain = ControlPanelSteps.getFirstNewsPublicationDateAgain();
        String firstCreationDateAgain = ControlPanelSteps.getFirstNewsCreationDateAgain();
        assertEquals(firstNews, firstNewsAgain);
        assertEquals(firstPublicationDate, firstPublicationDateAgain);
        assertEquals(firstCreationDate, firstCreationDateAgain);
        assertNotEquals(firstPublicationDate, lastPublicationDate);
    }

}
