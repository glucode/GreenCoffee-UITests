package com.example.greencoffeeuitests.features

import androidx.test.rule.ActivityTestRule
import com.example.greencoffeeuitests.TestSuite
import com.example.greencoffeeuitests.activities.ContactListActivity
import com.example.greencoffeeuitests.steps.ContactListSteps
import com.example.greencoffeeuitests.steps.DetailsSteps
import com.example.greencoffeeuitests.steps.ScreenshotSteps
import com.mauriciotogneri.greencoffee.GreenCoffeeConfig
import com.mauriciotogneri.greencoffee.GreenCoffeeTest
import com.mauriciotogneri.greencoffee.ScenarioConfig
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.io.IOException
import java.util.*

@RunWith(Parameterized::class)
class ContactListFeatureTest(scenarioConfig: ScenarioConfig?) : GreenCoffeeTest(scenarioConfig) {

    @Rule @JvmField
    val activity = ActivityTestRule(ContactListActivity::class.java, true, true)

    @Test
    fun test() {
        start(
                ContactListSteps(),
                DetailsSteps(),
                ScreenshotSteps())
    }

    companion object {
        @Parameterized.Parameters(name = "{0}")

        @JvmStatic
        fun scenarios(): Iterable<ScenarioConfig> {
            return GreenCoffeeConfig()
                    .withFeatureFromAssets("assets/contacts.feature")
                    .takeScreenshotOnFail()
                    .scenarios(TestSuite.ENGLISH, TestSuite.SPANISH)
        }
    }
}

