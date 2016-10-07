<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.sql.*"%> 
<%@ page import="java.io.*" %>
<%@ page import="java.net.URLEncoder" %>


<%
    // 다운로드 될 파일 명
    String fullPath = request.getParameter("filename");
	System.out.println(fullPath + "<-- fullPath filedown.jsp");
    // 실제 파일명
    //String fileName = request.getParameter("ofilename");
     
    // 서버상의 파일 위치
   // String path = "D:/kangeunji/orderproject15/orderproject/WebContent/admin/admin_menu/uploadimage";
   // String path = "D:/leehyoungyeol/OracleWork15/OraJspJavaFileUp03/WebContent/admin/admin_menu/uploadimage";
	String root = request.getSession().getServletContext().getRealPath("/");
	String path = root + "boardupload";
	System.out.println(path + "<-- saveFolder");
   
   
   
    // 컨텐트타입을 다운로드로 변경, 안될 경우 다음과 같이 변경 해봅니다.
    // application/octet-stream, application
     
    
    String downName = null;
    String browser = request.getHeader("User-Agent");
    //파일 인코딩
    if(browser.contains("MSIE") || browser.contains("Trident") || browser.contains("Chrome")){             
        downName = URLEncoder.encode(fullPath,"UTF-8").replaceAll("\\+", "%20");
    } else {               
        downName = new String(fullPath.getBytes("UTF-8"), "ISO-8859-1");
    }
    response.setHeader("Content-Disposition","attachment;filename=\"" + downName+"\"");             
    response.setContentType("application/octer-stream");
    response.setHeader("Content-Transfer-Encoding", "binary;");

    
    System.out.println(downName + "<-- downName");
    //System.out.println((path+"/"+fullPath).getBytes("utf-8"));  
    
 
    // 파일명을 인코딩 합니다.
    //String filename2 = java.net.URLEncoder.encode(new String(fullPath.getBytes("iso-8859-1"), "UTF-8"), "UTF-8");
    //String ofilename2 = java.net.URLEncoder.encode(new String(fullPath.getBytes("iso-8859-1"), "UTF-8"), "UTF-8");
    
    //System.out.println(filename2 + "<-- filename2  filedown.jsp");
    
    // 다운로드될 파일명을 셋팅합니다.
    //response.setHeader("Content-Disposition","attachment; filename="+fullPath+";");
     
    
    
    
    // 파일을 읽고 쓸 스트림
    BufferedInputStream in = null;
    BufferedOutputStream os = null;
     
    // 파일 생성
    

    
   // File file = new File (new String((path+"/"+fullPath).getBytes("utf-8"), "UTF-8"));
     File file = new File (path+"/"+fullPath);
     
    // 이것은 반드시 해주어야 합니다.
    // 왜냐하면 jsp 기본객체중 하나인 out이 response에 대해서 이미 stream을 열고 있기 때문입니다. 
    out.clear();
    out = pageContext.pushBody();
     
    // 파일을 스트림으로 뿌려 봅니다.
    try {
        // 인풋 아웃풋 스트림 생성
        in = new BufferedInputStream(new FileInputStream(file));
        os = new BufferedOutputStream(response.getOutputStream());
         
        // 버퍼
        byte[] buf = new byte[(int)file.length()]; 
        int count = 0;
         
        // 신나게 돌면서 버퍼에 있는 것을 뿌립니다.
        while ((count = in.read(buf)) != -1) {
            os.write(buf,0,count);
        }
    }
    catch(Exception e) {
        // 에러나면 에러를 콘솔에 뿌립니다.
        e.printStackTrace();    
    }
    finally
    {
        // 열려있는 스트림을 필시 닫아줍니다.
        if(in != null) try{in.close();}catch(Exception e){}
        if(os != null) try{os.close();}catch(Exception e){}
    }
%>

