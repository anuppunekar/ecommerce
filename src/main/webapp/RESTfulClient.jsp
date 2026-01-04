<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.BufferedReader, java.io.IOException,
java.io.InputStreamReader, java.net.MalformedURLException,
java.net.URL, java.net.URLConnection"%>
<%
try {
	URL twitter = new URL("http://scripting.com/rss.xml");
	URLConnection tc = twitter.openConnection();
	BufferedReader in = new BufferedReader(new InputStreamReader(tc.getInputStream()));
	String line;
	while ((line = in.readLine()) != null) {
		out.println(line);
	}
	in.close();
} catch (MalformedURLException e) {
	e.printStackTrace();
} catch (IOException e) {
	e.printStackTrace();
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>