package ru.iteco.fmhandroid.ui.tests;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

public class AuthorizationsPageTest extends BasicTest {

    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    CommonSteps CommonSteps = new CommonSteps();
    MainSteps MainSteps = new MainSteps();

    @Before
    public void logoutCheck() {
        SystemClock.sleep(7000);
        try {
            AuthorizationSteps.isAuthorizationScreen();
        } catch (NoMatchingViewException e) {
            CommonSteps.logout();
        }
    }

    @Test
    @DisplayName("Проверка входа с пустой формой и под несуществующим пользователем")
    public void signInWrong() {
        AuthorizationSteps.isAuthorizationScreen();
        AuthorizationSteps.signIn();
        ViewInteraction emptyToast = onView(withText(R.string.empty_login_or_password)).inRoot(withDecorView(not(is(mActivityTestRule.getActivity().getWindow().getDecorView()))));

        SystemClock.sleep(1500);
        AuthorizationSteps.enterLogin(" ");
        AuthorizationSteps.enterPassword(" ");
        AuthorizationSteps.signIn();
        emptyToast.check(matches(withText("Login and password cannot be empty")));

        AuthorizationSteps.isAuthorizationScreen();
    }

    @Test
    @DisplayName("Успешный вход за пользователя и выход из приложения")
    public void signInOK() {

        AuthorizationSteps.isAuthorizationScreen();
        AuthorizationSteps.enterLogin("login2");
        AuthorizationSteps.enterPassword("password2");
        AuthorizationSteps.signIn();
        SystemClock.sleep(2500);
        MainSteps.isMainScreen();

        CommonSteps.logout();

        AuthorizationSteps.isAuthorizationScreen();
    }

}
