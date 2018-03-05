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
 * Created by Jinchao_1.Zhang on 2017/7/19.
 */

@RunWith(AndroidJUnit4.class)
public class BaiDuMap {
    public static String BaiduMap = "am start -n  com.baidu.BaiduMap/com.baidu.baidumaps.WelcomeScreen";
    int x, y, i = 20, Runtimes = 1;
    UntilHelp help;
    UiDevice device;


    @Before
    public void init() throws Exception {
        help = UntilHelp.getInstance();
        x = help.GetWidth();
        y = help.GetHight();
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
//        Runtimes = help.Runtimes();
        help.OpenScreen();
    }


    @Test
    public void BaiDuMapTest() throws Exception {
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

        device.registerWatcher("Next", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 next = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/cc")), 1000);
                if (next != null) {
                    next.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        device.registerWatcher("cancle", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 next = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/cm")), 1000);
                if (next != null) {
                    next.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });

        device.registerWatcher("Pass", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 pass = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/x4")), 1000);
                if (pass != null) {
                    pass.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });

        device.registerWatcher("database", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 sdcard = device.wait(Until.findObject(By.text("选择存储卡(重启软件后生效)")), 1000);
                if (sdcard != null) {
                    UiObject2 inside = device.wait(Until.findObject(By.text("内置存储卡")), 1000).getParent().getChildren().get(3);
                    inside.clickAndWait(Until.newWindow(), 1000);
                    UiObject2 ok = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/dj").text("确认")), 1000);
                    ok.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("database")) {
            device.resetWatcherTriggers();
        }


        while (Runtimes-- > 0) {
            assertTrue("", enterApp(BaiduMap));
            Thread.sleep(5000);
            UiObject2 local = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/a7t").desc("我的位置")), 1000);
            while (local == null && i-- > 0) {
                Thread.sleep(2000);
                local = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/a7t").desc("我的位置")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("network enable", i > 0);
            local = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/a7t").desc("我的位置")), 1000);
            local.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(10000);
            UiObject2 rote = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/sb").desc("搜索路线")), 1000);
            rote.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(3000);
            UiObject2 start1 = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/b21")), 1000);
            if (start1.getText().toString().equals("输入起点")) {
                start1.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(2000);
                UiObject2 endline = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/of")), 1000);
                endline.setText("上海");
                UiObject2 list = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/ft")), 1000);
                i = 20;
                while (list == null && i-- > 0) {
                    Thread.sleep(3000);
                    help.Log(i + "   1");
                    list = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/ft")), 1000);
                }
                help.exitiinexception(i);
                assertTrue("不存在", i > 0);
                list = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/ft")), 1000);
                UiObject2 item = list.getChildren().get(3);
                item.clickAndWait(Until.newWindow(), 1000);
            }
            UiObject2 end = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/b25").text("输入终点")), 1000);
            end.clickAndWait(Until.newWindow(), 2000);
            UiObject2 endline = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/of")), 1000);
            endline.setText("深圳");
            UiObject2 list1 = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/ft")), 1000);
            i = 20;
            while (list1 == null && i-- > 0) {
                Thread.sleep(2000);
                list1 = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/ft")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("目的地不存在", i > 0);
            list1 = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/ft")), 1000);
            UiObject2 item = list1.getChildren().get(1);
            item.clickAndWait(Until.newWindow(), 1000);
            UiObject2 start = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/ayk")), 1000);
            i = 20;
            while (start == null && i-- > 0) {
                Thread.sleep(1000);
                start = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/ayk")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("路线规划失败", i > 0);
            start = device.wait(Until.findObject(By.res("com.baidu.BaiduMap:id/ayk")), 1000);
            start.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(10000);
            device.pressBack();
            device.pressBack();
            Thread.sleep(2000);
            device.pressBack();
            Thread.sleep(2000);
            device.pressBack();
            device.pressBack();
            device.pressBack();
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



