package com.spreadtrum.myapplication.uicase;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiCollection;
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
public class QQ {

    private static final String QQ = "am start -n com.tencent.mobileqq/.activity.SplashActivity";
    private static UntilHelp help = null;
    private static UiDevice device = null;
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
    public void UserSocietyQQ() throws Exception {

        //唤醒屏幕
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
        //有些apk进去之后会有一个弹出框，因此如果有弹出框的话需要先点掉弹出框
        DialogShow();
//        Login();

        while (Runtimes-- > 0) {
            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入应用", enterApp(QQ));
            Thread.sleep(2000);
            device.pressBack();
            enterApp(QQ);
            Thread.sleep(2000);
            //切换到QQ消息Tab页

//            device.click(x / 8, y / 15);
//            UiObject Message = null;
//            UiObject Contact = null;
//            Contact = new UiObject(new UiSelector().resourceId("com.tencent.mobileqq:id/name").text("联系人"));
//            if (Contact.exists()) {
//                Message = new UiObject(new UiSelector().resourceId("com.tencent.mobileqq:id/name").text("消息"));
//                help.Log(" messlist:", Message.exists());
//                Message.clickAndWaitForNewWindow();
//                Thread.sleep(1000);
//            } else {
//                boolean t = true;
//                while (t) {
//                    Thread.sleep(1000);
//                    help.Log(" messlist: ", Contact.exists());
//                    device.pressBack();
//                    Contact = new UiObject(new UiSelector().resourceId("com.tencent.mobileqq:id/name").text("联系人"));
//                    if (Contact.exists()) {
//                        Message = new UiObject(new UiSelector().resourceId("com.tencent.mobileqq:id/name").text("消息"));
//                        Message.clickAndWaitForNewWindow();
//                        t = false;
////
//                    }
//
//                }
//            }
            Thread.sleep(3000);
            //流畅性体验
            Fluency("消息");
            Fluency("联系人");
            Fluency("动态");
            Thread.sleep(3000);
            //浏览腾讯新闻
            ReadNews();


            UiScrollable scrollable = new UiScrollable(new UiSelector().resourceId("com.tencent.mobileqq:id/lebasv"));
            scrollable.scrollTextIntoView("好友动态");
            Thread.sleep(1000);
            UiObject2 test1 = device.wait(Until.findObject(By.res("com.tencent.mobileqq:id/qzone_feed_entry")), 1000);
            if (test1 != null) {
                help.Log("Zone: ", test1 != null);
                test1.click();
                Thread.sleep(1000);
            } else {
                while (test1 == null && Runtimes + 2 > 0) {
                    device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 10);
                    Thread.sleep(2000);
                    device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 10);
                    Thread.sleep(1000);
                    test1 = device.wait(Until.findObject(By.res("com.tencent.mobileqq:id/qzone_feed_entry")), 1000);
                    if (test1 == null) {
                        device.pressBack();
                    }
                    Runtimes--;
                    help.Log("Zone: ", test1 != null);
                }
                test1.clickAndWait(Until.newWindow(), 1000);
            }
            Thread.sleep(6000);


            //进入好友动态页，并刷新好友动态,浏览页面
            UiObject2 Reflush = device.wait(Until.findObject(By.res("com.tencent.mobileqq:id/ivTitleName")), 1000);
//            UiObject test2 = new UiObject(new UiSelector().className("android.widget.TextView").text("好友动态"));
            if (Reflush != null) {
                help.Log("dynamic: ", Reflush != null);
                Reflush.click();
                Thread.sleep(500);
                Reflush.clickAndWait(Until.newWindow(), 2000);
                Thread.sleep(1000);
            } else {
                scrollable = new UiScrollable(new UiSelector().className("android.widget.AbsListView"));
                Thread.sleep(1000);
                help.Log("dynamic: ", Reflush != null);
            }
            slide();
            Thread.sleep(5000);
//            device.drag(x / 2, y / 4, x / 2, y / 10 * 9, 3);
//            Thread.sleep(5000);


