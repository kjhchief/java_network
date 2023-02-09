package ezen.client;

import java.io.IOException;
import java.util.Scanner;

public class ezenTalk {

	public static void main(String[] args) {
		try {
			// 전화기 생성
			ChatClient chatClient = new ChatClient();
			// 서버 연결
			chatClient.connectServer();
			
			// 서버로부터 채팅 메세지 수신. 메인 스레드와 별도로 동작 해야함.
			chatClient.receiveMessage();
			
			// GUI로 변경
			Scanner scanner = new Scanner(System.in);
			while(true) {
				String inputMessage = null;
				System.out.print("서버에 전송할 메시지: ");
				inputMessage = scanner.nextLine();
				// 서버로 전송
				chatClient.sendMessage(inputMessage);
				
				if(inputMessage.equalsIgnoreCase("q")) {
					break;
				}
				
			}
			scanner.close();
			
			chatClient.disConnectServer();
			
		} catch (IOException e) {
			System.out.println("[ChatServer]와 연결할 수 없습니다.");
		}

	}

}
