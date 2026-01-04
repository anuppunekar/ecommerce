package com.a1electronics.ecommerce.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.annotation.WebServlet;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * Servlet implementation class myYahooImageSearch
 */
//@WebServlet("/myYahooImageSearch")
public class myYahooImageSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	// Note that 'appid=YahooDemo' should be your own appid if you
	// want to create a real application
	private static final String URI = "http://search.yahooapis.com/ImageSearchService/V1/imageSearch?output=json&appid=YahooDemo&query=";
	private static final String CONTENT_TYPE = "text/javascript";
	private static final String CHAR_ENCODING = "UTF-8";
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public myYahooImageSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		GetMethod getMethod = null;
		try {
			String query = URLEncoder.encode(request.getParameter("query"), CHAR_ENCODING);
			getMethod = new GetMethod(URI + query);
			HttpClient httpClient = new HttpClient();
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode == HttpStatus.SC_OK) {
				out.print(new String(getMethod.getResponseBody()));
			} else {
				out.print("HTTP error with code: " + statusCode);
			}
		} catch (Exception e) {
			// Send any errors to the view
			out.print(e.getMessage());
		} finally {
			if (getMethod != null) {
				getMethod.releaseConnection();
			}
		}			
	}

}
