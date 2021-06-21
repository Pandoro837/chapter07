package echo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ServerThread extends Thread {
	
	private Socket socket;
	
	//어느 소켓에 접속할 지 알아야 하므로, 생성자에서 소켓을 받아 필드에 저장한다
	public ServerThread(Socket socket) {
		this.socket = socket;
	}
	
	//thread로 실행될 코드(독립적인 프로세서는 부모인 thread 클래스에 있다)
	@Override
	public void run() {
		//메세지 주고받기

		try {
//			메세지 받기용 스트림
//			소켓이 인풋 스트림의 주소 역할을 한다
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			
//			메세지 보내기용 스트림
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
			BufferedWriter bw = new BufferedWriter(osw);
			
			while(true) {
				//	메세지 받기
				String msg = br.readLine();
				
				//  종료 조건
				if(msg ==null) {
					System.out.println("[클라이언트 접속 종료.]");
					break;
				}
				System.out.println("받은 메세지: " + msg);

				//	메세지 보내기
				bw.write(msg);
				bw.newLine();
				bw.flush();
			}
			
		} catch (IOException e) {
			//	IOE - 입출력 주소 오류
			System.out.println("에러 발생");
		}
		
		
		
	}

	
}
