package com.spreadtrum.myapplication.preset;

import android.location.LocationListener;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import com.spreadtrum.myapplication.help.UntilHelp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by Jinchao_1.Zhang on 2017/10/18.
 */

@RunWith(AndroidJUnit4.class)
public class Preset8 {
    UiDevice device;
    int x, y;
    UntilHelp help = null;

    @Before
    public void init() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        x = device.getDisplayWidth();
        y = device.getDisplayHeight();
        help = new UntilHelp();

    }

    @Test
    public void test() throws Exception {
        screen();//解锁
        setlanguage();//设置语言为中文
        wifiOn();//打开wifi
        Autolaunch();
        setDisplay();//设置屏幕显示
        localon();//打开gps
        open();//打开安装应用的所有权限

    }

    @After
    public void end() throws IOException {
        device.pressHome();
        device.executeShellCommand("am force-stop com.android.settings");

    }

    private void screen() throws RemoteException {
        if (!device.isScreenOn()) {
            device.wakeUp();
        }
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
    }

    //开机自启动
    private void Autolaunch() throws IOException, UiObjectNotFoundException, InterruptedException {
        device.executeShellCommand("am start com.android.settings");
        UiScrollable scrollable = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
        scrollable.scrollTextIntoView("电池");
        UiObject2 batter = device.wait(Until.findObject(By.text("电池")), 2000);
        if (batter != null) {
            batter.clickAndWait(Until.newWindow(), 2000);
        }
        scrollable=new UiScrollable(new UiSelector().resourceId("com.android.settings:id/list"));
        scrollable.scrollTextIntoView("自启动管理");
        UiObject2 autolaunch = device.wait(Until.findObject(By.text("自启动管理")), 2000);
        autolaunch.clickAndWait(Until.newWindow(), 2000);
        UiObject2 autolaunch2 = device.wait(Until.findObject(By.text("开机自启动")), 2000);
        autolaunch2.clickAndWait(Until.newWindow(), 2000);
        Thread.sleep(5000);
        UiObject2 allapp = device.wait(Until.findObject(By.text("全部")), 2000);
        if (allapp != null) {
            UiObject2 security_toggle_all =device.wait(Until.findObject(By.res("com.android.settings:id/security_toggle_all")),2000);
            if (security_toggle_all.getText().equals("关闭")) {
                security_toggle_all.clickAndWait(Until.newWindow(), 2000);
            }
        }

    }

    private void setDisplay() throws IOException, UiObjectNotFoundException, InterruptedException {
        device.executeShellCommand("settings put system screen_brightness 200");
        device.executeShellCommand("am start com.android.settings");
        UiScrollable scrollable = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
        scrollable.scrollTextIntoView("显示");
        UiObject2 display = device.wait(Until.findObject(By.text("显示")), 1000);
        display.clickAndWait(Until.newWindow(), 1000);
        UiObject2 sleep = device.wait(Until.findObject(By.text("休眠")), 1000);
        sleep.clickAndWait(Until.newWindow(), 1000);
        UiObject2 min = device.wait(Until.findObject(By.text("30分钟")), 1000);
        min.clickAndWait(Until.newWindow(), 1000);
        UiObject2 advance = device.wait(Until.findObject(By.text("高级")), 1000);
        advance.clickAndWait(Until.newWindow(), 1000);
        try {
            UiObject2 orientation = device.wait(Until.findObject(By.text("设备旋转")), 1000);
            orientation.clickAndWait(Until.newWindow(), 1000);
            UiObject2 state = device.wait(Until.findObject(By.text("保持纵向")), 1000);
            state.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(1000);
        } catch (Exception e) {
            UiObject2 auto = device.wait(Until.findObject(By.text("自动旋转屏幕")), 1000).getParent().getParent().getChildren().get(1).getChildren().get(0);
            if (auto.getText().equals("ON")) {
                auto.clickAndWait(Until.newWindow(), 2000);
            }
        }
        device.pressHome();
        device.executeShellCommand("am force-stop com.android.settings");
        screenon();

    }

    private void screenon() throws IOException, UiObjectNotFoundException {
        device.executeShellCommand("am start com.android.settings");
        UiScrollable scrollable = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
        scrollable.scrollTextIntoView("系统");
        UiObject2 systems = device.wait(Until.findObject(By.text("系统")), 1000);
        systems.clickAndWait(Until.newWindow(), 1000);
        UiObject2 dev = device.wait(Until.findObject(By.text("开发者选项")), 1000);
        dev.clickAndWait(Until.newWindow(), 1000);
        UiObject2 notlock = device.wait(Until.findObject(By.text("不锁定屏幕")), 1000);
        UiObject2 swictch = null;
        try {
            swictch = notlock.getParent().getParent().getChildren().get(2).getChildren().get(0);
        } catch (Exception e) {
            swictch = notlock.getParent().getChildren().get(2);
        }
        if (swictch.getText().equals("关闭") || swictch.getText().equals("OFF")) {
            swictch.clickAndWait(Until.newWindow(), 1000);
        }
        UiScrollable list2 = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/list"));
        list2.scrollTextIntoView("日志记录器缓冲区大小");
        UiObject2 log = device.wait(Until.findObject(By.text("日志记录器缓冲区大小")), 1000);
        log.clickAndWait(Until.newWindow(), 1000);
        UiObject2 MM = null;
        try {
            MM = device.wait(Until.findObject(By.text("16M")), 1000);
            MM.clickAndWait(Until.newWindow(), 1000);
        } catch (Exception e) {
            MM = device.wait(Until.findObject(By.text("1M")), 1000);
            MM.clickAndWait(Until.newWindow(), 1000);
        }

    }

    private void setlanguage() throws IOException, UiObjectNotFoundException, InterruptedException {
        String language = Locale.getDefault().getLanguage();
        if (language.contains("en")) {
            device.executeShellCommand("am start com.android.settings");
            UiScrollable scrollable = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
            scrollable.scrollTextIntoView("System");
            UiObject2 Sys = device.wait(Until.findObject(By.text("System")), 1000);
            Sys.clickAndWait(Until.newWindow(), 1000);
            UiObject2 LanguageInput = device.wait(Until.findObject(By.text("Languages & input")), 1000);
            LanguageInput.clickAndWait(Until.newWindow(), 1000);
            UiObject2 Language = device.wait(Until.findObject(By.text("Languages")), 1000);
            Language.clickAndWait(Until.newWindow(), 1000);
            UiObject2 chinese = device.wait(Until.findObject(By.text("简体中文（中国）")), 1000);
            if (chinese != null) {
                UiObject2 cheage = chinese.getParent().getChildren().get(3);
                int a = cheage.getVisibleBounds().centerY();
                device.swipe(x / 10 * 9, a, x / 10 * 9, y / 20, 10);
            } else {
                UiObject2 add = device.wait(Until.findObject(By.text("Add a language")), 1000);
                add.clickAndWait(Until.newWindow(), 1000);
                UiScrollable list = new UiScrollable(new UiSelector().resourceId("android:id/list"));
                list.scrollTextIntoView("简体中文（中国）");
                chinese = device.wait(Until.findObject(By.text("简体中文（中国）")), 1000);
                chinese.clickAndWait(Until.newWindow(), 1000);
                UiObject2 cheage = chinese.getParent().getChildren().get(3);
                int a = cheage.getVisibleBounds().centerY();
                device.swipe(x / 10 * 9, a, x / 10 * 9, y / 20, 10);
                device.pressBack();
                Thread.sleep(1000);
                device.pressBack();
                Thread.sleep(1000);


            }

        }
    }

    private void localon() throws IOException, UiObjectNotFoundException {
        device.executeShellCommand("am start com.android.settings");
        UiScrollable scrollable = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
        UiObject2 safe = null;
        try {
            scrollable.scrollTextIntoView("安全性与位置信息");
            safe = device.wait(Until.findObject(By.text("安全性与位置信息")), 1000);
            safe.clickAndWait(Until.newWindow(), 1000);
        } catch (Exception e) {
            scrollable.scrollTextIntoView("安全性和位置信息");
            safe = device.wait(Until.findObject(By.text("安全性和位置信息")), 1000);
            safe.clickAndWait(Until.newWindow(), 1000);
        }

        UiObject2 local = device.wait(Until.findObject(By.text("位置信息")), 1000);
        local.clickAndWait(Until.newWindow(), 1000);
        UiObject2 switchs = device.wait(Until.findObject(By.res("com.android.settings:id/switch_widget")), 1000);
        if (!switchs.getText().equals("开启")) {
            switchs.clickAndWait(Until.newWindow(), 1000);
        }
        device.pressBack();
        device.pressBack();
        device.pressHome();
    }

    public boolean wifiOn() {
        try {
            device.executeShellCommand("svc wifi enable");
            device.executeShellCommand("svc data disable");
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    private void open() throws IOException, UiObjectNotFoundException, InterruptedException {
        device.executeShellCommand("am start com.android.settings");
        String build = getProp("ro.build.version.release");
        if (build.contains("8.0") || build.contains("8.1.0")) {
            UiScrollable lis = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
            lis.scrollTextIntoView("应用和通知");
            UiObject2 app = device.wait(Until.findObject(By.text("应用和通知")), 1000);
            app.clickAndWait(Until.newWindow(), 1000);
            try {
                UiObject2 appinfo = device.wait(Until.findObject(By.text("应用信息")), 1000);
                appinfo.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(6000);
            } catch (Exception e) {
                UiObject2 appinfo = device.wait(Until.findObject(By.textContains("查看全部")), 1000);
                appinfo.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(6000);
            }
            UiObject2 list = device.wait(Until.findObject(By.res("android:id/list")), 1000);
            for (int n = 0; n < 10; n++) {
                oppressions(device, list);
                device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 30);
            }
        } else {
            UiScrollable lis = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
            lis.scrollTextIntoView("应用");
            UiObject2 app = device.wait(Until.findObject(By.text("应用")), 1000);
            app.clickAndWait(Until.newWindow(), 1000);
            UiObject2 list = device.wait(Until.findObject(By.res("android:id/list")), 1000);
            for (int n = 0; n < 6; n++) {
                oppressions(device, list);
                device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 30);
            }
        }
    }


    private void oppressions(UiDevice device, UiObject2 list) {
        for (int i = 0; i < list.getChildCount(); i++) {
            UiObject2 item = list.getChildren().get(i);
            item.clickAndWait(Until.newWindow(), 1000);
            UiObject2 permission = device.wait(Until.findObject(By.text("权限")), 1000);
            permission.clickAndWait(Until.newWindow(), 1000);
            UiObject2 lis1 = device.wait(Until.findObject(By.res("android:id/list")), 1000);
            if (lis1 != null) {
                for (int j = 0; j < lis1.getChildCount(); j++) {
                    UiObject2 item1 = lis1.getChildren().get(j);
                    if (!item1.getChildren().get(1).getChildren().get(0).getText().equals("其他权限")) {
                        UiObject2 swicth = item1.getChildren().get(2).getChildren().get(0);
                        if (swicth.getText().equals("关闭")) {
                            swicth.clickAndWait(Until.newWindow(), 1000);
                        }
                    }
                }
                device.pressBack();
                device.pressBack();
            } else {
                device.pressBack();
            }
        }
    }

    public String getProp(String key) {
        try {
            //命令窗口输入命令
            Process p = Runtime.getRuntime().exec("getprop");
            //从命令中提取的输入流
            InputStream in = p.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader buff = new BufferedReader(reader);
            //逐行读取并输出
            String line = "";
            while ((line = buff.readLine()) != null) {
                if (line.contains("[" + key + "]")) {
                    String[] s = line.split("\\[");
                    //返回值
                    return s[2].replaceAll("\\].*", "");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        //如果没取到就返回这个
        return "未找到对应KEY";
    }
}
