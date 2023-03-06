package ru.iteco.fmhandroid.ui.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static ru.iteco.fmhandroid.ui.utils.Utils.waitId;

import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

public class MainPageTest extends GeneralHelper {
    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    MainSteps MainSteps = new MainSteps();
    NewsSteps NewsSteps = new NewsSteps();
    CommonSteps CommonSteps = new CommonSteps();
    ControlPanelSteps ControlPanelSteps = new ControlPanelSteps();

    @Before
    public void loginCheck() throws InterruptedException {
        Thread.sleep(7000);
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            return;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        AuthorizationSteps.enterLogin("login2");
        AuthorizationSteps.enterPassword("password2");
        AuthorizationSteps.signIn();
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

    @Test //Тест не проходит ошибка при переходе в новости!
    @DisplayName("Сортировка новостей на экране управления")
    public void controlPanelSorting() {

        CommonSteps.goToScreen("News");
        waitId(R.id.news_item_title_text_view,7500);
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
