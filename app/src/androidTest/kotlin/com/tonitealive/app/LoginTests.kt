package com.tonitealive.app

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiScrollable
import android.support.test.uiautomator.UiSelector
import android.widget.Button
import android.widget.TextView
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginTests {

    val appName = "Tonite Alive"

    lateinit var device: UiDevice

    @Before
    fun setup() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        openActivity()
    }

    fun openActivity() {
        device.pressHome()

        val allAppsButton = device.findObject(UiSelector().description("Apps"))
        allAppsButton.clickAndWaitForNewWindow()

        val appsTab = device.findObject(UiSelector().text("Apps"))
        appsTab.click()

        val appViews = UiScrollable(UiSelector().scrollable(true))
        appViews.setAsVerticalList()

        val myApp = appViews.getChildByText(UiSelector().className(TextView::class.java), appName)
        myApp.clickAndWaitForNewWindow()

        val appValidation = device.findObject(UiSelector().packageName("com.tonitealive.app.test"))

        assertThat(appValidation.exists()).isTrue()
    }

    @Test
    fun testContents() {
        val signInButton = device.findObject(UiSelector().className(Button::class.java))
    }
}