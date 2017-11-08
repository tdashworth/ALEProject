package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Performance;

/**
 * Servlet implementation class AddPerformance
 */
@WebServlet("/AddPerformance")
public class AddPerformance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPerformance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Performance p = new Performance();
		p.setTitle(request.getParameter("title"));
		p.setType(request.getParameter("showType"));
		p.setLanguages(request.getParameter("languages"));
		p.setDuration(request.getParameter("duration"));
		p.setDescription(request.getParameter("description"));
		p.setPrice(Double.parseDouble(request.getParameter("price")));
		int performanceId = p.writeToDB();
		if (performanceId > 0) {
			request.setAttribute("performanceId", performanceId);
			request.getRequestDispatcher("addShowing.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("index.jsp").include(request, response);
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Creation of performance failed!');");
			out.println("location='index.jsp';");
			out.println("</script>");
		}
	}

}
