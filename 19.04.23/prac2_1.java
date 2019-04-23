import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class prac2_1 {

	public static void main(String[] args) throws java.net.SocketException {
		ServerSocket serverSocket = null;
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost", 10001));
			System.out.println("[����] ���� ��ٸ�");
			Socket socket = serverSocket.accept();
			InetSocketAddress inetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			System.out.println("[����] ����� from " +
					inetSocketAddress.getHostName() + ":" + 
					inetSocketAddress.getPort());
			
			// ������ �ޱ� �� ������
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
			while(true) {
				byte[] buffer = new byte[128];
				int readByteCount = is.read(buffer);
				
				if(readByteCount < 0) {
					System.out.println("[����] Ŭ���̾�Ʈ�� ���� �������");
					is.close();
					os.close();
					socket.close();
					break;
				}
				
				// byte�迭, ��Ʈ��������ġ(offset), ��Ʈ������(length), Character Set�� �����Ͽ� String ��ü�� ����
				String data = new String(buffer, 0, readByteCount, "UTF-8");
				
				// ������ ������
				System.out.print(data);
				os.write(data.getBytes("UTF-8"));
				os.flush();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		finally {
			if(serverSocket != null && serverSocket.isClosed()==false) {
				try {
					serverSocket.close();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

}
