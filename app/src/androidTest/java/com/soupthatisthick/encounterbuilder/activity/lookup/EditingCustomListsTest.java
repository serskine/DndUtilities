package com.soupthatisthick.encounterbuilder.activity.lookup;


import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import soupthatisthick.encounterapp.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class EditingCustomListsTest {

    @Rule
    public ActivityTestRule<ViewSplashScreenActivity> mActivityTestRule = new ActivityTestRule<>(ViewSplashScreenActivity.class);

    @Test
    public void editingCustomListsTest() {
        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction button = onView(
                allOf(withId(R.id.editDatabasesButton), withText("Edit Databases"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                4)));
        button.perform(scrollTo(), click());

        ViewInteraction button2 = onView(
                allOf(withId(R.id.manageDatabaseStorageButton), withText("Manage Storage"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        button2.perform(scrollTo(), click());

        ViewInteraction checkBox = onView(
                allOf(withId(R.id.logsheet_toggle), withText("Logsheets Database"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                0),
                        isDisplayed()));
        checkBox.perform(click());

        ViewInteraction checkBox2 = onView(
                allOf(withId(R.id.encounters_toggle), withText("Encounter Database"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        0),
                                1),
                        isDisplayed()));
        checkBox2.perform(click());

        ViewInteraction imageButton = onView(
                allOf(withId(R.id.resetButton),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.TableLayout")),
                                        0),
                                0),
                        isDisplayed()));
        imageButton.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.messageView), withText("Imported dnd.db from\nASSETS_DIRECTORY location.\n\n"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.<View>instanceOf(android.widget.LinearLayout.class),
                                        1),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Imported dnd.db from ASSETS_DIRECTORY location.  ")));

        pressBack();

        ViewInteraction button3 = onView(
                allOf(withId(R.id.editCustomListsButton), withText("Custom Lists"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                7)));
        button3.perform(scrollTo(), click());

        pressBack();

        ViewInteraction imageButton2 = onView(
                allOf(withId(R.id.ml_edit_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.TableLayout")),
                                        0),
                                3),
                        isDisplayed()));
        imageButton2.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.eil_name_edit), withText("Stuart Erskine's List"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.el_header),
                                        0),
                                1),
                        isDisplayed()));
        editText.perform(replaceText("Stuart Erskine"));

        ViewInteraction editText2 = onView(
                allOf(withId(R.id.eil_name_edit), withText("Stuart Erskine"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.el_header),
                                        0),
                                1),
                        isDisplayed()));
        editText2.perform(closeSoftKeyboard());

        pressBack();

        ViewInteraction imageButton3 = onView(
                allOf(withId(R.id.ml_save_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.TableLayout")),
                                        0),
                                4),
                        isDisplayed()));
        imageButton3.perform(click());

        DataInteraction linearLayout = onData(anything())
                .inAdapterView(allOf(withId(R.id.ml_list_view),
                        childAtPosition(
                                withId(R.id.activity_view_list),
                                2)))
                .atPosition(0);
        linearLayout.perform(click());

        ViewInteraction imageButton4 = onView(
                allOf(withId(R.id.ml_add_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.TableLayout")),
                                        0),
                                0),
                        isDisplayed()));
        imageButton4.perform(click());

        ViewInteraction editText3 = onView(
                allOf(withId(R.id.theSearchEdit),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.resultsGroup),
                                        0),
                                0),
                        isDisplayed()));
        editText3.perform(replaceText("wish"), closeSoftKeyboard());

        ViewInteraction editText4 = onView(
                allOf(withId(R.id.theSearchEdit), withText("wish"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.resultsGroup),
                                        0),
                                0),
                        isDisplayed()));
        editText4.perform(pressImeActionButton());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.theAddToListButton), withText("Add to list"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.TableLayout")),
                                        0),
                                0),
                        isDisplayed()));
        button4.perform(click());

        DataInteraction linearLayout2 = onData(anything())
                .inAdapterView(allOf(withId(R.id.select_dialog_listview),
                        childAtPosition(
                                withId(R.id.contentPanel),
                                0)))
                .atPosition(2);
        linearLayout2.perform(click());

        pressBack();

        DataInteraction linearLayout3 = onData(anything())
                .inAdapterView(allOf(withId(R.id.ml_list_view),
                        childAtPosition(
                                withId(R.id.activity_view_list),
                                2)))
                .atPosition(1);
        linearLayout3.perform(click());

        ViewInteraction imageButton5 = onView(
                allOf(withId(R.id.ml_save_button),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.TableLayout")),
                                        0),
                                4),
                        isDisplayed()));
        imageButton5.perform(click());

        pressBack();

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
