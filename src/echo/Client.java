package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws IOException {

		Socket socket = new Socket();
		
		System.out.println("<클라이언트 시작>");
		System.out.println("=========================================");
		
		
		System.out.println("[서버에 연결을 요청합니다]");
		//	해당 IP, 포트에 연결 요청
		socket.connect((new InetSocketAddress("3.36.134.188", 10001)));
		System.out.println("[서버에 연결되었습니다]");
		
//		cmd - > bin까지 경로를 찾은 후, java 패키지명 클래스명 입력하여 실행
//		ex) c:\JavaStudy\workspace\chapter06\bin>java echo.ex01.Client
		
		//	메세지 보내기용 스트림
		//	socket이 아웃풋 스트림의 주소 역할을 한다
		OutputStream os = socket.getOutputStream();
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		
		//	메세지 받기용 스트림
		InputStream is = socket.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		
		//	키보드 입력
		//	스캐너
//		Scanner sc = new Scanner(System.in);
		//	스트림을 사용한 스캐너
		InputStream in = System.in;	//스태틱 클래스 System의 스테틱 메소드 in = 키보드
		InputStreamReader sisr = new InputStreamReader(in);
		BufferedReader sbr = new BufferedReader(sisr);
		
		while(true) {
			//	메세지 입력
			String str = sbr.readLine(); // new String("안녕")
			
			//  종료 조건
			if("/q".equals(str)) {	//조건을 앞에 써주면 NullPointException을
									//어느정도 막아줄 수 있다
				System.out.println("[접속이 종료되었습니다.]");
				break;
			}

			//보내기
			bw.write("(최영교)" + str);
			bw.newLine();
			bw.flush();

			//	메세지 받기
			String reMsg = br.readLine();
			System.out.println("[Server: " + reMsg +" ]");
		}
		// 스트림을 사용한 프린트라인
		OutputStream out = System.out;
		OutputStreamWriter sosw = new OutputStreamWriter(out);
		BufferedWriter sbw = new BufferedWriter(sosw);
		
		System.out.println("=========================================");
		sbw.write("<클라이언트 종료>");
		sbw.newLine();
		sbw.flush();
//		System.out.println("<클라이언트 종료>");

		sbr.close();
//		sc.close();
		socket.close();
		
	}

}
