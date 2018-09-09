import java.io.IOException;

import org.snmp4j.smi.OID;

public class GetSNMPNode {

	private String SysDec  = null;
	private String SysName = null;
	private String Connect = null;

	public GetSNMPNode(String addr, int port, String comunity) {
		/**
		* Port 161 is used for Read and Other operations
		* Port 162 is used for the trap generation
		*/
		Connect = addr+"/"+port;
		SNMPClient client = new SNMPClient(Connect, comunity);
		try {
			client.start();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		/* 
		* OID - .1.3.6.1.2.1.1.1.0 => SysDec
		* OID - .1.3.6.1.2.1.1.5.0 => SysName
		* => MIB explorer will be useful here, as discussed in previous article 
		*/
		
		try {
			SysDec = client.getAsString(new OID(".1.3.6.1.2.1.1.1.0"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			SysName = client.getAsString(new OID(".1.3.6.1.2.1.1.5.0"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        //new OIDXML("C:/Svarga/etc/datacollection-config.xml"); 	
	}

	public void print() {
		
		if (!SysDec.equals("noSuchObject"))
			System.out.println("SysDec  ("+Connect+") = "+SysDec);
		if (!SysName.equals("noSuchObject"))
			System.out.println("SysName ("+Connect+") = "+SysName);
		System.out.println(" ");	
	}

}
