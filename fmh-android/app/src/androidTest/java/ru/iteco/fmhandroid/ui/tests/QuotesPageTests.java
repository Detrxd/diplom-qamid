package ru.iteco.fmhandroid.ui.tests;

import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;
import ru.iteco.fmhandroid.ui.steps.ThematicQuotesSteps;

public class QuotesPageTests extends GeneralHelper {

    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();

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

    CommonSteps CommonSteps = new CommonSteps();
    ThematicQuotesSteps ThematicQuotesSteps = new ThematicQuotesSteps();

    @Test
    @DisplayName("Открытие экрана тематических цитат и взаимодействие с цитатами")
    public void thematicQuotes() {
        MainSteps.waitForLoadingMain();
        CommonSteps.goToThematicQuotes();
        ThematicQuotesSteps.checkAll();
        ThematicQuotesSteps.expandQuote();
        ThematicQuotesSteps.collapseQuote();
    }
}
