package ru.iteco.fmhandroid.ui.tests;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

public class AboutPageTests extends BasicTest {

    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    AboutSteps AboutSteps = new AboutSteps();
    CommonSteps CommonSteps = new CommonSteps();
    MainSteps MainSteps = new MainSteps();

    @Before
    public void loginCheck() {
        SystemClock.sleep(1500);
//        AuthorizationSteps.testWaiting();
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            return;
        }
        AuthorizationSteps.enterLogin("login2");
        AuthorizationSteps.enterPassword("password2");
        AuthorizationSteps.signIn();
    }

    @Test
    @DisplayName("Открытие экрана о приложение и возврат на главный экран")
    public void aboutScreenAndBackToMain() {
        CommonSteps.goToScreen("About");
        AboutSteps.checkEverythingYouWant();
        AboutSteps.goBack();
        MainSteps.isMainScreen();
    }
}
