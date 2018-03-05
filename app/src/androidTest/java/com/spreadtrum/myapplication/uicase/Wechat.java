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
public class Wechat {

    private static final String Wechat = "am start -n com.tencent.mm/.ui.LauncherUI";
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
    public void UserSocietyWechat() throws Exception {

        device.registerWatcher("permession", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 perssion = device.wait(Until.findObject(By.text("允许")), 1000);
                if (perssion != null) {
                    perssion.clickAndWait(Until.newWindow(), 3000);
                    return true;
                }
                return false;

            }
        });

        device.registerWatcher("daduan", new UiWatcher() {

            @Override
            public boolean checkForCondition() {
                // TODO Auto-generated method stub
//                UiObject text = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/b1l").text("更新"));
                UiObject2 text = device.wait(Until.findObject(By.res("com.tencent.mm:id/b1l").text("更新")), 1000);
                UiObject2 button = device.wait(Until.findObject(By.res("com.tencent.mm:id/b1t").text("　取消　")), 1000);
                if (text != null) {
                    try {
                        button.click();
                        Thread.sleep(2000);
                        button = device.wait(Until.findObject(By.res("com.tencent.mm:id/b1u").text("Yes")), 1000);
                        if (button != null) {
                            button.click();
                        }
                        Thread.sleep(2000);
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                return false;
            }
        });


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

        //这里是点亮屏幕方法。
        help.OpenScreen();
//        Login();

        //有些apk进去之后会有一个弹出框，因此如果有弹出框的话需要先点掉弹出框
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

        while (Runtimes-- > 0) {
            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入应用", enterApp(Wechat));
            Thread.sleep(1000);
            device.pressBack();

            enterApp(Wechat);

            Thread.sleep(2000);

            //切换到微信界面
            UiObject2 wechat = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("微信")), 1000);
            if (wechat != null) {
                help.Log("wechat:", wechat != null);
                wechat.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(2000);
            } else {
                Thread.sleep(1000);
                help.Log("wechat: ", wechat != null);
            }

            //查找微信团队
            UiObject object = null;
            object = new UiObject(new UiSelector().text("微信团队"));
            int i = 5;
            while (object == null && i > 0) {
                device.swipe(x / 2, y / 4 * 3, x / 2, y / 4, 20);
                object = new UiObject(new UiSelector().text("微信团队"));
                i--;
            }
            object = null;
//            object2.click();
//            UiScrollable keypad0 = new UiScrollable(new UiSelector().resourceId("android.widget.ListView"));
//            UiObject WeChatTeam = keypad0.getChildByInstance(new UiSelector().resourceId("com.tencent.mm:id/afa").text("微信团队"), 1);
////            UiSelector selec = new UiSelector().className("android.view.View").text("微信团队");
////            keypad0.scrollIntoView(selec);
//            help.Log(" search \"Wechat tuandui\"");
////            keypad0 = new UiScrollable(new UiSelector().className("android.view.View").text("微信团队"));
            if (object != null) {
                Thread.sleep(1000);
                help.Log("\"Wechat tuandui\"  ", object != null);
                Thread.sleep(1000);
                int b = object.getVisibleBounds().centerY();
                device.click(x / 2, b);
                Thread.sleep(2000);
                UiObject keypad = new UiObject(new UiSelector().className("android.widget.ImageView").description("消息"));
                if (keypad != null) {
                    help.Log("\"消息\"  ", keypad != null);
                    keypad.click();
                }
                Thread.sleep(1000);

                int Runtime1 = 1;
                while (Runtime1-- > 0) {
                    help.Log("Runtime1: " + Runtime1);
                    //按住说话发送语音
                    UiObject2 keypad1 = device.wait(Until.findObject(By.clazz("android.widget.Button").desc("按住说话")), 1000);
                    UiObject2 keypad2 = device.wait(Until.findObject(By.clazz("android.widget.ImageButton").desc("切换到按住说话")), 1000);
                    UiObject2 keypad3 = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("发现")), 1000);
                    if (keypad1 != null) {
                        Thread.sleep(1000);
                        help.Log("keypad1:按住说话  ", keypad1 != null);
                        int kx = keypad1.getVisibleCenter().x;
                        int ky = keypad1.getVisibleCenter().y;
                        device.swipe(kx, ky, kx + 1, ky + 1, 500);
                        Thread.sleep(1000);
                    } else {
                        Thread.sleep(1000);
                        help.Log("keypad2:切换到按住说话  ", keypad2 != null);
                        keypad2.click();
                        Thread.sleep(3000);
                        if (keypad1 != null) {
                            help.Log("keypad3: find ", keypad3 != null);
                            int kx1 = keypad3.getVisibleCenter().x;
                            int ky1 = keypad3.getVisibleCenter().y;
                            device.swipe(kx1, ky1, kx1 + 1, ky1 + 1, 500);
                            Thread.sleep(1000);
                        } else {
                            Thread.sleep(1000);
                            help.Log("keypad3: find  ", keypad3 != null);
                        }

                    }
                    //点击播放语音
                    UiObject2 keypad4 = device.wait(Until.findObject(By.res("com.tencent.mm:id/a7k")), 1000);
                    if (keypad4 != null) {
                        help.Log("keypad4:com.tencent.mm:id/a7k ", keypad4 != null);
                        keypad4.click();
                        Thread.sleep(3000);
                    } else {
                        help.Log("keypad4:keypad4:com.tencent.mm:id/a7k  ", keypad4 != null);
                    }
                }
                device.pressBack();
            } else {
                help.Log("\"Wechat tuandui\"  ", object != null);
                Thread.sleep(1000);
            }

