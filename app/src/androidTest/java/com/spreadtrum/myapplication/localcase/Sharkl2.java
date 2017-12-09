package com.spreadtrum.myapplication.localcase;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiWatcher;
import android.support.test.uiautomator.Until;

import com.spreadtrum.myapplication.help.UntilHelp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static junit.framework.Assert.assertTrue;

/**
 * Created by Jinchao_1.Zhang on 2017/11/7.
 */
@RunWith(AndroidJUnit4.class)
public class Sharkl2 {
    //定义启动/停止应用的命令
    private static final String dialer = "am start -n com.android.dialer/com.android.dialer.calllog.CallLogActivity";
    private static final String stop_dialer = "am force-stop com.android.dialer";

    private static final String sms = "am start -n com.android.messaging/.ui.conversationlist.ConversationListActivity";
    private static final String stop_sms = "am force-stop com.android.messaging";

    private static final String contacts = "am start -n com.android.providers.contacts/com.android.contacts.activities.PeopleActivity";
    private static final String stop_contacts = "am force-stop com.android.contacts";

    private static final String browser = "am start -n com.android.browser/.BrowserActivity";
    private static final String stop_browser = "am force-stop com.android.browser";

    private static final String music = "am start -n com.android.music/.MusicBrowserActivity";
    private static final String stop_music = "am force-stop com.android.music";

    private static final String camera = "am start -n com.android.camera2/com.android.camera.CameraLauncher";
    private static final String stop_camera = "am force-stop com.android.camera2";

    private static final String gallery = "am start -n com.android.gallery3d/.app.GalleryActivity";
    private static final String stop_gallery = "am force-stop com.android.gallery3d";

    private static final String Video = "am start -n com.android.gallery3d/com.sprd.gallery3d.app.NewVideoActivity";
    private static final String stop_Video = "am force-stop com.android.gallery3d";

    private static final String setting = "am start -n com.android.settings/.Settings";
    private static final String stop_setting = "am force-stop com.android.settings/.Settings";
    private UiDevice device;
    private UntilHelp help = null;
    int x, y, i = 0, num = 5;

    @Before
    public void init() {
        help = UntilHelp.getInstance();
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
        x = help.GetWidth();
        y = help.GetHight();
    }

