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
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * Servlet implementation class myTwitterSearch
 */
//@WebServlet("/myTwitterSearch")
public class myTwitterSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String URI = "http://search.twitter.com/search.json?q=";
	private static final String CONTENT_TYPE = "text/javascript";
	private static final String CHAR_ENCODING = "UTF-8";	

	/**
     * @see HttpServlet#HttpServlet()
     */
    public myTwitterSearch() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType(CONTENT_TYPE);
		PrintWriter out = response.getWriter();
		GetMethod getMethod = null;

		try {
    		String topCloud = request.getSession()
    		.getAttribute("TOP_CLOUD").toString();
    		// Only search if we have top cloud
    		if ((topCloud != null) && !topCloud.isEmpty()) {
    			String q = URLEncoder.encode(topCloud, CHAR_ENCODING);
    			getMethod = new GetMethod(URI + q);
    			HttpClient httpClient = new HttpClient();
	    		int statusCode = httpClient.executeMethod(getMethod);
	    		if (statusCode == HttpStatus.SC_OK) {
		    		out.print(new String(getMethod
		    		.getResponseBody()));
	    		} else {
		    		out.print("HTTP error with code: " + statusCode);
	    		}
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
