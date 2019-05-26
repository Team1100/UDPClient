# NOTE: This client is yet to be tested. Not guaranteed to work as described.

# UDPClient
Repository dedicated to the development of the UDP client meant to update PID variables on the robot on the fly.

# How to Use the UDP Client
![Image of UDP Client](https://github.com/Team1100/UDPClient/blob/master/UDP.PNG)

1. Determine the ip address of the robot when connected to the robot network. Enter this into the "Robot IP" field.
1. Enter your "P", "I", "D", and "F" values ([read about PID variables here](https://frc-pdr.readthedocs.io/en/latest/control/pid_control.html))
    1. Note: If there is no need for the Feed Forward variable, enter "0" as the F value.
1. Use the dropdown menu named "UDP Port" to select which port to send the UDP packet to (ports range from 5800-5810)
1. Press send to attempt to send the UDP packet.
