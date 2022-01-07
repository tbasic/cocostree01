package customer.controller.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.midi.Soundbank;

import customer.controller.Controller;
import customer.dao.MemberDao;
import customer.dao.NoticeDao;
import customer.vo.Member;
import customer.vo.Notice;

public class LoginProcController implements Controller{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception{
		System.out.println("=== < LoginProcController >===");
//		로그인 처리
		String uid=request.getParameter("id");
		String pwd=request.getParameter("password");
		
		MemberDao dao=new MemberDao();
		Member m=dao.getMember(uid);
		
		//System.out.println("m.pwd : "+m.getPwd());
		
		if(m==null) {//아이디 없음
			request.setAttribute("error", "아이디틀림");
			request.getRequestDispatcher("loginForm.jsp")
			.forward(request, response);
		}else if (!m.getPwd().equals(pwd)) {//비번틀림
			request.setAttribute("error", "비번틀림");
			request.getRequestDispatcher("loginForm.jsp")
			.forward(request, response);
			
		}else {//로그인 성공
			//아이디를 세션에 담기, notice.do진행
			request.getSession().setAttribute("uid",uid);
			response.sendRedirect("../customer/notice.do");
		}
		
//		request.getRequestDispatcher("loginForm.jsp")
//		.forward(request, response);
	}
}

