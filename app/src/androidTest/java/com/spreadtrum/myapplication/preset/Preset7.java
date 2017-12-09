package com.spreadtrum.myapplication.preset;

import android.graphics.Point;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.spreadtrum.myapplication.help.UntilHelp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Created by Jinchao_1.Zhang on 2017/5/12.
 */
@RunWith(AndroidJUnit4.class)
public class Preset7 {
    public static final String Dialer = "am start -n com.android.dialer/.DialtactsActivity";
    public static final String bizhi = "am start -n  com.adermark.autumnforestfull/com.adermark.autumnforestfull.LWPActivity";
    public static final String Kill_dialer = "am force-stop com.android.dialer";
    public static final String Kill_engineermode = "am force-stop com.sprd.engineermode";
    public static final String Setting = "am start com.android.settings";
    public static final String Kill_Setting = "am force-stop com.android.settings";
    public static final String screen = "settings put system screen_brightness 200";

    //    public static final String dialer = "am start -a Android.intent.action.CALL tel:8888888888888";
    private UiDevice device = null;
    private UntilHelp help = null;
    int x, y;

    @Before
    public void init() throws IOException {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        help = UntilHelp.getInstance();
        x = help.GetWidth();
        y = help.GetHight();
    }


    @Test
    public void DefaultSet() throws Exception {
        help.OpenScreen();//唤醒屏幕
        device.pressHome();
        device.executeShellCommand(screen);
        SetLanguage();
        lighton();
        setbugreport();
    }

    private void lighton() throws IOException, UiObjectNotFoundException {
        device.executeShellCommand(Setting);
        UiScrollable scrollable = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
        scrollable.scrollTextIntoView("显示");
        UiObject2 light = device.wait(Until.findObject(By.text("显示")), 2000);
        if (light != null) {
            light.clickAndWait(Until.newWindow(), 2000);
        }
        scrollable = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/list"));
        scrollable.scrollTextIntoView("休眠");
        UiObject2 wake = device.wait(Until.findObject(By.text("休眠")), 2000);
        if (wake != null) {
            wake.clickAndWait(Until.newWindow(), 1000);
            wake = device.wait(Until.findObject(By.text("30分钟")), 2000);
            wake.clickAndWait(Until.newWindow(), 3000);
        }
        UiObject2 auto = device.wait(Until.findObject(By.text("设备旋转时")), 2000);
        auto.clickAndWait(Until.newWindow(), 2000);
        auto = device.wait(Until.findObject(By.text("保持纵向")), 2000);
        auto.clickAndWait(Until.newWindow(), 2000);
        device.pressBack();
        scrollable = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
        scrollable.scrollTextIntoView("位置信息");
        auto = device.wait(Until.findObject(By.text("位置信息")), 2000);
        UiObject2 check = auto.getParent().getChildren().get(1);
        if (check.getText().equals("关闭")) {
            check.clickAndWait(Until.newWindow(), 2000);
            auto = device.wait(Until.findObject(By.res("com.android.settings:id/switch_bar")), 2000);
            auto.clickAndWait(Until.newWindow(), 2000);
            device.pressBack();
        }
        scrollable = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
        scrollable.scrollTextIntoView("WLAN");
        auto = device.wait(Until.findObject(By.text("WLAN")), 2000);
        if (auto.getParent().getChildren().get(1).getText().equals("已关闭")) {
            auto.clickAndWait(Until.newWindow(), 2000);
            UiObject2 swic=device.wait(Until.findObject(By.res("com.android.settings:id/switch_bar")),2000);
            swic.clickAndWait(Until.newWindow(),2000);
            device.pressBack();
        }


    }