    @Test
    public void UserLocalApp() throws Exception {

        help.OpenScreen();

        device.registerWatcher("permession", new UiWatcher() {
            @Override
            public boolean checkForCondition() {
                UiObject2 OK = device.wait(Until.findObject(By.text("允许")), 1000);
                if (OK != null) {
                    OK.clickAndWait(Until.newWindow(), 10000);
                    help.Log("click 允许");
                    return true;
                }
                return false;

            }
        });
        if (device.hasWatcherTriggered("permession")) {
            device.resetWatcherTriggers();
        }
        i = num;
        while (i-- > 0) {
            //停止拨号器进程
            help.Log("stop dialer");
            command(stop_dialer);
            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入dialer", command(dialer));
            Thread.sleep(1000);
            device.pressBack();
            help.Log("start dialer " + String.valueOf(i));
            command(dialer);
            Thread.sleep(5000);
            help.Log("Slide to the left 2");
            device.swipe(x / 5 * 4, y / 2, x / 5, y / 2, 20);
            Thread.sleep(1000);
            device.swipe(x / 5 * 4, y / 2, x / 5, y / 2, 20);
            Thread.sleep(1000);
            help.Log("Slide to the left 2");
            device.swipe(x / 5, y / 2, x / 5 * 4, y / 2, 20);
            Thread.sleep(1000);
            device.swipe(x / 5, y / 2, x / 5 * 4, y / 2, 20);
            Thread.sleep(2000);

            UiObject2 dial = device.wait(Until.findObject(By.res("com.android.dialer:id/floating_action_button")), 3000);
            if (dial != null) {
                help.Log("click dialer");
                dial.clickAndWait(Until.newWindow(), 2000);
            }
            UiObject2 one = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("1")), 1000);
            if (one != null) {
                help.Log(" click 1");
                one.clickAndWait(Until.newWindow(), 1000);
                one.clickAndWait(Until.newWindow(), 1000);
            }
            UiObject2 two = device.wait(Until.findObject(By.clazz("android.widget.TextView").text("2")), 1000);
            if (two != null) {
                help.Log("click 2");
                two.click();
            }
            UiObject2 call = device.wait(Until.findObject(By.res("com.android.dialer:id/dialpad_floating_action_button")), 1000);
            if (call != null) {
                help.Log("call");
                call.clickAndWait(Until.newWindow(), 1000);
                Thread.sleep(5000);
            }
            UiObject2 stopcall = device.wait(Until.findObject(By.res("com.android.dialer:id/incall_end_call")), 1000);
            if (stopcall != null) {
                help.Log("stop call");
                stopcall.clickAndWait(Until.newWindow(), 2000);
            }
            Thread.sleep(2000);
        }
        device.pressHome();
        i = num;
        while (i-- > 0) {
            //杀掉sms进程
            help.Log("stop sms");
            command(stop_sms);
            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入sms", command(sms));
            Thread.sleep(1000);
            device.pressBack();
            help.Log("start sms " + String.valueOf(i));
            command(sms);
            Thread.sleep(5000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            help.Log("Slide to the up 2");
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 4, 10);
            help.Log("Slide to the down 2");
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 4, 10);
            Thread.sleep(1000);
        }
        device.pressHome();
        i = num;
        while (i-- > 0) {
            //杀掉进程
            help.Log("stop contacts");
            command(stop_contacts);
            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入contacts", command(contacts));
            Thread.sleep(1000);
            device.pressBack();
            help.Log("start contacts  " + String.valueOf(i));
            command(contacts);
            Thread.sleep(5000);

            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            help.Log("Slide to the up 2");
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 4, 10);
            help.Log("Slide to the down 2");
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 4, 10);
            Thread.sleep(1000);
        }
        device.pressHome();
        i = num;
        while (i-- > 0) {
            //杀掉进程
            help.Log("stop browser");
            command(stop_browser);
            //打开apk

            assertTrue(help.GetCurrTime() + "  未成功进入browser", command(browser));
            Thread.sleep(1000);
            device.pressBack();
            help.Log("start browser " + String.valueOf(i));
            command(browser);
            Thread.sleep(5000);

            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            help.Log("Slide to the up 2");
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 4, 10);
            help.Log(" Slide to the down 2");
            Thread.sleep(1000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 4, 10);
            Thread.sleep(1000);
        }
        device.pressHome();
        i = num;
        while (i-- > 0) {
            //杀掉进程
            help.Log(" stop music");
            command(stop_music);
            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入music", command(music));
            Thread.sleep(1000);
            device.pressBack();
            help.Log("start music " + String.valueOf(i));
            command(music);
            Thread.sleep(5000);
            UiObject2 song = device.wait(Until.findObject(By.text("歌曲")), 1000);
            song.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(2000);
            UiObject2 satrt = device.wait(Until.findObject(By.res("com.android.music:id/random_text")), 1000);
            if (satrt != null)
                satrt.clickAndWait(Until.newWindow(), 1000);
            Thread.sleep(10000);
            device.pressBack();
            device.pressBack();
            device.pressBack();
            device.pressBack();
            device.pressBack();
        }
        device.pressHome();
        i = num;
        while (i-- > 0) {
            //杀掉进程
            help.Log("stop camera");
            command(stop_camera);
            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入camera", command(camera));
            Thread.sleep(1000);
            device.pressBack();
            help.Log("start camera " + String.valueOf(i));
            command(camera);
            Thread.sleep(5000);
            help.Log("play photo");
            UiObject2 take = device.wait(Until.findObject(By.res("com.android.camera2:id/shutter_button")), 1000);
            if (take != null) take.clickAndWait(Until.newWindow(), 3000);
            Thread.sleep(5000);
            device.pressBack();
        }
        device.pressHome();
        i = num;
        while (i-- > 0) {
            //杀掉进程
            help.Log(" stop gallery");
            command(stop_gallery);
            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入gallery", command(gallery));
            Thread.sleep(1000);
            device.pressBack();
            help.Log("start gallery " + String.valueOf(i));
            command(gallery);
            Thread.sleep(5000);

            device.click(x / 2, y / 2);
            Thread.sleep(3000);
            device.swipe(x / 5 * 4, y / 2, x / 5, y / 2, 10);
            Thread.sleep(1000);
            device.swipe(x / 5, y / 2, x / 5 * 4, y / 2, 10);
            Thread.sleep(1000);
        }
        device.pressHome();
        i = num;
        while (i-- > 0) {
            //杀掉进程
            help.Log("stop video");
            command(stop_Video);
            //打开apk
            assertTrue(help.GetCurrTime() + "  未成功进入Video", command(Video));
            Thread.sleep(1000);
            device.pressBack();
            help.Log("start Video " + String.valueOf(i));
            command(Video);
            Thread.sleep(5000);

            device.click(x / 2, y / 2);
            Thread.sleep(3000);
        }
        device.pressHome();
        i = num;
        while (i-- > 0) {
            //杀掉进程
            help.Log("stop setting");
            command(stop_setting);
            //打开apk

            assertTrue(help.GetCurrTime() + "未成功进入setting", command(setting));
            Thread.sleep(1000);
            device.pressBack();
            help.Log("start setting " + String.valueOf(i));
            command(setting);
            Thread.sleep(5000);

            device.swipe(x / 2, y / 5 * 4, x / 2, y / 5, 10);
            Thread.sleep(2000);
            device.swipe(x / 2, y / 5, x / 2, y / 5 * 4, 10);
            Thread.sleep(1000);
        }
        device.pressHome();
    }

    //执行命令
    private boolean command(String command) throws Exception {
        try {
            device.executeShellCommand(command);
            Thread.sleep(1000);
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
