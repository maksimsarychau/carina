/*******************************************************************************
 * Copyright 2020-2022 Zebrunner Inc (https://www.zebrunner.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.qaprosoft.carina.core.foundation.webdriver.core.capability.impl.desktop;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;

public class DesktopCapabilitiesTest {

    // default FirefoxProfile preferences
    private static final boolean MEDIA_EME_ENABLED = true;
    private static final boolean MEDIA_GMP_MANAGER_UPDATE_ENABLED = true;

    @Test(groups = {"DesktopCapabilitiesTestClass"})
    public static void getChromeCapabilityTest() {
        String testName = "chrome - getChromeCapabilityTest";

        ChromeCapabilities chromeCapabilities = new ChromeCapabilities();
        DesiredCapabilities capabilities = chromeCapabilities.getCapability(testName);

        Assert.assertEquals(capabilities.getBrowserName(), BrowserType.CHROME, "Returned browser name is not valid!");

        Assert.assertEquals(capabilities.getCapability("name"), testName, "Returned test name is not valid!");

        Assert.assertEquals(capabilities.getCapability("chrome.switches"), Arrays.asList("--start-maximized", "--ignore-ssl-errors"),
                "Returned capability value is not valid!");
        Assert.assertTrue((Boolean) capabilities.getCapability(CapabilityType.ACCEPT_SSL_CERTS), "Returned capability value is not valid!");

        Assert.assertFalse((Boolean) capabilities.getCapability(CapabilityType.TAKES_SCREENSHOT), "Returned capability value is not valid!");

        Assert.assertTrue((Boolean) capabilities.getCapability(CapabilityType.ACCEPT_INSECURE_CERTS), "Returned capability value is not valid!");
    }

    @Test(groups = {"DesktopCapabilitiesTestClass"})
    public static void getFirefoxCapabilityWithDefaultFirefoxProfileTest() {
        String testName = "firefox - getFirefoxDefaultCapabilityTest";

        FirefoxCapabilities firefoxCapabilities = new FirefoxCapabilities();
        DesiredCapabilities capabilities = firefoxCapabilities.getCapability(testName);

        Assert.assertEquals(capabilities.getBrowserName(), BrowserType.FIREFOX, "Returned browser name is not valid!");

        Assert.assertEquals(capabilities.getCapability("name"), testName, "Returned test name is not valid!");

        Assert.assertFalse((Boolean) capabilities.getCapability(CapabilityType.TAKES_SCREENSHOT), "Returned capability value is not valid!");

        boolean actualMediaEmeEnabled = ((FirefoxProfile) capabilities.getCapability("firefox_profile"))
                .getBooleanPreference("media.eme.enabled", false);
        Assert.assertEquals(actualMediaEmeEnabled, MEDIA_EME_ENABLED, "Returned firefox profile preference is not valid!");

        boolean actualMediaGmpManagerUpdateEnabled = ((FirefoxProfile) capabilities.getCapability("firefox_profile"))
                .getBooleanPreference("media.gmp-manager.updateEnabled", false);
        Assert.assertEquals(actualMediaGmpManagerUpdateEnabled, MEDIA_GMP_MANAGER_UPDATE_ENABLED, "Returned firefox profile preference is not valid!");
    }

    @Test(groups = {"DesktopCapabilitiesTestClass"})
    public static void getFirefoxCapabilityWithCustomFirefoxProfileTest() {
        String testName = "firefox - getFirefoxCustomCapabilityTest";

        FirefoxCapabilities firefoxCapabilities = new FirefoxCapabilities();

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("media.eme.enabled", !MEDIA_EME_ENABLED);
        profile.setPreference("media.gmp-manager.updateEnabled", !MEDIA_GMP_MANAGER_UPDATE_ENABLED);

        DesiredCapabilities capabilities = firefoxCapabilities.getCapability(testName, profile);

        Assert.assertEquals(capabilities.getBrowserName(), BrowserType.FIREFOX, "Returned browser name is not valid!");

        Assert.assertEquals(capabilities.getCapability("name"), testName, "Returned test name is not valid!");

        Assert.assertFalse((Boolean) capabilities.getCapability(CapabilityType.TAKES_SCREENSHOT), "Returned capability value is not valid!");

        boolean actualMediaEmeEnabled = ((FirefoxProfile) capabilities.getCapability("firefox_profile"))
                .getBooleanPreference("media.eme.enabled", true);
        Assert.assertEquals(actualMediaEmeEnabled, !MEDIA_EME_ENABLED, "Returned firefox profile preference is not valid!");

        boolean actualMediaGmpManagerUpdateEnabled = ((FirefoxProfile) capabilities.getCapability("firefox_profile"))
                .getBooleanPreference("media.gmp-manager.updateEnabled", true);
        Assert.assertEquals(actualMediaGmpManagerUpdateEnabled, !MEDIA_GMP_MANAGER_UPDATE_ENABLED, "Returned firefox profile preference is not valid!");
    }

    @Test(groups = {"DesktopCapabilitiesTestClass"})
    public static void getOperaCapabilityTest() {
        String testName = "opera - getOperaCapabilityTest";

        OperaCapabilities operaCapabilities = new OperaCapabilities();
        DesiredCapabilities capabilities = operaCapabilities.getCapability(testName);

        Assert.assertEquals(capabilities.getBrowserName(), BrowserType.OPERA_BLINK, "Returned browser name is not valid!");

        Assert.assertEquals(capabilities.getCapability("name"), testName, "Returned test name is not valid!");

        Assert.assertTrue((Boolean) capabilities.getCapability(CapabilityType.ACCEPT_SSL_CERTS), "Returned capability value is not valid!");

        Assert.assertFalse((Boolean) capabilities.getCapability(CapabilityType.TAKES_SCREENSHOT), "Returned capability value is not valid!");
    }

    @Test(groups = {"DesktopCapabilitiesTestClass"})
    public static void getSafariCapabilityTest() {
        String testName = "safari - getSafariCapabilityTest";

        SafariCapabilities safariCapabilities = new SafariCapabilities();
        DesiredCapabilities capabilities = safariCapabilities.getCapability(testName);

        Assert.assertEquals(capabilities.getBrowserName(), BrowserType.SAFARI, "Returned browser name is not valid!");

        Assert.assertEquals(capabilities.getCapability("name"), testName, "Returned test name is not valid!");
    }

    @Test(groups = {"DesktopCapabilitiesTestClass"})
    public static void getEdgeCapabilityTest() {
        String testName = "edge - getEdgeCapabilityTest";

        EdgeCapabilities edgeCapabilities = new EdgeCapabilities();
        DesiredCapabilities capabilities = edgeCapabilities.getCapability(testName);

        Assert.assertEquals(capabilities.getBrowserName(), BrowserType.EDGE, "Returned browser name is not valid!");

        Assert.assertEquals(capabilities.getCapability("name"), testName, "Returned test name is not valid!");

        Assert.assertTrue((Boolean) capabilities.getCapability(CapabilityType.ACCEPT_SSL_CERTS), "Returned capability value is not valid!");

        Assert.assertFalse((Boolean) capabilities.getCapability(CapabilityType.TAKES_SCREENSHOT), "Returned capability value is not valid!");
    }

    @Test(groups = {"DesktopCapabilitiesTestClass"})
    public static void getIECapabilityTest() {
        String testName = "ie - getIECapabilityTest";

        IECapabilities ieCapabilities = new IECapabilities();
        DesiredCapabilities capabilities = ieCapabilities.getCapability(testName);

        Assert.assertEquals(capabilities.getBrowserName(), BrowserType.IE, "Returned browser name is not valid!");

        Assert.assertEquals(capabilities.getCapability("name"), testName, "Returned test name is not valid!");

        Assert.assertTrue((Boolean) capabilities.getCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS),
                "Returned capability value is not valid!");

        Assert.assertTrue((Boolean) capabilities.getCapability(CapabilityType.ACCEPT_SSL_CERTS), "Returned capability value is not valid!");

        Assert.assertFalse((Boolean) capabilities.getCapability(CapabilityType.TAKES_SCREENSHOT), "Returned capability value is not valid!");
    }

    @Test(groups = {"DesktopCapabilitiesTestClass"})
    public static void getChromeCapabilityHeadlessTest() {
        R.CONFIG.put(Configuration.Parameter.HEADLESS.getKey(), "true");

        ChromeCapabilities chromeCapabilities = new ChromeCapabilities();
        DesiredCapabilities capabilities = chromeCapabilities.getCapability("chrome - getChromeCapabilityHeadlessTest");

        Assert.assertFalse((Boolean) capabilities.getCapability("enableVNC"), "Returned capability value is not valid!");

        Assert.assertFalse((Boolean) capabilities.getCapability("enableVideo"), "Returned capability value is not valid!");
    }

    @Test(groups = {"DesktopCapabilitiesTestClass"})
    public static void getChromeCapabilityProxyTest() {
        String proxyHost = "host.example.com";
        String proxyPort = "80";
        String noProxy = "localhost.example.com";

        R.CONFIG.put(Configuration.Parameter.PROXY_HOST.getKey(), proxyHost, true);
        R.CONFIG.put(Configuration.Parameter.PROXY_PORT.getKey(), proxyPort, true);
        R.CONFIG.put(Configuration.Parameter.PROXY_PROTOCOLS.getKey(), "http,https,ftp,socks", true);
        R.CONFIG.put(Configuration.Parameter.NO_PROXY.getKey(), noProxy, true);

        ChromeCapabilities chromeCapabilities = new ChromeCapabilities();
        DesiredCapabilities capabilities = chromeCapabilities.getCapability("chrome - getChromeCapabilityProxyTest");

        String proxyHostWithPort = proxyHost + ":" + proxyPort;

        Assert.assertEquals(((Proxy) capabilities.getCapability(CapabilityType.PROXY)).getHttpProxy(), proxyHostWithPort, "Http proxy host is not valid!");

        Assert.assertEquals(((Proxy) capabilities.getCapability(CapabilityType.PROXY)).getSslProxy(), proxyHostWithPort, "Ssl proxy host is not valid!");

        Assert.assertEquals(((Proxy) capabilities.getCapability(CapabilityType.PROXY)).getFtpProxy(), proxyHostWithPort, "Ftp proxy host is not valid!");

        Assert.assertEquals(((Proxy) capabilities.getCapability(CapabilityType.PROXY)).getSocksProxy(), proxyHostWithPort, "Socks proxy host is not valid!");

        Assert.assertEquals(((Proxy) capabilities.getCapability(CapabilityType.PROXY)).getNoProxy(), noProxy, "No proxy is not valid!");
    }

    @Test(groups = {"DesktopCapabilitiesTestClass"})
    public static void getChromeCapabilityBrowserLanguageTest() {
        String browserLanguage = "en_US";
        R.CONFIG.put(Configuration.Parameter.BROWSER_LANGUAGE.getKey(), browserLanguage, true);

        ChromeCapabilities chromeCapabilities = new ChromeCapabilities();
        DesiredCapabilities capabilities = chromeCapabilities.getCapability("chrome - getChromeCapabilityBrowserLanguageTest");

        Map<String, Object> chromeOptions = (Map<String, Object>) capabilities.getCapability("goog:chromeOptions");
        List<String> chromeOptionsArgs = (List<String>) chromeOptions.get("args");

        Assert.assertTrue(chromeOptionsArgs.contains("--lang=" + browserLanguage), "Browser language wasn't set!");
    }

    @Test(groups = {"DesktopCapabilitiesTestClass"})
    public static void getChromeCapabilityAutoDownloadTest() {
        R.CONFIG.put(Configuration.Parameter.AUTO_DOWNLOAD.getKey(), "true", true);

        ChromeCapabilities chromeCapabilities = new ChromeCapabilities();
        DesiredCapabilities capabilities = chromeCapabilities.getCapability("chrome - getChromeCapabilityAutoDownloadTest");

        Map<String, Object> chromeOptions = (Map<String, Object>) capabilities.getCapability("goog:chromeOptions");
        Map<String, Object> chromeOptionsPref = (Map<String, Object>) chromeOptions.get("prefs");

        Assert.assertFalse((Boolean) chromeOptionsPref.get("download.prompt_for_download"),
                "chromeOptionsPref download.prompt_for_download wasn't set!");
        Assert.assertNotNull(chromeOptionsPref.get("download.default_directory"),
                "chromeOptionsPref download.default_directory wasn't set!");
        Assert.assertTrue((Boolean) chromeOptionsPref.get("plugins.always_open_pdf_externally"),
                "chromeOptionsPref lugins.always_open_pdf_externally wasn't set!");
    }

}
