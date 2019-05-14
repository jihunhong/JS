import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class prac1_1 {

	public static void main(String[] args) {
		try {			
			String msg;
			Scanner scan = new Scanner(System.in);
			
			while(true) {
				System.out.print("도메인을 입력하세요 (종료=exit) : ");
				msg = scan.nextLine();
				if(msg.equals("exit")) {
					scan.close();
					break;
				}
	
				InetAddress info[] = InetAddress.getAllByName(msg);
				
				for(InetAddress inetAddress : info) {
					String[] inet = inetAddress.toString().split("/");
					System.out.println(inet[0] + " : " + inet[1]);
				}
			}

		}catch(UnknownHostException e) {
			e.printStackTrace();
		}
		
	}

}
