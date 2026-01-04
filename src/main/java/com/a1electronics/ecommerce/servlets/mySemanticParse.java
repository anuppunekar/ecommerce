package com.a1electronics.ecommerce.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.net.URLEncoder;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;


/**
 * Servlet implementation class mySemanticParse
 */
//@WebServlet("/mySemanticParse")
public class mySemanticParse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String URI = "http://api.semantichacker.com/YOUR_API_KEY/concept?format=tagcloud";
	private static final String CONTENT_TYPE = "text/html";
	private static final String CHAR_ENCODING = "UTF-8";
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public mySemanticParse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		PostMethod postMethod = null;

		try {
			String entry = URLEncoder.encode(request.getParameter("entry"), CHAR_ENCODING);
			postMethod = new PostMethod(URI);
			postMethod.setRequestEntity(new StringRequestEntity(entry, CONTENT_TYPE, CHAR_ENCODING));
			HttpClient httpClient = new HttpClient();
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
	    		String semanticResponse = new
	    		String(postMethod.getResponseBody());
				// Set top cloud value
				String tmp1 = semanticResponse.substring(semanticResponse.indexOf("title=\"") + 7);
				String topCloud = tmp1.substring(0, tmp1.indexOf('(')).trim();
				request.getSession(true).setAttribute("TOP_CLOUD",topCloud);
				// Sent to out stream
				out.print(semanticResponse);
			} else {
				out.print("HTTP error with code: " + statusCode);
			}
		} catch (Exception e) {
			// Send any errors to the view
			out.print(e.getMessage());
		} finally {
			if (postMethod != null) {
				postMethod.releaseConnection();
			}
		}
	}
}
