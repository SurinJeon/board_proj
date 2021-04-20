package board_proj.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "encoding", value = "UTF-8")
		})
public class CharacterEncodingFilter implements Filter {
	private String encoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("init filter"); // servlet보다 먼저 호출되는지 확인용 콘솔창에 찍어봄
		encoding = fConfig.getInitParameter("encoding"); // 위에 설정한 initParams에서 설정한 그 value를 가져옴
		
		// 만약 encoding을 utf-8로 지정하는걸 까먹었을 때 아래 if절로 들어가서 설정되도록 만드는 문장
		if(encoding == null) {
			encoding = "UTF-8";
		}
	}
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);
//		response.setContentType("text/html; charset =" +  encoding); // 이제는 setContentType말고 setCharacterEncoding 써도 됨(이제 Servlet에서 작성하기 때문)
		chain.doFilter(request, response);
	}

}