    private void setbugreport() throws IOException, UiObjectNotFoundException, InterruptedException {
        device.executeShellCommand(Setting);
        UiScrollable scrollable = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
        scrollable.scrollTextIntoView("开发者选项");
        UiObject2 dev = device.wait(Until.findObject(By.text("开发者选项")), 1000);
        dev.clickAndWait(Until.newWindow(), 1000);
        Thread.sleep(1000);
        scrollable = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/list"));
        scrollable.scrollTextIntoView("不锁定屏幕");
        UiObject2 stay = device.wait(Until.findObject(By.text("不锁定屏幕")), 2000);
        UiObject2 switc = stay.getParent().getParent().getChildren().get(1).getChildren().get(0);
        if (switc.getText().equals("关闭")) {
            switc.clickAndWait(Until.newWindow(), 1000);
        }
        scrollable.scrollTextIntoView("日志记录器缓冲区大小");
        Thread.sleep(1000);
        UiObject2 cache = device.wait(Until.findObject(By.text("日志记录器缓冲区大小")), 1000);
        cache.click();
        UiObject2 Mb = device.wait(Until.findObject(By.res("android:id/select_dialog_listview")), 1000).getChildren().get(5);
        Mb.click();
        device.pressBack();
        device.pressBack();
        device.pressBack();
        device.pressBack();
    }


 /*   private void setlock() throws IOException, UiObjectNotFoundException, InterruptedException {
        device.executeShellCommand(Setting);
        UiScrollable scrollable = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
        scrollable.scrollTextIntoView("安全");
        UiObject2 safe = device.wait(Until.findObject(By.text("安全")), 1000);
        safe.clickAndWait(Until.newWindow(), 1000);
        scrollable = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/list"));
        scrollable.scrollTextIntoView("屏幕锁定方式");
        UiObject2 slide = device.wait(Until.findObject(By.res("android:id/summary")), 1000);
        if (!slide.getText().toString().equals("图案")) {
            slide.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(2000);
            UiObject2 rgb = device.wait(Until.findObject(By.text("图案")), 1000);
            rgb.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(2000);
            Point p1 = new Point();
            Point p2 = new Point();
            Point p3 = new Point();
            Point p4 = new Point();
            p1.x = x / 720 * 120;
            p1.y = y / 1280 * 480;
            p2.x = x / 720 * 360;
            p2.y = y / 1280 * 480;
            p3.x = x / 720 * 600;
            p3.y = y / 1280 * 480;
            p4.x = x / 720 * 600;
            p4.y = y / 1280 * 720;
            Point[] p = {p1, p2, p3, p4};
            device.swipe(p, 40);
            Thread.sleep(2000);
            UiObject2 continu = device.wait(Until.findObject(By.res("com.android.settings:id/footerRightButton")), 1000);
            continu.click();
            Thread.sleep(1000);
            device.swipe(p, 40);
            Thread.sleep(2000);
            UiObject2 sure = device.wait(Until.findObject(By.res("com.android.settings:id/footerRightButton").text("确认")), 1000);
            sure.click();
            Thread.sleep(1000);
            UiObject2 ok = device.wait(Until.findObject(By.res("com.android.settings:id/next_button")), 1000);
            ok.click();
            device.pressBack();
            device.pressBack();
            device.pressBack();
            device.pressBack();
            device.pressBack();
        }
        device.pressBack();
        device.pressBack();
        device.pressBack();
    }*/

    public void SetScreenOn() throws Exception {
        device.executeShellCommand(Kill_Setting);
        device.executeShellCommand(Setting);
        Thread.sleep(2000);
        UiScrollable scrollable = new UiScrollable(new UiSelector().scrollable(true));
        UiObject Developer = null;
        UiObject AboutPhone = null;
        UiObject BuildNumber = null;
        try {
            Developer = scrollable.getChildByText(new UiSelector().text("Developer options"), "Developer options", true);
        } catch (UiObjectNotFoundException e) {
            Developer = scrollable.getChildByText(new UiSelector().text("开发者选项"), "开发者选项", true);
        }

        try {
            AboutPhone = scrollable.getChildByText(new UiSelector().text("About phone"), "About phone", true);
        } catch (UiObjectNotFoundException e) {
            AboutPhone = scrollable.getChildByText(new UiSelector().text("关于手机"), "关于手机", true);
        }
        if (Developer.exists()) {
            StayWake(Developer);
        } else {
            AboutPhone.clickAndWaitForNewWindow();
            try {
                BuildNumber = scrollable.getChildByText(new UiSelector().text("Build number"), "Build number", true);
            } catch (UiObjectNotFoundException e) {
                BuildNumber = scrollable.getChildByText(new UiSelector().text("版本号"), "版本号", true);
            } finally {
                BuildNumber.click();
                Thread.sleep(500);
                BuildNumber.click();
                Thread.sleep(500);
                BuildNumber.click();
                Thread.sleep(500);
                BuildNumber.click();
                Thread.sleep(500);
                BuildNumber.click();
                Thread.sleep(500);
                BuildNumber.click();
                Thread.sleep(500);
                BuildNumber.click();
                Thread.sleep(500);
                BuildNumber.click();
                Thread.sleep(500);
                device.pressBack();
                StayWake(Developer);
            }


        }
    }

