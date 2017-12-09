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
public class panda {
    public static String Panda = "am start -n  com.panda.videoliveplatform/com.panda.videoliveplatform.activity.WelcomeActivity";
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
    public void pandatest() throws Exception {

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

        device.registerWatcher("Update", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 permerssion = device.wait(Until.findObject(By.textContains("立即更新")), 1000);
                if (permerssion != null) {
                    device.pressBack();
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("Update")) {
            device.resetWatcherTriggers();
        }

        while (Runtimes-- > 0) {
            assertTrue(" 未成功进入应用", enterApp(Panda));
            Thread.sleep(2000);
            enterApp(Panda);
            UiObject2 skip = device.wait(Until.findObject(By.res("com.panda.videoliveplatform:id/tv_skip").text("跳过")), 1000);
            if (skip != null) skip.clickAndWait(Until.newWindow(), 1000);
            UiObject2 game = device.wait(Until.findObject(By.res("com.panda.videoliveplatform:id/main_column_live_btn").text("游戏")), 1000);
            assertTrue("  未成功进入应用", game != null);
            game.clickAndWait(Until.newWindow(), 1000);
            UiObject2 progress = device.wait(Until.findObject(By.res("com.panda.videoliveplatform:id/loading_img")), 2000);
            while (progress != null && i-- > 0) {
                Thread.sleep(1000);
                progress = device.wait(Until.findObject(By.res("com.panda.videoliveplatform:id/loading_img")), 2000);
            }
            assertTrue("  网络异常", i >= 0);
            UiObject2 LoL = device.wait(Until.findObject(By.res("com.panda.videoliveplatform:id/iv_pic")), 1000);
            i = 20;
            while (LoL == null && i-- > 0) {
                Thread.sleep(1000);
                LoL = device.wait(Until.findObject(By.res("com.panda.videoliveplatform:id/iv_pic")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("  网络异常", i >= 0);
            LoL.clickAndWait(Until.newWindow(), 2000);
            i = 20;
            Thread.sleep(60000);
            i = 3;
            while (i-- > 0) {
                device.pressBack();
                device.pressBack();
                device.pressBack();
                device.pressBack();
            }
            System.out.println("exit");

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
