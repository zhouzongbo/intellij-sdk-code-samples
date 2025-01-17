// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.toolWindow;

import com.intellij.openapi.wm.ToolWindow;
import org.intellij.sdk.toolWindow.dialog.SimpleDialogWrapper;
import org.intellij.sdk.toolWindow.optionpane.SimpleOptionPane;
import org.intellij.sdk.toolWindow.popups.SimplePopup;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Calendar;

public class MyToolWindow {
    
    private JButton refreshToolWindowButton;
    
    private JButton hideToolWindowButton;
    
    private JLabel currentDate;
    
    private JLabel currentTime;
    
    private JLabel timeZone;
    
    private JPanel myToolWindowContent;
    
    private JTextField myText;
    
    private JButton showDialog;
    
    public MyToolWindow(ToolWindow toolWindow) {
        // 貌似不用初始化
        hideToolWindowButton.addActionListener(e -> toolWindow.hide(null));
        refreshToolWindowButton.addActionListener(e -> currentDateTime());
        myText.setText("不晓得");
        // 添加对话框
        SimpleDialogWrapper.initSimpleDialog(showDialog);
        // 添加弹出框
        SimpleOptionPane.addWarnTextFieldChangeListener(myText);
        this.currentDateTime();
    }
    public void currentDateTime() {
        // Get current date and time
        Calendar instance = Calendar.getInstance();
        currentDate.setText(
            instance.get(Calendar.DAY_OF_MONTH) + "/" + (instance.get(Calendar.MONTH) + 1) + "/" + instance
                .get(Calendar.YEAR));
        currentDate.setIcon(new ImageIcon(getClass().getResource("/toolWindow/Calendar-icon.png")));
        int min = instance.get(Calendar.MINUTE);
        String strMin = min < 10 ? "0" + min : String.valueOf(min);
        currentTime.setText(instance.get(Calendar.HOUR_OF_DAY) + ":" + strMin);
        currentTime.setIcon(new ImageIcon(getClass().getResource("/toolWindow/Time-icon.png")));
        // Get time zone
        long gmt_Offset = instance.get(Calendar.ZONE_OFFSET); // offset from GMT in milliseconds
        String str_gmt_Offset = String.valueOf(gmt_Offset / 3600000);
        str_gmt_Offset = (gmt_Offset > 0) ? "GMT + " + str_gmt_Offset : "GMT - " + str_gmt_Offset;
        timeZone.setText(str_gmt_Offset);
        timeZone.setIcon(new ImageIcon(getClass().getResource("/toolWindow/Time-zone-icon.png")));
    }
    
    public JPanel getContent() {
        return myToolWindowContent;
    }
}
