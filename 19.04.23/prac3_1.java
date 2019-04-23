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
				System.out.println("[서버] 연결 기다림");
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
			System.out.println("[서버] 연결됨 from " +
					inetSocketAddress.getHostName() + ":" + 
					inetSocketAddress.getPort());
			
			// 데이터 받기 및 보내기
			InputStream is;
			try {
				is = this.socket.getInputStream();
				OutputStream os = this.socket.getOutputStream();
				
				while(true) {
					byte[] buffer = new byte[128];
					int readByteCount = is.read(buffer);
					
					if(readByteCount < 0) {
						System.out.println("[서버] 클라이언트로 부터 연결끊김");
						is.close();
						os.close();
						socket.close();
						break;
					}
					
					// byte배열, 스트링시작위치(offset), 스트링길이(length), Character Set을 지정하여 String 객체를 생성
					String data = new String(buffer, 0, readByteCount, "UTF-8");
					
					// 데이터 보내기
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
