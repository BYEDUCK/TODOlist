<%@ include file="../common/header.jspf" %>


	
<nav class="navbar navbar-default">

		<a href="/" class="navbar-brand">Brand</a>

		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Home</a></li>
			<li><a href="/list-todo.do">Todos</a></li>
		</ul>
</nav>
<div class="container">
	<form action="/login.do" method="post">
		Name <input type="text" name="name"/> Password:<input type="password" name="password"/><input class="btn btn-success" type="submit" value="login"/>
	</form><BR/><BR/>
	<a class="btn btn-success" href="/user.new">NEW USER</a>
	<p><font color="red">${errorMessage}</font></p>
</div>
	
<%@ include file="../common/footer.jspf" %>