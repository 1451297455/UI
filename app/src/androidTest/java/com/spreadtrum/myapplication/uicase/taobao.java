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
public class taobao {
    public static String Taobao = "am start -n  com.taobao.taobao/com.taobao.tao.welcome.Welcome";
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
    public void taobao() throws Exception {

        device.registerWatcher("Permession", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 permerssion = device.wait(Until.findObject(By.res("com.android.packageinstaller:id/permission_allow_button").textContains("允许")), 1000);
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
        device.registerWatcher("Local", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 permerssion = device.wait(Until.findObject(By.res("com.taobao.taobao:id/uik_mdButtonDefaultPositive").text("允许")), 1000);
                if (permerssion != null) {
                    permerssion.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("Local")) {
            device.resetWatcherTriggers();
        }
        device.registerWatcher("Close", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 close = device.wait(Until.findObject(By.res("com.taobao.taobao:id/uik_mdButtonClose")), 1000);
                if (close != null) {
                    close.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("close")) {
            device.resetWatcherTriggers();
        }

        assertTrue("enter fail", enterApp(Taobao));
        Thread.sleep(10000);
        UiObject2 tao = device.wait(Until.findObject(By.descContains("微淘")), 1000);
        while (tao == null && i-- > 0) {
            Thread.sleep(3000);
            tao = device.wait(Until.findObject(By.descContains("微淘")), 1000);
        }
        help.exitiinexception(i);
        assertTrue("enter fail", i > 0);
        i = 5;
        while (i-- > 0) {
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(3000);
        }
        tao = device.wait(Until.findObject(By.descContains("微淘")), 1000);
        if (tao != null) {
            tao.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(10000);
        }
        i = 5;
        while (i-- > 0) {
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(3000);
        }
        device.click(x / 2, y / 2);
        Thread.sleep(10000);
        device.pressBack();
        UiObject2 home = device.wait(Until.findObject(By.descContains("首页")), 1000);
        if (home != null) {
            home.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(10000);
            device.click(x / 2, y / 2);
            Thread.sleep(10000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.pressBack();
            device.pressBack();
            device.pressBack();
            device.pressBack();
        }
        device.pressBack();
        device.pressBack();
        device.pressBack();

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
