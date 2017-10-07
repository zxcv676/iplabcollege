

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * Servlet implementation class KeyIPLock
 */
@WebServlet("/KeyIPLock")
public class KeyIPLock extends GenericServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see GenericServlet#GenericServlet()
     */
    public KeyIPLock() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#service(ServletRequest request, ServletResponse response)
	 */
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/plain");
		PrintWriter out=response.getWriter();
		String userKey=request.getParameter("user_key");
		String key=getInitParameter("key");
		String host=request.getServerName();
		String HostIP=""; long result=0; int port=request.getServerPort(); if(key==null)
		{
		out.println("Invalid access");
		} try
		{
		InetAddress IP=InetAddress.getLocalHost();
		HostIP=IP.getHostAddress();
		String[] ipAddressInArray=HostIP.split("\\.");
		for(int i=3;i>=0;i--)
		{
		long ip=Long.parseLong(ipAddressInArray[3-i]);
		//left shifting 24,16,8,0 and bitwise OR
		//1.192<<24
		//1.168<<16
		//1.1<<8
		//1.2<<0
		result|=ip<<(i*8);
		//result contains numeric value of IP address
		//now add port number to this numeric value
		result=result+port;
		}
		}
		catch(UnknownHostException e)
		{
		out.println(e);
		}
		long k=Long.parseLong(userKey); out.println("Key submitted by User="+k); out.println("Key present as init parameter="+result); if(k==result)
		{
		out.println("Authentic access:request is handled!!!");
		} else
		{
		out.println("error:request is handled");
		}
	}

}