//
//            //查找腾讯新闻
//            //scrollClickObject("android.view.View","腾讯新闻");
//            UiScrollable news0 = new UiScrollable(new UiSelector().className("android.view.View"));
//            selec = new UiSelector().className("android.view.View").text("腾讯新闻");
//            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 10);
//            news0.scrollIntoView(selec);
//            news0 = new UiScrollable(new UiSelector().className("android.view.View").text("腾讯新闻"));
//            if (news0.exists()) {
//                help.Log("tencent news ", news0.exists());
//                Thread.sleep(1000);
//                news0.clickAndWaitForNewWindow();
//
//                //打开一个新闻
//                device.click(x / 2, y / 3 * 2);
//                Thread.sleep(10000);
//                slide();
//                device.pressBack();
//                Thread.sleep(2000);
//                device.pressBack();
//            } else {
//                help.Log("tencent news ", news0.exists());
//                Thread.sleep(1000);
//            }
//
//            device.pressBack();
//            Thread.sleep(2000);
//            device.pressBack();
//
//            enterApp(Wechat);
            //切换到通讯录
            UiObject2 contact = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("通讯录")), 1000);
            if (contact != null) {
                help.Log("contact:", contact != null);
                contact.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(2000);
            } else {
                Thread.sleep(1000);
                help.Log("contact:", contact != null);
            }
            //查找联系人
            device.swipe(x / 2, y / 32 * 25, x / 2, y / 10, 20);
            help.Log("Slide to the up");
            Thread.sleep(5000);
            scrollClickObject("群聊");
            help.Log("search 群聊");
            device.pressBack();
            Thread.sleep(1000);
            device.swipe(x / 2, y / 9, x / 2, y / 32 * 25, 6);
            help.Log("Slide to the up");
            Thread.sleep(5000);
            scrollClickObject("公众号");
            help.Log("search 公众号");
            device.pressBack();
            Thread.sleep(1000);
            device.swipe(x / 2, y / 9, x / 2, y / 32 * 25, 6);
            help.Log("Slide to the up");
            Thread.sleep(5000);
            scrollClickObject("标签");
            help.Log("search 标签");
            device.pressBack();
            Thread.sleep(1000);
            device.swipe(x / 2, y / 9, x / 2, y / 32 * 25, 6);
            Thread.sleep(5000);

            //切换到发现
            UiObject2 find = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("发现")), 1000);
