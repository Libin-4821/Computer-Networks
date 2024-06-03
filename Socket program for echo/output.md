Server output:

D:\ai&ds\4th sem\cn\cn programs>javac eserver.java

D:\ai&ds\4th sem\cn\cn programs>java eserver
msg received and sent back to client
msg received and sent back to client
msg received and sent back to client
msg received and sent back to client
java.net.SocketException: Software caused connection abort: recv failed

D:\ai&ds\4th sem\cn\cn programs>

Client output:

D:\ai&ds\4th sem\cn\cn programs>javac eclient.java

D:\ai&ds\4th sem\cn\cn programs>java eclient
client
Hello
Hi
server:Hi
client
How is your day?
Fine.
server:Fine.
client
end
^Z
server:null
client
^Z
Exception in thread "main" java.lang.NullPointerException
        at eclient.main(eclient.java:21)

D:\ai&ds\4th sem\cn\cn programs>
