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
public class huya {
    public static String Huya = "am start -n  com.duowan.kiwi/com.duowan.kiwi.simpleactivity.SplashActivity";
    int x, y, i = 20, Runtimes = 2;
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
    public void huyatest() throws Exception {

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


        device.registerWatcher("Pass", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 pass = device.wait(Until.findObject(By.res("com.duowan.kiwi:id/guide_btn")), 1000);
                if (pass != null) {
                    pass.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("Pass")) {
            device.resetWatcherTriggers();
        }


        while (Runtimes-- > 0) {
            assertTrue("  未进入应用", enterApp(Huya));
            Thread.sleep(6000);
            UiObject2 home = device.wait(Until.findObject(By.res("com.duowan.kiwi:id/title").text("首页")), 1000);
            i = 20;
            while (home == null && i-- > 0) {
                Thread.sleep(1000);
                home = device.wait(Until.findObject(By.res("com.duowan.kiwi:id/title").text("首页")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("网络异常", i > 0);
            UiObject2 LoL = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("LOL")), 1000);
            i = 20;
            while (LoL == null && i-- > 0) {
                Thread.sleep(1000);
                LoL = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("LOL")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("网络异常", i > 0);
            LoL = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("LOL")), 1000);
            LoL.clickAndWait(Until.newWindow(), 1000);
            UiObject2 list = device.wait(Until.findObject(By.res("com.duowan.kiwi:id/image")), 1000);
            i = 20;
            while (list == null && i-- > 0) {
                Thread.sleep(3000);
                list = device.wait(Until.findObject(By.res("com.duowan.kiwi:id/image")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("网络异常", i > 0);
            list.clickAndWait(Until.newWindow(), 5000);
//            UiObject2 listitem = list.getChildren().get(7).getChildren().get(0).getChildren().get(1);
//            if (listitem != null)
//                listitem.clickAndWait(Until.newWindow(), 1000);
            device.click(x / 2, y / 3 * 2);
            Thread.sleep(300000);
//            UiObject2 Launch = device.wait(Until.findObject(By.res("com.android.launcher3:id/launcher")), 1000);
            i = 5;
            while (i-- > 0) {
                device.pressBack();
                device.pressBack();
                device.pressBack();
                device.pressBack();
//                Launch = device.wait(Until.findObject(By.res("com.android.launcher3:id/launcher")), 1000);
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
