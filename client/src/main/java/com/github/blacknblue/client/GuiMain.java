package com.github.blacknblue.client;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class GuiMain {
    Button b1;
    TextField tf;
    TextArea ta;
    ServerSocket ss;
    Socket s;
    PrintWriter pw;
    BufferedReader br;
    Thread th;

    public AppClient()
    {
        Frame f=new Frame("Client Side Chatting");//Frame for Client
        f.setLayout(new FlowLayout());//set layout
        f.setBackground(Color.orange);//set background color of the Frame
        b=new Button("Send");//Send Button
        b.addActionListener(this);//Add action listener to send button.
        f.addWindowListener(new W1());//add Window Listener to the Frame
        tf=new TextField(15);
        ta=new TextArea(12,20);
        ta.setBackground(Color.cyan);
        f.add(tf);//Add TextField to the frame
        f.add(b);//Add send Button to the frame
        f.add(ta);//Add TextArea to the frame
        try{
            s=new Socket(InetAddress.getLocalHost(),12000);//Socket for client
                //below line reads input from InputStreamReader
            br=new BufferedReader(new InputStreamReader(s.getInputStream()));
                //below line writes output to OutPutStream
            pw=new PrintWriter(s.getOutputStream(),true);
        }catch(Exception e)
        {

        }
        th=new Thread(this);//start a new thread
        th.setDaemon(true);//set the thread as demon
        th.start();
        setFont(new Font("Arial",Font.BOLD,20));
        f.setSize(200,200);//set the size
        f.setVisible(true);
        f.setLocation(100,300);//set the location
        f.validate();

    }
    private class W1 extends WindowAdapter
    {
        public void windowClosing(WindowEvent we)
        {
            System.exit(0);
        }
    }

    public void actionPerformed(ActionEvent ae)

    {
        pw.println(tf.getText());//write the value of textfield into PrintWriter
        tf.setText("");//clean the textfield

    }

}
