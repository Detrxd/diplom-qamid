package ru.iteco.fmhandroid.ui.tests;


import androidx.test.espresso.ViewAction;
import androidx.test.rule.ActivityTestRule;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import ru.iteco.fmhandroid.ui.AppActivity;

@RunWith(AllureAndroidJUnit4.class)
public abstract class BasicTest {
    @Rule
    public ActivityTestRule<AppActivity> mActivityTestRule = new ActivityTestRule<>(AppActivity.class);

}