            //发布说说
            UiObject2 test3 = device.wait(Until.findObject(By.desc("写说说等按钮")), 1000);
            if (test3 != null) {
                test3.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(2000);
//                UiObject type = new UiObject(new UiSelector().className("android.widget.TextView").text("说说"));
//                UiObject type = new UiObject(new UiSelector().className("android.widget.LinearLayout")).getChild(new UiSelector().className("").text("说说"));
//                UiObject2 shuoshuo = device.wait(Until.findObject(By.res("com.tencent.mobileqq:id/name")), 2000);
//                UiObject2 shuoshuo = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("说说")), 2000);
//                if (shuoshuo != null) {
//                    shuoshuo.clickAndWait(Until.newWindow(), 2000);
//                } else {
//
//                }
                device.click(x / 6, y / 50 * 9);
                Thread.sleep(3000);
                device.swipe(x / 2, y / 4 * 3, x / 2, y / 2, 10);
                Thread.sleep(2000);
                UiObject AddPicture = new UiObject(new UiSelector().className("android.widget.ImageView").description("添加图片"));
                UiObject AddText = new UiObject(new UiSelector().className("android.widget.EditText").text("说点什么吧..."));
                if (AddText != null) {
                    AddText.setText("测试说说");
                    Thread.sleep(1000);
                    AddPicture.clickAndWaitForNewWindow();
                } else {
                    help.Log("未成功进入好友动态页");
//                    test3.clickAndWait(Until.newWindow(), 1000);
                }
            } else {
                help.Log("未成功进入好友动态页");
            }


            //挨个选择图片
            SelectPicture();

