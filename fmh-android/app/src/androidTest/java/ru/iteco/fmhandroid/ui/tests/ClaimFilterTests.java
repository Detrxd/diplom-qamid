package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.utils.Utils.checkClaimStatus;
import static ru.iteco.fmhandroid.ui.utils.Utils.waitId;

import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.enums.Status;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

public class ClaimFilterTests extends GeneralHelper {
    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    MainSteps MainSteps = new MainSteps();
    ClaimsSteps ClaimsSteps = new ClaimsSteps();

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
    @DisplayName("Фильтрация претензий")
    public void filteringClaims() throws InterruptedException {
        MainSteps.openAllClaims();

        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.clickCancel();
        ClaimsSteps.openFiltering();
        ClaimsSteps.checkCheckboxInProgress(true);
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.clickOK();
        waitId(R.id.claim_list_recycler_view,3500);
        checkClaimStatus(Status.OPEN);

        ClaimsSteps.isClaimsScreen();

        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxOpen();
        ClaimsSteps.checkCheckboxOpen(false);
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.checkCheckboxInProgress(true);
        ClaimsSteps.clickOK();

        waitId(R.id.claim_list_recycler_view,3500);
        checkClaimStatus(Status.IN_PROGRESS);

        ClaimsSteps.isClaimsScreen();

        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxExecuted();
        ClaimsSteps.checkCheckboxExecuted(true);
        ClaimsSteps.clickCheckboxInProgress();
        ClaimsSteps.checkCheckboxInProgress(false);
        ClaimsSteps.clickOK();

        waitId(R.id.claim_list_recycler_view,3500);
        checkClaimStatus(Status.EXECUTED);

        ClaimsSteps.isClaimsScreen();

        ClaimsSteps.openFiltering();
        ClaimsSteps.clickCheckboxCancelled();
        ClaimsSteps.checkCheckboxCancelled(true);
        ClaimsSteps.clickCheckboxExecuted();
        ClaimsSteps.checkCheckboxExecuted(false);
        ClaimsSteps.clickOK();

        waitId(R.id.claim_list_recycler_view,3500);
        checkClaimStatus(Status.CANCELED);

        ClaimsSteps.isClaimsScreen();
    }
}
