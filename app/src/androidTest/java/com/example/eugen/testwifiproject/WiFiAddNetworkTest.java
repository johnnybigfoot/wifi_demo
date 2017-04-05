package com.example.eugen.testwifiproject;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.*;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eugen on 4/02/2017.
 */
@RunWith(AndroidJUnit4.class)
public class WiFiAddNetworkTest {
    private UiDevice mDevice;

    @Test
    public void addWiFitest1 () throws UiObjectNotFoundException {
        String testNetworkName = "TestNet123";
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        mDevice.pressHome();
        mDevice.openQuickSettings();
        addNetwork(testNetworkName);
        if (!getNetworks().get("Connected").equals(testNetworkName))
            clickNetworkFromListIfExists(testNetworkName); //here I should probably insert some pause, in order to wait untill real network gets connected
        TestCase.assertEquals("Network is not connected", testNetworkName, getNetworks().get("Connected"));
    }

    public void addNetwork (String ssid) throws UiObjectNotFoundException {
        UiObject settingsIcon = mDevice.findObject(new UiSelector().resourceId("com.android.systemui:id/settings_button"));
        settingsIcon.clickAndWaitForNewWindow();
        UiObject wifi = mDevice.findObject(new UiSelector().text("Wi‑Fi"));
        wifi.clickAndWaitForNewWindow();
        ensureWiFiIsOn();
        UiObject moreOptionsBtn = mDevice.findObject(new UiSelector().descriptionContains("More options"));
        moreOptionsBtn.clickAndWaitForNewWindow();
        UiObject addNetworkBtn = mDevice.findObject(new UiSelector().text("Add network"));
        addNetworkBtn.clickAndWaitForNewWindow();
        UiObject ssidField = mDevice.findObject(new UiSelector().text("Enter the SSID"));
        ssidField.setText(ssid);
        UiObject saveBtn = mDevice.findObject(new UiSelector().text("Save"));
        saveBtn.clickAndWaitForNewWindow();
    }

    public Map<String, String> getNetworks () {
        Map<String, String> networksList = new HashMap<>();
        List<UiObject2> networks = mDevice.findObjects(By.clazz("android.widget.RelativeLayout"));
        for (UiObject2 network : networks) {
            if (network.getChildren().size() == 2)
                networksList.put(network.getChildren().get(1).getText(), network.getChildren().get(0).getText());
            else
                networksList.put(null, network.getChildren().get(0).getText());
        }
        return networksList;
    }

    public void clickNetworkFromListIfExists (String netName) throws UiObjectNotFoundException {
        UiObject wifi = mDevice.findObject(new UiSelector().text(netName));
        if (wifi.exists()) wifi.clickAndWaitForNewWindow();
    }

    public void ensureWiFiIsOn () throws UiObjectNotFoundException {
        UiObject wiFiSwitcher = mDevice.findObject(new UiSelector().resourceId("com.android.settings:id/switch_widget"));
        if (wiFiSwitcher.getText().equals("OFF")) wiFiSwitcher.clickAndWaitForNewWindow();
    }
}