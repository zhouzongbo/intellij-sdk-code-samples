package org.intellij.sdk.toolWindow.dialog;

import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * @author create by Zhou Zongbo on 2021/1/4.
 */
public class SimpleDialogWrapper extends DialogWrapper {
    
    public SimpleDialogWrapper() {
        // use current window as parent
        super(true);
        init();
        setTitle("测试dialog");
    }
    
    @Override
    protected @Nullable JComponent createCenterPanel() {
        // 对话框面板
        JPanel dialogPanel = new JPanel(new BorderLayout());
        
        // 标签
        JLabel label = new JLabel("testing");
        // 初始化大小
        label.setPreferredSize(new Dimension(100, 100));
        dialogPanel.add(label);
        return dialogPanel;
    }
    
    @Override
    protected @Nullable ValidationInfo doValidate() {
        // 重写校验规则
        return super.doValidate();
    }
    
    public static void initSimpleDialog(JButton button) {
        if (button == null) {
            throw new NullPointerException("button 不能为空");
        }
        button.addActionListener(e -> {
            final SimpleDialogWrapper simpleDialogWrapper = new SimpleDialogWrapper();
            simpleDialogWrapper.showAndGet();
        });
    }
}
