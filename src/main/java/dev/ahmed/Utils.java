package dev.ahmed;


import org.apache.commons.net.whois.WhoisClient;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;


/**
 * @author Ahmed Bughra
 * @create 2022-12-20  11:43 PM
 *  *                      mac adress,
 *  *                      whois infos ,
 *  *                      opened port information,
 *  *                      geolocation etc
 */
public class Utils {


    //  get Ip information == working perfectly
    public static JSONObject getIpInfos(String ip) throws Exception {
        URL url = new URL("http://ip-api.com/json/" + ip);

        // Open a connection to the URL and get the InputStream
        InputStream input = url.openStream();

        // Use the InputStreamReader and BufferedReader classes to read the response
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader buffer = new BufferedReader(reader);

        // Read the response line by line and store it in a StringBuilder
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = buffer.readLine()) != null) {
            response.append(line);
        }
        buffer.close();

        // Use the JSONObject class to parse the response
        JSONObject json = new JSONObject(response.toString());

        return json;
    }

    // get Whois information == working perfectly
    public static String getWhoisInfo(String urlString) {
        StringBuilder sb = new StringBuilder("");
        WhoisClient wic = new WhoisClient();
        try {
            wic.connect(WhoisClient.DEFAULT_HOST);
            String whoisData1 = wic.query("=" + urlString);
            sb.append(whoisData1);
            wic.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sb);
        return sb.toString();
    }



    // write txt to the desktop == working perfectly
    public static void writeTxtToDeskop(String content, String nameOfTxtFile) throws IOException {
        //  to call this method like this ====>  writeTxtToDeskop(content,"text");
        String contentHeader = "\n\n\n\n\n"+"==========================="+"\n"
                +"Date of this record:  "
                +"\n "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"))
                +"\n"+"==========================="+"\n"
                +content;
        FileWriter fileWriter = new FileWriter(System.getProperty("user.home")+"\\Desktop\\"+nameOfTxtFile+".txt", true);
        fileWriter.write(contentHeader);
        fileWriter.close();
        System.out.println("Finished to write record to desktop.");


    }


    // get html contents as a string == working perfetctly
    public static String getHTMLContentAsString(String urlString) throws IOException {
        // Make an HTTP request to the URL of the web page
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Get the HTML content as an InputStream
        InputStream inputStream = connection.getInputStream();

        // Read the HTML content from the InputStream as a string
        InputStreamReader reader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuilder htmlStringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            htmlStringBuilder.append(line);
        }
        String htmlString = htmlStringBuilder.toString();

        return htmlString;
    }



    // get your mac address
    public static String getYourMac() {

        InetAddress ip = null;
        StringBuilder sb = null;
        try {
            ip = InetAddress.getLocalHost();
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac = network.getHardwareAddress();

            sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Your IP address : " + ip.getHostAddress()
                + "\nYour MAC address : "
                + sb;
    }

    // get your network infrormation == calisiyor
    public void getYourNetwork() throws SocketException {
        Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();

        while (en.hasMoreElements()) {
            NetworkInterface ni = en.nextElement();

            System.out.println("Interface Name:  " + ni.getName());
            System.out.println("  Display Name = " + ni.getDisplayName());
            System.out.println("  Is up = " + ni.isUp());
            System.out.println("  Support multicast = " + ni.supportsMulticast());
            System.out.println("  Is loopback = " + ni.isLoopback());
            System.out.println("  Is virtual = " + ni.isVirtual());
            System.out.println("  Is point to point = " + ni.isPointToPoint());
            System.out.println("  Hardware address = " + ni.getHardwareAddress());
            System.out.println("  MTU = " + ni.getMTU());

            System.out.println("  List of Interface Addresses:");
            List<InterfaceAddress> list = ni.getInterfaceAddresses();
            Iterator<InterfaceAddress> it = list.iterator();

            while (it.hasNext()) {
                InterfaceAddress ia = it.next();
                System.out.println("    Address = " + ia.getAddress());
                System.out.println("    Broadcast = " + ia.getBroadcast());
                System.out.println("    Network prefix length = " + ia.getNetworkPrefixLength());
                System.out.println("");
            }
        }
    }

    // ping Ip address
    public static String pingIpAddress(String ipAddress) {
        try {
            InetAddress inet = InetAddress.getByName(ipAddress);
            System.out.println("Sending Ping Request to " + ipAddress);
            if (inet.isReachable(5000)){
                System.out.println(ipAddress + " is reachable.");
                return ipAddress + " is reachable.";
            } else {
                System.out.println(ipAddress + " NOT reachable.");
                return ipAddress + "  NOT reachable.";
            }
        } catch ( Exception e ) {
            System.out.println("Exception:" + e.getMessage());
        }
        return null;
    }

    // port scanner === wroking perfectly
    public void portScanner(String adress) {
        for (int port = 1; port <= 65535; port++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(adress, port), 1000);
                socket.close();
                System.out.println("Port " + port + " is open");
            } catch (Exception ex) {
            }
        }
    }


    // working perfectly
        public static ArrayList<String> portScannerMultiThread(String adress) {

            int finalPort = 0;
            ArrayList<String> ports = new ArrayList<String>();

            for (int port = 1; port <= 65535; port++) {
                // Create a new thread for each port
                finalPort = port;
                int finalPort1 = finalPort;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Socket socket = new Socket();
                            socket.connect(new InetSocketAddress(adress, finalPort1), 1000);
                            socket.close();
                            System.out.println("Port " + finalPort1 + " is open.");
                            ports.add("\nPort " + finalPort1 + " is open.");

                        } catch (Exception ex) {
                        }
                    }
                });
                thread.start();
            }
            return ports;
        }
    public void portScannerMultiThread(String hostname,  int startPort, int endPort)  {
        ExecutorService executor = Executors.newFixedThreadPool(50);

        for (int port = startPort; port <= endPort; port++) {
            int finalPort = port;
            Runnable task = () -> {
                try {
                    Socket socket = new Socket();
                    socket.connect(new InetSocketAddress(hostname, finalPort), 1000);
                    socket.close();
                    System.out.println("Port " + finalPort + " is open");
                } catch (Exception ex) {
                    // Port is closed or filtered
                }
            };
            executor.execute(task);
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

//        // not working
//    public void sendEmail(String username, String password) {
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//                return new javax.mail.PasswordAuthentication(username, password);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse("ahmedbughra@gmail.com"));
//            message.setSubject("Testing Subject");
//            message.setText("This is the body of the email...");
//
//            Transport.send(message);
//
//            System.out.println("Mail successfully sent.");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public void GetAllIpAddresses(String url) {

        try {
            InetAddress[] myHost = InetAddress.getAllByName(url);
            for(InetAddress host:myHost){
                System.out.println(host.getHostAddress());
            }
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
    }








    @Test
    public void test1() throws Exception {
//        GetAllIpAddresses("java.com");
//        portScannerMultiThread("google.com");
//        portScanner("google.com");
//        portScannerMultiThread("google.com");
        String url = "google.com";
//        getWhoisInfo(url);
//        System.out.println(getIpInfos(url));
//        System.out.println(getYourMac());
//        getYourNetwork();
//        pingIpAddress("google.com");
        System.out.println(getIpInfos(url));
//        System.out.println(getIpInfos(url).get("country"));
//        System.out.println(getIpInfos(url).get("city"));
//        System.out.println(getIpInfos(url).get("org"));
//        System.out.println(getIpInfos(url).get("timezone"));
//        System.out.println(getIpInfos(url).get("countryCode"));
//        System.out.println(getIpInfos(url).get("lon"));
//        System.out.println(getIpInfos(url).get("lat"));
//        System.out.println(getIpInfos(url).get("query"));
    }
}
