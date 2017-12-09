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
public class Weibo {

    private static final String Weibo = "am start -n com.sina.weibo/.SplashActivity";
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
    public void UserSocietyWeibo() throws Exception {

        //设置监听事件
        device.registerWatcher("daduan", new UiWatcher() {

            @Override
            public boolean checkForCondition() {
//                UiObject2 button =device.wait(Until.findObject(By.clazz("ndroid.widget.TextView").desc("不了，谢谢")),1000);
                UiObject button = new UiObject(new UiSelector().className("android.widget.TextView").text("不了，谢谢"));
                if (button.exists()) {
                    try {
                        button.click();
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {

                    }
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


        //这里是点亮屏幕方法。
        help.OpenScreen();


        //有些apk进去之后会有一个弹出框，因此如果有弹出框的话需要先点掉弹出框
//        UiObject2 simchoose =device.wait(Until.findObject(By.res("android:id/button1")),1000).getChildren().get(3);
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

        device.registerWatcher("permission", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject Ok = new UiObject(new UiSelector().textContains("开启"));
                try {
                    if (Ok.exists()) {
                        Ok.clickAndWaitForNewWindow();
                        return true;
                    }
                } catch (UiObjectNotFoundException e) {
                    return false;
                }
                return false;
            }
        });

        if (device.hasWatcherTriggered("permission")) {
            device.resetWatcherTriggers();
        }


        while (Runtimes-- > 0)

        {
            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入应用", enterApp(Weibo));
            Thread.sleep(1000);
            device.pressBack();
            enterApp(Weibo);
            Thread.sleep(20000);
            UiObject yichang = new UiObject(new UiSelector().resourceId("com.sina.weibo:id/skip").index(3));
            if (yichang.exists()) {
                help.Log("com.sina.weibo:id/skip ", simchoose.exists());
                yichang.click();
                Thread.sleep(5000);
            }

            //点击首页刷新
//            UiObject2 home =device.wait(Until.findObject(By.clazz("android.view.View").desc("微博")),1000);
            UiObject home = new UiObject(new UiSelector().className("android.view.View").description("微博"));
            if (home.exists()) {
                help.Log("home: ", home.exists());
                home.clickAndWaitForNewWindow();
                Thread.sleep(1000);
            } else {
                Thread.sleep(1000);
                help.Log("home: ", home.exists());
            }

            if (home.exists()) {
                help.Log("home: ", home.exists());
                home.clickAndWaitForNewWindow();
                Thread.sleep(1000);
            } else {
                Thread.sleep(1000);
                help.Log(" home: ", home.exists());
            }
            slide();

            //切换到消息
//            UiObject2 news =device.wait(Until.findObject(By.clazz("android.view.View").desc("消息")),1000);
            UiObject news = new UiObject(new UiSelector().className("android.view.View").description("消息"));
            if (news.exists()) {
                help.Log(" new: ", news.exists());
                news.clickAndWaitForNewWindow();
                Thread.sleep(1000);
            } else {
                Thread.sleep(1000);
                help.Log("new: ", news.exists());
            }
            //进入@我的
//            UiObject2 atme =device.wait(Until.findObject(By.clazz("android.widget.View").desc("@我的")),1000);
            UiObject atme = new UiObject(new UiSelector().className("android.widget.TextView").text("@我的"));
            if (atme.exists()) {
                help.Log("atme: ", atme.exists());
                atme.clickAndWaitForNewWindow();
                Thread.sleep(5000);
            } else {
                Thread.sleep(1000);
                help.Log("atme: ", atme.exists());
            }

            slide();
            Thread.sleep(1000);
            device.pressBack();
            Thread.sleep(5000);
            //进入评论
//            UiObject2 comment =device.wait(Until.findObject(By.clazz("android.widget.View").desc("评论")),1000);
            UiObject comment = new UiObject(new UiSelector().className("android.widget.TextView").text("评论"));
            if (comment.exists()) {
                help.Log("comment:", comment.exists());
                comment.clickAndWaitForNewWindow();
                Thread.sleep(5000);
            } else {
                Thread.sleep(1000);
                help.Log("comment: ", comment.exists());
            }

            slide();
            Thread.sleep(1000);
            device.pressBack();
            Thread.sleep(5000);

            //进入赞
//            UiObject2 zan =device.wait(Until.findObject(By.clazz("android.widget.View").desc("赞")),1000);
            UiObject zan = new UiObject(new UiSelector().className("android.widget.TextView").text("赞"));
            if (zan.exists()) {
                help.Log("zan: ", zan.exists());
                zan.clickAndWaitForNewWindow();
                Thread.sleep(5000);
            } else {
                Thread.sleep(1000);
                help.Log("zan: ", zan.exists());
            }

            slide();
            Thread.sleep(1000);
            device.pressBack();
            Thread.sleep(5000);

            //切换到发现
//            UiObject2 find =device.wait(Until.findObject(By.clazz("android.view.View").desc("发现")),1000);
            UiObject find = new UiObject(new UiSelector().className("android.view.View").description("发现"));
            if (find.exists()) {
                help.Log("find: ", find.exists());
                find.clickAndWaitForNewWindow();
                UiObject2 location = device.wait(Until.findObject(By.text("开启")), 1000);
                if (location != null) {
                    location.clickAndWait(Until.newWindow(), 3000);
                }
                Thread.sleep(1000);
            } else {
                Thread.sleep(1000);
                help.Log("find: ", find.exists());
            }
            //浏览热门
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 6);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 6);
            Thread.sleep(3000);
            device.click(x / 2, y / 2);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 6);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 4, 6);
            Thread.sleep(3000);
            device.pressBack();
            Thread.sleep(3000);
            //浏览榜单
            device.swipe(x / 5 * 4, y / 2, x / 5, y / 2, 10);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 6);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 6);
            Thread.sleep(3000);
            device.click(x / 2, y / 2);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 6);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 4, 6);
            device.pressBack();
            Thread.sleep(3000);
            //浏览视频
            device.swipe(x / 5 * 4, y / 2, x / 5, y / 2, 10);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 6);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 6);
            Thread.sleep(3000);
            device.click(x / 2, y / 2);
            Thread.sleep(10000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 6);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 4, 6);
            Thread.sleep(3000);
            device.pressBack();
            Thread.sleep(3000);
            //浏览头条
            device.swipe(x / 5 * 4, y / 2, x / 5, y / 2, 10);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 6);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 6);
            Thread.sleep(3000);
            device.click(x / 2, y / 2);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 6);
            Thread.sleep(3000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 4, 6);
            device.pressBack();
            Thread.sleep(3000);
            device.pressBack();
            Thread.sleep(3000);


