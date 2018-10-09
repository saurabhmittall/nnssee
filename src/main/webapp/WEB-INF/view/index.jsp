<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<body>
	<div>
		<div>
			<h1>Spring Boot JSP Example</h1>
			<h2>Hello ${message}</h2>
			<table border="1">
				<tr>
					<td>Last DownLoaded File Date:${date}</td>
					<td><a href="downloadDateRange?day1=${date1}&day2=${date2}"  target="blank">DownLoad</a></td>
				</tr>
				<tr>
					<td>Last DB entry Date:</td>
					<td><a href="unZip" target="blank">DB</a></td>
				</tr>
				<tr>
					<td>Last Analytics Date:</td>
					<td><a href="calculate" target="blank">analytics</a></td>
				</tr>
			</table>
			Click on this <strong><a href="next">link</a></strong> to visit
			report page.
		</div>
	</div>
</body>
</html>