//            UiObject find = new UiObject(new UiSelector().className("android.widget.TextView").text("发现"));
            if (find != null) {
                help.Log("find:", find != null);
                find.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(2000);
            } else {
                help.Log("find: ", find != null);
                Thread.sleep(1000);
            }
            //点击游戏
            UiObject2 game = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("游戏")), 1000);
//            UiObject game = new UiObject(new UiSelector().className("android.widget.TextView").text("游戏"));
            if (game != null) {
                help.Log("game:", game != null);
                game.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(2000);
                slide();
                Thread.sleep(2000);
                device.pressBack();
                Thread.sleep(2000);
            } else {
                help.Log("game:", game != null);
                Thread.sleep(1000);
            }

            //点击购物
//            UiObject2 shop = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("购物")), 1000);
            UiObject shop = new UiObject(new UiSelector().className("android.widget.TextView").text("购物"));

            if (shop != null) {
                help.Log("shop:", shop != null);
                shop.clickAndWaitForNewWindow();
//                shop.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(2000);
                slide();
                Thread.sleep(2000);
                device.pressBack();
                UiObject2 cancle = device.wait(Until.findObject(By.text("关闭")), 1000);
                if (cancle != null) {
                    cancle.clickAndWait(Until.newWindow(), 1000);
                }
                Thread.sleep(2000);
            } else {
                help.Log("shop:", shop != null);
                Thread.sleep(1000);
            }
            //点击扫一扫
            UiObject2 saoyisao = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("扫一扫")), 1000);
//            UiObject saoyisao = new UiObject(new UiSelector().className("android.widget.TextView").text("扫一扫"));
            if (saoyisao != null) {
                help.Log("扫一扫:", saoyisao != null);
                saoyisao.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(3000);
                UiObject2 yes = device.findObject(By.text("允许"));
                if (yes != null) {
                    yes.clickAndWait(Until.newWindow(), 1000);
                }
                Thread.sleep(1000);
                device.pressBack();
                Thread.sleep(2000);
                UiObject2 ok = device.wait(Until.findObject(By.res("com.tencent.mm:id/abg").text("确定")), 1000);
                if (ok != null) {
                    ok.clickAndWait(Until.newWindow(), 1000);
                }
            } else {
                help.Log("扫一扫:", saoyisao != null);
                Thread.sleep(1000);
            }

            //点击朋友圈
            UiObject2 zone = device.wait(Until.findObject(By.text("朋友圈")), 1000);
