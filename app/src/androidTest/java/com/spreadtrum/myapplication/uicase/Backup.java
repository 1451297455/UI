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
public class Backup {

    private static final String Backup = "am start -n com.zte.backup.mmi/com.zte.backup.view_blueBG.StartActivity";
    UntilHelp help = null;
    UiDevice device = null;
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
    public void Backup() throws Exception {
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

        while (Runtimes-- > 0) {
            enterApp(Backup);
            //判断是否有协议弹出框
            FirstEnter();
            //case
            TestCase();

        }


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

    private void FirstEnter() {
        UiObject2 Accept = device.wait(Until.findObject(By.res("com.zte.backup.mmi:id/btn_dialog_confirm")), 1000);
        if (Accept != null) {
            UiObject2 Checkbox = device.wait(Until.findObject(By.res("com.zte.backup.mmi:id/checked")), 1000);
            Checkbox.click();
            Accept.clickAndWait(Until.newWindow(), 2000);
        }
    }

    //case
    private void TestCase() throws InterruptedException, IOException {
        UiObject2 navigation = device.wait(Until.findObject(By.res("com.zte.backup.mmi:id/navigation")), 1000);
        while (navigation != null) {
            device.swipe(x * 11 / 12, y / 2, x / 20, y / 2, 10);
            Thread.sleep(1000);
            navigation = device.wait(Until.findObject(By.res("com.zte.backup.mmi:id/navigation")), 1000);
        }
        UiObject2 checkbox = device.wait(Until.findObject(By.res("com.zte.backup.mmi:id/checkBox")), 1000);
        if (checkbox != null) {
            checkbox.clickAndWait(Until.newWindow(), 1000);
        }
        UiObject2 start = device.wait(Until.findObject(By.res("com.zte.backup.mmi:id/startButton").text("开始体验")), 1000);
        if (start != null) {
            start.clickAndWait(Until.newWindow(), 10000);
        }
        UiObject2 Safety = device.wait(Until.findObject(By.res("com.zte.backup.mmi:id/circleRotationAnation")), 1000);
        assertTrue(help.GetCurrTime() + "  未成功进入", Safety != null);
        help.Log("未成功进入", Safety != null);
        device.swipe(x / 20, y / 2, x * 11 / 12, y / 2, 10);
        Thread.sleep(1000);
        device.swipe(x * 11 / 12, y / 2, x / 20, y / 2, 10);
        Thread.sleep(1000);
        device.swipe(x / 20, y / 2, x * 11 / 12, y / 2, 10);
        Thread.sleep(1000);
        device.swipe(x * 11 / 12, y / 2, x / 20, y / 2, 10);
        Thread.sleep(1000);
        //备份数据
        NewBack("com.zte.backup.mmi:id/data_item_2");
        NewBack("com.zte.backup.mmi:id/data_item_1");
        NewBack("com.zte.backup.mmi:id/data_item_5");
        NewBack("com.zte.backup.mmi:id/data_item_7");
        NewBack("com.zte.backup.mmi:id/data_item_6");
        while (Safety != null) {
            device.pressBack();
            Safety = device.wait(Until.findObject(By.res("com.zte.backup.mmi:id/circleRotationAnation")), 1000);
        }
    }

    //备份方法
    private void NewBack(String BackType) throws InterruptedException {
        UiObject2 Newback = device.wait(Until.findObject(By.text("新建备份")), 1000);
        UiObject2 NewBackUp = Newback.getParent().getChildren().get(0);
        NewBackUp.clickAndWait(Until.newWindow(), 1000);
        Thread.sleep(1000);
        UiObject2 back = device.wait(Until.findObject(By.text("备份数据")), 1000);
        UiObject2 BackData = back.getParent().getChildren().get(0);
        BackData.clickAndWait(Until.newWindow(), 1000);
        UiObject2 dataItem = device.wait(Until.findObject(By.res(BackType)), 1000);
        dataItem.clickAndWait(Until.newWindow(), 1000);
        UiObject2 BackUp = device.wait(Until.findObject(By.res("com.zte.backup.mmi:id/operateCircle")), 1000);
        BackUp.clickAndWait(Until.newWindow(), 3000);
        device.pressBack();
        device.pressBack();
        device.pressBack();
    }


    @After
    public void end() {
        device.pressBack();
        device.pressBack();
        device.pressBack();
        device.pressBack();
        device.pressHome();
    }
}
