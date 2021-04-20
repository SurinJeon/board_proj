package board_proj.action;

import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board_proj.dto.ActionForward;

public class FileDownloadAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset = UTF-8");
		// fileDown.do?downFile=home.png
		ActionForward forward = null;
		String fileName = request.getParameter("downFile");
		System.out.println("fileName >> " + fileName);
		
		String savePath = "/boardUpload";
		ServletContext context = request.getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		String sFilePath = sDownloadPath + "\\" + fileName;
		
		byte b[] = new byte[4096];
		FileInputStream in = new FileInputStream(sFilePath);
		String sMimeType = request.getServletContext().getMimeType(sFilePath);
		System.out.println("sMimeType >> " + sMimeType);
		
		if(sMimeType == null) {
			sMimeType = "application/octet-stream";
		}
		
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		
		ServletOutputStream out = response.getOutputStream();
		int numRead;
		
		while((numRead = in.read(b, 0, b.length)) != -1) {
			out.write(b, 0, numRead);
		}
		
		out.flush();
		out.close();
		in.close();
		
		
		return forward;
	}

}