            //滑动浏览空间
            Reflush.clickAndWait(Until.newWindow(), 1000);
            slide();
            device.pressBack();
            device.pressBack();
            help.Log("Exit");
        }
    }


    //浏览新闻
    private void ReadNews() throws Exception {
        UiScrollable scrollable = null;
        //打开腾讯新闻
        scrollable = new UiScrollable(new UiSelector().resourceId("com.tencent.mobileqq:id/lebasv"));
        scrollable.scrollTextIntoView("腾讯新闻");
        UiObject2 newss = device.wait(Until.findObject(By.text("腾讯新闻")), 1000);
        if (newss != null) {
            help.Log("simchoose: ", newss != null);
            newss.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(2000);
        } else {
            Thread.sleep(2000);
            UiObject2 more = device.wait(Until.findObject(By.res("com.tencent.mobileqq:id/ivTitleBtnRightText")), 1000);
            more.clickAndWait(Until.newWindow(), 2000);
            scrollable = new UiScrollable(new UiSelector().resourceId("com.tencent.mobileqq:id/name"));
            scrollable.scrollTextIntoView("腾讯新闻");
            UiObject2 on = device.wait(Until.findObject(By.text("腾讯新闻")), 1000).getParent().getChildren().get(2);
            on.clickAndWait(Until.newWindow(), 1000);
            device.pressBack();
            scrollable = new UiScrollable(new UiSelector().resourceId("com.tencent.mobileqq:id/lebasv"));
            scrollable.scrollTextIntoView("腾讯新闻");
            UiObject2 newss2 = device.wait(Until.findObject(By.text("腾讯新闻")), 1000);
            newss2.clickAndWait(Until.newWindow(), 1000);
        }

        //滑动浏览新闻首页然后返回动态页
        slide();
        device.click(x / 2, y * 4 / 5);
        Thread.sleep(2000);
        device.swipe(x / 2, y / 5, x / 2, y * 4 / 5, 10);
        Thread.sleep(2000);
        device.swipe(x / 2, y * 4 / 5, x / 2, y / 5, 10);
        Thread.sleep(2000);
        device.swipe(x / 2, y / 5, x / 2, y * 4 / 5, 10);
        Thread.sleep(2000);
        device.swipe(x / 2, y * 4 / 5, x / 2, y / 5, 10);
        Thread.sleep(2000);
        UiObject2 news1 = device.wait(Until.findObject(By.res("com.tencent.mobileqq:id/letsTextView").text("腾讯新闻")), 1000);
        int i = 20;
        while (news1 == null && i-- > 0) {
            device.pressBack();
            Thread.sleep(1000);
            news1 = device.wait(Until.findObject(By.res("com.tencent.mobileqq:id/letsTextView").text("腾讯新闻")), 1000);
        }
    }

    private void Login() throws Exception {
        UiObject2 Login1 = device.wait(Until.findObject(By.res("com.tencent.mobileqq:id/btn_login")), 1000);
        UiObject2 Usename = device.wait(Until.findObject(By.desc("请输入QQ号码或手机或邮箱")), 1000);
        UiObject2 Passwd = device.wait(Until.findObject(By.desc("密码 安全")), 1000);
        UiObject2 Login2 = device.wait(Until.findObject(By.res("com.tencent.mobileqq:id/login")), 1000);
        if (Login1 != null) {
            Login1.clickAndWait(Until.newWindow(), 2000);
            Usename.setText(help.Username());
            Passwd.setText(help.Passwd());
            Login2.clickAndWait(Until.newWindow(), 5000);
        }
    }

    //选择图片并发表说说
    private void SelectPicture() throws Exception {
        UiObject SelectFromPhone = null;
        SelectFromPhone = new UiObject(new UiSelector().resourceId("com.tencent.mobileqq:id/action_sheet_button").text("从手机选择"));
        SelectFromPhone.clickAndWaitForNewWindow();
        Boolean selected = false;
        UiCollection test4 = new UiCollection(new UiSelector().className("android.widget.CheckBox").instance(0));
        if (test4.exists()) {
            help.Log("android.widget.CheckBox", test4.exists());
            test4.click();
            Thread.sleep(2000);
            selected = true;
        }
        test4 = new UiCollection(new UiSelector().className("android.widget.CheckBox").instance(1));
        if (test4.exists()) {
            test4.click();
            Thread.sleep(2000);
            selected = true;
        }
        test4 = new UiCollection(new UiSelector().className("android.widget.CheckBox").instance(2));
        if (test4.exists()) {
            help.Log("ndroid.widget.CheckBox  ", test4.exists());
            test4.click();
            Thread.sleep(2000);
            selected = true;
        }
        Thread.sleep(2000);
        //点击确定
        if (selected) {
            UiObject2 send_Button = device.wait(Until.findObject(By.res("com.tencent.mobileqq:id/send_btn")), 1000);
            if (send_Button != null) {
                help.Log("android.widget.Button sure ", send_Button != null);
                send_Button.clickAndWait(Until.newWindow(), 2000);
                Thread.sleep(2000);
            } else {
                Thread.sleep(2000);
                help.Log("android.widget.Button  not sure ", send_Button != null);
            }
            Thread.sleep(3000);
            //点击发表
            UiObject2 test6 = device.wait(Until.findObject(By.res("com.tencent.mobileqq:id/ivTitleBtnRightText")), 1000);
            if (test6 != null) {
                help.Log("Send: ", test6 != null);
                test6.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(3000);
            } else {
                Thread.sleep(2000);
                help.Log("Send:", test6 != null);
            }
        } else {
            UiObject2 cancle = device.wait(Until.findObject(By.res("com.tencent.mobileqq:id/ivTitleBtnRightText")), 1000);
            cancle.clickAndWait(Until.newWindow(), 1000);
            SelectPicture();
        }
    }

    //流畅性测试
    private void Fluency(String str) throws InterruptedException, UiObjectNotFoundException {
        if (str == "消息") {
            device.click(x / 8, y / 15 * 14);
            Thread.sleep(3000);
            device.swipe(x / 8, y / 4 * 3, x / 5 * 4, y / 4 * 3, 10);
            Thread.sleep(3000);
            device.pressBack();
            Thread.sleep(3000);
            device.swipe(x / 8, y / 4 * 3, x / 5 * 4, y / 4 * 3, 10);
            Thread.sleep(3000);
            device.pressBack();
            Thread.sleep(2000);
        } else if (str == "联系人") {
            device.click(x / 2, y / 15 * 14);
            Thread.sleep(2000);
        } else if (str == "动态") {
            device.click(x / 8 * 7, y / 15 * 14);
        }

    }

    private void DialogShow() throws UiObjectNotFoundException, InterruptedException {
        UiObject simchoose = new UiObject(new UiSelector().resourceId("android:id/button1")).getChild(new UiSelector().index(3));
        if (simchoose.exists()) {
            help.Log("simchoose", simchoose.exists());
            simchoose.clickAndWaitForNewWindow();
            Thread.sleep(1000);
        } else {
            Thread.sleep(1000);
            help.Log("simchoose:", simchoose.exists());
        }
        Thread.sleep(1000);
    }

    //滑动浏览
    public void slide() throws Exception {
        int i;
        for (i = 1; i < 2; i++) {
            //往下滑
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 4);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 6);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 2);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 4);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 5);
            Thread.sleep(5000);
            //往上滑
            device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 5);
            Thread.sleep(5000);

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
            Thread.sleep(2000);
            return true;
        } catch (IOException e) {
            return false;
        }

    }


}