//            UiObject zone = new UiObject(new UiSelector().className("android.widget.TextView").text("朋友圈"));
            if (zone != null) {
                help.Log("zone:", zone != null);
                zone.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(5000);
                slide();
                Thread.sleep(5000);
//                slide();
            } else {
                help.Log("zone:", zone != null);
                Thread.sleep(1000);
            }

            int Runtime2 = 5;
            while (Runtime2-- > 0) {
                help.Log("Runtime2: " + Runtime2);
                //点击相机图标分享
                UiObject share = new UiObject(new UiSelector().className("android.widget.RelativeLayout").description("更多功能按钮"));
//                UiObject2 share = device.wait(Until.findObject(By.clazz("android.widget.RelativeLayout").desc("更多功能按钮")), 1000);
                UiObject video = new UiObject(new UiSelector().className("android.widget.TextView").text("拍摄"));
//                UiObject2 video = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("拍摄")), 1000);
                UiObject video1 = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/bsa"));
//                UiObject2 video1 = device.wait(Until.findObject(By.res("com.tencent.mm:id/btv")), 1000);
                UiObject ok = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/ok"));
//                UiObject2 ok = device.wait(Until.findObject(By.res("com.tencent.mm:id/p0")), 1000);
                UiObject send = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/gd").text("发送"));
//                UiObject2 send = device.wait(Until.findObject(By.res("com.tencent.mm:id/gd").text("发送")), 1000);
                UiObject text = new UiObject(new UiSelector().resourceId("com.tencent.mm:id/cn4").text("这一刻的想法..."));
//                UiObject2 text = device.wait(Until.findObject(By.res("com.tencent.mm:id/cpe").text("这一刻的想法...")), 1000);
                if (share != null) {
                    help.Log("photo share:", share != null);
                    share.clickAndWaitForNewWindow();
//                    share.clickAndWait(Until.newWindow(), 1000);
                    Thread.sleep(8000);

                    //发照片
                    if (video != null) {
                        help.Log("video:", video != null);
//                        video.clickAndWait(Until.newWindow(), 1000);
                        video.clickAndWaitForNewWindow();
                        if (video1 != null) {
                            video1.click();
                            Thread.sleep(3000);
                            help.Log("video1:", video1 != null);
                            if (ok != null) {
//                                ok.clickAndWait(Until.newWindow(), 1000);
                                ok.clickAndWaitForNewWindow();
                                Thread.sleep(2000);
                                help.Log("ok: ", ok != null);
                                if (send != null) {
                                    text.setText("test");
                                    Thread.sleep(2000);
//                                    send.clickAndWait(Until.newWindow(), 1000);
                                    send.clickAndWaitForNewWindow();
                                    Thread.sleep(2000);
                                    device.pressBack();
                                    break;
                                }
                            }

                        }
                        //发短视频
//                        if (video.exists()) {
//                            help.Log("video2:", video.exists());
//                            int kx = video.getBounds().centerX();
//                            int ky = video.getBounds().centerY();
//                            device.swipe(kx, ky, kx + 1, ky + 1, 1000);//长按拍小视频
//                            Thread.sleep(5000);
//                            if (send.exists()) {
//                                help.Log("send:", send.exists());
//                                send.clickAndWaitForNewWindow();//点击发送
//                                Thread.sleep(5000);
//                                slide();
//                                Thread.sleep(5000);
//                            } else {
//                                help.Log("send:", send.exists());
//                                Thread.sleep(1000);
//                            }
//                        }
                    } else {
                        help.Log("video:", video != null);
                        Thread.sleep(1000);
                    }

                } else {
                    help.Log("share:", share != null);
                    Thread.sleep(1000);
                }
            }


            //退出微信
            device.pressBack();
            device.pressBack();
            device.pressBack();
            help.Log("Exit");
        }
    }

    private void Login() throws Exception {
        UiObject2 Login = device.wait(Until.findObject(By.res("com.tencent.mm:id/bh6")), 1000);
        UiObject2 Usename = device.wait(Until.findObject(By.text("WeChat ID/Email/QQ ID")), 1000);
        UiObject2 Passwd = device.wait(Until.findObject(By.res("com.tencent.mm:id/er")), 1000);
        UiObject2 Login2 = device.wait(Until.findObject(By.res("com.tencent.mm:id/zx")), 1000);
        UiObject2 QQLogin = device.wait(Until.findObject(By.text("Log in with another type of ID")), 1000);
        if (Login != null) {
            Login.clickAndWait(Until.newWindow(), 1000);
            QQLogin.clickAndWait(Until.newWindow(), 1000);
            Usename.setText(help.Username());
            Passwd.setText(help.Passwd());
            Login2.clickAndWait(Until.newWindow(), 5000);
        }
    }


    //进入app
    private boolean enterApp(String command) throws InterruptedException {
        try {
            device.executeShellCommand(command);
            Thread.sleep(1000);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    //查找内容
    public void scrollClickObject(String targetName) throws Exception {


        UiScrollable collectionObject = new UiScrollable(new UiSelector().resourceId("com.tencent.mm:id/hr"));
        collectionObject.scrollTextIntoView(targetName);
        UiObject2 target = device.wait(Until.findObject(By.text(targetName)), 1000);
        if (target != null) {
            target.clickAndWait(Until.newWindow(), 1000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 20);
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 4, 20);
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 4, 20);
            Thread.sleep(1000);
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

    //滑动浏览
    public void slide() throws Exception {
        int i;
        for (i = 1; i < 2; i++) {
            //往下滑
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5 * 2, 5);
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
            device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 5);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 2, x / 2, y / 5 * 4, 5);
            Thread.sleep(5000);

        }

    }
}
