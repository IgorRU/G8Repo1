	
public class SNMPManager {

	
	public static void main(String[] args) {		

		System.out.println("SNMP devices: \n");
		
		new GetSNMPNode("udp:192.168.88.242",161,"public").print();
		new GetSNMPNode("udp:192.168.88.1"  ,161,"public").print();	
		
		System.out.println("End.");				
	}

	
}
