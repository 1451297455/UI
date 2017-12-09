package com.spreadtrum.myapplication.uicase;


import android.os.RemoteException;
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
public class Baidu {
    //获取百度的包名
    private static final String Baidu = "am start -n com.baidu.searchbox/.MainActivity";
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
    //手机百度操作
    public void UserBroswerBaidu() throws RemoteException, InterruptedException, UiObjectNotFoundException, NullPointerException, IOException {
        help = UntilHelp.getInstance();
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        UiObject2 Baidutext, Baiduview, BaiduButton;
        UiObject Baidutextview;
        int ii = 0;
        int i;

//升级弹框
        device.registerWatcher("Update", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 updata = device.wait(Until.findObject(By.text("立即升级")), 1000);
                if (updata != null) {
                    device.pressBack();
                    return true;
                }
                return false;
            }
        });

        //点到下载按钮
        device.registerWatcher("cancle", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 cancle = device.wait(Until.findObject(By.text("取消")), 1000);
                if (cancle != null) {
                    cancle.clickAndWait(Until.newWindow(), 2000);
                    return true;
                }
                return false;
            }
        });
        //设置监听事件
        device.registerWatcher("daduan", new UiWatcher() {

            @Override
            public boolean checkForCondition() {
                // TODO Auto-generated method stub
                UiObject2 button = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/close")), 1000);
                if (button != null) {
                    try {
                        button.click();
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return false;
            }

        });


        help.OpenScreen();//点亮屏幕，上拉解锁

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

        UiObject2 simchoose = null;

        try {
            simchoose = device.wait(Until.findObject(By.res("android:id/button1")), 1000).getChildren().get(3);
            if (simchoose != null) {
                help.Log("simchoose: ", simchoose != null);
                simchoose.clickAndWait(Until.newWindow(), 2000);
                Thread.sleep(3000);

            }
        } catch (Exception e) {
            Thread.sleep(3000);
            help.Log("simchoose: ", simchoose != null);
        }
        Thread.sleep(3000);

        while (Runtimes-- > 0) {
            help.Log("Baidu Test times: " + ii++);
            //按home键
            help.Log("pressHome");
            device.pressHome();
            Thread.sleep(3000);
            device.pressHome();

            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入应用", enterApp(Baidu));
            Thread.sleep(3000);
            device.pressHome();
            help.Log("Enter the application");
            enterApp(Baidu);
            Thread.sleep(3000);
            //判断应用是否是安装后第一次打开
            UiObject2 login = null;
            try {
                login = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/home_login_entrance").text("登录")), 5000);
            } catch (Exception e) {
                login = null;
            }
            i = 10;
            while (i-- > 0 && login == null) {
                help.Log("Slide to the left");
                device.drag(x * 4 / 5, y / 2, x / 5, y / 2, 20);
                Thread.sleep(3000);
                login = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/home_login_entrance").text("登录")), 5000);
            }

            Thread.sleep(10000);
            UiObject2 close = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/close")), 1000);
            if (close != null) {
                help.Log("com.baidu.searchbox:id/close ", close != null);
                close.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(3000);
            }
            //判断百度搜索框是否存在
            i = 10;
            Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
            if (Baiduview == null && i-- > 0) {
                while (Baiduview == null) {
                    help.Log("com.baidu.searchbox:id/baidu_searchbox ", Baiduview != null);
                    device.pressBack();
                    device.pressBack();
                    device.pressBack();
                    assertTrue(help.GetCurrTime() + "未成功进入应用", enterApp(Baidu));
                    Thread.sleep(3000);
                    device.pressHome();
                    enterApp(Baidu);
                    Thread.sleep(3000);
                    Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
                    if (close != null) {
                        help.Log("com.baidu.searchbox:id/close ", close != null);
                        close.clickAndWait(Until.newWindow(), 1000);
                        Thread.sleep(3000);
                    }
                }
            } else {
                help.Log("input \"Youku\" ");
                Baiduview.clickAndWait(Until.newWindow(), 3000);
                Baidutextview = new UiObject(new UiSelector().resourceId("com.baidu.searchbox:id/SearchTextInput"));
//                Baidutextview = device.wait(Until.findObject(By.clazz("android.widget.EditText").res("com.baidu.searchbox:id/SearchTextInput")), 5000);
                Baidutextview.setText("Youku");
                Thread.sleep(10000);
                Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/float_search_or_cancel")), 1000);
                if (Baiduview != null) {
                    help.Log("com.baidu.searchbox:id/float_search_or_cancel ", Baiduview != null);
                    Baiduview.clickAndWait(Until.newWindow(), 1000);
                } else {
                    device.click(x * 85 / 100, y / 10);
                    UiObject2 Download = device.wait(Until.findObject(By.text("取消")), 1000);
                    if (Download != null)
                        Download.clickAndWait(Until.newWindow(), 1000);
                }
                Thread.sleep(3000);
                device.click(x / 2, y * 4 / 10);
                Thread.sleep(3000);
                help.Log("Slide to the up");
                device.drag(x / 2, y / 5 * 4, x / 2, y / 5, 10);
                device.swipe(x / 2, y / 2, x / 2, y / 2, 400);  //通过原地滑动 实现长按点击
                Thread.sleep(3000);
                //判断有没有长按弹出的选择框
                Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/item_title")), 1000);
                if (Baiduview != null) {
                    help.Log(" com.baidu.searchbox:id/item_title ", Baiduview != null);
                    device.click(x * 9 / 10, y / 2);
                }
                Thread.sleep(3000);
                device.pressBack();
                Thread.sleep(3000);
                device.drag(x / 2, y * 3 / 5, x / 2, y / 5, 10);
                device.swipe(x / 2, y / 2, x / 2, y / 2, 400);  //通过原地滑动 实现长按点击
                Thread.sleep(3000);
                Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/item_title")), 1000);
                if (Baiduview != null) {
                    help.Log("com.baidu.searchbox:id/item_title ", Baiduview != null);
                    device.click(x * 9 / 10, y / 2);
                }
                Thread.sleep(3000);
                device.click(x / 2, y * 6 / 10);
                Thread.sleep(3000);
                device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                Thread.sleep(3000);
                Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/item_title")), 1000);
                if (Baiduview != null) {
                    help.Log(" com.baidu.searchbox:id/item_title ", Baiduview != null);
                    device.click(x * 9 / 10, y / 2);
                }
                device.click(x / 3, y / 2);
                Thread.sleep(10000);
                device.click(x / 2, y * 2 / 5);
                Thread.sleep(10000);
                Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/positive_button")), 1000);
                if (Baiduview != null) {
                    help.Log("com.baidu.searchbox:id/positive_button ", Baiduview != null);
                    Baiduview.clickAndWait(Until.newWindow(), 1000);
                }
                Thread.sleep(3000);
                help.Log("pressBack");
                device.pressBack();
                Thread.sleep(3000);
                help.Log("Slide to the up");
                device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                Thread.sleep(3000);
                device.click(x / 3, y / 2);
                Thread.sleep(10000);
                device.click(x / 2, y * 2 / 5);
                Thread.sleep(10000);
                Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/positive_button")), 1000);
                if (Baiduview != null) {
                    help.Log("com.baidu.searchbox:id/positive_button ", Baiduview != null);
                    Baiduview.clickAndWait(Until.newWindow(), 1000);
                }
                Thread.sleep(10000);
                Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/browser_home")), 1000);
                if (Baiduview != null) {
                    help.Log(" com.baidu.searchbox:id/browser_home ", Baiduview != null);
                    Baiduview.clickAndWait(Until.newWindow(), 1000);
                }
                i = 10;
                Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
                while (Baiduview == null && i-- > 0) {
                    help.Log("com.baidu.searchbox:id/baidu_searchbox ", Baiduview != null);
                    help.Log("pressback");
                    device.pressBack();
                    device.swipe(x/2,y/5,x/2,y/5*4,20);
                    Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
                }

                if (close != null) {
                    help.Log("com.baidu.searchbox:id/close ", close != null);
                    close.clickAndWait(Until.newWindow(), 1000);
                    Thread.sleep(3000);
                }

                Baidutext = device.wait(Until.findObject(By.text("新闻")), 1000);
                if (Baidutext != null) {
                    help.Log("textMatches(\"新闻\") click ");
                    Baidutext.clickAndWait(Until.newWindow(), 1000);
                    Thread.sleep(10000);
                    device.click(x / 2, y / 2);
                    Thread.sleep(10000);
                    device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                    Thread.sleep(3000);
                    device.pressBack();
                    Thread.sleep(10000);
                    device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                    Thread.sleep(3000);
                    device.click(x / 2, y / 2);
                    Thread.sleep(10000);
                    help.Log("browsing textMatches(\"新闻\") ");
                    device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                    Thread.sleep(3000);
                    device.pressBack();
                    Thread.sleep(3000);
                    device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                    Thread.sleep(3000);
                    device.click(x / 2, y / 2);
                    Thread.sleep(10000);
                    device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                    Thread.sleep(3000);
                    device.pressBack();
                    Thread.sleep(3000);
                    device.pressBack();
                    Thread.sleep(3000);
                    UiObject2 home = device.findObject(By.res("com.baidu.searchbox:id/home_tab_item_textview").text("百度"));
                    home.clickAndWait(Until.newWindow(), 2000);
                    i = 10;
                    Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
                    while (Baiduview == null && i-- > 0) {
                        help.Log("com.baidu.searchbox:id/baidu_searchbox ", Baiduview != null);
                        device.pressBack();
                        Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
                    }
                }

                if (close != null) {
                    help.Log("com.baidu.searchbox:id/close ", close != null);
                    close.clickAndWait(Until.newWindow(), 1000);
                    Thread.sleep(3000);
                }

                Baidutext = device.wait(Until.findObject(By.text("小说")), 1000);
                if (Baidutext != null) {
                    help.Log("textMatches(\"小说\") click ");
                    Baidutext.clickAndWait(Until.newWindow(), 1000);
                    Thread.sleep(5000);
                    Baidutext = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/novel_introduce_cancel")), 1000);
                    if (Baidutext != null) {
                        help.Log("com.baidu.searchbox:id/novel_introduce_cancel click ");
                        Baidutext.clickAndWait(Until.newWindow(), 1000);
                    }
                    Thread.sleep(3000);
                    device.drag(x * 4 / 5, y / 2, x / 5, y / 2, 20);
                    Thread.sleep(3000);
                    device.click(x / 2, y * 2 / 3);
                    Thread.sleep(10000);
                    device.click(x / 5, y / 100 * 38);
                    Thread.sleep(3000);
                    device.click(x / 2, y / 2);
                    Thread.sleep(3000);
                    help.Log("browsing textMatches(\"小说\")  ");
                    device.drag(x * 4 / 5, y / 2, x / 5, y / 2, 20);
                    Thread.sleep(3000);
                    device.drag(x * 4 / 5, y / 2, x / 5, y / 2, 20);
                    Thread.sleep(3000);
                    device.drag(x * 4 / 5, y / 2, x / 5, y / 2, 20);
                    Thread.sleep(3000);
                    device.pressBack();
                    Thread.sleep(3000);
                    BaiduButton = device.wait(Until.findObject(By.text("com.baidu.searchbox:id/positive_button")), 1000);
                    if (BaiduButton != null) {
                        help.Log(" com.baidu.searchbox:id/positive_button click ");
                        BaiduButton.clickAndWait(Until.newWindow(), 1000);
                    }
                    Thread.sleep(10000);

                    i = 10;
                    Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
                    while (Baiduview == null && i-- > 0) {
                        device.pressBack();
                        Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
                        help.Log("com.baidu.searchbox:id/baidu_searchbox ", Baiduview != null);
                    }
                }

                Baidutext = device.wait(Until.findObject(By.text("视频")), 1000);
                if (Baidutext != null) {
                    help.Log("textMatches(\"视频\") click ");
                    Baidutext.clickAndWait(Until.newWindow(), 1000);
                    Thread.sleep(10000);
                    device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                    Thread.sleep(5000);
                    help.Log("browing textMatches(\"视频\")");
                    device.click(x / 2, y / 2);
                    Thread.sleep(300000);
                    BaiduButton = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/positive_button")), 1000);
                    if (BaiduButton != null) {
                        help.Log("com.baidu.searchbox:id/positive_button ");
                        BaiduButton.clickAndWait(Until.newWindow(), 1000);
                        Thread.sleep(3000);
                    }
                    device.click(x / 3 * 2, y * 46 / 100);
                    Thread.sleep(10000);
                    device.click(x / 2, y * 2 / 5);
                    Thread.sleep(10000);
                    BaiduButton = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/positive_button")), 1000);
                    if (BaiduButton != null) {
                        help.Log("com.baidu.searchbox:id/positive_button ", BaiduButton != null);
                        BaiduButton.clickAndWait(Until.newWindow(), 1000);
                    }
                    Thread.sleep(10000);
                    Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/browser_home")), 1000);
                    if (Baiduview != null) {
                        help.Log("com.baidu.searchbox:id/browser_home ", Baiduview != null);
                        Baiduview.clickAndWait(Until.newWindow(), 1000);
                    }
                    i = 10;
                    Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
                    while (Baiduview == null && i-- > 0) {
                        device.pressBack();
                        help.Log("com.baidu.searchbox:id/baidu_searchbox ", Baiduview != null);
                        Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
                    }
                }

                Baidutext = device.wait(Until.findObject(By.text("网址")), 1000);
                if (Baidutext != null) {
                    help.Log("textMatches(\"网址\") click");
                    Baidutext.clickAndWait(Until.newWindow(), 1000);
                    Thread.sleep(10000);
                    Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/w2n_tip_view")), 1000);
                    if (Baiduview != null) {
                        help.Log("com.baidu.searchbox:id/w2n_tip_view ");
                        Baiduview.clickAndWait(Until.newWindow(), 1000);
                        Thread.sleep(10000);
                    }
                    device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                    Thread.sleep(3000);
                    device.click(x / 2, y / 2);
                    Thread.sleep(10000);
                    device.pressBack();
                    Thread.sleep(3000);
                    help.Log("browing textMatches(\"网址\") ");
                    device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                    Thread.sleep(3000);
                    device.click(x / 2, y / 2);
                    Thread.sleep(10000);
                    Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/browser_home")), 1000);
                    if (Baiduview != null) {
                        help.Log("com.baidu.searchbox:id/browser_home ", Baiduview != null);
                        Baiduview.clickAndWait(Until.newWindow(), 1000);
                    }
                    i = 10;
                    Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
                    while (Baiduview == null && i-- > 0) {
                        device.pressBack();
                        help.Log("com.baidu.searchbox:id/baidu_searchbox ", Baiduview != null);
                        Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
                    }
                }

                if (close != null) {
                    close.clickAndWait(Until.newWindow(), 1000);
                    Thread.sleep(3000);
                }
                Thread.sleep(10000);
                Baidutext = device.wait(Until.findObject(By.text("贴吧")), 1000);
                if (Baidutext != null) {
                    help.Log("textMatches(\"贴吧\") click");
                    Baidutext.clickAndWait(Until.newWindow(), 1000);
                    Thread.sleep(10000);
                    BaiduButton = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/positive_button")), 1000);
                    if (BaiduButton != null) {
                        help.Log("com.baidu.searchbox:id/positive_button ", BaiduButton != null);
                        BaiduButton.click();
                        Thread.sleep(3000);
                    }
                    device.click(x / 2, y / 100 * 77);
                    Thread.sleep(10000);
                    BaiduButton = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/text1")), 1000);
                    if (BaiduButton != null) {
                        help.Log("com.baidu.searchbox:id/text1 ", BaiduButton != null);
                        BaiduButton.clickAndWait(Until.newWindow(), 1000);
                        Thread.sleep(3000);
                    }
                    device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                    Thread.sleep(3000);
                    device.click(x / 2, y / 2);
                    Thread.sleep(10000);
                    device.pressBack();
                    device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                    Thread.sleep(3000);
                    device.click(x / 2, y / 2);
                    Thread.sleep(10000);
                    help.Log("browing textMatches(\"贴吧\") ");
                    device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                    Thread.sleep(3000);
                    device.click(x / 2, y / 2);
                    Thread.sleep(10000);
                    device.pressBack();
                    device.drag(x / 2, y * 4 / 5, x / 2, y / 5, 10);
                    Thread.sleep(3000);
                    device.click(x / 2, y / 2);
                    Thread.sleep(10000);
                    Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/browser_home")), 1000);
                    if (Baiduview != null) {
                        help.Log("com.baidu.searchbox:id/browser_home ", Baiduview != null);
                        Baiduview.clickAndWait(Until.newWindow(), 1000);
                    }
                    i = 10;
                    Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
                    while (Baiduview == null && i-- > 0) {
                        device.pressBack();
                        help.Log("com.baidu.searchbox:id/baidu_searchbox ", Baiduview != null);
                        Baiduview = device.wait(Until.findObject(By.res("com.baidu.searchbox:id/baidu_searchbox")), 1000);
                    }
                }
                device.pressBack();
                device.pressBack();
            }
            help.Log(" Exit");
            device.pressBack();
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

    private boolean enterApp(String command) {
        try {
            device.executeShellCommand(command);
            Thread.sleep(1000);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