    private void SetLanguage() throws Exception {
        device.executeShellCommand(Setting);
        Thread.sleep(2000);
        UiScrollable scrollable = new UiScrollable(new UiSelector().scrollable(true));
        UiObject Language = null;
        UiObject Languages = null;
        UiObject2 AddLanguage = null;
        UiObject Chinese = null;
        UiObject2 China = null;
        UiObject2 set = null;

        try {
            Language = scrollable.getChildByText(new UiSelector().resourceId("android:id/title").text("Languages & input"), "Languages & input", true);
        } catch (UiObjectNotFoundException e) {
            Language = null;
        }
        if (Language != null) {
            Language.clickAndWaitForNewWindow();
            Thread.sleep(1000);
            Languages = new UiObject(new UiSelector().text("Languages"));
            if (Languages != null) {
                Languages.clickAndWaitForNewWindow();
                Thread.sleep(1000);
                set = device.wait(Until.findObject(By.res("com.android.settings:id/label").text("简体中文（中国）")), 2000);
                if (set == null) {
                    AddLanguage = device.wait(Until.findObject(By.res("com.android.settings:id/add_language").text("Add a language")), 2000);
                    AddLanguage.clickAndWait(Until.newWindow(), 2000);
                    Thread.sleep(1000);
                    Chinese = scrollable.getChildByText(new UiSelector().resourceId("android:id/locale").text("简体中文"), "简体中文", true);
                    Chinese.clickAndWaitForNewWindow();
                    Thread.sleep(1000);
                    China = device.wait(Until.findObject(By.text("中国")), 1000);
                    China.clickAndWait(Until.newWindow(), 2000);
                    Thread.sleep(1000);
                    set = device.wait(Until.findObject(By.res("com.android.settings:id/label").text("简体中文（中国）")), 2000);
                }
                int a = set.getVisibleBounds().centerY();
                device.swipe(x / 10 * 9, a, x / 10 * 9, y / 20, 10);
                Thread.sleep(2000);
            }
        }

        device.pressHome();

    }

    private void StayWake(UiObject developer) throws Exception {
        developer.clickAndWaitForNewWindow();
        UiObject2 bugMode = device.wait(Until.findObject(By.res("com.android.settings:id/switch_widget")), 1000);
        if (bugMode != null && bugMode.isChecked()) {
            UiObject2 StayWake = null;
            try {
                StayWake = device.wait(Until.findObject(By.res("android:id/switchWidget")), 3000);
            } catch (Exception e) {
                StayWake = device.wait(Until.findObject(By.clazz("android.widget.Switch").res("android:id/switch_widget")), 3000);
            }
            try {
                if (!StayWake.isChecked()) {
                    StayWake.click();
                }
            } catch (Exception e) {
                device.pressHome();
                device.executeShellCommand(Kill_Setting);
            }

        } else if (bugMode != null && !bugMode.isChecked()) {
            bugMode.clickAndWait(Until.newWindow(), 2000);
            UiObject2 StayWake = null;
            try {
                StayWake = device.wait(Until.findObject(By.res("android:id/switchWidget")), 1000);
            } catch (Exception e) {
                StayWake = device.wait(Until.findObject(By.res("android:id/switch_widget")), 1000);
            }
            try {
                if (!StayWake.isChecked()) {
                    StayWake.click();
                }
            } catch (Exception e) {
                device.pressHome();
                device.executeShellCommand(Kill_Setting);
            }

        }
    }
}



