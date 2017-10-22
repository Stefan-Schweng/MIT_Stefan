package examples.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.UserTransaction;
import javax.servlet.annotation.WebServlet;


@WebServlet(name="ClientServlet", 
            urlPatterns="/ClientServlet")
public class ClientServlet extends HttpServlet {

    private final String TITLE = 
        "Chapter 8: Query Language";

    @PersistenceUnit(unitName="iiii_chapter8_jpqlExamplesPU")
    private EntityManagerFactory emf;
    
    @Resource
    private UserTransaction tx;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        printHtmlHeader(out);

        
        // check the requested query type and execute
        String query = request.getParameter("query");
        if (query.equals("dynamic")) {
            String queryString = request.getParameter("queryString");
            executeAndPrintQuery(queryString, out);
        } 
       
        
        printHtmlFooter(out);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }    

    private void executeAndPrintQuery(String queryString, PrintWriter out) {
        EntityManager em = emf.createEntityManager();
        try {
            Query query = em.createQuery(queryString);
            printQueryResult(queryString, query.getResultList(), out);
        } finally {
            em.close();
        }
    }

    private void executeBulkQuery(String queryString, PrintWriter out) {
        EntityManager em = emf.createEntityManager();
        try {
            tx.begin();
            em.joinTransaction();
            em.createQuery(queryString).executeUpdate();
            tx.commit();
            out.println("<b>JP QL: </b>" + queryString + " </br>Done.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }
    
    
    private void printQueryResult(String queryString, List result, PrintWriter out) {
        out.println("<table><tbody>");
        out.println("<tr><td><b>JP QL: </b>" + queryString + "</td></tr>");
        out.println("<tr><td><b>Result:</b></td></tr>");
        if (result.isEmpty()) {
            out.println("<tr><td>No results Found</td></tr>");
        } else {
            for (Object o : result) {
                out.println("<tr><td>" + resultAsString(o) + "</td></tr>");
            }
        }
        out.println("</tbody></table>");
    }

    
    private String resultAsString(Object o) {
        if (o instanceof Object[]) {
            return Arrays.asList((Object[])o).toString();
        } else {
            return String.valueOf(o);
        }
    }
    
    
    private void printHtmlHeader(PrintWriter out) throws IOException {
        out.println("<body>");
        out.println("<html>");
        out.println("<head><title>" + TITLE + "</title></head>");
    }
    
    
    private void printHtmlFooter(PrintWriter out) throws IOException {
        out.println("<hr/>");
        out.println("<a href=\"index.html\">Back</a>");
        out.println("</html>");
        out.println("</body>");
        out.close();
    }
}
