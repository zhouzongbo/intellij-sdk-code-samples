package org.intellij.sdk.toolWindow.popups;

import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.Disposer;
import com.intellij.ui.awt.RelativePoint;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

/**
 * 简单的弹出框
 * https://www.programcreek.com/java-api-examples/?api=com.intellij.openapi.ui.popup.JBPopupFactory
 * */
public class SimplePopup {

    public static void lengthError(final JTextField field, final String message) {
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                errorBalloon(field, message);
            }
    
            @Override
            public void removeUpdate(DocumentEvent e) {
                errorBalloon(field, message);
            }
    
            @Override
            public void changedUpdate(DocumentEvent e) {
                errorBalloon(field, message);
            }
        });
        
    }
    
    private static void errorBalloon(JTextField field, String message) {
        if (field == null) {
            throw new NullPointerException("JTextField can not be null");
        }
        
        String text = field.getText();
        System.out.println(text);
        if (message.length() < 5) {
            return;
        }
        
        BalloonBuilder balloonBuilder = JBPopupFactory
            .getInstance().createHtmlTextBalloonBuilder(message, MessageType.ERROR, null);
        balloonBuilder.setFadeoutTime(1500);
        final Balloon balloon = balloonBuilder.createBalloon();
        final Rectangle rect = field.getBounds();
        final Point p = new Point(0, rect.height);
        final RelativePoint point = new RelativePoint(field, p);
        balloon.show(point, Balloon.Position.below);
        Disposer.register(ProjectManager.getInstance().getDefaultProject(), balloon);
    }
}
