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
public class tmall {
    public static String Tmall = "am start -n  com.tmall.wireless/com.tmall.wireless.splash.TMSplashActivity";
    public static String KILL_Tmall = "am force-stop  com.tmall.wireless";
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
    public void tmall() throws Exception {

        device.registerWatcher("Permession", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 permerssion = device.wait(Until.findObject(By.text("允许")), 1000);
                if (permerssion != null) {
                    permerssion.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("Permession")) {
            device.resetWatcherTriggers();
        }

        device.registerWatcher("ss", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 content = device.wait(Until.findObject(By.res("android:id/content")), 1000);
                UiObject2 skipe = content.getChildren().get(0).getChildren().get(0).getChildren().get(4).getChildren().get(0);
                if (skipe != null) {
                    skipe.clickAndWait(Until.newWindow(), 1000);
                    System.out.println("Img click");
                    return true;
                }
                return false;

            }
        });
        device.registerWatcher("Pass", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 pass = device.wait(Until.findObject(By.res("com.tmall.wireless:id/tm_page_guide_id")), 1000);
                if (pass != null) {
                    UiObject2 skip = pass.getChildren().get(0);
                    skip.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("Pass")) {
            device.resetWatcherTriggers();
        }

        while (Runtimes-- > 0) {
            assertTrue(" 未成功进入应用", enterApp(Tmall));
            Thread.sleep(10000);
            UiObject2 list = device.wait(Until.findObject(By.res("android:id/tabs")), 1000);
            while (list == null && i-- > 0) {
                Thread.sleep(2000);
                list = device.wait(Until.findObject(By.res("android:id/tabs")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("网络超时", i > 0);
            i = 10;
            while (i-- > 0) {
                device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
                Thread.sleep(2000);
            }
            enterApp(KILL_Tmall);
            Thread.sleep(3000);
            enterApp(Tmall);
            list = device.wait(Until.findObject(By.res("android:id/tabs")), 1000);
            i = 20;
            while (list == null && i-- > 0) {
                Thread.sleep(3000);
                list = device.wait(Until.findObject(By.res("android:id/tabs")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("网络超时", i > 0);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.click(x / 3, y / 3 * 2);
            Thread.sleep(3000);
            device.swipe(x / 6 * 5, y / 6 * 5, x / 6, y / 6 * 5, 20);
            Thread.sleep(1000);
            device.swipe(x / 6 * 5, y / 6 * 5, x / 6, y / 6 * 5, 20);
            Thread.sleep(1000);
            device.swipe(x / 6 * 5, y / 6 * 5, x / 6, y / 6 * 5, 20);
            Thread.sleep(1000);
            device.swipe(x / 6, y / 6 * 5, x / 6 * 5, y / 6 * 5, 20);
            Thread.sleep(1000);
            device.swipe(x / 6, y / 6 * 5, x / 6 * 5, y / 6 * 5, 20);
            Thread.sleep(1000);
            device.swipe(x / 6, y / 6 * 5, x / 6 * 5, y / 6 * 5, 20);
            Thread.sleep(1000);
            device.pressBack();
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(1000);
            device.click(x / 3 * 2, y / 3 * 2);
            Thread.sleep(3000);
            device.swipe(x / 6 * 5, y / 6 * 5, x / 6, y / 6 * 5, 20);
            Thread.sleep(1000);
            device.swipe(x / 6 * 5, y / 6 * 5, x / 6, y / 6 * 5, 20);
            Thread.sleep(1000);
            device.swipe(x / 6 * 5, y / 6 * 5, x / 6, y / 6 * 5, 20);
            Thread.sleep(1000);
            device.swipe(x / 6, y / 6 * 5, x / 6 * 5, y / 6 * 5, 20);
            Thread.sleep(1000);
            device.swipe(x / 6, y / 6 * 5, x / 6 * 5, y / 6 * 5, 20);
            Thread.sleep(1000);
            device.swipe(x / 6, y / 6 * 5, x / 6 * 5, y / 6 * 5, 20);
            Thread.sleep(1000);
            device.pressBack();

            list = device.wait(Until.findObject(By.res("android:id/tabs")), 1000);
            if (list != null) {
                help.Log("list", list != null);
                UiObject2 pingpai = device.wait(Until.findObject(By.text("会员")), 2000);
                help.Log("list1", pingpai != null);
                if (pingpai != null) {
//                    pingpai.clickAndWait(Until.newWindow(), 5000);
                    device.click(x / 2, y / 100 * 99);
                    device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
                    Thread.sleep(2000);
                    device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
                    Thread.sleep(2000);
                    device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
                    Thread.sleep(2000);
                    device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
                    Thread.sleep(2000);
                } else {
                    device.pressBack();
                    device.pressBack();
                    device.pressBack();
                    device.pressBack();
                }

            } else {
                device.pressBack();
                device.pressBack();
                device.pressBack();
                device.pressBack();
            }

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
