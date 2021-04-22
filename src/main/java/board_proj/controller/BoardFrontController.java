package board_proj.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.action.Action;
import board_proj.dto.ActionForward;

@WebServlet(urlPatterns = {"*.do"},
			loadOnStartup = 1,
			initParams = {
					@WebInitParam(
							name="configFile", 
							value="/WEB-INF/commandAction.properties"
							)}
		) // 작업 지시만 하는 command servlet이라 생각해야됨
public class BoardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, Action> actionMap = new HashMap<String, Action>();
 
	
	// doGet doPost이전에 init 먼저 수행됨
	// config는 위 annotation에서 지정한 것이 넘어옴
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() >> config " + config.getInitParameter("configFile")); // debugging sysout
		String configFile = config.getInitParameter("configFile");
		try(InputStream is = config.getServletContext().getResourceAsStream(configFile)){
			Properties props = new Properties();
			props.load(is);
//			System.out.println(props); // debugging sysout
			for(Entry<Object, Object> entry : props.entrySet()) {
//				System.out.println("name >> " + entry.getKey() + ", " + "value >> " + entry.getValue()); // debugging sysout
				Class<?> cls = Class.forName((String)entry.getValue()); // JVM에서 class 이름을 통해 class를 로드함
				Action action = (Action) cls.newInstance(); // 찾아낸 클래스를 인스턴스화 해서 Action으로 타입 변환
				actionMap.put((String)entry.getKey(), action);
			}
			
//			for(Entry<String, Action> entry : actionMap.entrySet()) { debugging
//				System.out.println("name >> " + entry.getKey() + ", " + "value >> " + entry.getValue());
//			}
		} catch (IOException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * String requestURI = request.getRequestURI(); String contextPath =
		 * request.getContextPath(); System.out.println("requestURI >> " + requestURI +
		 * " -> " + "contextPath >> " + contextPath); // 확인용 출력문
		 */

	/*	try { */
			String command = request.getServletPath(); 
			System.out.println("command >> " + command);

			// 아래 두 개는 내가 만든 class와 interface
//			ActionForward forward = null; // 다형성을 이용 (같은 명령으로 다른 결과 접근할 수 있음)
//			Action action = null;
			
			Action action = actionMap.get(command); // >> 이거 처리하면서 객체 생성까지 위에서 끝냄
			ActionForward forward = action.execute(request, response);
			// 위 두 줄로 끝냄
			
			/*
			if (command.equals("/boardWriteForm.do")) {
				action = new BoardWriteFormAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/boardWritePro.do")) {
//			BOARD_NAME=aaa&BOARD_PASS=aaa&BOARD_SUBJECT=aaa&BOARD_CONTENT=aaa&BOARD_CONTENT=apple.jpg
				action = new BoardWriteProAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (command.equals("/boardList.do")) {
				action = new BoardListAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/boardDetail.do")) {
				action = new BoardDetailAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/boardReplyForm.do")) {
				action = new BoardReplyFormAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/boardDeleteForm.do")) {
				action = new BoardDeleteFormAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/boardDeletePro.do")) {
				// http://localhost:8080/board_proj/boardDeletePro.do?board_num=23
				action = new BoardDeleteProAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}

			} else if (command.equals("/fileDown.do")) {
				action = new FileDownloadAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/boardModifyForm.do")) {
				action = new BoardModifyFormAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/boardModifyPro.do")) {
				action = new BoardModifyProAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/boardReplyForm.do")) {
				action = new BoardReplyFormAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (command.equals("/boardReplyPro.do")) {
				action = new BoardReplyProAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			*/
			if (forward != null) {
				if (forward.isRedirect()) {
					response.sendRedirect(forward.getPath());
				} else { // redirect가 아니면 forward해라
					request.getRequestDispatcher(forward.getPath()).forward(request, response);
				}
			}

	/*	} catch (Exception e) {
			e.printStackTrace();
		} */

	}

}
