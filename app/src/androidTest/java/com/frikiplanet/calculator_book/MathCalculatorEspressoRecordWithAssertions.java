package com.frikiplanet.calculator_book;


import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MathCalculatorEspressoRecordWithAssertions {

    @Rule
    public ActivityTestRule<MathCalculatorActivity> mActivityTestRule = new ActivityTestRule<>(MathCalculatorActivity.class);

    @Test
    public void mathCalculatorEspressoRecordWithAssertions() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.bt7), withText("7"),
                        childAtPosition(
                                allOf(withId(R.id.second_row),
                                        childAtPosition(
                                                withId(R.id.calculator),
                                                1)),
                                0),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withText("7"),
                        childAtPosition(
                                allOf(withId(R.id.result),
                                        childAtPosition(
                                                withId(R.id.result_screen),
                                                1)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("7")));

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(R.id.bt_multiplication), withText("✕"),
                        childAtPosition(
                                allOf(withId(R.id.second_row),
                                        childAtPosition(
                                                withId(R.id.calculator),
                                                1)),
                                4),
                        isDisplayed()));
        appCompatButton2.perform(click());

        ViewInteraction appCompatButton3 = onView(
                allOf(withId(R.id.bt5), withText("5"),
                        childAtPosition(
                                allOf(withId(R.id.third_row),
                                        childAtPosition(
                                                withId(R.id.calculator),
                                                2)),
                                1),
                        isDisplayed()));
        appCompatButton3.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withText("35"),
                        childAtPosition(
                                allOf(withId(R.id.result),
                                        childAtPosition(
                                                withId(R.id.result_screen),
                                                1)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("35")));
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
