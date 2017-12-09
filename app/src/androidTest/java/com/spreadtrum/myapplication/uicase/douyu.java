package com.spreadtrum.myapplication.uicase;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiWatcher;
import android.support.test.uiautomator.Until;

import com.spreadtrum.myapplication.help.UntilHelp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by Jinchao_1.Zhang on 2017/7/14.
 */

@RunWith(AndroidJUnit4.class)
public class douyu {
    public static String Douyu = "am start -n  air.tv.douyu.android/tv.douyu.view.activity.SplashActivity ";
    int x, y, i = 20, Runtimes = 1;
    UntilHelp help;
    UiDevice device;

    @Before
    public void inti() throws Exception {
        help = UntilHelp.getInstance();
        x = help.GetWidth();
        y = help.GetHight();
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        Runtimes = help.Runtimes();
        help.OpenScreen();
    }

    @Test
    public void douyu() throws Exception {

        help.OpenScreen();
        device.registerWatcher("Permession", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 permerssion = device.wait(Until.findObject(By.res("com.android.packageinstaller:id/permission_allow_button").text("允许")), 1000);
                if (permerssion != null) {
                    permerssion.clickAndWait(Until.newWindow(), 1000);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });


        device.registerWatcher("Update", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 cancle = device.wait(Until.findObject(By.res("air.tv.douyu.android:id/cancel_btn")), 1000);
                if (cancle != null) {
                    cancle.clickAndWait(Until.newWindow(), 1000);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });
        device.registerWatcher("Login", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 login = device.wait(Until.findObject(By.textContains("选择登录方式")), 1000);
                if (login != null) {
                    device.pressBack();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });
        device.registerWatcher("Cancle", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 cans = device.wait(Until.findObject(By.text("取消")), 1000);
                if (cans != null) {
                    cans.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        device.registerWatcher("dialog", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 login = device.wait(Until.findObject(By.res("air.tv.douyu.android:id/root_view")), 1000);
                if (login != null) {
                    device.pressBack();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("Permession")) {
            device.resetWatcherTriggers();
        }
        if (device.hasWatcherTriggered("Cancle")) {
            device.resetWatcherTriggers();
        }
        if (device.hasWatcherTriggered("Update")) {
            device.resetWatcherTriggers();
        }
        if (device.hasWatcherTriggered("Login")) {
            device.resetWatcherTriggers();
        }

        while (Runtimes-- > 0) {
            assertTrue("  未成功进入app", enterApp(Douyu));
            Thread.sleep(5000);
            enterApp(Douyu);

            UiObject2 guideView = device.wait(Until.findObject(By.res("air.tv.douyu.android:id/guideView")), 1000);
            UiObject2 start = device.wait(Until.findObject(By.res("air.tv.douyu.android:id/img_just_do")), 1000);
            if (guideView != null) {
                System.out.println("start fasle guideview  true");
                while (guideView != null && start == null) {
                    device.swipe(x / 5 * 4, y / 2, x / 5, y / 2, 20);
                    System.out.println("swipe");
                    guideView = device.wait(Until.findObject(By.res("air.tv.douyu.android:id/guideView")), 1000);
                    start = device.wait(Until.findObject(By.res("air.tv.douyu.android:id/img_just_do")), 1000);
                }
                if (start != null) {
                    start.clickAndWait(Until.newWindow(), 1000);
                    System.out.println("start true");
                }
            }
            device.swipe(x / 5 * 4, y / 2, x / 5, y / 2, 20);
            Thread.sleep(1000);
            device.swipe(x / 5 * 4, y / 2, x / 5, y / 2, 20);
            start = device.wait(Until.findObject(By.res("air.tv.douyu.android:id/img_just_do")), 1000);
            if (start != null)
                start.clickAndWait(Until.newWindow(), 1000);
            System.out.println("start fasle guideview  false");
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 2, 100);
            Thread.sleep(1000);
            UiObject2 homepage = device.wait(Until.findObject(By.text("首页")), 1000);
            if (homepage == null)
                device.swipe(x / 2, y / 5, x / 2, y / 5 * 2, 100);

            UiObject2 live = device.wait(Until.findObject(By.text("直播")), 1000);
            live.clickAndWait(Until.newWindow(), 1000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 2, 100);
            Thread.sleep(1000);
            UiObject2 all = device.wait(Until.findObject(By.res("android:id/text1").text("全部")), 1000);
            i = 20;
            while (all == null && i-- > 0) {
                Thread.sleep(1000);
                System.out.println(i);
                all = device.wait(Until.findObject(By.res("android:id/text1").text("全部")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("  网络超时", i > 0);
            all.clickAndWait(Until.newWindow(), 1000);
            UiObject2 listview = device.wait(Until.findObject(By.res("air.tv.douyu.android:id/live_all_recycler_view")), 1000);
            i = 20;
            while (listview == null && i-- > 0) {
                Thread.sleep(1000);
                System.out.println(i);
                listview = device.wait(Until.findObject(By.res("air.tv.douyu.android:id/live_all_recycler_view")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("  网络超时", i > 0);
//            UiObject2 itemview = listview.getChildren().get(1).getChildren().get(2);
//            assertTrue("网络异常", itemview != null);
//            itemview.clickAndWait(Until.newWindow(), 5000);
//            device.click(x / 2, y / 2);
            UiObject2 item = listview.getChildren().get(1);
            item.click();
            Thread.sleep(10000);
            device.pressBack();

//            UiObject2 home = device.wait(Until.findObject(By.res("com.android.launcher3:id/launcher")), 1000);
            i = 5;
            while (i-- > 0) {
                device.pressBack();
                UiObject2 cancle = device.wait(Until.findObject(By.text("取消")), 1000);
                if (cancle != null) {
                    cancle.clickAndWait(Until.newWindow(), 1000);
                }
                device.pressBack();
                device.pressBack();
                device.pressBack();
                device.pressBack();
//                home = device.wait(Until.findObject(By.res("com.android.launcher3:id/launcher")), 1000);
            }
            System.out.println("Exit");
        }

    }


    @After
    public void end() {
        device.pressBack();
        device.pressBack();
        device.pressBack();
        device.pressBack();
        device.pressHome();
    }


    //打开应用
    private boolean enterApp(String command) throws Exception {
        try {
            device.executeShellCommand(command);
            Thread.sleep(1000);
        } catch (IOException e) {
            return false;
        }
        return true;
    }


}
