package org.intellij.sdk.toolWindow.optionpane;

import org.apache.commons.lang3.StringUtils;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * 简单的提示框.
 * @author create by Zhou Zongbo on 2021/1/4.
 */
public class SimpleOptionPane {
    private static final String DEFAULT_TEXT_FILED_LENGTH_ERROR = "长度超了.";
    
    public static void addWarnTextFieldChangeListener(JTextField textField) {
        addWarnTextFieldChangeListener(textField, StringUtils.EMPTY, StringUtils.EMPTY);
    }
    
    public static void addWarnTextFieldChangeListener(JTextField textField, String message) {
        addWarnTextFieldChangeListener(textField, StringUtils.EMPTY, message);
    }
    
    public static void addWarnTextFieldChangeListener(JTextField textField, String title, String message) {
        textField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                warn();
            }
            
            @Override
            public void removeUpdate(DocumentEvent e) {
                warn();
            }
            
            @Override
            public void changedUpdate(DocumentEvent e) {
                warn();
            }
            
            public void warn() {
                if (textField.getText().length() > 6) {
                    JOptionPane.showMessageDialog(null,
                        StringUtils.isNotBlank(message) ? message : DEFAULT_TEXT_FILED_LENGTH_ERROR,
                        StringUtils.isNotBlank(title) ? title : "Error Message",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
