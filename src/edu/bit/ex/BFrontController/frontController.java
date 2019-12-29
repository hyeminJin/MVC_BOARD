package edu.bit.ex.BFrontController;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.bit.ex.Command.Command;
import edu.bit.ex.Command.contentCommand;
import edu.bit.ex.Command.deleteCommand;
import edu.bit.ex.Command.listCommand;
import edu.bit.ex.Command.modifyCommand;
import edu.bit.ex.Command.replyCommand;
import edu.bit.ex.Command.replyViewCommand;
import edu.bit.ex.Command.writeCommand;

/**
 * Servlet implementation class BFrontController
 */
@WebServlet("*.do")
public class frontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public frontController() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		actionDo(request, response);
	}
	
	protected void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("acionDo");
		request.setCharacterEncoding("EUC-KR");
		Command comm = null;
		String viewPage = null;
		
		String uri = request.getRequestURI();
		String path = request.getContextPath();
		String com = uri.substring(path.length());
		
		if(com.equals("/list.do")) {
			comm = new listCommand();
			comm.execute(request, response);
			viewPage = "list.jsp";
			
		}else if(com.equals("/write.do")) {
			comm = new writeCommand();
			comm.execute(request, response);
			viewPage = "list.do";
		}else if(com.equals("/content_view.do")) {
			comm = new contentCommand();
			comm.execute(request, response);
			viewPage = "content_view.jsp";
		}else if(com.equals("/delete.do")) {
			comm = new deleteCommand();
			comm.execute(request, response);
			viewPage = "list.do";
		}else if(com.equals("/modify.do")) {
			comm = new modifyCommand();
			comm.execute(request, response);
			viewPage = "list.do";
		}else if(com.equals("/reply_view.do")) {
			comm = new replyViewCommand();
			comm.execute(request, response);
			viewPage = "reply.jsp";
		}else if(com.equals("/reply.do")) {
			comm = new replyCommand();
			comm.execute(request, response);
			viewPage = "list.do";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	
	
	
	
	}





}
