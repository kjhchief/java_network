package ezen.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressExample {

	public static void main(String[] args) throws UnknownHostException {
		// 내 컴퓨터의 로컬 아이피 정보 얻기
		InetAddress ia = InetAddress.getLocalHost();
		String ip = ia.getHostAddress();
		System.out.println(ip);
		
		String domainName = "www.google.com";
		// DNS통신을 통해 도메인 이름 -> IP로 변환받음
		InetAddress serverIp= InetAddress.getByName(domainName);
		System.out.println(serverIp.getHostAddress());
		System.out.println("-----------");
		// 멀티서버로 구성된 경우
		InetAddress[] ias= InetAddress.getAllByName(domainName);
		for (InetAddress inetAddress : ias) {
			System.out.println(inetAddress.getHostAddress());
			System.out.println(inetAddress);
		}
		
		
		
		
	}

}
