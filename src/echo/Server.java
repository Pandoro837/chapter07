package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException{

		ServerSocket serverSocket = new ServerSocket();
		//IP, 포트 번호	- new InetSocketAddress("IP", 포트번호) 로 입력 - 바인딩
		serverSocket.bind(new InetSocketAddress("192.168.0.95", 10001));	
		//IP는 cmd - > ipconfig를 실행하여 확인한다
		
		System.out.println("<서버 시작>");
		System.out.println("=========================================");
		System.out.println("[연결을 기다리고 있습니다.]==============");
		
		//반복 구간
		while(true) {
			Socket socket = serverSocket.accept();
			System.out.println("클라이언트가 연결되었습니다.");
			
			//thread 객체화, 실행
			Thread thread = new ServerThread(socket);	//어떤 소켓에 접속하는지 알려주어야 함
			thread.start();
			
			//탈출 조건 - 생략
		}
		/*
		System.out.println("=========================================");
		System.out.println("<서버 종료>");
		
		socket.close();
		serverSocket.close();
		*/
	}

}
