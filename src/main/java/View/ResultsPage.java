package View;

import Model.PopulationModel;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import java.awt.BorderLayout;
import org.jfree.chart.ChartPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.Math.floor;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class houses everything that is displayed in the results page. This 
 * includes the charts and their animations, the text about each disease 
 * along with their corresponding 'read more' buttons, information about how many 
 * people were never sick, recovered, and died, and the option to save all 
 * that data in a user report. 
 * 
 */
public class ResultsPage extends javax.swing.JDialog implements ActionListener{
    
    int i = 0;
    Timer time;
    ChartPanel panel;
    ChartSimulation chart;
    PopulationModel epidemic;
    int timeframePrint, susceptibleIntValue, exposedIntValue, infectedIntValue;
    int survivedIntValue, diedIntValue;
    PieChart pieChart;
    String diseaseName;
    int maxInfected, survivors;
    MainPage iet;
    java.util.Date date = new java.util.Date(); 
    
    /**
    * ResultsPage is the constructor that sets up the window to display
    * the results.
     * @param iet super
     * @param modal super
     * @param timeframe index
    */
    public ResultsPage(MainPage iet, boolean modal, int timeframe) {
        super(iet, modal);
        timeframePrint = timeframe;
        this.iet = iet;
        initComponents();
        epidemic = iet.resultsPopObserver.model;
        diseaseName = epidemic.getDisease();
        maxInfected = (int) floor(Collections.max(epidemic.I));
        survivors = (int) floor(epidemic.R.get(epidemic.getTimeframe()) - epidemic.D.get(epidemic.getTimeframe()));
        
        susceptibleIntValue = epidemic.S.get(i).intValue();
        diedIntValue = epidemic.D.get(i);
        survivedIntValue = epidemic.R.get(i).intValue() - diedIntValue;
        
        susceptibleValue.setText(String.valueOf(susceptibleIntValue));
        recoveredValue.setText(String.valueOf(survivedIntValue));
        diedValue.setText(String.valueOf(diedIntValue));
        
        chart = new ChartSimulation();
        panel = chart.makeChart(epidemic, i);
        chartPanel.add(panel, BorderLayout.CENTER);
         
        pieChart = new PieChart(epidemic, timeframe);
        pieChart.chartPanel.setVisible(true);
        piePanel2.add(pieChart.chartPanel, BorderLayout.CENTER);

        pieChart.chartPanelMortality.setVisible(true);
        piePanel.add(pieChart.chartPanelMortality, BorderLayout.CENTER);
        
        nameLabel.setText(diseaseName);
        setTextBlurb(iet);
        
        
        time = new Timer(16, this);
        time.start(); 
    }
    
    /**
    * This function displays informative text on the results page for each disease,
    * depending on which disease was selected.
    */
    private void setTextBlurb(MainPage iet){
        if ("Covid".equals(iet.selectedDisease))
            diseaseInfo.setText("""
                               The Covid-19 Pandemic was caused by 
                               the SARS-CoV-2 virus. First appearing in
                               Wuhan China in December of 2019, 
                               within a four month period the virus 
                               had spread to over 200 countries. 
                               Covid-19 is spread through droplets
                               transmitted from the mouth of an
                               infected person. Covid-19 is also known
                               as the coronavirus, which is actually
                               another name for SARS-CoV-2 virus due 
                               to the shape of its structural proteins.""");
        else if ("Influenza".equals(iet.selectedDisease))
            diseaseInfo.setText("""
                               The seasonal Flu consists of three main
                               families of the Influenza virus that are 
                               constantly changing through genetic 
                               mutation. The multitude of strains makes
                               it difficult to eliminate the disease and 
                               new Flu vaccines must be developed 
                               every year. Typically running from 
                               October to May of every year in the US,
                               the Flu is an example of an endemic. 
                               Characterized by a sore throat and body
                               aches the Flu can be dangerous 
                               if not treated quickly.""");
        else if ("Black Death".equals(iet.selectedDisease))
            diseaseInfo.setText("""
                               The bubonic plague, caused by the 
                               bacteria Yersinia Pestis, has had a 
                               long and devastating history. The most 
                               infamous outbreak was the black death
                               in the 14th century where somewhere 
                               between 75 and 200 million people 
                               were killed. The bubonic plague was
                               typically caught from fleas. Today, 
                               the Bubonic Plague is considered a 
                               rare disease thanks to the 
                               development of antibiotics and 
                               improved sanitation and public health.""");
        else if ("Spanish Flu".equals(iet.selectedDisease))
            diseaseInfo.setText("""
                               The Spanish flu was a particularly 
                               deadly influenza pandemic occurring 
                               near the turn of the 20th century, 
                               exacerbated by WWI. It was one of the 
                               most deadly pandemics in history, 
                               killing roughly 60 millions people. 
                               While the symptoms were often similar 
                               to that of the common Flu, they were 
                               far more intense. The disease is now 
                               more commonly known as the 1918 flu.
                               It was known as the Spanish flu as
                               Spain was a neutral country during
                               WWI and most reports of the disease
                               came from Spain.""");
        else if ("Delta".equals(iet.selectedDisease))
            diseaseInfo.setText("""
                               Delta is one of several variants of the
                               SARS‑CoV‑2 virus, the same virus that
                               was responsible for the Covid-19
                               pandemic. The difference in proteins in 
                               Delta contributed to a much greater 
                               infection and mortality rate. However,
                               people who were previously vaccinated
                               for Covid 19 or developed antibodies
                               naturally from surviving the disease were
                               more resistant to the Delta variant.""");
        else if ("Small Pox".equals(iet.selectedDisease))
            diseaseInfo.setText("""
                               Smallpox is one of the most devastating
                               diseases in history mainly thanks to its
                               continuous presence in human population
                               is for what is believed to be over 3000 
                               years. Caused by a seriously infectious
                               member of the orthopox virus, smallpox
                               would leave permanent scars on those
                               lucky enough to survive. Fortunately, 
                               as of 1980 the disease has been 
                               eradicated thanks to combined efforts
                               of the WHO, the US, and the Soviet
                               Union.""");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        chartPanel = new javax.swing.JPanel();
        piePanel = new javax.swing.JPanel();
        exitButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        piePanel2 = new javax.swing.JPanel();
        writeReport = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        diseaseInfo = new javax.swing.JTextArea();
        infoBG = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        recoveredValue = new javax.swing.JLabel();
        rDescription = new javax.swing.JLabel();
        diedValue = new javax.swing.JLabel();
        dDescription = new javax.swing.JLabel();
        susceptibleValue = new javax.swing.JLabel();
        sDescription = new javax.swing.JLabel();
        diseaseinfoButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Results Page");
        setMinimumSize(new java.awt.Dimension(1, 1));
        setSize(new java.awt.Dimension(900, 600));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.setRequestFocusEnabled(false);

        chartPanel.setBackground(new java.awt.Color(51, 51, 51));
        chartPanel.setForeground(new java.awt.Color(204, 204, 204));
        chartPanel.setLayout(new java.awt.BorderLayout());

        piePanel.setBackground(getBackground());
        piePanel.setLayout(new java.awt.BorderLayout());

        exitButton.setBackground(getBackground());
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setText("Return");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        nameLabel.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(102, 153, 0));
        nameLabel.setText("Z");

