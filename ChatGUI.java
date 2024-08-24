/* Based on TextDemo.java by Oracle. */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;


public class ChatGUI extends JPanel implements ActionListener {
    protected JTextField textField;
    //protected JTextArea textArea;
    protected JList<JTextpane> answersList;
    protected JList<JTextpane> promptList;
    //protected JTextPane postPane;
    //protected JTextPane answerPane;
    static Client con = new Client();
    private final static String newline = "\n";

    private javax.swing.JTextArea chatArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel status;

    private void ChatGUI() {}
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(null);

        jTextField1.setToolTipText("text\tType your message here...");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(10, 370, 410, 40);

        jButton1.setBackground(new java.awt.Color(204, 204, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Send");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(420, 370, 80, 40);

        chatArea.setColumns(20);
        chatArea.setRows(5);
        jScrollPane1.setViewportView(chatArea);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 80, 490, 280);

        jLabel2.setFont(new java.awt.Font("Myriad Pro", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Client");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(140, 20, 180, 40);

        status.setForeground(new java.awt.Color(255, 255, 255));
        status.setText("...");
        jPanel1.add(status);
        status.setBounds(10, 50, 300, 40);
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 400, 400);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 508, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(508, 441));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /*public ChatGUI() {
        super(new GridBagLayout());

        textField = new JTextField(50);
        textField.addActionListener(this);

        //textArea = new JTextArea(23, 20);
        //textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textField);

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        add(postPane, c);
        add(answerPane, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(textField, c);
    }*/

    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        textField.setText("");
        JTextPane postPane = new JTextPane();
        JTextPane answerPane = new JTextPane();

        answerPane.setEditable(false);
        postPane.setEditable(false);

        SimpleAttributeSet left_style = new SimpleAttributeSet();
        SimpleAttributeSet right_style = new SimpleAttributeSet();

        StyleConstants.setAlignment(left_style, StyleConstants.ALIGN_LEFT);
        StyleConstants.setAlignment(right_style, StyleConstants.ALIGN_RIGHT);

        postPane.setParagraphAttributes(right_style, true);
        answerPane.setParagraphAttributes(left_style, true);


        try {
            postPane.getStyledDocument().insertString(
                postPane.getDocument().getLength(), newline + text + newline, null
                );
            }
        catch(BadLocationException e) {
            System.out.println("Text konnte nicht zum Verlauf hinzugefügt werden");
        }

        //postPane.setText(text + newline); //-------------------important line!
        con.send_msg(text);
        String answer = con.reicv_msg();
        //textArea.append(answer + newline);
        try {
            answerPane.getStyledDocument().insertString(
                answerPane.getDocument().getLength(), newline + answer + newline, null
                );
            }
        catch(BadLocationException e) {
            System.out.println("Text konnte nicht zum Verlauf hinzugefügt werden");
        }
;       textField.selectAll();

        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        answerPane.setCaretPosition(answerPane.getDocument().getLength());
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("ChatBotS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Add contents to the window.
        frame.add(new ChatGUI());

        //Display the window.
        frame.pack();
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent e) {
            con.close_con();
            super.windowClosing(e); 
            }
        
        });
    }

    public static void main(String[] args) throws Exception {
        con.est_con();
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
           });
        
    }
}
