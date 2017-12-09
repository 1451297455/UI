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

import static java.lang.Thread.sleep;
import static junit.framework.Assert.assertTrue;

//import android.util.Log;

@RunWith(AndroidJUnit4.class)
public class UC {

    //获取UC的包名
    private static final String UC = "am start -n com.UCMobile/com.UCMobile.main.UCMobile";
    private UntilHelp help = null;
    private UiDevice device = null;
    private int x, y, Runtimes = 1;

    @Before
    public void init() throws Exception {
        help = UntilHelp.getInstance();
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        x = help.GetWidth();
        y = help.GetHight();
        Runtimes = help.Runtimes();
    }

    @Test
    //UC浏览器模块
    public void UserBroswerUC() throws Exception {

        //获取测试时间的传值

        UiObject UCview;
        UiObject otherok;
        int ii = 0;
        int i;
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


        UiObject2 cancle = device.wait(Until.findObject(By.text("取消")), 1000);
        if (cancle != null) {
            cancle.clickAndWait(Until.newWindow(), 1000);
        }

        UiObject simchoose = new UiObject(new UiSelector().resourceId("android:id/button1")).getChild(new UiSelector().index(3));
        if (simchoose.exists()) {
            help.Log("simchoose: ", simchoose.exists());
            simchoose.clickAndWaitForNewWindow();
            sleep(1000);
        } else {
            sleep(1000);
            help.Log(" simchoose: ", simchoose.exists());
        }
        sleep(100);

        while (Runtimes-- > 0) {
            help.Log("UC Test times: " + ii++);
            //按home键
            device.pressHome();
            sleep(100);
            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入应用", enterApp(UC));
            sleep(1000);
            device.pressHome();
            enterApp(UC);
            help.Log("Enter the application");

            sleep(20000);
            UiObject2 skipe = device.wait(Until.findObject(By.text("跳过")), 5000);
            if (skipe != null) {
                skipe.clickAndWait(Until.newWindow(), 5000);
            }
            UCview = new UiObject(new UiSelector().text("开始体验"));
            if (UCview.exists()) {
                UCview.click();
                sleep(1000);
                help.Log("Enter '开始体验1'");
            }
            sleep(10000);
            UCview = new UiObject(new UiSelector().text("开始体验"));
            if (UCview.exists()) {
                UCview.click();
                sleep(1000);
                help.Log("Enter '开始体验2'");
            }
            sleep(10000);
            UCview = new UiObject(new UiSelector().text("开始体验"));
            if (UCview.exists()) {
                UCview.click();
                sleep(1000);
                help.Log("Enter '开始体验3'");
            }
            UiObject2 dialog = device.wait(Until.findObject(By.text("不，谢谢")), 1000);
            if (dialog != null) {
                dialog.clickAndWait(Until.newWindow(), 1000);
            }
            //device.click(x/2, y*95/100);//点击相应坐标
            sleep(2000);
            UiObject2 view = null;
            view = device.wait(Until.findObject(By.clazz("android.view.View").descContains("homepage_search")), 1000);
            UiObject2 home = device.wait(Until.findObject(By.clazz("android.widget.RelativeLayout").descContains("controlbar_homepage")), 1000);
            if (view == null) {
                help.Log("shouye1:  ", view == null);
                home.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(2000);
            }
            //往上滑
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            sleep(2000);
            help.Log("Slide to the up");
            //下拉
            device.swipe(x / 2, y / 5, x / 2, y / 2, 10);
            help.Log("Slide to the down");
            sleep(5000);
            //点击坐标
            device.click(x / 2, y / 3 * 2);
            help.Log("click");
            sleep(10000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            help.Log("Slide to the up");
            sleep(5000);
            device.pressBack();
            sleep(2000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            help.Log("Slide to the up");
            sleep(5000);
            device.click(x / 2, y / 3 * 2);
            help.Log("click");
            sleep(10000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            help.Log("Slide to the up");
            sleep(5000);
            device.pressBack();
            sleep(2000);
            //往左滑
            Slide();
            //往左滑
            Slide();
            //往左滑
            Slide();
            //往左滑
            Slide();
            //往左滑
            Slide();
            //往左滑
            device.swipe(x * 4 / 5, y / 2, x / 5, y / 2, 20);
            help.Log("Slide to the left");
            sleep(5000);
            device.click(x / 2, y / 3 * 2);
            help.Log("click");
            sleep(10000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            help.Log("Slide to the up");
            sleep(5000);
            device.pressBack();
            sleep(2000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            help.Log("Slide to the up");
            sleep(5000);
            device.click(x / 2, y / 3 * 2);
            help.Log("click");
            sleep(10000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            help.Log("Slide to the up");
            sleep(5000);
            device.pressBack();
            sleep(2000);
            device.pressBack();
            sleep(5000);
            //获取控件并判断控件是否存在
            i = 10;
            try {
                UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(0));
                while (!UCview.exists() && i-- > 0) {
                    device.swipe(x / 2, y / 2, x * 4 / 5, y / 2, 20);
                    device.pressBack();
                    help.Log("PressBack main interface");
                    UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(0));
                    help.Log("click android.view.View0");
                }
                UCview.click();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }

            sleep(10000);
            device.click(x / 2, y / 2);
            sleep(10000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            sleep(10000);
            device.click(x / 2, y * 2 / 3);
            sleep(10000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            sleep(10000);
            device.click(x / 2, y / 3);
            help.Log("click");
            sleep(10000);
            device.pressBack();
            device.pressBack();
            UCview = new UiObject(new UiSelector().textMatches("退出"));
            while (!UCview.exists()) {
                device.pressBack();
                help.Log("PressBack main interface");
                UCview = new UiObject(new UiSelector().textMatches("退出"));
            }
            device.pressBack();
            sleep(2000);
            device.click(x / 100 * 99, y / 100 * 99);
            sleep(5000);

            try {
                i = 10;
                UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(1));
                while (!UCview.exists() && i-- > 0) {
                    device.swipe(x / 2, y / 2, x * 4 / 5, y / 2, 20);
                    device.pressBack();
                    help.Log("PressBack main interface");
                    UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(1));
                }
                UCview.click();
                help.Log("click android.view.View1");
            } catch (UiObjectNotFoundException e) {
                help.Log("对象未找到");
            }
            sleep(5000);
            device.click(x / 2, y / 2);
            sleep(10000);
            device.click(x / 2, y * 2 / 3);
            sleep(10000);
            device.click(x / 2, y / 3);
            help.Log("click");
            sleep(10000);
            device.pressBack();
            device.pressBack();
            i = 10;
            UCview = new UiObject(new UiSelector().textMatches("退出"));
            while (!UCview.exists() && i-- > 0) {
                device.pressBack();
                help.Log("PressBack main interface");
                sleep(1000);
                otherok = new UiObject(new UiSelector().textMatches("确 定"));
                if (otherok.exists()) {
                    otherok.click();
                    help.Log("click ok");
                    sleep(2000);
                }
                UCview = new UiObject(new UiSelector().textMatches("退出"));
            }
            device.pressBack();
            sleep(2000);
            device.click(x / 100 * 99, y / 100 * 99);
            sleep(5000);

            try {
                i = 10;
                UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(2));
                while (!UCview.exists() && i-- > 0) {
                    device.swipe(x / 2, y / 2, x * 4 / 5, y / 2, 20);
                    help.Log("PressBack main interface");
                    device.pressBack();
                    UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(2));
                }
                UCview.click();
                help.Log("click android.view.View2");
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
            sleep(10000);
            device.click(x / 2, y / 2);
            sleep(10000);
            device.click(x / 2, y * 2 / 3);
            sleep(10000);
            device.click(x / 2, y / 3);
            help.Log("click");
            sleep(10000);
            device.pressBack();
            device.pressBack();
            i = 10;
            UCview = new UiObject(new UiSelector().textMatches("退出"));
            while (!UCview.exists() && i-- > 0) {
                device.pressBack();
                help.Log("PressBack main interface");
                sleep(1000);
                otherok = new UiObject(new UiSelector().textMatches("确 定"));
                if (otherok.exists()) {
                    otherok.click();
                    help.Log("click ok");
                    sleep(2000);
                }
                UCview = new UiObject(new UiSelector().textMatches("退出"));
            }
            device.pressBack();
            sleep(2000);
            device.click(x / 100 * 99, y / 100 * 99);
            sleep(5000);

            try {
                i = 10;
                UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(3));
                while (!UCview.exists() && i-- > 0) {
                    device.swipe(x / 2, y / 2, x * 4 / 5, y / 2, 20);
                    device.pressBack();
                    help.Log("PressBack main interface");
                    UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(3));
                }
                UCview.click();

                help.Log("click android.view.View3");
            } catch (UiObjectNotFoundException e) {
                help.Log("对象未找到");
            }
            sleep(10000);
            device.click(x / 2, y / 2);
            sleep(10000);
            device.click(x / 2, y * 2 / 3);
            sleep(10000);
            device.click(x / 2, y / 3);
            help.Log("click");
            sleep(10000);
            device.pressBack();
            device.pressBack();
            i = 10;
            UCview = new UiObject(new UiSelector().textMatches("退出"));
            while (!UCview.exists() && i-- > 0) {
                device.pressBack();
                help.Log("PressBack main interface");
                sleep(1000);
                otherok = new UiObject(new UiSelector().textMatches("确 定"));
                if (otherok.exists()) {
                    otherok.click();
                    help.Log("click ok");
                    sleep(2000);
                }
                UCview = new UiObject(new UiSelector().textMatches("退出"));
            }
            device.pressBack();
            sleep(2000);
            device.click(x / 100 * 99, y / 100 * 99);
            sleep(5000);

            try {
                i = 10;
                UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(4));
                while (!UCview.exists() && i-- > 0) {
                    device.swipe(x / 2, y / 2, x * 4 / 5, y / 2, 20);
                    device.pressBack();
                    help.Log("PressBack main interface");
                    UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(4));
                }
                UCview.click();
                help.Log("click android.view.View4");
            } catch (UiObjectNotFoundException e) {
                help.Log("对象未找到");
            }
            sleep(10000);
            device.click(x / 2, y / 2);
            sleep(10000);
            device.click(x / 2, y * 2 / 3);
            sleep(10000);
            device.click(x / 2, y / 3);
            help.Log("click");
            sleep(10000);
            device.pressBack();
            device.pressBack();
            i = 10;
            UCview = new UiObject(new UiSelector().textMatches("退出"));
            while (!UCview.exists() && i-- > 0) {
                device.pressBack();
                help.Log("PressBack main interface");
                sleep(1000);
                otherok = new UiObject(new UiSelector().textMatches("确 定"));
                if (otherok.exists()) {
                    otherok.click();
                    help.Log("click ok");
                    sleep(2000);
                }
                UCview = new UiObject(new UiSelector().textMatches("退出"));
            }
            device.pressBack();
            sleep(2000);
            device.click(x / 100 * 99, y / 100 * 99);
            sleep(5000);

            try {
                i = 10;
                UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(5));
                while (!UCview.exists() && i-- > 0) {
                    device.swipe(x / 2, y / 2, x * 4 / 5, y / 2, 20);
                    device.pressBack();
                    help.Log("PressBack main interface");
                    UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(5));
                }
                UCview.click();
                help.Log("click android.view.View5");
            } catch (UiObjectNotFoundException e) {
                help.Log("对象未找到");
            }
            sleep(10000);
            device.click(x / 2, y / 2);
            sleep(10000);
            device.click(x / 2, y * 2 / 3);
            sleep(10000);
            device.click(x / 2, y / 3);
            help.Log("click");
            sleep(10000);
            device.pressBack();
            device.pressBack();
            i = 10;
            UCview = new UiObject(new UiSelector().textMatches("退出"));
            while (!UCview.exists() && i-- > 0) {
                device.pressBack();
                help.Log("PressBack main interface");
                sleep(1000);
                otherok = new UiObject(new UiSelector().textMatches("确 定"));
                if (otherok.exists()) {
                    otherok.click();
                    help.Log("click ok");
                    sleep(2000);
                }
                UCview = new UiObject(new UiSelector().textMatches("退出"));
            }
            device.pressBack();
            sleep(2000);
            device.click(x / 100 * 99, y / 100 * 99);
            sleep(5000);

            try {
                i = 10;
                UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(6));
                while (!UCview.exists() && i-- > 0) {
                    device.swipe(x / 2, y / 2, x * 4 / 5, y / 2, 20);
                    help.Log("PressBack main interface");
                    device.pressBack();
                    UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(6));
                }
                UCview.click();
                help.Log("click android.view.View6");
            } catch (UiObjectNotFoundException e) {
                help.Log("对象未找到");
            }
            sleep(10000);
            device.click(x / 2, y / 2);
            sleep(10000);
            device.click(x / 2, y * 2 / 3);
            sleep(10000);
            device.click(x / 2, y / 3);

            sleep(10000);
            device.pressBack();
            device.pressBack();
            i = 10;
            UCview = new UiObject(new UiSelector().textMatches("退出"));
            while (!UCview.exists() && i-- > 0) {
                device.pressBack();
                help.Log("PressBack main interface");
                sleep(1000);
                otherok = new UiObject(new UiSelector().textMatches("确 定"));
                if (otherok.exists()) {
                    help.Log("click ok");
                    otherok.click();
                    sleep(2000);
                }
                UCview = new UiObject(new UiSelector().textMatches("退出"));
            }
            device.pressBack();
            sleep(2000);
            device.click(x / 100 * 99, y / 100 * 99);
            sleep(5000);

            try {
                i = 10;
                UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(7));
                while (!UCview.exists() && i-- > 0) {
                    device.swipe(x / 2, y / 2, x * 4 / 5, y / 2, 20);
                    help.Log("PressBack main interface");
                    device.pressBack();
                    UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(7));
                }
                UCview.click();
                help.Log("click android.view.View7");
            } catch (UiObjectNotFoundException e) {
                help.Log("对象未找到");
            }

            sleep(10000);
            device.click(x / 2, y / 2);
            sleep(10000);
            device.click(x / 2, y * 2 / 3);
            sleep(10000);
            device.click(x / 2, y / 3);
            help.Log("click");
            sleep(10000);
            device.pressBack();
            device.pressBack();
            i = 10;
            UCview = new UiObject(new UiSelector().textMatches("退出"));
            while (!UCview.exists() && i-- > 0) {
                device.pressBack();
                help.Log("PressBack main interface");
                sleep(1000);
                otherok = new UiObject(new UiSelector().textMatches("确 定"));
                if (otherok.exists()) {
                    otherok.click();
                    help.Log("click ok");
                    sleep(2000);
                }
                UCview = new UiObject(new UiSelector().textMatches("退出"));
            }
            device.pressBack();
            sleep(2000);
            device.click(x / 100 * 99, y / 100 * 99);
            sleep(5000);

            try {
                i = 10;
                UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(8));
                while (!UCview.exists() && i-- > 0) {
                    device.swipe(x / 2, y / 2, x * 4 / 5, y / 2, 20);
                    device.pressBack();
                    help.Log("PressBack main interface");
                    UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(8));
                }
                UCview.click();
                help.Log("click android.view.View8");
            } catch (UiObjectNotFoundException e) {
                help.Log("对象未找到");
            }
            sleep(10000);
            device.click(x / 2, y / 2);
            sleep(10000);
            device.click(x / 2, y * 2 / 3);
            sleep(10000);
            device.click(x / 2, y / 3);
            help.Log("click");
            sleep(10000);
            device.pressBack();
            device.pressBack();
            i = 10;
            UCview = new UiObject(new UiSelector().textMatches("退出"));
            while (!UCview.exists() && i-- > 0) {
                device.pressBack();
                help.Log("click android.view.View");
                sleep(1000);
                otherok = new UiObject(new UiSelector().textMatches("确 定"));
                if (otherok.exists()) {
                    otherok.click();
                    help.Log("click ok");
                    sleep(2000);
                }
                UCview = new UiObject(new UiSelector().textMatches("退出"));
            }
            device.pressBack();
            sleep(2000);
            device.click(x / 100 * 99, y / 100 * 99);
            sleep(5000);

            try {
                i = 10;
                UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(9));
                while (!UCview.exists() && i-- > 0) {
                    device.swipe(x / 2, y / 2, x * 4 / 5, y / 2, 20);
                    device.pressBack();
                    help.Log("PressBack main interface");
                    UCview = new UiObject(new UiSelector().classNameMatches("android.view.View").index(9));
                }
                UCview.click();
                help.Log("click android.view.View9");
            } catch (UiObjectNotFoundException e) {
                help.Log("对象未找到");
            }
            sleep(10000);
            device.click(x / 2, y / 2);
            sleep(10000);
            device.click(x / 2, y * 2 / 3);
            sleep(5000);
            device.click(x / 2, y / 3);
            help.Log("click");
            sleep(10000);
            device.pressBack();
            device.pressBack();
            i = 10;
            UCview = new UiObject(new UiSelector().textMatches("退出"));
            while (!UCview.exists() && i-- > 0) {
                device.pressBack();
                help.Log("PressBack main interface");
                sleep(1000);
                otherok = new UiObject(new UiSelector().textMatches("确 定"));
                if (otherok.exists()) {
                    otherok.click();
                    help.Log("click ok");
                    sleep(2000);
                }
                UCview = new UiObject(new UiSelector().textMatches("退出"));
            }
            device.pressBack();
            sleep(2000);
            device.click(x / 100 * 99, y / 100 * 99);
            sleep(5000);


            UCview = new UiObject(new UiSelector().textMatches("退出"));
            while (!UCview.exists()) {
                device.pressBack();
                UCview = new UiObject(new UiSelector().textMatches("退出"));
            }
            UCview.click();
            help.Log("Exit");
            sleep(5000);

        }
    }

    private void Slide() throws InterruptedException {
        //往左滑
        device.swipe(x * 4 / 5, y / 2, x / 5, y / 2, 20);
        help.Log("Slide to the left");
        sleep(5000);
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
        help.Log("Slide to the down");
        sleep(5000);
        device.click(x / 2, y / 3 * 2);
        help.Log("click");
        sleep(10000);
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
        help.Log("Slide to the up");
        sleep(5000);
        UiObject2 uctop = device.wait(Until.findObject(By.text("UC头条")), 1000);
        if (uctop == null) {
            device.pressBack();
            sleep(2000);
        }
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
        help.Log("Slide to the up");
        sleep(5000);
        device.click(x / 2, y / 3 * 2);
        help.Log("click");
        sleep(10000);
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
        help.Log("Slide to the up");
        sleep(5000);
        uctop = device.wait(Until.findObject(By.text("UC头条")), 1000);
        if (uctop == null) {
            device.pressBack();
            sleep(2000);
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
