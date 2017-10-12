package edu.eku.mrawlings.assignment1;

import android.support.test.rule.ActivityTestRule;
import static android.support.test.espresso.Espresso.*;

import org.junit.Rule;
import org.junit.Test;

import java.util.Random;

import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static org.junit.Assert.*;

public class SettingsActivityTest
{
    @Rule
    public ActivityTestRule<SettingsActivity> settingsActivityActivityTestRule = new ActivityTestRule<SettingsActivity>(SettingsActivity.class);

    @Test
    public void checkTaxLevels() throws Exception
    {
        SettingsActivity settingsActivity = settingsActivityActivityTestRule.getActivity();
        boolean res = settingsActivity.checkTaxLevels(5928, 1923);
        assertEquals(res, false);
    }

    @Test
    public void checkTaxRates() throws Exception
    {
        SettingsActivity settingsActivity = settingsActivityActivityTestRule.getActivity();
        boolean res = settingsActivity.checkTaxRates(0.5, 1.1, 0.5);
        assertEquals(res, false);
    }

    @Test
    public void UiTaxBracketTest() throws Exception
    {
        Random rng = new Random();

        int level1 = rng.nextInt(1000) + 1;
        int level2 = rng.nextInt(10000) + 10000;

        onView(withId(R.id.et_LowerTaxBracket)).perform(clearText(), typeText("" + level1), closeSoftKeyboard());
        onView(withId(R.id.et_MiddleTaxBracket)).perform(clearText(), typeText("" + level2), closeSoftKeyboard());

        onView(withId(R.id.btn_Save)).perform(click());

//        onView(withId(R.id.btn_settings)).perform(click());
//
//        onView(withId(R.id.et_LowerTaxBracket)).check(matches(withText("" + level1)));
//        onView(withId(R.id.et_MiddleTaxBracket)).check(matches(withText("" + level2)));
    }

    @Test
    public void UiTaxRateTest() throws Exception
    {
        Random rng = new Random();

        double level1 = rng.nextDouble();
        double level2 = rng.nextDouble();
        double level3 = rng.nextDouble();

        onView(withId(R.id.et_LowerBracketRate)).perform(clearText(), typeText("" + level1), closeSoftKeyboard());
        onView(withId(R.id.et_MiddleBracketRate)).perform(clearText(), typeText("" + level2), closeSoftKeyboard());
        onView(withId(R.id.et_UpperBracketRate)).perform(clearText(), typeText("" + level3), closeSoftKeyboard());

        onView(withId(R.id.btn_Save)).perform(click());

//        onView(withId(R.id.btn_settings)).perform(click());
//
//        onView(withId(R.id.et_LowerTaxBracket)).check(matches(withText("" + level1)));
//        onView(withId(R.id.et_MiddleTaxBracket)).check(matches(withText("" + level2)));
    }
}