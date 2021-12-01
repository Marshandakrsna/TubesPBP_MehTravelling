package com.example.tubespw_mehtravelling.survey;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.tubespw_mehtravelling.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddEditActivityTest {

    @Rule
    public ActivityTestRule<AddEditActivity> mActivityTestRule = new ActivityTestRule<>(AddEditActivity.class);

    @Test
    public void addEditActivityTest() {
        ViewInteraction materialButton = onView(
                allOf(withId(R.id.btn_save), withText("Simpan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton.perform(click());

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.et_namadestinasi),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_namadestinasi),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Mali"), closeSoftKeyboard());



        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.et_namadestinasi), withText("Mali"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_namadestinasi),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("Malioboro"));

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.et_namadestinasi), withText("Malioboro"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_namadestinasi),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(closeSoftKeyboard());

        ViewInteraction materialButton2 = onView(
                allOf(withId(R.id.btn_save), withText("Simpan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton2.perform(click());

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.et_namapengguna),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_namapengguna),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(replaceText("hesti"), closeSoftKeyboard());



        ViewInteraction materialButton3 = onView(
                allOf(withId(R.id.btn_save), withText("Simpan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton3.perform(click());

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.et_penilaian),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_penilaian),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("5"), closeSoftKeyboard());



        ViewInteraction materialButton4 = onView(
                allOf(withId(R.id.btn_save), withText("Simpan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton4.perform(click());

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.et_alasan),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_alasan),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText6.perform(replaceText("gatau kak"), closeSoftKeyboard());



        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btn_save), withText("Simpan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                6),
                        isDisplayed()));
        materialButton5.perform(click());
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
