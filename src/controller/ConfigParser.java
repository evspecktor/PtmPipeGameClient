package controller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;

public class ConfigParser {
	
	Document doc;
	String serverIp;
	int port;
	private String defaultXmlPath = "./resources/config.xml";


	public ConfigParser(String xmlPath) throws ParserConfigurationException, SAXException, IOException
	{
		File fXmlFile = new File(xmlPath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		this.doc = dBuilder.parse(fXmlFile);
	}
	
	public ConfigParser() throws ParserConfigurationException, SAXException, IOException
	{
		File fXmlFile = new File(defaultXmlPath);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		this.doc = dBuilder.parse(fXmlFile);
	}

	public ConfigParser(File xmlFile) throws ParserConfigurationException, SAXException, IOException
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		this.doc = dBuilder.parse(xmlFile);
	}
	
	public String getServerIp() {
		try {
			this.serverIp = this.doc.getElementsByTagName("ip").item(0).getTextContent();
			System.out.println("reading root serverip: " + this.serverIp);
			return this.serverIp;
		}
		catch (Exception e) {
			e.printStackTrace();
			return "coudnt parse servers ip";
		}
	}

	public int getPort() {
		String stport;
		try {
			stport = this.doc.getElementsByTagName("port").item(0).getTextContent();
			this.port = Integer.parseInt(stport);
			System.out.println("reading port : " + this.port);
			return this.port;
		}
		catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	
}
