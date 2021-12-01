package com.example.tubespw_mehtravelling;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.tubespw_mehtravelling.survey.AddEditActivity;

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
        ViewInteraction Button1 = onView(
                allOf(withId(R.id.btn_save), withText("Simpan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        Button1.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction textInputEditText = onView(
                allOf(withId(R.id.et_namadestinasi),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_namadestinasi),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Malioboro"), closeSoftKeyboard());

//        pressBack();

        ViewInteraction Button2 = onView(
                allOf(withId(R.id.btn_save), withText("Simpan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        Button2.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction textInputEditText2 = onView(
                allOf(withId(R.id.et_namapengguna),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_namapengguna),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText2.perform(replaceText("Marshanda!,."), closeSoftKeyboard());

//        pressBack();

        ViewInteraction Button3 = onView(
                allOf(withId(R.id.btn_save), withText("Simpan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        Button3.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction textInputEditText3 = onView(
                allOf(withId(R.id.et_namapengguna), withText("sha"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_namapengguna),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText3.perform(replaceText("Marshanda"));

        ViewInteraction textInputEditText4 = onView(
                allOf(withId(R.id.et_namapengguna), withText("Marshanda"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_namapengguna),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText4.perform(closeSoftKeyboard());

        ViewInteraction Button4 = onView(
                allOf(withId(R.id.btn_save), withText("Simpan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        Button4.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction textInputEditText5 = onView(
                allOf(withId(R.id.et_namapengguna), withText("Marshanda"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_namapengguna),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText5.perform(replaceText("1907100931"));

        ViewInteraction textInputEditText6 = onView(
                allOf(withId(R.id.et_namapengguna), withText("MarshandaKrisnawi"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_namapengguna),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText6.perform(closeSoftKeyboard());

        ViewInteraction materialButton5 = onView(
                allOf(withId(R.id.btn_save), withText("Simpan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        materialButton5.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction textInputEditText7 = onView(
                allOf(withId(R.id.et_namapengguna), withText("MarshandaKrisnawi"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_namapengguna),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText7.perform(replaceText("KrisnawiSaputri"));

        ViewInteraction textInputEditText8 = onView(
                allOf(withId(R.id.et_namapengguna), withText("KrisnawiSaputri"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_namapengguna),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText8.perform(closeSoftKeyboard());

        ViewInteraction materialButton6 = onView(
                allOf(withId(R.id.btn_save), withText("Simpan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        materialButton6.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        //penilaian
        ViewInteraction Button5 = onView(
                allOf(withId(R.id.btn_save), withText("Simpan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        Button5.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction textInputEditText10 = onView(
                allOf(withId(R.id.et_penilaian),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_penilaian),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText10.perform(replaceText("10"), closeSoftKeyboard());

        //alasan
        ViewInteraction Button6 = onView(
                allOf(withId(R.id.btn_save), withText("Simpan"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                7),
                        isDisplayed()));
        Button6.perform(click());
        onView(isRoot()).perform(waitFor(3000));

        ViewInteraction textInputEditText11 = onView(
                allOf(withId(R.id.et_alasan),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.layout_alasan),
                                        0),
                                1),
                        isDisplayed()));
        textInputEditText11.perform(replaceText("Rapi dan teratur"), closeSoftKeyboard());
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

    public static ViewAction waitFor(long delay) {
        return new ViewAction() {
            @Override public Matcher<View> getConstraints() {
                return isRoot();
            }
            @Override public String getDescription() {
                return "wait for " + delay + "milliseconds";
            }
            @Override public void perform(UiController uiController, View view) {
                uiController.loopMainThreadForAtLeast(delay);
            }
        };
    }

}
