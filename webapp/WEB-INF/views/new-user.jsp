<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>

<div class="container">
	<form action="/user.new" method="post">
		<fieldset class="form-group">
			<label>Name</label>
			<input class="form-control" type="text" name="name"/> <BR/>
		</fieldset>
		<fieldset class="form-group">
			<label>Password</label>
			<input class="form-control" name="password" type="password"/> <BR/>
		</fieldset>
		<input type="submit" value="Create" class="btn btn-success"/>
	</form>
</div>

<%@ include file="../common/footer.jspf" %>