package com.spreadtrum.myapplication.uicase;


import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
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
public class Tencentvideo {

    private static final String Tencentvideo = "am start -n com.tencent.qqlive/.ona.activity.WelcomeActivity";
    UiDevice device = null;
    int x, y;
    UntilHelp help = null;
    UiObject2 upgrade = null;
    int Runtimes = 1;

    @Before
    public void init() throws Exception {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        help = UntilHelp.getInstance();
        x = help.GetWidth();
        y = help.GetHight();
        Runtimes = help.Runtimes();
    }

    @Test
    public void UserVideoTencentvideo() throws Exception {

        //杀进程

        //点亮屏幕
        help.OpenScreen();
        Thread.sleep(1000);

        device.registerWatcher("permession", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 OK = device.wait(Until.findObject(By.text("允许")), 1000);
                if (OK != null) {
                    OK.clickAndWait(Until.newWindow(), 10000);
                    return true;
                }
                return false;

            }
        });
        device.registerWatcher("Update", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 updata = device.wait(Until.findObject(By.textContains("暂不升级")), 1000);
                if (updata != null) {
                    updata.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("permession")) {
            device.resetWatcherTriggers();
        }
        if (device.hasWatcherTriggered("Update")) {
            device.resetWatcherTriggers();
        }

        //自启动提示
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


        //有些apk进去之后会有一个弹出框，因此如果有弹出框的话需要先点掉弹出框
        try {
            UiObject simchoose = new UiObject(new UiSelector().resourceId("android:id/button1")).getChild(new UiSelector().index(3));
            if (simchoose != null) {
                help.Log("simchoose: ", simchoose != null);
                simchoose.clickAndWaitForNewWindow();
                Thread.sleep(1000);
            } else {
                Thread.sleep(1000);
                help.Log("simchoose: ", simchoose != null);
            }
            Thread.sleep(100);
        } catch (Exception e) {
            help.Log("simchoose is not exsit");
        }

        int ii = 0;
        while (Runtimes-- > 0) {
            help.Log("TodayNews Test times: " + ii++);
            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入应用", enterApp(Tencentvideo));
            Thread.sleep(1000);
            device.pressBack();
            enterApp(Tencentvideo);
            Thread.sleep(10000);
            //判断在线升级弹出框

            //切换界面
            device.swipe(x * 4 / 5, y / 2, x / 5, y / 2, 20);
            Thread.sleep(5000);
            device.swipe(x * 4 / 5, y / 2, x / 5, y / 2, 20);
            Thread.sleep(5000);
            help.Log("Slide to the right");
            device.swipe(x * 4 / 5, y / 2, x / 5, y / 2, 20);
            Thread.sleep(5000);
            //判断在线升级弹出框


            //打开视频
            device.click(x / 2, y * 3 / 5);
            help.Log(" play video");
            Thread.sleep(10000);
            //device.click(x/2, y/5);//点到广告了
            Thread.sleep(10000);
            help.Log("Slide to the up and down");
            slide();

            //判断在线升级弹出框
            //播放视频10分钟
            Thread.sleep(600000);

            Thread.sleep(1000);
            device.pressBack();
            Thread.sleep(1000);
            device.pressBack();
            device.pressBack();
            help.Log("Exit");
        }
    }


    //打开应用
    private boolean enterApp(String command) throws InterruptedException {
        try {
            device.executeShellCommand(command);
            Thread.sleep(1000);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    //滑动
    public void slide() throws InterruptedException {
        int i;
        for (i = 1; i < 10; i++) {
            device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 6);
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 6);
            Thread.sleep(1000);
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

}