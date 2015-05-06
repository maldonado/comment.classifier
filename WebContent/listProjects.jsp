<%@page import="java.util.ArrayList"%>
<%@page import="ca.com.evermal.util.ConnectionFactory"%>
<%@page import="ca.com.evermal.dao.ProjectDao"%>


<html>
  <body>
    <table>
      <%
      ProjectDao dao = new ProjectDao(new ConnectionFactory().getConnection());
      ArrayList<String> projects = dao.getProjects();
      
      for (String project : projects ) {
      %>
        <tr>
          <td><%=project%></td> 
        </tr>
      <%
      }
      %>
    </table>
  </body>
</html>