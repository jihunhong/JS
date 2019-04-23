import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class prac3_1 {
	
	public static final int PORT = 10001;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket();
			// 192.168.56.1
			serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost().getHostAddress(), PORT));
			
			while(true) {
				System.out.println("[����] ���� ��ٸ�");
				Socket socket = serverSocket.accept();
				
				Thread thread = new EchoServerReceiveThread(socket);
				thread.start();
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(serverSocket != null && serverSocket.isClosed()==false) {
				try {
					serverSocket.close();
				}catch(IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	
	static class EchoServerReceiveThread extends Thread{
		
		Socket socket;
		
		public EchoServerReceiveThread(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			InetSocketAddress inetSocketAddress = (InetSocketAddress)this.socket.getRemoteSocketAddress();
			System.out.println("[����] ����� from " +
					inetSocketAddress.getHostName() + ":" + 
					inetSocketAddress.getPort());
			
			// ������ �ޱ� �� ������
			InputStream is;
			try {
				is = this.socket.getInputStream();
				OutputStream os = this.socket.getOutputStream();
				
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
}
