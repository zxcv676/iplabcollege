import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentServlet3
 */
@WebServlet("/StudentServlet3")
public class StudentServlet3 extends HttpServlet {
	String SeatNum, Name;
	String ans1, ans2, ans3, ans4, ans5;
	int a1 = 0, a2 = 0, a3 = 0, a4 = 0, a5 = 0;
	Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		try
		{
			Class.forName("com.mysql.jdbc.Driver"); 
			String url = "jdbc:mysql://localhost:3306/vasanth";
			con = DriverManager.getConnection(url, "root", "");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		//Gathering parameters from HTML form
		SeatNum = request.getParameter("Seat_no");
		Name = request.getParameter("Name");
		ans1 =request.getParameter("group1");
		if(ans1.equals("True")) 
			a1=2;
		else
			a1=0;
		ans2 = request.getParameter("group2");
		if(ans2.equals("True"))
			a2=0;
		else
			a2=2;
		ans3 = request.getParameter("group3");
		if(ans3.equals("True"))
			a3=0;
		else
			a3=2;
		ans4 = request.getParameter("group4");
		if(ans4.equals("True"))
			a4=2;
		else
			a4=0;
		ans5 = request.getParameter("group5");
		if(ans5.equals("True"))
			a5=0;
		else
			a5=2;
		int Total=a1+a2+a3+a4+a5;
		//inserting values in the database 
		try {
			Statement stmt = con.createStatement();
			String query = "INSERT INTO StudentTable (" + "Seat_no,Name,Marks" + ") VALUES ('" +SeatNum + "', '" + Name + "', '"+Total+ "')"; 
			int result = 
					stmt.executeUpdate(query);
			stmt.close();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		//retrieving the records from database
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html>"); 
		out.println("<head>");
		out.println("</head>"); 
		out.println("<body bgcolor=pink>");
		out.println("<center>"); 
		out.println("<br><br>"); 
		out.println("<h2>Students Database</h2>"); 
		out.println("<table border=5>"); 
		try	{
			stmt = con.createStatement();
			String query = "SELECT * FROM StudentTable WHERE Name="+"'"+Name+"'"; rs = stmt.executeQuery(query); out.println("<th>"+"Seat_no"+"</th>");
			out.println("<th>"+"Name"+"</th>");
			out.println("<th>"+"Marks"+"</th>");
			while(rs.next())
			{
				out.println(" <tr>"); out.println(" <td>"+rs.getInt(1)+"</td>"); out.println(" <td> "+rs.getString(2)+" </td>"); out.println(" <td> "+rs.getString(3)+" </td>");
				out.println(" </tr>");
			}
			out.println("</table>");
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			try
			{
				if(rs!=null)
					rs.close();
				if(stmt!=null)
					stmt.close();
				if(con!=null)
					con.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			} 
			out.println("<h1>Thanks</h1>\n");
			out.println("</table>"); out.println("</center>"); 
			out.println("</body></html>");
		}
	}

}