        piePanel2.setBackground(getBackground());
        piePanel2.setLayout(new java.awt.BorderLayout());

        writeReport.setBackground(getBackground());
        writeReport.setForeground(new java.awt.Color(255, 255, 255));
        writeReport.setText("Save Report");
        writeReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                writeReportActionPerformed(evt);
            }
        });

        diseaseInfo.setEditable(false);
        diseaseInfo.setBackground(getBackground());
        diseaseInfo.setColumns(20);
        diseaseInfo.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        diseaseInfo.setForeground(new java.awt.Color(255, 255, 255));
        diseaseInfo.setRows(5);
        jScrollPane1.setViewportView(diseaseInfo);

        infoBG.setBackground(getBackground());
        infoBG.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        infoBG.setForeground(new java.awt.Color(255, 255, 255));

        titleLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        titleLabel.setForeground(new java.awt.Color(255, 255, 255));
        titleLabel.setText("For the simulated population:");

        recoveredValue.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        recoveredValue.setForeground(new java.awt.Color(255, 255, 255));
        recoveredValue.setText("1");

        rDescription.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        rDescription.setForeground(new java.awt.Color(255, 255, 255));
        rDescription.setLabelFor(recoveredValue);
        rDescription.setText("Number of people who got better");

        diedValue.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        diedValue.setForeground(new java.awt.Color(255, 255, 255));
        diedValue.setText("1");

        dDescription.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        dDescription.setForeground(new java.awt.Color(255, 255, 255));
        dDescription.setLabelFor(diedValue);
        dDescription.setText("Number of people who died");

        susceptibleValue.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        susceptibleValue.setForeground(new java.awt.Color(255, 255, 255));
        susceptibleValue.setText("1");

        sDescription.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        sDescription.setForeground(new java.awt.Color(255, 255, 255));
        sDescription.setLabelFor(susceptibleValue);
        sDescription.setText("Number of people who never got sick");

        javax.swing.GroupLayout infoBGLayout = new javax.swing.GroupLayout(infoBG);
        infoBG.setLayout(infoBGLayout);
        infoBGLayout.setHorizontalGroup(
            infoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoBGLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoBGLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(titleLabel))
                    .addComponent(sDescription)
                    .addGroup(infoBGLayout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(dDescription))
                    .addGroup(infoBGLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(rDescription)))
                .addContainerGap(14, Short.MAX_VALUE))
            .addGroup(infoBGLayout.createSequentialGroup()
                .addGap(118, 118, 118)
                .addComponent(susceptibleValue)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoBGLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(infoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoBGLayout.createSequentialGroup()
                        .addComponent(diedValue)
                        .addGap(106, 106, 106))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoBGLayout.createSequentialGroup()
                        .addComponent(recoveredValue)
                        .addGap(104, 104, 104))))
        );
        infoBGLayout.setVerticalGroup(
            infoBGLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoBGLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addGap(25, 25, 25)
                .addComponent(sDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(susceptibleValue)
                .addGap(18, 18, 18)
                .addComponent(dDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(diedValue)
                .addGap(18, 18, 18)
                .addComponent(rDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recoveredValue)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        diseaseinfoButton.setBackground(getBackground());
        diseaseinfoButton.setForeground(new java.awt.Color(255, 255, 255));
        diseaseinfoButton.setText("Learn More");
        diseaseinfoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                diseaseinfoButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(piePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(piePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 475, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 955, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(113, 113, 113)
                                .addComponent(diseaseinfoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 29, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(nameLabel))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(writeReport))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(101, 101, 101)
                                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(infoBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(nameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(diseaseinfoButton)
                        .addGap(38, 38, 38))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(chartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(infoBG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(writeReport)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(piePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(piePanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     /**
    * This function allows for the page to be closed when the 
    * return button is pressed.
    */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        dispose();
    }//GEN-LAST:event_exitButtonActionPerformed

    /**
    * This function writes to a text file to save user data from a session using the 
    * program.
    */
    private void writeReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_writeReportActionPerformed
        try {
            FileWriter writer;
            writer = new FileWriter("UserReport.txt", true);
            writer.write("Report Date: " + date);
            writer.write("\r\n");
            writer.write("A " + diseaseName + " pandemic lasted for " + timeframePrint + " days\r\n");
            writer.write("At its peak " + maxInfected + " were infected\r\n");
            writer.write("A total of " + survivors + " people survived the pandemic\r\n");
            writer.write("A total of " + Math.round(epidemic.S.get(epidemic.getTimeframe())) + " people never got sick\r\n");
            writer.write("A total of " + epidemic.D.get(epidemic.getTimeframe()).toString() + " people died\r\n\r\n");
            
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        writeReport.setEnabled(false);
    }//GEN-LAST:event_writeReportActionPerformed

    /**
    * This function houses the information that is in the "read more" button
    * on the results page, depending on which disease was clicked.
    */
    private void diseaseinfoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_diseaseinfoButtonActionPerformed
        String fileToRead = "asdf.txt";
        if ("Covid".equals(iet.selectedDisease))
            fileToRead = "Covid.txt";
        else if ("Influenza".equals(iet.selectedDisease))
            fileToRead = "Influenza.txt";
         else if ("Black Death".equals(iet.selectedDisease))
            fileToRead = "Black Death.txt";
         else if ("Small Pox".equals(iet.selectedDisease))
            fileToRead = "Small Pox.txt";
         else if ("Delta".equals(iet.selectedDisease))
            fileToRead = "Delta.txt";
         else if ("Spanish Flu".equals(iet.selectedDisease))
            fileToRead = "Spanish Flu.txt";
        ReadMorePage infodump = new ReadMorePage(new MainPage(), true, fileToRead);
        infodump.setLocationRelativeTo(this);
        infodump.setVisible(true);
        
    }//GEN-LAST:event_diseaseinfoButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        FlatLaf.setGlobalExtraDefaults( Collections.singletonMap( "@accentColor", "#f00" ) );
        FlatDarkLaf.setup();//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ResultsPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run(int timeframe) {
                ResultsPage dialog = new ResultsPage(new MainPage(), true, timeframe);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }

            @Override
            public void run() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
    }
    
    // @parag Graphics g
    public void paint(Graphics g) {
        super.paint(g);
    }
    
    /**
     * actionPerformed delays the charts from filling out so
     * the user can see the animations happen.
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(i < timeframePrint){
            if (i == 0) {
                try {
                // The time to wait before starting the animation
                Thread.sleep(1000);
                } catch (InterruptedException s) {
                    Thread.currentThread().interrupt();
                }
            }
            i = i + 1;
            
            panel = chart.makeChart(epidemic, i);
            
            susceptibleIntValue = epidemic.S.get(i).intValue();
            diedIntValue = epidemic.D.get(i);
            survivedIntValue = epidemic.R.get(i).intValue() - diedIntValue;
        
            susceptibleValue.setText(String.valueOf(susceptibleIntValue));
            recoveredValue.setText(String.valueOf(survivedIntValue));
            diedValue.setText(String.valueOf(diedIntValue));
            
            pieChart.updatePieChart(epidemic, i);
            
            repaint();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel chartPanel;
    private javax.swing.JLabel dDescription;
    private javax.swing.JLabel diedValue;
    private javax.swing.JTextArea diseaseInfo;
    private javax.swing.JButton diseaseinfoButton;
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel infoBG;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JPanel piePanel;
    private javax.swing.JPanel piePanel2;
    private javax.swing.JLabel rDescription;
    private javax.swing.JLabel recoveredValue;
    private javax.swing.JLabel sDescription;
    private javax.swing.JLabel susceptibleValue;
    private javax.swing.JLabel titleLabel;
    private javax.swing.JButton writeReport;
    // End of variables declaration//GEN-END:variables
}
