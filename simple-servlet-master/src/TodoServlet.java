import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TodoServlet extends HttpServlet {

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    // Establish print writer to formulate response
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    
    List<TodoItem> todoList = new ArrayList<TodoItem>();
    TodoList app = null;
    
    // Establish connection to database and parse request parameters
    try {
      app = new TodoList("test", "wikash", "mysql", "localhost", 3306, "web");
      String newitem = request.getParameter("additem");
      String deleteitem = request.getParameter("deleteitem");
      if (newitem != null) {
        app.addItem(newitem);
        response.sendRedirect(request.getRequestURI());
      }
      if (deleteitem != null) {
        app.deleteItem(Integer.parseInt(deleteitem));
        response.sendRedirect(request.getRequestURI());
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
      out.println("</p>SQL exception");
    }
    catch (ClassNotFoundException e) {
      e.printStackTrace();
      out.println("</p>Class not found exception");
    }
    catch (NumberFormatException e) {
      e.printStackTrace();
      out.println("</p>Cannot delete, invalid ID");
    }
    
    // Retrieve all items from database
    try {
      todoList = app.getAllItems();
    }
    catch (SQLException e) {
      e.printStackTrace();
      out.println("</p>SQL exception");
    }

    // Print out HTML response
    out.println("<html>");
    out.println("<head>");
    out.println("<title>Wie is waar op Ibiza?</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Wie is waar op Ibiza?</h1>");

    // Create form for adding new items
    out.println("<form action='" + request.getRequestURI() + "' method=post>");
    out.println("<input type=text size=40 name=additem id='input'>  ");
    out.println("<input type=submit id='submit' value='Add new item'>");
    out.println("</form>");

    // Create form for deleting an item by ID
    //out.println("<form action='" + request.getRequestURI() + "' method=post>");
    //out.println("<input type=text size=10 name=deleteitem>  ");
    //out.println("<input type=submit value='Delete item by ID'>");
    //out.println("</form>");

    out.println("<table border='1' cellpadding='5'>");
    out.println("<tr>");
    out.println("<th>Item ID</th>");
    out.println("<th>Item Description</th>");
    out.println("</tr>");

    // Add each item into HTML table
    for (TodoItem item : todoList) {
      out.println("<tr>");
      out.println("<td>" + item.getId() + "</td>");
      out.println("<td>" + item.getItem() + "</td>");
      out.println("</td>");
      out.println("</tr>");
    }
    out.println("</table><p>");



    out.println("</body>");
    out.println("</html>");
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request, response);
  }

}
