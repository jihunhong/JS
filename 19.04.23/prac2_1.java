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
			System.out.println("[서버] 연결 기다림");
			Socket socket = serverSocket.accept();
			InetSocketAddress inetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			System.out.println("[서버] 연결됨 from " +
					inetSocketAddress.getHostName() + ":" + 
					inetSocketAddress.getPort());
			
			// 데이터 받기 및 보내기
			InputStream is = socket.getInputStream();
			OutputStream os = socket.getOutputStream();
			
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
