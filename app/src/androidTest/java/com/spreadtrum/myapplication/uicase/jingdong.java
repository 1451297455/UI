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
public class jingdong {
    public static String JingDong = "am start -n com.jingdong.app.mall/com.jingdong.app.mall.main.MainActivity";
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
        device.registerWatcher("Close", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 close = device.wait(Until.findObject(By.textContains("关闭")), 1000);
                if (close != null) {
                    close.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("Close")) {
            device.resetWatcherTriggers();
        }
        device.registerWatcher("Closead", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 close = device.wait(Until.findObject(By.res("com.jingdong.app.mall:id/aqz")), 1000);
                if (close != null) {
                    close.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("Closead")) {
            device.resetWatcherTriggers();
        }


        while (Runtimes-- > 0) {
            assertTrue("  未成功进入应用", enterApp(JingDong));
            Thread.sleep(5000);
            UiObject2 home = device.wait(Until.findObject(By.res("com.jingdong.app.mall:id/q1")), 1000);
            while (home == null && i-- > 0) {
                Thread.sleep(3000);
                help.Log("times1:  " + i);
                home = device.wait(Until.findObject(By.res("com.jingdong.app.mall:id/q1")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("network enable", i > 0);
            home = device.wait(Until.findObject(By.res("com.jingdong.app.mall:id/q1")), 1000);
            UiObject2 homepage = home.getChildren().get(0);
            homepage.clickAndWait(Until.newWindow(), 1000);
            UiObject2 list = device.wait(Until.findObject(By.res("com.jingdong.app.mall:id/g_")), 1000);
            i = 20;
            while (list == null && i-- > 0) {
                Thread.sleep(3000);
                help.Log("times2:  " + i);
                list = device.wait(Until.findObject(By.res("com.jingdong.app.mall:id/g_")), 1000);
            }
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);

            UiObject2 hometab = device.wait(Until.findObject(By.res("com.jingdong.app.mall:id/q1")), 1000);
            UiObject2 stype = hometab.getChildren().get(1);
            assertTrue("未知错误", stype != null);
            stype.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(2000);
            UiObject2 item = device.wait(Until.findObject(By.res("com.jd.lib.category:id/category_item_have_picture_image_2")), 1000);
            i = 20;
            while (item == null && i-- > 0) {
                Thread.sleep(3000);
                help.Log("times4:  " + i);
                item = device.wait(Until.findObject(By.res("com.jd.lib.category:id/category_item_have_picture_image_2")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("network enable ", i > 0);
            item.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(2000);
            device.pressBack();
            Thread.sleep(2000);
            device.pressBack();
            Thread.sleep(2000);
            device.pressBack();
            device.pressBack();
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
