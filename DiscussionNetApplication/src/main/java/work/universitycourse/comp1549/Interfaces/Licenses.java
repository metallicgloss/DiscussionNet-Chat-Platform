package work.universitycourse.comp1549.Interfaces;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import work.universitycourse.comp1549.Modules.InterfaceManager;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 * 
 */

@SuppressWarnings("serial")
public class Licenses extends JFrame {

    // #-----------------------------------------------------------------------#
    // #                       Create New Licenses Form                        #
    // #-----------------------------------------------------------------------#
    public Licenses() {
        initComponents();
    }

    // #-----------------------------------------------------------------------#
    // #                  Initialise User Interface Components                 #
    // #-----------------------------------------------------------------------#
    private void initComponents() {

        // Initialise interface objects.
        googleFontsLabel = new JLabel();
        googleFontsTitleLabel = new JLabel();
        licensesPanel = new JPanel();

        // Define application window settings.
        setBackground(new Color(255, 255, 255));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setIconImage(InterfaceManager.programIcon.getImage());
        setMinimumSize(new Dimension(854, 519));
        setName("DiscussionNet");
        setResizable(false);
        setTitle("DiscussionNet V1.0 ");

        InterfaceManager.detectExitRequest(licensesPanel);

        // Apply settings to the license panel.
        licensesPanel.setBackground(new Color(255, 255, 255));
        licensesPanel.setMaximumSize(new Dimension(847, 519));
        licensesPanel.setMinimumSize(new Dimension(847, 519));
        licensesPanel.setName("licensesPanel");
        licensesPanel.setPreferredSize(new Dimension(847, 519));

        // Apply settings to the google fonts title.
        googleFontsTitleLabel.setFont(InterfaceManager.montserratSemiBold.deriveFont(23.0f));
        googleFontsTitleLabel.setName("googleFontsTitleLabel");
        googleFontsTitleLabel.setText("Google Fonts (Montserrat)");

        // Apply settings to the google font license label.
        googleFontsLabel.setFont(InterfaceManager.montserratRegular.deriveFont(12.0f));
        googleFontsLabel.setText("<html>Google Fonts (Montserrat)<br>" + "Version: 1.1 Update 5<br>"
                + "License: SIL Open Font License (OFL)<br>" + "URL: https://fonts.google.com/specimen/Montserrat<br>"
                + "Copyright: © 2016-2021 Julieta Ulanovsky and other contributors.</html>");
        googleFontsLabel.setName("googleFontsLabel");

        // Apply group layout configuration.
        GroupLayout licensesPanelLayout = new GroupLayout(licensesPanel);
        licensesPanel.setLayout(licensesPanelLayout);
        licensesPanelLayout.setHorizontalGroup(licensesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(licensesPanelLayout.createSequentialGroup().addGap(33, 33, 33)
                        .addGroup(licensesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(googleFontsTitleLabel).addComponent(googleFontsLabel,
                                        GroupLayout.PREFERRED_SIZE, 779, GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(35, Short.MAX_VALUE)));
        licensesPanelLayout.setVerticalGroup(licensesPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(licensesPanelLayout.createSequentialGroup().addGap(34, 34, 34)
                        .addComponent(googleFontsTitleLabel).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(googleFontsLabel).addContainerGap(434, Short.MAX_VALUE)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING,
                        layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(licensesPanel,
                                GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(licensesPanel,
                GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

        getAccessibleContext().setAccessibleName("DiscussionNet");

        pack();
    }

    // #-----------------------------------------------------------------------#
    // #                           Interface Runnable                          #
    // #-----------------------------------------------------------------------#
    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    // If Windows style available, set the default look and feel of the generated elements.
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Licenses.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        // Execute startup of interface.
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Licenses().setVisible(true);
            }
        });
    }

    private JLabel googleFontsLabel;
    private JLabel googleFontsTitleLabel;
    private JPanel licensesPanel;
}
