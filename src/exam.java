
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class exam
 */
@WebServlet("/exam")
public class exam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public exam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String temp;
		String[] qanswer = new String[5];
		for(int i=0; i<5; i++) {
			temp = Integer.toString(i);
			qanswer[i] = request.getParameter(temp);
//			out.println("Your answer is " + qanswer[i] + );
		}
/*
		String qanswer;
		String b = "0";
		qanswer = request.getParameter(b);
		out.println(qanswer);
		
*/		
//		String qanswer[i] = "testString";
//		char[] charArray = qanswer[0].toCharArray();
//		out.println(charArray[0]);
		
//		Character[] charObjectArray = ArrayUtils.toObject(charArray);
		
//		char anschar[i] = 
//		String answers[] = {"a","c","b","a","b"};
		char[] answers = {'a','c','b','a','b'};
		
//		if(charArray[0] == answers[0]) out.println("success");
		int count = 0;
		for(int i=0; i<5; i++) {
			char[] charArray = qanswer[i].toCharArray();
//			out.println(charArray[0]);
			if(charArray[0] == answers[i]) {
				
				count++;
			}
//			out.println(qanswer[i] + " " + answers[i]);
		}
		out.println("You answered " + count + " questions correctly");
		
	}

}
