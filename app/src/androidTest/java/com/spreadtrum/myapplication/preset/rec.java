package com.spreadtrum.myapplication.preset;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.Until;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

/**
 * Created by Jinchao_1.Zhang on 2017/8/15.
 */

@RunWith(AndroidJUnit4.class)
public class rec {

    UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    @Before
    public void init() {

    }

    @Test
    public void test() throws IOException {
        device.executeShellCommand("am start -n com.sprd.note/com.sprd.note.NoteActivity ");
        int i = 0;
        while (i < 40) {
            UiObject2 create = device.wait(Until.findObject(By.descContains("新建便签")), 1000);
            create.click();
            UiObject2 text = device.wait(Until.findObject(By.res("com.sprd.note:id/et_content")), 1000);
            text.setText(i + "test");
            UiObject2 more = device.wait(Until.findObject(By.descContains("更多选项")), 1000);
            more.click();
            UiObject2 save = device.wait(Until.findObject(By.text("保存")), 1000);
            save.click();
            i++;
        }

    }

}
