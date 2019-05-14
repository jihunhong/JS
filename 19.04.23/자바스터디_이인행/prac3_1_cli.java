import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class prac3_1_cli {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket();
			System.out.println("[Ŭ���̾�Ʈ] ���� ��û");
			socket.connect(new InetSocketAddress("192.168.56.1", 10001));
			System.out.println("[Ŭ���̾�Ʈ] ���� ����");
			
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			Scanner scan = new Scanner(System.in);
			String msg;
			byte[] buffer = new byte[128];
			
			while(true) {
				System.out.print("�޼��� �Է� (����=exit) >> ");
				msg = scan.nextLine();
				if(msg.equals("exit")) {
					is.close();
					os.close();
					scan.close();
					break;
				}
				os.write(msg.getBytes("UTF-8"));
				int readByteCount = is.read(buffer);
				String reMsg = new String(buffer, 0, readByteCount, "UTF-8");
				System.out.println("�޼��� �Է� (����=exit) << " + reMsg);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(socket != null && socket.isClosed() == false) {
				try {
					socket.close();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}