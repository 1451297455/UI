package com.spreadtrum.myapplication.uicase;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
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
public class Youku {

    private static final String Youku = "am start -n com.youku.phone/.ActivityWelcome";
    private UntilHelp help = null;
    private UiDevice device = null;
    int x, y, Runtimes = 1;

    @Before
    public void init() throws Exception {
        help = UntilHelp.getInstance();
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        x = help.GetWidth();
        y = help.GetHight();
        Runtimes = help.Runtimes();
    }

    @Test
    public void UserVideoYouku() throws Exception {
        //点亮屏幕
        help.OpenScreen();


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

        device.registerWatcher("Updata", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 OK = device.wait(Until.findObject(By.text("取消")), 1000);
                if (OK != null) {
                    OK.clickAndWait(Until.newWindow(), 10000);
                    return true;
                }
                return false;

            }
        });
        if (device.hasWatcherTriggered("Updata")) {
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
//        UiObject2 simchoose = device.wait(Until.findObject(By.res("android:id/button1")), 1000).getChildren().get(3);

        UiObject simchoose = new UiObject(new UiSelector().resourceId("android:id/button1")).getChild(new UiSelector().index(3));
        if (simchoose.exists()) {
            help.Log("simchoose: ", simchoose.exists());
            simchoose.clickAndWaitForNewWindow();
            Thread.sleep(1000);
        } else {
            Thread.sleep(1000);
            help.Log("simchoose: ", simchoose.exists());
        }
        Thread.sleep(100);
        int ii = 1;
        while (Runtimes-- > 0) {
            //打开apk
            help.Log("Youku Test times: " + ii++);

            assertTrue(help.GetCurrTime() + "  未成功进入应用", enterApp(Youku));
            Thread.sleep(1000);
            device.pressBack();

            enterApp(Youku);
            //判断是否有引导页
            Grade();
            Thread.sleep(5000);
            //判断自动升级是否存在
            UiObject2 preview = device.wait(Until.findObject(By.clazz("android.widget.RadioGroup")), 1000);
//            UiObject preview = new UiObject(new UiSelector().className("android.widget.RadioGroup"));
            if (preview != null) {
                help.Log("preview: ", preview != null);
                device.pressBack();
                Thread.sleep(5000);
            } else {
                help.Log("preview: ", preview != null);
                Thread.sleep(5000);
            }

            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 40);
            help.Log("swipe");
            ChangTab("com.youku.phone:id/layout_home");
            ChangTab("com.youku.phone:id/layout_hotspot");
            ChangTab("com.youku.phone:id/layout_vip");

            device.pressBack();
            Thread.sleep(3000);
            device.pressBack();
            Thread.sleep(3000);
            device.pressBack();
            Thread.sleep(3000);
            device.pressBack();


            Thread.sleep(2000);
            device.pressBack();
            Thread.sleep(100);
            device.pressBack();
            Thread.sleep(100);
            device.pressBack();
            Thread.sleep(100);
            device.pressBack();
            help.Log("Exit");
        }
    }

    private void ChangTab(String string) throws UiObjectNotFoundException, InterruptedException {
        if (string.equals("com.youku.phone:id/layout_home")) {
            device.click(x / 10 * 1, y / 100 * 99);
        } else if (string.equals("com.youku.phone:id/layout_hotspot")) {
            device.click(x / 10 * 3, y / 100 * 99);
        } else {
            device.click(x / 10 * 5, y / 100 * 99);
        }

        //浏览精选页
//        UiScrollable scrollable = new UiScrollable(new UiSelector().resourceId("com.youku.phone:id/home_main_recyclerview"));
//        scrollable.flingToEnd(4);
//        scrollable.flingToBeginning(4);
        Slide();
        //浏览剧集
        device.swipe(x / 5 * 4, y / 2, x / 5, y / 2, 20);
        Thread.sleep(2000);
        Slide();
        //观看视频并上下滑动视频观看页面
        device.click(x / 4 * 3, y / 4 * 3);
        Thread.sleep(2000);
        device.swipe(x / 2, y / 10 * 9, x / 2, y / 2, 20);
        Thread.sleep(2000);
        device.swipe(x / 2, y / 10 * 9, x / 2, y / 2, 20);
        Thread.sleep(2000);
        device.swipe(x / 2, y / 2, x / 2, y / 10 * 9, 20);
        Thread.sleep(2000);
        device.swipe(x / 2, y / 2, x / 2, y / 10 * 9, 20);
        Thread.sleep(2000);
        help.Log("观看视频5分钟");
        Thread.sleep(300000);
        //防止弹出升级框
        device.pressBack();
        //浏览综艺
        device.swipe(x / 6 * 5, y / 2, x / 6, y / 2, 10);
        Thread.sleep(2000);
        Slide();
        //浏览电影
        device.swipe(x / 6 * 5, y / 2, x / 6, y / 2, 10);
        Thread.sleep(2000);
        Slide();
        //浏览娱乐
        device.swipe(x / 6 * 5, y / 2, x / 6, y / 2, 10);
        Thread.sleep(2000);
        Slide();
        //浏览资讯
        device.swipe(x / 6 * 5, y / 2, x / 6, y / 2, 10);
        Thread.sleep(2000);
        Slide();
        //浏览动漫
        device.swipe(x / 6 * 5, y / 2, x / 6, y / 2, 10);
        Thread.sleep(2000);
        Slide();


    }

    private void Slide() throws InterruptedException, UiObjectNotFoundException {


        help.Log("slipe to up");
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 20);
        Thread.sleep(2000);
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 20);
        Thread.sleep(2000);
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 20);
        Thread.sleep(2000);
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 20);
        Thread.sleep(2000);
        help.Log(" slipe to down");
        device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 20);
        Thread.sleep(2000);
        device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 20);
        Thread.sleep(2000);
        device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 20);
        Thread.sleep(2000);
        device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 20);
        Thread.sleep(2000);
    }


    private void Grade() {
        UiObject2 pass = device.wait(Until.findObject(By.text("跳过")), 1000);
        if (pass != null) {
            pass.clickAndWait(Until.newWindow(), 1000);
            help.Log("pass");
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

