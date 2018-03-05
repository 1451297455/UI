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
public class subway {
    public static String Subway = "am start -n com.kiloo.subwaysurf/com.kiloo.unityutilities.UnityPluginActivity";
    public static String KIll_Subway = "am force-stop com.kiloo.subwaysurf";
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
    public void Subway() throws Exception {

        device.registerWatcher("Permession", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 permerssion = device.wait(Until.findObject(By.text("允许")), 1000);
                if (permerssion != null) {
                    permerssion.clickAndWait(Until.newWindow(), 1000);
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

        device.registerWatcher("Exit", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 exit = device.wait(Until.findObject(By.text("Quit")), 1000);
                if (exit != null) {
                    exit.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("Exit")) {
            device.resetWatcherTriggers();
        }

        while (Runtimes-- > 0) {

            assertTrue("enter fail", enterApp(Subway));
            Thread.sleep(60000);
            device.click(x / 2, y / 2);
            help.Log("start" + x / 2 + " " + y / 2);
            Thread.sleep(30000);//游戏中
            device.pressBack();
            Thread.sleep(3000);
            device.click(x / 720 * 100, y / 1280 * 1177);
            Thread.sleep(3000);
            UiObject2 exit = device.wait(Until.findObject(By.text("Quit")), 1000);
            i = 10;
            while (exit == null && i-- > 0) {
                device.pressBack();
                Thread.sleep(2000);
                exit = device.wait(Until.findObject(By.text("Quit")), 1000);
            }
            if (i == 0) {
                enterApp(KIll_Subway);
            }else {
                exit = device.wait(Until.findObject(By.text("Quit")), 1000);
                exit.clickAndWait(Until.newWindow(), 1000);
            }

            UiObject2 close = device.wait(Until.findObject(By.res("android:id/aerr_close").text("关闭应用")), 1000);
            if (close != null) {
                close.clickAndWait(Until.newWindow(), 1000);
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
