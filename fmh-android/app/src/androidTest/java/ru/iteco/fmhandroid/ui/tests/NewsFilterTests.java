package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.tests.NewsPageTests.newsDescriptionString;
import static ru.iteco.fmhandroid.ui.tests.NewsPageTests.newsTitleString;
import static ru.iteco.fmhandroid.ui.utils.Utils.getCurrentDate;
import static ru.iteco.fmhandroid.ui.utils.Utils.getCurrentTime;

import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.ControlPanelSteps;
import ru.iteco.fmhandroid.ui.steps.CreateNewsSteps;
import ru.iteco.fmhandroid.ui.steps.NewsFilterSteps;
import ru.iteco.fmhandroid.ui.steps.NewsSteps;

public class NewsFilterTests extends GeneralHelper {

    NewsFilterSteps NewsFilterSteps = new NewsFilterSteps();
    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    NewsSteps NewsSteps = new NewsSteps();
    CommonSteps CommonSteps = new CommonSteps();
    ControlPanelSteps ControlPanelSteps = new ControlPanelSteps();
    CreateNewsSteps CreateNewsSteps = new CreateNewsSteps();

    String newsPublicationDate = getCurrentDate();
    String newsTime = getCurrentTime();

    @Before
    public void loginCheck() {

        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationSteps.enterLogin(userLogin);
        AuthorizationSteps.enterPassword(userPassword);
        AuthorizationSteps.signIn();

    }

    @Test //Тесты не проходят, ошибка на экране Новости
    @DisplayName("Сортировка новостей")
    public void newsScreenFiltering() {

        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();
        CommonSteps.clickOK();

        NewsSteps.goToControlPanel();
        ControlPanelSteps.isControlPanel();

        ControlPanelSteps.createNews();
        CreateNewsSteps.isCreateNewsScreen();
        CreateNewsSteps.selectNewsCategory();

        CreateNewsSteps.enterNewsTitle(newsTitleString);
        CreateNewsSteps.enterNewsPublicationDate(newsPublicationDate);
        CreateNewsSteps.enterNewsTime(newsTime);
        CreateNewsSteps.enterNewsDescription(newsDescriptionString);
        CreateNewsSteps.checkNewsSwitcher();

        CommonSteps.clickSave();
        ControlPanelSteps.isControlPanel();

        CommonSteps.goToScreen("News");
        NewsSteps.isNewsScreen();

        NewsSteps.openFilter();

        NewsFilterSteps.enterPublishDateStart(newsPublicationDate);
        NewsFilterSteps.enterPublishDateEnd(newsPublicationDate);
        NewsFilterSteps.clickFilter();

        NewsSteps.checkFirstNewsDate(newsPublicationDate);

        NewsSteps.goToControlPanel();

        ControlPanelSteps.isControlPanel();

        NewsSteps.openFilter();

        NewsFilterSteps.enterPublishDateStart(newsPublicationDate);
        NewsFilterSteps.enterPublishDateEnd(newsPublicationDate);
        NewsFilterSteps.clickFilter();

        ControlPanelSteps.checkFirstPublicationDate(newsPublicationDate);

        ControlPanelSteps.clickEditNews();
        CreateNewsSteps.clickNewsSwitcher();
        CommonSteps.clickSave();

        NewsSteps.openFilter();
        NewsFilterSteps.enterPublishDateStart(newsPublicationDate);
        NewsFilterSteps.enterPublishDateEnd(newsPublicationDate);
        NewsFilterSteps.clickCheckboxActive();
        NewsFilterSteps.checkCheckboxActive(false);
        NewsFilterSteps.checkCheckboxNotActive(true);
        NewsFilterSteps.clickFilter();

        ControlPanelSteps.checkFirstPublicationDateNotActive(newsPublicationDate);
        ControlPanelSteps.checkNewsStatus();

        ControlPanelSteps.checkNewsStatusNotActive();
        CreateNewsSteps.clickNewsSwitcher();
        CommonSteps.clickSave();

        NewsSteps.openFilter();
        NewsFilterSteps.enterPublishDateStart(newsPublicationDate);
        NewsFilterSteps.enterPublishDateEnd(newsPublicationDate);
        NewsFilterSteps.checkCheckboxActive(true);
        NewsFilterSteps.clickCheckboxNotActive();
        NewsFilterSteps.checkCheckboxNotActive(false);
        NewsFilterSteps.clickFilter();

        ControlPanelSteps.checkFirstPublicationDateActive(newsPublicationDate);
        ControlPanelSteps.checkNewsStatusActive();

        //todo Код удаления новости закоментирован, так как проходит раз через раз.
//        SystemClock.sleep(3500);
//        ControlPanelSteps.clickDeleteNews();
//        CommonSteps.clickCancel();
//
//        SystemClock.sleep(3500);
//        ControlPanelSteps.clickDeleteNews();
//        CommonSteps.clickOK();
    }
}
