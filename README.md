# IP and Whois Info Tool 💻
Welcome to the IP and Whois Info Tool! This is a simple Java Swing application that allows you to get various information about a given URL. With this tool, you can easily retrieve IP address, country, city, ISP, and geolocation of an IP 🌍, Whois information for a URL 🔍, opened ports of a URL 🔒, and ping a URL to check its reachability 🔌.

## Features
1. Get IP address, country, city, ISP, and geolocation of an IP: Just enter a URL and click the "IP Infos" button to get all the IP information for that URL.
2. Get Whois information for a URL: Simply enter a URL and click the "Whois Infos" button to get the Whois information for that URL.
3. Scan ports and show opened ports of a URL: Enter a URL and click the "Port Scanner" button to scan the ports of that URL and show the opened ports.
4. Ping a URL and show if it is reachable or not: Enter a URL and click the "Ping" button to check the reachability of the URL.

## Screenshots of the Application

<details>
<summary><strong>Show more</strong></summary>

</details>



## Requirements
- Java 8 or higher: Make sure you have Java 8 or a higher version installed on your system.
## Installation
1. Clone the repository:
```
git clone https://github.com/YOUR_USERNAME/ip-and-whois-info-tool.git
```
2. Open the project in your preferred Java IDE.
## Usage
#### Method 1
- Run the exe file named `UrlInfo.exe`
* I used [Jsmooth](https://jsmooth.sourceforge.net/index.php) tool to generate exe file from jar file.

#### Method 2
- Open cmd and execute following command:
```shell
java -jar ExecutableFile.jar
```
#### Method 3
1. Run the Main class.
2. Enter a URL in the text field and click the desired button to get the corresponding information.

## Technologies 
- Java 8
- Maven
- JSONObject
- WhoisClient
- NetWorking
- Git
- JavaX Swing
- Jsmooth


## Dependencies

<details>
<summary><strong>Show more</strong></summary>

```
        <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>RELEASE</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>RELEASE</version>
            <scope>compile</scope>
        </dependency>
        <dependency>
            <groupId>com.vaadin.external.google</groupId>
            <artifactId>android-json</artifactId>
            <version>0.0.20131108.vaadin1</version>
        </dependency>
        <dependency>
            <groupId>commons-net</groupId>
            <artifactId>commons-net</artifactId>
            <version>3.9.0</version>
        </dependency>
        <dependency>
            <groupId>javax.mail</groupId>
            <artifactId>mail</artifactId>
            <version>1.4.7</version>
        </dependency>

```

</details>


## Credits
ChatGPT was used during the development to shorten the development time.
## Disclaimer
This application is not meant to be a professional tool and is only intended to help me improve my Java knowledge. Use it at your own risk. 😉