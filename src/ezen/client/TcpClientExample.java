package ezen.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * TCP/IP 기반의 자바 클라이언트
 * 
 * @Author 김재훈
 * @Date 2023. 2. 6.
 */
public class TcpClientExample {

	public static void main(String[] args) {
		String serverIp = "192.168.0.94";
		int serverPort = 2023;
//		String serverIp = "127.0.0.1"; //자기 컴퓨터 의미하는 가상 아이피
//		String serverIp = "localhost"; // 자기 컴퓨터 의미. 셋 다 같음.
		String ssamIp = "192.168.0.22";

		// #1. TCP 서버 연결
		try {
			Socket socket = new Socket(ssamIp, serverPort);
			System.out.println("[서버(" + ssamIp + ", " + serverPort + ")]와 연결되었습니다.");
			// #2. 생성된 소켓을 이용해서 데이터 송수신
//			byte data = 13;
//			String message = "안녕하세요 서버!!!";
			
			
			// Socket과의 바이트 출력 스트림 생성
			OutputStream os = socket.getOutputStream();// 소켓한테 출력 스트림 하나 달라고 요청함. 지나다닐 빨대.
			// Socket에 써주는 거지만 OS가 지원하는 TCP/IP 계층에 의해 서버로 전송된다.
//			os.write(data);
			
			DataOutputStream dos = new DataOutputStream(os);
//			
			
			InputStream in = socket.getInputStream();
//			int result = in.read(); //빨대로 받은거 읽기.
//			System.out.println("[서버]로부터 받은 결과: " + result);
			
			DataInputStream dis = new DataInputStream(in);
//			String serverMessage = dis.readUTF();
//			System.out.println("[서버]로부터 받은 메세지: " + serverMessage);
			
			Scanner scanner = new Scanner(System.in);
			while(true) {
				String inputMessage = null;
				
				System.out.print("서버에 전송할 메세지: ");
				inputMessage = scanner.nextLine();
				
				// 서버로 전송
				dos.writeUTF(inputMessage);
				if(inputMessage.equalsIgnoreCase("q")) {
					break;
				}
				String serverMessage = dis.readUTF();
				System.out.println("[서버]로부터 받은 메세지: " + serverMessage);
			}
			
//			in.close();
//			os.close();

			// #3. 연결 끊기(소켓 닫기)
			socket.close(); //소켓 닫으면 스트림도 다 닫힌다.
			System.out.println("[서버]와 연결 종료...");
		} catch (IOException e) {
			System.out.println("서버를 찾을 수 없습니다.");
		}

	}

}
