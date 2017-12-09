package com.spreadtrum.myapplication.uicase;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
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
public class TodayNews {

    private static final String todaynews = "am start -n com.ss.android.article.news/.activity.SplashActivity";
    UiDevice device = null;
    UntilHelp help = null;
    int x, y, Runtimes = 1;

    @Before
    public void init() throws Exception {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        help = UntilHelp.getInstance();
        x = help.GetWidth();
        y = help.GetHight();
        Runtimes = help.Runtimes();
    }

    @Test
    public void UserNewsTodaynews() throws Exception {

        //获取测试时间的传值
        UiObject Todaybutton, Todayview, Todaytext;
        int ii = 0;

        //设置监听事件
        device.registerWatcher("daduan", new UiWatcher() {

            @Override
            public boolean checkForCondition() {
                // TODO Auto-generated method stub
                UiObject Todaybutton = new UiObject(new UiSelector().resourceId("com.ss.android.article.news:id/later_btn").text("以后再说"));
                if (Todaybutton.exists()) {
                    try {
                        Todaybutton.click();
                    } catch (UiObjectNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {

                    }
                }
                UiObject2 Todaytext = device.wait(Until.findObject(By.text("忽略")), 1000);
                if (Todaytext != null) {
                    try {
                        Todaytext.click();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
                return false;
            }

        });


        //这里是点亮屏幕方法。
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
        //判断是否有弹出框
        UiObject simchoose = new UiObject(new UiSelector().resourceId("android:id/button1")).getChild(new UiSelector().index(3));
        if (simchoose.exists()) {
            help.Log(" simchoose:", simchoose.exists());
            simchoose.clickAndWaitForNewWindow();
            Thread.sleep(1000);
        } else {
            Thread.sleep(1000);
            help.Log("simchoose: ", simchoose.exists());
        }
        Thread.sleep(100);

        //开始测试case
        while (Runtimes-- > 0) {
            help.Log("TodayNews Test times: " + ii++);

            device.pressHome();
            Thread.sleep(100);
            device.pressHome();

            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入应用", enterApp(todaynews));
            Thread.sleep(1000);
            device.pressHome();

            enterApp(todaynews);
            Thread.sleep(10000);

            Todaybutton = new UiObject(new UiSelector().resourceId("com.ss.android.article.news:id/start_btn").text("进入应用"));
            if (Todaybutton.exists()) {
                help.Log("com.ss.android.article.news:id/start_btn", Todaybutton.exists());
                Todaybutton = new UiObject(new UiSelector().resourceId("com.ss.android.article.news:id/hint_text"));
                if (Todaybutton.exists()) {
                    help.Log("com.ss.android.article.news:id/hint_text ", Todaybutton.exists());
                    Todaybutton.click();
                }
                Thread.sleep(5000);
            }

            Todaybutton = new UiObject(new UiSelector().resourceId("com.ss.android.article.news:id/close_introduction").index(5));
            if (Todaybutton.exists()) {
                help.Log("com.ss.android.article.news:id/close_introduction ", Todaybutton.exists());
                Todaybutton.click();
                Thread.sleep(5000);
            }

            Todaybutton = new UiObject(new UiSelector().resourceId("android:id/button1").index(1));
            if (Todaybutton.exists()) {
                help.Log("android:id/button1 ", Todaybutton.exists());
                Todaybutton.click();
                Thread.sleep(5000);
            }

            Todayview = new UiObject(new UiSelector().resourceId("com.ss.android.article.news:id/handle_view").index(1));
            if (Todaybutton.exists()) {
                help.Log("android:id/button1 ", Todaybutton.exists());
                Todaybutton.click();
                Thread.sleep(5000);
            }
            Thread.sleep(10000);
            Todayview = new UiObject(new UiSelector().resourceId("com.ss.android.article.news:id/btn_left").index(0));
            if (Todaybutton.exists()) {
                help.Log("android:id/button1 ", Todaybutton.exists());
                Todaybutton.click();
                Thread.sleep(5000);
            }

            Todayview = new UiObject(new UiSelector().resourceId("com.ss.android.article.news:id/top_head").index(0));
            if (!Todayview.exists()) {
                help.Log("com.ss.android.article.news:id/top_head ", Todayview.exists());
                device.pressBack();
                Thread.sleep(5000);
            }


            try {
                Todaytext = new UiObject(new UiSelector().text("确定"));
//            Todaytext = device.wait(Until.findObject(By.text("确定")));
                if (Todaytext != null) {
                    Todaytext.click();
                    Thread.sleep(5000);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            int i = 10;
            while (i-- > 0) {
                device.drag(x / 2, y / 5, x / 2, y / 5 * 4, 20);
                help.Log("Slide to the down");
                Thread.sleep(5000);
                device.click(x / 2, y / 2);
                help.Log("click");
                Thread.sleep(10000);
                device.drag(x / 2, y / 5 * 4, x / 2, y / 5, 10);
                help.Log(" Slide to the up");
                Thread.sleep(5000);
                device.pressBack();
                help.Log("pressback");
                Thread.sleep(5000);
                device.drag(x / 2, y / 5 * 4, x / 2, y / 5, 10);
                help.Log("Slide to the up");
                Thread.sleep(5000);
                device.click(x / 2, y / 2);
                help.Log("click");
                Thread.sleep(10000);
                device.drag(x / 2, y / 5 * 4, x / 2, y / 5, 10);
                help.Log("Slide to the up");
                Thread.sleep(5000);
                device.click(x / 2, y / 2);
                help.Log("click");
                Thread.sleep(10000);
                device.pressBack();
                help.Log("pressback");
                Thread.sleep(5000);
                int iii = 20;
//                Todayview = device.wait(Until.findObject(By.res("com.ss.android.article.news:id/icon_category")), 1000);
                Todayview = new UiObject(new UiSelector().resourceId("com.ss.android.article.news:id/icon_category"));
                while (!Todayview.exists() && iii-- > 0) {
                    device.pressBack();
                    help.Log("com.ss.android.article.news:id/icon_category ", Todayview.exists());
                    Todayview = new UiObject(new UiSelector().resourceId("com.ss.android.article.news:id/icon_category"));
//                    Todayview = device.wait(Until.findObject(By.res("com.ss.android.article.news:id/icon_category")), 1000);
                }
                Thread.sleep(5000);
                device.drag(x / 5 * 4, y / 2, x / 5, y / 2, 20);
                Thread.sleep(5000);
            }
            int iii = 20;
            Todaytext = new UiObject(new UiSelector().textMatches("推荐").index(0));
//            UiObject2 Todaytext1 = device.wait(Until.findObject(By.text("推荐")), 1000).getChildren().get(0);
            while (Todaytext == null && iii-- > 0) {
                device.drag(x / 5, y / 2, x / 5 * 4, y / 2, 20);
                Thread.sleep(1000);
                help.Log("textMatches(\"推荐\") ", Todaytext != null);
                Todaytext = new UiObject(new UiSelector().textMatches("推荐").index(0));
//                Todaytext1 = device.wait(Until.findObject(By.text("推荐")), 1000).getChildren().get(0);
            }
            Thread.sleep(1000);
            device.pressBack();
            device.pressBack();
            Thread.sleep(1000);
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

    //启动应用的函数
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
