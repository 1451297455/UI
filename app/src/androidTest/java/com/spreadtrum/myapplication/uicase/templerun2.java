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

import static org.junit.Assert.assertTrue;

/**
 * Created by Jinchao_1.Zhang on 2017/7/14.
 */
@RunWith(AndroidJUnit4.class)
public class templerun2 {
    public static String templerun2 = "am start -n  com.imangi.templerun2/com.prime31.UnityPlayerProxyActivity";
    int x, y, i = 10, Runtimes = 1;
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
    public void templeruntest() throws Exception {

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

        device.registerWatcher("web", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 web = device.wait(Until.findObject(By.clazz("android.webkit.WebView")), 1000);
                if (web != null) {
                    device.pressBack();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("web")) {
            device.resetWatcherTriggers();
        }

        while (Runtimes-- > 0) {
            assertTrue("未进入应用", enterApp(templerun2));
            Thread.sleep(50000);
            device.click(x / 2, y / 2);
            help.Log("click");
            Thread.sleep(2000);
            //游戏中
            while (i-- > 0) {
                device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
                Thread.sleep(2000);
            }


            device.pressBack();
            Thread.sleep(3000);
            int a = x / 720 * 70, b = y / 1280 * 1240;
            int a1 = x / 720 * 540, b1 = y / 1280 * 720;
            Thread.sleep(3000);
            help.Log(a + " " + b + " " + x + " " + y);
            device.click(a, b);
            Thread.sleep(3000);
            device.click(a1, b1);
            help.Log(a1 + " " + b1);

            Thread.sleep(3000);
            device.pressBack();
            device.pressBack();
            Thread.sleep(3000);

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
