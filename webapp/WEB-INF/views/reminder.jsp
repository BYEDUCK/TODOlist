<%@ include file="../common/header.jspf"%>
<%@ include file="../common/navigation.jspf"%>

<div class="container">

	<form action="/remind.do" method="post">
		<fieldset class="form-group">
			<label>Date</label> <input class="form-control" name="date"
				type="date" />
		</fieldset>
		<fieldset class="form-group">
			<label>Hour</label> <input class="form-control" name="hour"
				type="time" />
		</fieldset>
		<input type="hidden" name="todo_id" value="${todo_id}" /> <input
			type="submit" value="Add" class="btn btn-success" />
	</form>
	<p><font color="red">${errorMessage}</font></p>
</div>

<%@ include file="../common/footer.jspf"%>