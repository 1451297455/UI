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
public class gaodemap {
    public static String GaodeMap = "am start -n  com.autonavi.minimap/com.autonavi.map.activity.SplashActivity";
    int x, y, i = 20, Runtimes = 1;
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
    public void Map() throws Exception {

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

        if (device.hasWatcherTriggered("Permession")) {
            device.resetWatcherTriggers();
        }

        device.registerWatcher("True", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 yes = device.wait(Until.findObject(By.text("确定")), 1000);
                if (yes != null) {
                    yes.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("True")) {
            device.resetWatcherTriggers();
        }


        device.registerWatcher("Pass", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 skip = device.wait(Until.findObject(By.res("com.autonavi.minimap:id/skip").text("跳过")), 1000);
                if (skip != null) {
                    skip.clickAndWait(Until.newWindow(), 1000);
                    return true;
                }
                return false;
            }
        });
        if (device.hasWatcherTriggered("Pass")) {
            device.resetWatcherTriggers();
        }


        while (Runtimes-- > 0) {
            assertTrue("enter fail", enterApp(GaodeMap));
            Thread.sleep(10000);
            UiObject2 local = device.wait(Until.findObject(By.res("com.autonavi.minimap:id/linear_path_container")), 1000);
            while (local == null && i-- > 20) {
                Thread.sleep(3000);
                local = device.wait(Until.findObject(By.res("com.autonavi.minimap:id/linear_path_container")), 1000);
            }
            help.exitiinexception(i);
            assertTrue("network enable ", i > 0);
            local = device.wait(Until.findObject(By.res("com.autonavi.minimap:id/linear_path_container")), 1000);
            local.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(3000);
            UiObject2 end = device.wait(Until.findObject(By.res("com.autonavi.minimap:id/tv_input_line").text("输入终点")), 1000);
            end.click();
            UiObject2 searchtext = device.wait(Until.findObject(By.res("com.autonavi.minimap:id/search_text").text("输入终点")), 1000);
            searchtext.setText("深圳");
            UiObject2 list = device.wait(Until.findObject(By.res("com.autonavi.minimap:id/search_sug_container")), 1000);
            i = 5;
            while (list == null && i-- > 0) {
                Thread.sleep(3500);
                list = device.wait(Until.findObject(By.res("com.autonavi.minimap:id/search_sug_container")), 1000);
            }
            assertTrue("网络请求超时", i > 0);
            UiObject2 item = list.getChildren().get(1);
            if (item != null) {
                item.clickAndWait(Until.newWindow(), 8000);
                UiObject2 fail = device.wait(Until.findObject(By.res("com.autonavi.minimap:id/route_car_result_error_text")), 1000);
                assertTrue("定位失败", fail != null);
                UiObject2 start = device.wait(Until.findObject(By.res("com.autonavi.minimap:id/btn_startnavi").text("开始导航")), 1000);
                start.clickAndWait(Until.newWindow(), 1000);
                UiObject2 pass = device.wait(Until.findObject(By.text("忽略")), 1000);
                if (pass != null) {
                    pass.clickAndWait(Until.newWindow(), 1000);
                    Thread.sleep(2000);
                }
                UiObject2 on = device.wait(Until.findObject(By.res("android:id/switch_widget").text("关闭")), 1000);
                if (on != null) {
                    on.click();
                    device.pressBack();
                }

                UiObject2 OK = device.wait(Until.findObject(By.text("同意")), 1000);
                if (OK != null) {
                    OK.clickAndWait(Until.newWindow(), 1000);
                }
                Thread.sleep(10000);
                UiObject2 exit = device.wait(Until.findObject(By.res("com.autonavi.minimap:id/exit_navigation_portrait").descContains("导航退出按钮")), 1000);
                if (exit != null) {
                    exit.clickAndWait(Until.newWindow(), 1000);
                    Thread.sleep(2000);
                    UiObject2 yes = device.wait(Until.findObject(By.res("com.autonavi.minimap:id/btnRight").text("确认")), 1000);
                    if (yes != null)
                        yes.click();
                }
                device.pressBack();
                Thread.sleep(2000);
                device.pressBack();
                device.pressBack();
                device.pressBack();
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
