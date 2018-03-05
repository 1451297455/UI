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
public class kaixinxiaoxiaole {
    public static String Xiaoxiaole = "am start -n  com.happyelements.AndroidAnimal.wdj/com.happyelements.hellolua.MainActivity";
    int x, y, i, Runtimes = 1;
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
    public void xiaoxiaole() throws Exception {

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


        while (Runtimes-- > 0) {
            assertTrue("未进入游戏", enterApp(Xiaoxiaole));
            Thread.sleep(60000);
            device.click(x / 720 * 360, y / 1280 * 1100);
            help.Log("click login ：" + x / 2 + " " + y / 1280 * 1100);
            Thread.sleep(10000);
            device.click(x / 720 * 500, y / 1280 * 875);
            Thread.sleep(1000);
            device.click(x / 720 * 500, y / 1280 * 875);
            Thread.sleep(1000);
            device.click(x / 720 * 500, y / 1280 * 875);
            Thread.sleep(1000);
            device.click(x / 720 * 500, y / 1280 * 875);
            Thread.sleep(1000);
            device.click(x / 720 * 500, y / 1280 * 875);
            Thread.sleep(6000);
            device.click(x / 720 * 500, y / 1280 * 875);
            Thread.sleep(2000);
            device.click(x / 720 * 500, y / 1280 * 875);
            Thread.sleep(2000);
            device.click(x / 720 * 500, y / 1280 * 875);
            Thread.sleep(2000);
            device.click(x / 720 * 500, y / 1280 * 875);
            Thread.sleep(2000);
            device.click(x / 2, y / 1280 * 877);
            Thread.sleep(1000);
            device.click(x / 720 * 500, y / 1280 * 877);
            help.Log("click login ：" + x / 720 * 500 + " " + y / 1280 * 877);
            help.Log("click get");
            Thread.sleep(5000);
            device.pressBack();
            device.click(x / 720 * 200, y / 1280 * 900);
            help.Log("click login ：" + x / 720 * 200 + " " + y / 1280 * 900);
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
