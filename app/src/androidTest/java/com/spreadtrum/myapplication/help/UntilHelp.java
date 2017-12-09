package com.spreadtrum.myapplication.help;

import android.graphics.Point;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.test.InstrumentationRegistry;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;
import android.support.test.uiautomator.Until;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * Created by Jinchao_1.Zhang on 2017/5/16.
 */

public class UntilHelp {

    private static UntilHelp untilHelp = null;
    private static UiDevice device = null;
    private Date data = null;
    private String curData = null;
    private static SimpleDateFormat dataformat = null;

    public static UntilHelp getInstance() {
        untilHelp = new UntilHelp();
        dataformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        return untilHelp;
    }

    public int GetHight() {
        int Hight = device.getDisplayHeight();
        return Hight;


    }

    public int GetWidth() {
        int width = device.getDisplayWidth();
        return width;
    }

    public void permession(String appname) throws IOException, UiObjectNotFoundException {
        device.executeShellCommand("am start com.android.settings");
        if (getProp("ro.build.version.release").toString().contains("8.0")) {
            UiScrollable lis = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
            lis.scrollTextIntoView("应用和通知");
            UiObject2 app = device.wait(Until.findObject(By.text("应用和通知")), 1000);
            app.clickAndWait(Until.newWindow(), 1000);
            UiObject2 appinfo = device.wait(Until.findObject(By.text("应用信息")), 1000);
            appinfo.clickAndWait(Until.newWindow(), 1000);
            UiScrollable list = new UiScrollable(new UiSelector().resourceId("android:id/list"));
            list.scrollTextIntoView(appname);
            UiObject2 name = device.wait(Until.findObject(By.textContains(appname)), 1000);
            name.clickAndWait(Until.newWindow(), 1000);
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
                device.pressHome();
            } else {
                device.pressBack();
                device.pressHome();
            }

        } else {
            UiScrollable lis = new UiScrollable(new UiSelector().resourceId("com.android.settings:id/dashboard_container"));
            lis.scrollTextIntoView("应用");
            UiObject2 app = device.wait(Until.findObject(By.text("应用")), 1000);
            app.clickAndWait(Until.newWindow(), 1000);
            UiScrollable list = new UiScrollable(new UiSelector().resourceId("android:id/list"));
            list.scrollTextIntoView(appname);
            UiObject2 name = device.wait(Until.findObject(By.textContains(appname)), 1000);
            name.clickAndWait(Until.newWindow(), 1000);
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
                device.pressHome();
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

    //获取硬件配置信息

    private String getProp(String key) {
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

    public void OpenScreen() throws RemoteException, InterruptedException {
        if (!device.isScreenOn()) {
            System.out.println(GetCurrTime() + "  WakeUp");
            device.wakeUp();
            device.swipe(GetWidth() / 2, GetHight() / 4 * 3, GetWidth() / 2, GetHight() / 4, 15);
            Thread.sleep(2000);
            UiObject2 lock = device.wait(Until.findObject(By.res("com.android.systemui:id/lockPatternView")), 1000);
            if (lock != null) {
                Point p1 = new Point();
                Point p2 = new Point();
                Point p3 = new Point();
                Point p4 = new Point();
                p1.x = GetWidth() / 720 * 180;
                p1.y = GetHight() / 1280 * 625;
                p2.x = GetWidth() / 720 * 360;
                p2.y = GetHight() / 1280 * 625;
                p3.x = GetWidth() / 720 * 540;
                p3.y = GetHight() / 1280 * 625;
                p4.x = GetWidth() / 720 * 540;
                p4.y = GetHight() / 1280 * 820;
                Point[] p = {p1, p2, p3, p4};
                device.swipe(p, 40);
                Thread.sleep(2000);
            }
            device.pressHome();
        } else {
            device.swipe(GetWidth() / 2, GetHight() / 4 * 3, GetWidth() / 2, GetHight() / 4, 15);
            UiObject2 lock = device.wait(Until.findObject(By.res("com.android.systemui:id/lockPatternView")), 1000);
            if (lock != null) {
                Point p1 = new Point();
                Point p2 = new Point();
                Point p3 = new Point();
                Point p4 = new Point();
                p1.x = GetWidth() / 720 * 180;
                p1.y = GetHight() / 1280 * 625;
                p2.x = GetWidth() / 720 * 360;
                p2.y = GetHight() / 1280 * 625;
                p3.x = GetWidth() / 720 * 540;
                p3.y = GetHight() / 1280 * 625;
                p4.x = GetWidth() / 720 * 540;
                p4.y = GetHight() / 1280 * 820;
                Point[] p = {p1, p2, p3, p4};
                device.swipe(p, 40);
                Thread.sleep(2000);
            }
            device.pressHome();
        }
        Thread.sleep(1000);
    }

    public String GetCurrTime() {
        data = new Date(System.currentTimeMillis());
        curData = dataformat.format(data);
        return curData;
    }

    public int Runtimes() throws Exception {
        Bundle bundle = InstrumentationRegistry.getArguments();
        String StrRuntimes = bundle.getString("Runtime");
        int Runtimes = Integer.parseInt(StrRuntimes);
        return Runtimes;
    }

    public String Username() throws Exception {
        Bundle bundle = InstrumentationRegistry.getArguments();
        String StrRuntimes = bundle.getString("Username");
        return StrRuntimes;
    }

    public String Passwd() throws Exception {
        Bundle bundle = InstrumentationRegistry.getArguments();
        String StrRuntimes = bundle.getString("Passwd");
        return StrRuntimes;
    }

    public void Log(String str) {
        System.out.println(GetCurrTime() + "  " + str);
    }

    public void Log(String str, boolean bool) {
        System.out.println(GetCurrTime() + "  " + str + "   " + bool);
    }

    public void exitiinexception(int a) {
        if (a <= 0) {
            device.pressBack();
            device.pressBack();
            device.pressBack();
            device.pressBack();
            device.pressBack();
            device.pressBack();
        }
    }

    public boolean command(String command) throws Exception {
        try {
            device.executeShellCommand(command);
            Thread.sleep(1000);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public void accept(){
        device.registerWatcher("permession", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 OK = device.wait(Until.findObject(By.text("允许")), 1000);
                if (OK != null) {
                    OK.clickAndWait(Until.newWindow(), 10000);
                    return true;
                } else {
                    UiObject2 accept = device.wait(Until.findObject(By.res("com.android.packageinstaller:id/permission_allow_button")), 5000);
                    if (accept != null) {
                        accept.clickAndWait(Until.newWindow(), 5000);
                        return true;
                    }
                }
                return false;

            }
        });
        if (device.hasWatcherTriggered("permession")) {
            device.resetWatcherTriggers();
        }
    }

}
