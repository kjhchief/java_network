package ezen.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 인터넷상의 URL에 해당하는 데이터 읽어오기
 * https://www.naver.com/index.html
 * @Author 김재훈
 * @Date 2023. 2. 6.
 */
public class URLExample {

	public static void main(String[] args) {
		String urlString = "https://www.naver.com/index.html";
		BufferedReader br = null;
		try {
			URL url = new URL(urlString);
//			// 실제 데이터 읽어오기
//			InputStream in = url.openStream();
//			Reader reader = new InputStreamReader(in); // 중간 브릿지 역할. 문자 스트림으로 변환.
//			BufferedReader br = new BufferedReader(reader); // 효율성, 라인별로 읽어들이는 메소드.
			br = new BufferedReader(new InputStreamReader(url.openStream()));
			int lineNumber= 1;
			String html = null;
			while ((html = br.readLine()) != null) {
				System.out.println(lineNumber++ +" : " + html);
			}
			
			
		} catch (MalformedURLException e) {
			System.out.println("URL 형식이 틀립니다.");
		} catch (IOException e) {
			System.out.println("데이터 읽어오는 중 오류가 발생하였습니다.");
		} finally {
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
