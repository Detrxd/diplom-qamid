package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.utils.Utils.waitId;

import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.steps.AboutSteps;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

public class AboutPageTests extends GeneralHelper {

    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    AboutSteps AboutSteps = new AboutSteps();
    CommonSteps CommonSteps = new CommonSteps();
    MainSteps MainSteps = new MainSteps();

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
    @DisplayName("Открытие экрана о приложение и возврат на главный экран")
    public void aboutScreenAndBackToMain() {
        CommonSteps.goToScreen("About");
        waitId(R.id.about_version_title_text_view,2500);
        AboutSteps.checkEverythingYouWant();
        AboutSteps.goBack();
        waitId(R.id.all_claims_text_view,1500);
        MainSteps.isMainScreen();
    }
}
