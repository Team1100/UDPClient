import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;


/**
 * @author Tejas Maraliga (tm1287)
 *
 * This is a java client meant to be used to update pid values on the fly for Team 1100's future robots.
 * When combined with the UDP reciever wrapper, this java client can be used to send P, I, D, and F variables
 * encoded in a raw UDP packet to the robot.
 */
public class UDPClient {
    public static String pid_var;
    public static byte buf[];
    public static DatagramSocket ds;
    public static InetAddress ip;
    public static int port = 5800;

    public static void main(String[] args) {
        setup_gui();
        setup_network_utilities();
    }

    public static void setup_network_utilities(){
        // Step 1:Create the socket object for
        // carrying the data.
        ds = null;

        try {
            ds = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        ip = null;

        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        buf = null;
    }

    public static void send_data(InetAddress destination_ip, String text_to_send, int port) {

        // loop while user not enters "bye"

        //String inp = sc.nextLine();
        String inp = text_to_send;
        // convert the String input into the byte array.
        buf = inp.getBytes();

        // Step 2 : Create the datagramPacket for sending
        // the data.
        DatagramPacket DpSend = new DatagramPacket(buf, buf.length, destination_ip, port);

        // Step 3 : invoke the send call to actually send
        // the data.
        try {
            ds.send(DpSend);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void setup_gui(){
        JFrame frame = new JFrame("UDP Client");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("UDP Port");
        mb.add(m1);
        JMenuItem m11 = new JMenuItem("5800");
        JMenuItem m12 = new JMenuItem("5801");
        JMenuItem m13 = new JMenuItem("5802");
        JMenuItem m14 = new JMenuItem("5803");
        JMenuItem m15 = new JMenuItem("5804");
        JMenuItem m16 = new JMenuItem("5805");
        JMenuItem m17 = new JMenuItem("5806");
        JMenuItem m18 = new JMenuItem("5807");
        JMenuItem m19 = new JMenuItem("5808");
        JMenuItem m110 = new JMenuItem("5809");
        JMenuItem m111 = new JMenuItem("5810");
        m1.add(m11);
        m1.add(m12);
        m1.add(m13);
        m1.add(m14);
        m1.add(m15);
        m1.add(m16);
        m1.add(m17);
        m1.add(m18);
        m1.add(m19);
        m1.add(m110);
        m1.add(m111);

        //Creating the panel at bottom and adding components
        JPanel panel = new JPanel(); // the panel is not visible in output
        JLabel label = new JLabel("Enter PID Variables: P, I, D, F");
        JLabel ip_label = new JLabel("Enter Robot IP");
        JTextField tf_p = new JTextField(5); // accepts upto 10 characters
        JTextField tf_i = new JTextField(5);
        JTextField tf_d = new JTextField(5);
        JTextField tf_f = new JTextField(5);
        JTextField tf_ip = new JTextField(10);
        JButton send = new JButton("Send");
        JButton reset = new JButton("Reset");
        DefaultListModel<String> l1 = new DefaultListModel<>();
        JList<String> list = new JList<>(l1);

        panel.add(ip_label);
        panel.add(tf_ip);

        panel.add(label); // Components Added using Flow Layout
        panel.add(tf_p);
        panel.add(tf_i);
        panel.add(tf_d);
        panel.add(tf_f);
        panel.add(send);
        panel.add(reset);
        frame.add(list);


        //Adding Components to the frame.
        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, mb);
        //frame.getContentPane().add(BorderLayout.CENTER, ta);
        frame.setVisible(true);

        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pid_var = tf_p.getText() + " " + tf_i.getText() + " " + tf_d.getText() + " " + tf_f.getText();
                try {
                    System.out.println(pid_var + " " + port + " " + InetAddress.getByName(tf_ip.getText()));
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                }

                try {
                    send_data(InetAddress.getByName(tf_ip.getText()),pid_var, port);
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                }
                l1.addElement(pid_var + " sent to " + tf_ip.getText() + ":" + port);
            }
        });

        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tf_p.setText("");
                tf_i.setText("");
                tf_d.setText("");
                tf_f.setText("");
                l1.clear();
            }
        });

        m11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = 5800;
            }
        });

        m12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = 5801;
            }
        });

        m13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = 5802;
            }
        });

        m14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = 5803;
            }
        });

        m15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = 5804;
            }
        });

        m16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = 5805;
            }
        });

        m17.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = 5806;
            }
        });

        m18.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = 5807;
            }
        });

        m19.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = 5808;
            }
        });

        m110.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = 5809;
            }
        });

        m111.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                port = 5810;
            }
        });



    }
}

