/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 


/* Based on TextDemo.java by Oracle. */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;


public class ChatGUI extends JPanel implements ActionListener {
    protected JTextField textField;
    //protected JTextArea textArea;
    protected JTextPane promptPane;
    protected JTextPane answerPane;
    static Client con = new Client();
    private final static String newline = "\n";

    public ChatGUI() {
        super(new GridBagLayout());

        textField = new JTextField(50);
        textField.addActionListener(this);

        promptPane = new JTextPane();
        answerPane = new JTextPane();
        promptPane.setEditable(false);
        answerPane.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(answerPane);
        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        add(answerPane,c);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        add(promptPane, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        add(textField, c);

        SimpleAttributeSet left_style = new SimpleAttributeSet();
        SimpleAttributeSet right_style = new SimpleAttributeSet();

        StyleConstants.setAlignment(left_style, StyleConstants.ALIGN_LEFT);
        StyleConstants.setAlignment(right_style, StyleConstants.ALIGN_RIGHT);

        promptPane.setParagraphAttributes(right_style, true);
        answerPane.setParagraphAttributes(left_style, true);
    }

    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        textField.setText("");
        con.send_msg(text);
        String answer = con.reicv_msg();
        
        try {
            promptPane.getStyledDocument().insertString(
                promptPane.getDocument().getLength(),newline + text + newline, null
                );
            }
        catch(BadLocationException e) {
            System.out.println("Text konnte nicht zum Verlauf hinzugefügt werden");
        }

        textField.selectAll();
        try {
            answerPane.getStyledDocument().insertString(
                answerPane.getDocument().getLength(), newline + answer + newline, null
                );
            }
        catch(BadLocationException e) {
            System.out.println("Text konnte nicht zum Verlauf hinzugefügt werden");
        }
        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textField.setCaretPosition(textField.getDocument().getLength());
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
