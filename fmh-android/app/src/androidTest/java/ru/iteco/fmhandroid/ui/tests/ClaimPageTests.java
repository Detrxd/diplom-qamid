package ru.iteco.fmhandroid.ui.tests;

import static ru.iteco.fmhandroid.ui.utils.Utils.getCurrentDate;
import static ru.iteco.fmhandroid.ui.utils.Utils.getCurrentTime;
import static ru.iteco.fmhandroid.ui.utils.Utils.waitId;

import androidx.test.espresso.NoMatchingViewException;

import org.junit.Before;
import org.junit.Test;

import io.qameta.allure.kotlin.junit4.DisplayName;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.steps.AuthorizationSteps;
import ru.iteco.fmhandroid.ui.steps.ClaimsSteps;
import ru.iteco.fmhandroid.ui.steps.CommonSteps;
import ru.iteco.fmhandroid.ui.steps.CreateClaimSteps;
import ru.iteco.fmhandroid.ui.steps.EditClaimSteps;
import ru.iteco.fmhandroid.ui.steps.MainSteps;

public class ClaimPageTests extends GeneralHelper {

    AuthorizationSteps AuthorizationSteps = new AuthorizationSteps();
    MainSteps MainSteps = new MainSteps();
    ClaimsSteps ClaimsSteps = new ClaimsSteps();
    EditClaimSteps EditClaimSteps = new EditClaimSteps();
    CommonSteps CommonSteps = new CommonSteps();
    CreateClaimSteps CreateClaimSteps = new CreateClaimSteps();


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
    @DisplayName("Открытие экрана претензий")
    public void openAllClaims() throws InterruptedException {
        MainSteps.openAllClaims();
        waitId(R.id.all_claims_text_view,2500);
        ClaimsSteps.isClaimsScreen();
    }

    @Test
    @DisplayName("Открытие претензии и возврат из нее")
    public void openSingleClaim() {
        MainSteps.openSingleClaim();
        EditClaimSteps.isClaimsEditScreen();
        EditClaimSteps.backFromClaim();
        MainSteps.isMainScreen();
    }

    @Test
    @DisplayName("Открытие экрана претензий из меню и переход к экрану создания претензии")
    public void claimScreen() throws InterruptedException {
        CommonSteps.goToScreen("Claims");
        ClaimsSteps.isClaimsScreen();

        ClaimsSteps.createClaim();
        CreateClaimSteps.isCreateClaimsScreen();
    }

    @Test
    @DisplayName("Создание претензии")
    public void createClaim() throws InterruptedException {
        String claimTitleString = "Притензия тест " + getCurrentDate() + "T" + getCurrentTime();
        String newClaimTitleString = "Тествое описание " + getCurrentDate();
        String currentDate = getCurrentDate();
        String currentTime = getCurrentTime();
        MainSteps.createClaim();

        CreateClaimSteps.isCreateClaimsScreen();

        CreateClaimSteps.checkClaimTitleLength();

        CommonSteps.clickSave();

        CreateClaimSteps.checkToastEmptyFields();
        CommonSteps.clickOK();

        CreateClaimSteps.enterClaimTitle(claimTitleString);

        CreateClaimSteps.selectExecutor();
        CreateClaimSteps.enterClaimDate(currentDate);
        CreateClaimSteps.enterClaimTime(currentTime);
        CreateClaimSteps.enterClaimDescription(newClaimTitleString);

        CommonSteps.clickSave();
        MainSteps.isMainScreen();
    }

}
