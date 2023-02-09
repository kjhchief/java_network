package ezen.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP/IP 기반의 자바 서버
 * 
 * @Author 김재훈
 * @Date 2023. 2. 6.
 */
public class TcpServerExample {

	public static void main(String[] args) {
		int port = 2023;

		// #1. 모든 클라이언트의 연결을 수신(청취)하기 위해 ServerSocket 생성.
		try {
			System.out.println("[서버] 구동되었습니다.");
			ServerSocket serverSocket = new ServerSocket(port);
			System.out.println("[서버(" + port + ")]가 클라이언트 연결 요청을 수신 대기합니다.");

			while (true) {

				// #2. 여기까지 연결 수신 대기

				Socket socket = serverSocket.accept(); // 대표적인 block 메소드.
				InetAddress ia = socket.getInetAddress();
				System.out.println("[클라이언트(" + ia.getHostAddress() + ")] 연결해옴.");
				
				// #3. 생성된 소켓을 이용해서 데이터 송수신
				// 서버쪽 소켓까지 지금 데이터가 들어왔다. 
				InputStream is = socket.getInputStream(); //소켓까지 들어온 데이터를 읽기 위해 서버에 받는 빨대 꽂기.
//				int data= is.read();
				DataInputStream dis = new DataInputStream(is);
				
//				System.out.println("[클라이언트]로부터 수신한 데이터: " + data);
//				String clientMessage = dis.readUTF();
//				System.out.println("[클라이언트]로부터 수신한 데이터: " + clientMessage);
				
				// 접속 클라이언트에게 데이터 전송
				OutputStream os = socket.getOutputStream(); //보내는 빨대!
				DataOutputStream dos = new DataOutputStream(os);
//				os.write(data*2);
				//Echo. 받은거 그대로 돌려주기.
//				dos.writeUTF(clientMessage);
				
				while(true) {
					String clientMessage = dis.readUTF();
					System.out.println("[클라이언트]로부터 수신한 메시지: " + clientMessage);
					if(clientMessage.equalsIgnoreCase("q")) {
						break;
					}
					dos.writeUTF(clientMessage);
					
				}
				
				
//				os.close();
//				is.close();

				// #4. 연결 끊기(소켓 닫기)
				socket.close();
				System.out.println("[클라이언트]와 연결을 종료합니다.");
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
