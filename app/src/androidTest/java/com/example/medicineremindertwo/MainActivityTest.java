package com.example.medicineremindertwo;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.set_reminder_btn),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        floatingActionButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatSpinner = onView(
                allOf(withId(R.id.drug_name_sp),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatSpinner.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction materialTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(8);
        materialTextView.perform(click());

        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btnSetTime), withText("Set time"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialButton.perform(scrollTo(), click());

        ViewInteraction numericTextView = onView(
                allOf(withClassName(is("com.android.internal.widget.NumericTextView")), withText("33"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.RelativeLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        numericTextView.perform(click());

        ViewInteraction materialButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton2.perform(scrollTo(), click());

        ViewInteraction materialCheckBox = onView(
                allOf(withId(R.id.dv_saturday), withText("Sat"),
                        childAtPosition(
                                allOf(withId(R.id.checkbox_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                6),
                        isDisplayed()));
        materialCheckBox.perform(click());

        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btnSetAlarm), withText("Set Reminder"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton3.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView = onView(
                allOf(withId(R.id.reminders), withContentDescription("Reminders"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView.perform(click());

        DataInteraction cardView = onData(anything())
                .inAdapterView(allOf(withId(R.id.reminders_list),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(0);
        cardView.perform(click());

        ViewInteraction materialButton4 = onView(
                allOf(withId(android.R.id.button2), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        materialButton4.perform(scrollTo(), click());

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Navigate up"),
                        childAtPosition(
                                allOf(withId(R.id.action_bar),
                                        childAtPosition(
                                                withId(R.id.action_bar_container),
                                                0)),
                                1),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatSpinner2 = onView(
                allOf(withId(R.id.drug_name_sp),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatSpinner2.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction materialTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(11);
        materialTextView2.perform(click());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btnSetTime), withText("Set time"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                1)));
        materialButton5.perform(scrollTo(), click());

        ViewInteraction numericTextView2 = onView(
                allOf(withClassName(is("com.android.internal.widget.NumericTextView")), withText("34"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.RelativeLayout")),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                0)),
                                2),
                        isDisplayed()));
        numericTextView2.perform(click());

        ViewInteraction materialButton6 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton6.perform(scrollTo(), click());

        ViewInteraction materialCheckBox2 = onView(
                allOf(withId(R.id.every_day), withText("every_day"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.week_days),
                                        0),
                                1)));
        materialCheckBox2.perform(scrollTo(), click());

        ViewInteraction materialCheckBox3 = onView(
                allOf(withId(R.id.dv_saturday), withText("Sat"),
                        childAtPosition(
                                allOf(withId(R.id.checkbox_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                6),
                        isDisplayed()));
        materialCheckBox3.perform(click());

        ViewInteraction materialCheckBox4 = onView(
                allOf(withId(R.id.dv_friday), withText("Fri"),
                        childAtPosition(
                                allOf(withId(R.id.checkbox_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                5),
                        isDisplayed()));
        materialCheckBox4.perform(click());

        ViewInteraction materialCheckBox5 = onView(
                allOf(withId(R.id.dv_sunday), withText("Sun"),
                        childAtPosition(
                                allOf(withId(R.id.checkbox_layout),
                                        childAtPosition(
                                                withClassName(is("android.widget.LinearLayout")),
                                                2)),
                                0),
                        isDisplayed()));
        materialCheckBox5.perform(click());

        ViewInteraction materialButton7 = onView(
                allOf(withId(R.id.btnSetAlarm), withText("Set Reminder"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton7.perform(scrollTo(), click());

        ViewInteraction materialCheckBox6 = onView(
                allOf(withId(R.id.every_day), withText("every_day"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.week_days),
                                        0),
                                1)));
        materialCheckBox6.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatSpinner3 = onView(
                allOf(withId(R.id.drug_name_sp),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatSpinner3.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        DataInteraction materialTextView3 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(13);
        materialTextView3.perform(click());

        ViewInteraction materialCheckBox7 = onView(
                allOf(withId(R.id.every_day), withText("every_day"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.week_days),
                                        0),
                                1)));
        materialCheckBox7.perform(scrollTo(), click());

        ViewInteraction materialButton8 = onView(
                allOf(withId(R.id.btnSetAlarm), withText("Set Reminder"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        materialButton8.perform(scrollTo(), click());

        ViewInteraction actionMenuItemView2 = onView(
                allOf(withId(R.id.reminders), withContentDescription("Reminders"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed()));
        actionMenuItemView2.perform(click());

        ViewInteraction materialButton9 = onView(
                allOf(withId(android.R.id.button1), withText("Ok"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton9.perform(scrollTo(), click());

        DataInteraction cardView2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.reminders_list),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(0);
        cardView2.perform(click());

        ViewInteraction materialButton10 = onView(
                allOf(withId(android.R.id.button2), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        materialButton10.perform(scrollTo(), click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.drug_name_et), withText("Lyrica"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatEditText.perform(scrollTo(), click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.drug_name_et), withText("Lyrica"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatEditText2.perform(scrollTo(), replaceText(""));

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.drug_name_et),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText3.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.drug_name_et),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatEditText4.perform(scrollTo(), click());

        pressBack();

        ViewInteraction materialButton11 = onView(
                allOf(withId(android.R.id.button2), withText("Cancel"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                2)));
        materialButton11.perform(scrollTo(), click());

        DataInteraction cardView3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.reminders_list),
                        childAtPosition(
                                withClassName(is("android.widget.RelativeLayout")),
                                0)))
                .atPosition(0);
        cardView3.perform(click());

        ViewInteraction appCompatEditText5 = onView(
                allOf(withId(R.id.drug_name_et), withText("Lyrica"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatEditText5.perform(scrollTo(), longClick());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText6 = onView(
                allOf(withId(R.id.drug_name_et), withText("Lyrica"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatEditText6.perform(scrollTo(), click());

        ViewInteraction appCompatEditText7 = onView(
                allOf(withId(R.id.drug_name_et), withText("Lyrica"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatEditText7.perform(scrollTo(), replaceText("Lyric"));

        ViewInteraction appCompatEditText8 = onView(
                allOf(withId(R.id.drug_name_et), withText("Lyric"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0),
                        isDisplayed()));
        appCompatEditText8.perform(closeSoftKeyboard());

        ViewInteraction appCompatEditText9 = onView(
                allOf(withId(R.id.drug_name_et), withText("Lyric"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatEditText9.perform(pressImeActionButton());

        ViewInteraction materialButton12 = onView(
                allOf(withId(android.R.id.button1), withText("Ok"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                3)));
        materialButton12.perform(scrollTo(), click());
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
