package com.spreadtrum.myapplication.uicase;


import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.UiWatcher;
import android.support.test.uiautomator.Until;

import com.spreadtrum.myapplication.help.UntilHelp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class Aiqiyi {
    private static final String Aiqiyi = "am start -n com.qiyi.video/.WelcomeActivity";
    private static UntilHelp help = null;
    private static UiDevice device = null;
    UiObject2 upgrade = null;
    int x, y, Runtimes = 1;


    @Before
    public void init() throws Exception {
        help = UntilHelp.getInstance();
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        x = help.GetWidth();
        y = help.GetHight();
        Runtimes = help.Runtimes();
    }

    @Test
    public void UserVideoAiqiyi() throws Exception {
        int ii = 0;
        //这里是点亮屏幕方法。
        help.OpenScreen();
        device.registerWatcher("batterDialog", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 yes = device.wait(Until.findObject(By.text("确定")), 1000);
                if (yes != null) {
                    yes.clickAndWait(Until.newWindow(), 2000);
                    return true;
                }
                return false;
            }
        });
        device.registerWatcher("permession", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 OK = device.wait(Until.findObject(By.text("允许")), 1000);
                if (OK != null) {
                    OK.clickAndWait(Until.newWindow(), 1000);
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

        //升级弹框
        device.registerWatcher("update", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 update = device.wait(Until.findObject(By.text("暂不升级")), 1000);
                if (update != null) {
                    update.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });

        if (device.hasWatcherTriggered("update")) {
            device.resetWatcherTriggers();
        }
        //有些apk进去之后会有一个弹出框，因此如果有弹出框的话需要先点掉弹出框
        UiObject2 simchoose = device.wait(Until.findObject(By.res("android:id/button1")), 1000);
        if (simchoose != null) {
            help.Log("simchoose:", simchoose != null);
            simchoose.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(1000);
        } else {
            Thread.sleep(1000);
            help.Log("simchoose:", simchoose != null);
        }
        Thread.sleep(100);
        while (Runtimes-- > 0) {
            help.Log("aiqiyi Test times: " + ii++);
            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入应用", enterApp(Aiqiyi));
            Thread.sleep(1000);
            help.Log("Enter the application");
            enterApp(Aiqiyi);
            Thread.sleep(3000);
            // 判断是否有导航页
            UiObject2 preivew = device.wait(Until.findObject(By.res("com.qiyi.video:id/phone_qiyi_guide_top")), 1000);
            UiObject enter = new UiObject(new UiSelector().resourceId("com.qiyi.video:id/phone_qiyi_guide_enter"));
            if (preivew != null) {
                help.Log("preivew:", preivew != null);
                device.swipe(x / 12 * 11, y / 2, x / 12, y / 2, 15);
                help.Log("Slide to the left");
                Thread.sleep(2000);
                device.swipe(x / 6 * 5, y / 2, x / 12, y / 2, 15);
                Thread.sleep(2000);
                device.swipe(x / 6 * 5, y / 2, x / 12, y / 2, 15);
                Thread.sleep(2000);
                device.swipe(x / 6 * 5, y / 2, x / 12, y / 2, 15);
                Thread.sleep(2000);
                device.swipe(x / 6 * 5, y / 2, x / 12, y / 2, 15);
                Thread.sleep(2000);
                help.Log("Entrt com.qiyi.video:id/phone_qiyi_guide_enter");
                enter.click();
                Thread.sleep(10000);
                //判断自动升级是否存在
            } else {
                help.Log("preivew:", preivew != null);
                Thread.sleep(1000);
            }
            Thread.sleep(6000);
            //页面滑动
            swipes();
            device.swipe(x / 6 * 5, y / 3 * 2, x / 6, y / 3 * 2, 20);
            Thread.sleep(1000);
            swipes();
            device.swipe(x / 6 * 5, y / 3 * 2, x / 6, y / 3 * 2, 20);
            Thread.sleep(1000);
            swipes();
            UiObject2 tv = device.wait(Until.findObject(By.text("电视剧")), 1000);
            if (tv != null) {
                tv.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(5000);
                device.click(x / 3 * 2, y / 5 * 2);
                device.swipe(x / 2, y / 6 * 5, x / 2, y / 6 * 4, 30);
                Thread.sleep(1000);
                device.swipe(x / 2, y / 6 * 5, x / 2, y / 6 * 4, 30);
                Thread.sleep(1000);
                device.swipe(x / 2, y / 6 * 5, x / 2, y / 6 * 4, 30);
                Thread.sleep(1000);
                device.swipe(x / 2, y / 6 * 4, x / 2, y / 6 * 5, 60);
                Thread.sleep(1000);
                //播放电视剧10分钟
                Thread.sleep(600000);
                device.pressBack();
            }
            device.swipe(x / 6 * 5, y / 3 * 2, x / 6, y / 3 * 2, 20);
            Thread.sleep(1000);
            swipes();
            device.swipe(x / 6 * 5, y / 3 * 2, x / 6, y / 3 * 2, 20);
            Thread.sleep(1000);
            UiScrollable title = new UiScrollable(new UiSelector().resourceId("com.qiyi.video:id/main_psts"));
            title.setAsHorizontalList();
            title.scrollTextIntoView("搞笑");
            UiObject2 joke = device.wait(Until.findObject(By.text("搞笑")), 1000);
            joke.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(5000);
            swipes();
            device.click(x / 2, y / 2);
            Thread.sleep(5000);
            device.swipe(x / 6 * 5, y / 2, x / 6, y / 2, 40);
            Thread.sleep(30000);
            device.swipe(x / 6 * 5, y / 2, x / 6, y / 2, 40);
            Thread.sleep(30000);

            device.pressBack();
            Thread.sleep(1000);
        }
    }

    private void swipes() throws InterruptedException {
        device.swipe(x / 2, y / 6 * 5, x / 2, y / 6, 40);
        Thread.sleep(1000);
        device.swipe(x / 2, y / 6 * 5, x / 2, y / 6, 40);
        Thread.sleep(1000);
        device.swipe(x / 2, y / 6 * 5, x / 2, y / 6, 40);
        Thread.sleep(1000);
        device.swipe(x / 2, y / 6, x / 2, y / 6 * 5, 40);
        Thread.sleep(1000);
        device.swipe(x / 2, y / 6, x / 2, y / 6 * 5, 40);
        Thread.sleep(1000);
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

    @After
    public void end() {
        int i = 6;
        while (i-- > 0) {
            device.pressBack();
            UiObject2 exit = device.wait(Until.findObject(By.res("com.qiyi.video:id/phone_exitpop_exit")), 1000);
            if (exit != null) {
                exit.clickAndWait(Until.newWindow(), 1000);
                break;
            }
        }
        device.pressHome();


    }
}
