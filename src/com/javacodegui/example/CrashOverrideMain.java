package com.javacodegui.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class CrashOverrideMain {
    private JPanel tabbedViewIHope;
    private JPanel crashOverrideView;
    private JTabbedPane tabbedPane1;
    private JButton OpenBtn;
    private ArrayList<JScrollPane> scrollPanes = new ArrayList<JScrollPane>();
    private ArrayList<JTextPane> textPanes = new ArrayList<JTextPane>();

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        crashOverrideView = new JPanel();
        crashOverrideView.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        tabbedViewIHope = new JPanel();
        tabbedViewIHope.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        crashOverrideView.add(tabbedViewIHope, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        tabbedPane1 = new JTabbedPane();
        tabbedViewIHope.add(tabbedPane1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        OpenBtn = new JButton();
        OpenBtn.setText("Open");
        crashOverrideView.add(OpenBtn, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return crashOverrideView;
    }

    public CrashOverrideMain() {
        OpenBtn.addActionListener(new openBtnClicked());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CrashOverride");
        frame.setContentPane(new CrashOverrideMain().crashOverrideView);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private class openBtnClicked implements ActionListener {
        JFileChooser chooser = new JFileChooser();

        public void actionPerformed(ActionEvent e) {

            chooser.setDialogTitle("Choose a project...");
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int opt = chooser.showOpenDialog(OpenBtn);
            if (opt == JFileChooser.APPROVE_OPTION) {
                File dir = chooser.getSelectedFile();
                    int tabIdx = 0;
                    for (File f : Objects.requireNonNull(dir.listFiles())) {
                        if (f.isDirectory()) {
                            continue;
                        }
                        textPanes.add(new JTextPane());
                        scrollPanes.add(new JScrollPane(textPanes.get(tabIdx)));
                        tabbedPane1.addTab("", scrollPanes.get(tabIdx));
                        try {
                            fillTextPane(f, tabIdx);
                        } catch (FileNotFoundException ex) {
                            tabbedPane1.removeTabAt(tabIdx);
                        }
                        tabIdx++;
                    }
            }
        }

        private void fillTextPane(File f, int tabIdx) throws FileNotFoundException {
            try {
                Scanner scan = new Scanner(f);
                StringBuilder str = new StringBuilder();
                while (scan.hasNextLine()) {
                    str.append(scan.nextLine());
                    str.append("\n");
                }
                textPanes.get(tabIdx).setText(str.toString());
                tabbedPane1.setTitleAt(tabIdx, f.getName());
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
        }
    }
}
