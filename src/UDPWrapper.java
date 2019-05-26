import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPWrapper{
    DatagramSocket recieveSocket = null;
    byte[] receiveData = new byte[2048];
    DatagramPacket recievePacket = null;

    /**
     * Constructor for the UDP reciever. Sets up internal memory structures in prep to start listening for packets.
     *
     * @param client_addr String of the client's ip address.
     * @param port int port number that the client is sending data to. According to FMS, must be between 5800-5810
     */

    public UDPWrapper(String client_addr, int port) {
        try {
            recieveSocket = new DatagramSocket(port);
            recievePacket = new DatagramPacket(receiveData, receiveData.length);
            recieveSocket.setSoTimeout(10);
        } catch (IOException e) {
            System.out.println("Error: Cannot set up UDP reciever socket: " + e.getMessage());
            recieveSocket = null;
        }

    }

    /**
     * Listens to the UDP connection and casts the received data into a string.
     * @return String of the data acquired from the UDP connection
     */
    public String getPacket(){
        boolean last_packet = false;
        String rx_string = "";
        if(recieveSocket != null){
            while(!last_packet){
                try {
                    recieveSocket.receive(recievePacket);
                    rx_string = new String(recievePacket.getData(), 0, recievePacket.getLength());
                } catch (java.net.SocketTimeoutException e) {
                    /* timeout exception - this is OK, just means we don't see new complete packet. */
                    if(rx_string.length() != 0){
                        last_packet = true;
                    }
                } catch (IOException e) {
                    System.out.println("Error: Cannot get data from UDP socket: " + e.getMessage());
                    recieveSocket = null;
                }
            }
        }

        return rx_string;
    }

    /**
     * Test main function to be used when writing the program. Should never be run on the robot.
     */
    public static void main(String[] args) {
        System.out.println("Starting UDP test");
        UDPWrapper testReceiver = new UDPWrapper("127.0.0.1", 5800);

        while(true){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Got Data: " + testReceiver.getPacket());
        }
    }

}