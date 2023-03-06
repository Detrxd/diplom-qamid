package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.utils.Utils.checkClaimStatus;

import android.os.SystemClock;

import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.ui.enums.Status;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

public class ClaimFilterTests extends BasicTest {
    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    MainSteps MainSteps = new MainSteps();
    ClaimsSteps ClaimsSteps = new ClaimsSteps();

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
    @DisplayName("Фильтрация претензий")
    public void filteringClaims() {
        MainSteps.openAllClaims();
        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.clickCancel();
        ClaimsSteps.openFiltering();
        ClaimsSteps.checkCheckboxInProgress(true);

        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.clickOK();
        checkClaimStatus(Status.OPEN);
        ClaimsSteps.isClaimsScreen();

        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxOpen();
        ClaimsSteps.checkCheckboxOpen(false);
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.checkCheckboxInProgress(true);
        ClaimsSteps.clickOK();
        checkClaimStatus(Status.IN_PROGRESS);
        ClaimsSteps.isClaimsScreen();

        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxExecuted();
        ClaimsSteps.checkCheckboxExecuted(true);
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.checkCheckboxInProgress(false);
        ClaimsSteps.clickOK();
        checkClaimStatus(Status.EXECUTED);
        ClaimsSteps.isClaimsScreen();

        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxCancelled();
        ClaimsSteps.checkCheckboxCancelled(true);
        ClaimsSteps.clickCheckboxExecuted();
        ClaimsSteps.checkCheckboxExecuted(false);
        ClaimsSteps.clickOK();
        checkClaimStatus(Status.CANCELED);
        ClaimsSteps.isClaimsScreen();
    }
}
