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

import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class FengHuang extends UntilHelp {

    //获取UC的包名
    private static final String FengHuang = "am start -n com.ifeng.news2/.activity.SplashActivity";
    private static final String KILL_FengHuang = "am force-stop com.ifeng.news2";
    private static UntilHelp help = null;
    private static UiDevice device = null;
    int x, y, Runtimes = 1;


    @Before
    public void init() throws Exception {
        help = UntilHelp.getInstance();
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        Runtimes = help.Runtimes();
        //获取屏幕分辨率
        x = help.GetWidth();
        y = help.GetHight();
    }

    @Test
    public void UserNewsFengHuang() throws Exception {

        UiObject2 FHtext = null;
        UiObject2 simchoose = null;
        int i;
        int ii = 0;
        //这里是点亮屏幕方法。
        help.OpenScreen();
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
        if (device.hasWatcherTriggered("permession")) {
            device.resetWatcherTriggers();
        }
        try {
            simchoose = device.wait(Until.findObject(By.res("android:id/button1")), 1000).getChildren().get(3);
            help.Log("simchoose: ", simchoose != null);
            simchoose.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(1000);
        } catch (Exception e) {
            help.Log("simchoose: ", simchoose != null);
        }
        Thread.sleep(100);
        while (Runtimes-- > 0) {
            help.Log("FengHuang Test times: " + ii++);
            //按home键
            device.pressHome();
            Thread.sleep(1000);

            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入应用", enterApp(FengHuang));
            Thread.sleep(1000);
            device.pressHome();

            enterApp(FengHuang);
            UiObject2 guidImg = device.wait(Until.findObject(By.res("com.ifeng.news2:id/guidImg")), 2000);
            int itime = 6;
            while (guidImg != null && itime > 0) {
                itime--;
                device.swipe(x / 5 * 4, y / 2, x / 5, y / 2, 20);
                guidImg = device.wait(Until.findObject(By.res("com.ifeng.news2:id/guidImg")), 2000);
            }
            enterApp(KILL_FengHuang);
            Thread.sleep(2000);
            enterApp(FengHuang);
            Thread.sleep(10000);

            i = 0;
            FHtext = device.wait(Until.findObject(By.res("com.ifeng.news2:id/logo")), 1000);
            while (FHtext == null && i < 10) {
                help.Log("com.ifeng.news2:id/logo ", FHtext != null);
                FHtext = device.wait(Until.findObject(By.res("com.ifeng.news2:id/logo")), 1000);
                i++;
            }
            device.pressBack();
            i = 10;
            while (i-- > 0) {
                device.drag(x * 4 / 5, y / 2, x / 5, y / 2, 20);
                help.Log("Slide to the left");
                Thread.sleep(5000);
                device.drag(x / 2, y / 5, x / 2, y * 4 / 5, 20);
                help.Log("Slide to the down");
                Thread.sleep(5000);
                device.click(x / 2, y / 2);
                help.Log("click");
                Thread.sleep(10000);
                device.drag(x / 2, y / 5 * 4, x / 2, y / 5, 20);
                help.Log("Slide to the up");
                Thread.sleep(5000);
                device.pressBack();
                help.Log("pressback");
                Thread.sleep(5000);
                device.drag(x / 2, y / 5 * 4, x / 2, y / 5, 20);
                help.Log("Slide to the up");
                Thread.sleep(5000);
                device.click(x / 2, y / 2);
                help.Log("click");
                Thread.sleep(10000);
                device.drag(x / 2, y / 5 * 4, x / 2, y / 5, 20);
                help.Log("Slide to the up");
                Thread.sleep(5000);
                help.Log("pressback");
                device.pressBack();
                Thread.sleep(5000);
            }

            i = 20;
            FHtext = device.wait(Until.findObject(By.text("头条")), 1000);
            while (i-- > 0 && FHtext == null) {
                help.Log("textMatches(\"头条\") ", FHtext != null);
                device.drag(x / 5, y / 2, x * 4 / 5, y / 2, 20);
                FHtext = device.wait(Until.findObject(By.text("头条")), 1000);
            }

            device.pressBack();
            device.pressBack();

            help.Log("Exit");
            device.pressBack();
            device.pressHome();

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

    private boolean enterApp(String command) throws InterruptedException {
        try {
            device.executeShellCommand(command);
            Thread.sleep(1000);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
