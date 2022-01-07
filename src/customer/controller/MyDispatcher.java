package customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import customer.controller.member.LoginController;
import customer.controller.member.LoginProcController;
import customer.controller.member.LogoutProcController;
import customer.controller.notice.DownloadController;
import customer.controller.notice.NoticeController;

public class MyDispatcher extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		System.out.println("============ < MyDispatcher In > ============");
		String uri=request.getRequestURI();
		String conPath=request.getContextPath();
		String com=uri.substring(conPath.length());
		
		System.out.println("uri: "+uri);
		System.out.println("conPath: "+conPath); // properties - Web Project Settings - context root 값 
		System.out.println("com: "+com); // 주소줄을 간결하게 표현하기 위해 conPath를 자른 것
		
		Controller controller=null;
		
		// NoticeDetailController controller1=new NoticeDetailController();
		// NoticeEditController controller2=new NoticeEditController();
		
		// 인터페이스 타입으로 통일시킴 (Controller 타입)
		try {
			if (com.equals("/customer/notice.do")){
				controller=new NoticeController();
			} else if (com.equals("/login/login.do")){
				controller=new LoginController();
			} else if (com.equals("/login/loginProc.do")){
				controller=new LoginProcController();
			} else if (com.equals("/login/logoutProc.do")){
				controller=new LogoutProcController();
			} else if (com.equals("/customer/download.do")){
				controller=new DownloadController();
			}
			
			controller.execute(request,response);
		} catch (Exception e) {
			
		}
	}
}
