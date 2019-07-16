<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Course Index</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<c:set var="prefix" value="${pageContext.request.contextPath}" />
</head>
<body>
	<a href="${prefix}/">Back</a>
	<br><br>
	<a href="${prefix}/course/saveCourse.jsp">Save</a>
	<a href="${prefix}/deleteCourseForm">Delete</a>
	<a href="${prefix}/course/findCourse.jsp">Find</a>
	<a href="${prefix}/showAllCourses">Show All</a>
</body>
</html>
