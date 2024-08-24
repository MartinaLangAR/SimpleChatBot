/* Based on TextDemo.java by Oracle. */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;


public class ChatGUI extends JFrame implements ActionListener {
    protected JTextField textField;

    protected JTextPane postPane;
    protected JTextPane answerPane;
    static Client con = new Client();
    private final static String newline = "\n";

    private JTextArea chatArea;
    private JLabel jLabel1;
    private JLabel jLabel2;
    protected JPanel jPanel1;
    private JScrollPane jScrollPane1;
    private JTextField jTextField1;
    private JLabel status;

    public void ChatGUI() {

        jPanel1 = new JPanel();
        jTextField1 = new JTextField(50);
        jScrollPane1 = new JScrollPane();
        chatArea = new JTextArea();
        jLabel2 = new JLabel();
        status = new JLabel();
        jLabel1 = new JLabel();

        jPanel1.setBackground(new java.awt.Color(0, 0, 51));
        jPanel1.setForeground(new java.awt.Color(204, 204, 204));
        jPanel1.setLayout(null);

        jTextField1.setToolTipText("text\tType your message here...");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(10, 370, 410, 40);

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

        jTextField1.setEditable(true);
        chatArea.setEditable(false);
    }

    public String post_msg(String text){
        //add prompt to chat area
        chatArea.append(text + newline);
        jTextField1.setText("");
        return text;
    }

    public void actionPerformed(ActionEvent evt) {
        String text = jTextField1.getText();
        post_msg(text);
        //request answer
        con.send_msg(text);
        String answer = con.reicv_msg();
        chatArea.append(answer + newline);
    }

}