//            //点击加号
//            ClickPanel();
//            //点击相册
//            SendPicture();
            //拍照发送图片
            ClickPanel();
            sendnews();
            Thread.sleep(1000);
//            ClickPanel();
//            sendnews();
//            Thread.sleep(1000);
//            ClickPanel();
//            sendnews();
//            Thread.sleep(1000);

            //返回首页
//            UiObject2 home1=device.wait(Until.findObject(By.clazz("android.view.View").desc("微博")),1000);
            UiObject home1 = new UiObject(new UiSelector().className("android.view.View").description("微博"));
            if (home1.exists()) {
                help.Log("home1:shouye  ", home1.exists());
                home1.clickAndWaitForNewWindow();
                Thread.sleep(1000);
            } else {
                Thread.sleep(1000);
                help.Log("home1:shouye  ", home1.exists());
            }

            if (home1.exists()) {
                help.Log("home1:shouye  ", home1.exists());
                home1.clickAndWaitForNewWindow();
                Thread.sleep(1000);
            } else {
                Thread.sleep(1000);
                help.Log("home1:shouye  ", home1.exists());
            }
            slide();

            device.pressBack();
            Thread.sleep(3000);
            device.pressBack();
            Thread.sleep(3000);
            help.Log("Exit");

        }

    }

    private void ClickPanel() throws UiObjectNotFoundException, InterruptedException {
//        UiObject2 plus=device.wait(Until.findObject(By.clazz("android.widget.ImageView").desc("打开发布面板")),1000);
        UiObject plus = new UiObject(new UiSelector().className("android.widget.ImageView").description("打开发布面板"));
        if (plus.exists()) {
            help.Log("plus: ", plus.exists());
            plus.clickAndWaitForNewWindow();
            Thread.sleep(5000);
        } else {
            Thread.sleep(1000);
            help.Log("plus: ", plus.exists());
        }
    }

    private void SendPicture() throws UiObjectNotFoundException, InterruptedException {
        UiObject gallery = new UiObject(new UiSelector().resourceId("com.sina.weibo:id/composer_item_text").text("相册"));
        UiObject picture1 = new UiObject(new UiSelector().className("android.view.View").index(1));
        UiObject picture2 = new UiObject(new UiSelector().className("android.view.View").index(2));
        UiObject next = new UiObject(new UiSelector().resourceId("com.sina.weibo:id/photoalbum_tool_bar_right_btn").description("下一步"));
        UiObject send = new UiObject(new UiSelector().className("android.widget.TextView").text("发送"));
        if (gallery.exists()) {
            help.Log("gallery: ", gallery.exists());
            gallery.clickAndWaitForNewWindow();
            Thread.sleep(15000);
            if (picture1 != null && picture2 != null) {
                picture1.click();
                picture2.click();
                Thread.sleep(1000);
                next.clickAndWaitForNewWindow();
                if (send.exists()) {
                    help.Log("send: ", send.exists());
                    send.clickAndWaitForNewWindow();
                    Thread.sleep(15000);
                } else {
                    Thread.sleep(1000);
                    help.Log("send: ", send.exists());
                }
            }
        } else {
            Thread.sleep(1000);
            help.Log("gallery: ", gallery.exists());
        }
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 6);
        Thread.sleep(1000);
    }

    private void ChangePage() throws InterruptedException {
        device.swipe(x / 10 * 9, y / 2, x / 5, y / 2, 10);
        Thread.sleep(5000);
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 12);
        Thread.sleep(5000);
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 12);
        Thread.sleep(5000);
        device.click(x / 2, y / 2);
        Thread.sleep(5000);
        device.swipe(x / 10 * 9, y / 5 * 2, x / 2, y / 5 * 4, 12);
        Thread.sleep(5000);
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 12);
        Thread.sleep(5000);
        device.pressBack();
        device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 10);
        Thread.sleep(5000);
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
    private boolean enterApp(String command) {
        try {
            device.executeShellCommand(command);
            Thread.sleep(1000);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //滑动
    public void slide() throws Exception {
        int i;
        for (i = 1; i < 2; i++) {
            //往下滑
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 2);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 4);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 6);
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
            device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 6);
            Thread.sleep(5000);

        }
    }

    public void sendnews() throws Exception {
        //点击进入相机
        UiObject2 cram = device.wait(Until.findObject(By.res("com.sina.weibo:id/composer_item_text").text("拍摄")), 1000);
//        UiObject cram = new UiObject(new UiSelector().resourceId("com.sina.weibo:id/photo_album_grideview_camera_image"));
        if (cram != null) {
            cram.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(10000);
            //拍照
//        UiObject2 crambutt = device.wait(Until.findObject(By.res("com.sina.weibo:id/camera_bottom_middle")), 1000);
            UiObject crambutt = new UiObject(new UiSelector().resourceId("com.sina.weibo:id/camera_bottom_middle"));
            if (crambutt != null) {
                crambutt.clickAndWaitForNewWindow();
            }
            Thread.sleep(10000);
            try {
                UiObject2 open = device.wait(Until.findObject(By.text("允许")), 2000);
                if (open != null) {
                    open.clickAndWait(Until.newWindow(), 3000);
                }
            } catch (Exception e) {
                help.Log("不存在弹出框");
            }
            //点击下一步
//        UiObject2 crambutt = device.wait(Until.findObject(By.res("com.sina.weibo:id/camera_bottom_middle").text("下一步")), 1000);
            UiObject next = new UiObject(new UiSelector().className("android.widget.TextView").text("下一步"));
            if (next.exists()) {
                help.Log("next: ", next.exists());
                next.clickAndWaitForNewWindow();
                Thread.sleep(15000);
            } else {
                Thread.sleep(1000);
                System.out.println("next: " + next.exists());
            }
            //点击发送
            //        UiObject2 crambutt = device.wait(Until.findObject(By.res("android.widget.TextView").text("发送")), 1000);
            UiObject send = new UiObject(new UiSelector().className("android.widget.TextView").text("发送"));
            if (send.exists()) {
                help.Log("send: ", send.exists());
                send.clickAndWaitForNewWindow();
                Thread.sleep(15000);
            } else {
                Thread.sleep(1000);
                help.Log("send: ", send.exists());
            }
        } else {
            help.Log("微博发送失败");
        }
    }

}
