<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

<div class="container">
	<form action="/add-todo.do" method="post">
		<fieldset class="form-group">
			<label>Description</label>
			<input class="form-control" type="text" name="todo"/> <BR/>
		</fieldset>
		<fieldset class="form-group">
			<label>Category</label>
			<input class="form-control" name="category" type="text"/> <BR/>
		</fieldset>
		<input type="submit" value="Add" class="btn btn-success"/>
	</form>
</div>

<%@ include file="../common/footer.jspf" %>