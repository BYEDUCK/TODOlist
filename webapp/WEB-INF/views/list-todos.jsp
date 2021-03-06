<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div class="container">
	<H1>Welcome ${name}</H1>
	<H3>Your Undone Todos are:</H3>
	<table class="table table-striped">
		<caption></caption>
		<thead>
			<th>Description</th>
			<th>Category</th>
			<th>Remind date</th>
		</thead>
		<tbody>
			<c:forEach items="${todos_undone}" var="todo_ud">
				<tr>
					<td>${todo_ud.name}</td>
					<td>${todo_ud.category}</td>
					<td>${todo_ud.remindDate}</td>
					<td><a class="btn btn-danger"
						href="\delete-todo.do?todo_id=${todo_ud.id}">DELETE</a></td>
					<td><a class="btn btn-success"
						href="\done-todo.do?todo_id=${todo_ud.id}">DONE</a></td>
					<c:choose>
						<c:when test="${todo_ud.remindDate=='--'}">
							<td><a class="btn btn-warning"
								href="\remind.do?todo_id=${todo_ud.id}">SET REMINDER</a></td>
						</c:when>
						<c:otherwise>
							<td><a class="btn btn-warning"
								href="\remove_remind.do?todo_id=${todo_ud.id}">DELETE
									REMINDER</a></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<H3>Your Done Todos are:</H3>
	<table class="table table-striped">
		<caption></caption>
		<thead>
			<th>Description</th>
			<th>Category</th>
		</thead>
		<tbody>
			<c:forEach items="${todos_done}" var="todo_d">
				<tr>
					<td>${todo_d.name}</td>
					<td>${todo_d.category}</td>
					<td><a class="btn btn-danger"
						href="\undone-todo.do?todo_id=${todo_d.id}">UNDONE</a></td>
					<td><a class="btn btn-success"
						href="\delete-todo.do?todo_id=${todo_d.id}">DELETE</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<font color="red">${errorMessage}</font>
	</p>
	<a class="btn btn-success" href="\add-todo.do">ADD</a>
</div>

<%@ include file="../common/footer.jspf"%>