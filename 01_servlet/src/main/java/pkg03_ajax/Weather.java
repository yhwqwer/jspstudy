
package pkg03_ajax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Weather extends HttpServlet {
  
  private static final long serialVersionUID = 1L;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
    String apiURL = "http://www.kma.go.kr/XML/weather/sfc_web_map.xml";
    URL url = URI.create(apiURL).toURL();
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    StringBuilder sb = new StringBuilder();
    String line = null;
    while((line = in.readLine()) != null) {
      sb.append(line);
    }
    String responseText = sb.toString();
    
    response.setContentType("application/xml; charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println(responseText);
    out.close();
    
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request, response);
  }

}
