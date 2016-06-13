<%@ include file="/WEB-INF/views/include/setting.jsp"%>
<!-- page header -->
<jsp:include page="/WEB-INF/views/admin/header.jsp" flush="true">
	<jsp:param name="navStr" value="Add/Update Article" />
</jsp:include>
<jsp:include page="/WEB-INF/views/admin/message.jsp" flush="true" />
<spring:url value="/admin/article/save" var="articleActionUrl" />
<form:form class="form-horizontal" method="post"
	modelAttribute="articleForm" action="${articleActionUrl}">
	<form:hidden path="id" />
	<form:hidden path="createDate" />
	<form:hidden path="createUser" />
	<form:hidden path="updateDate" />
	<form:hidden path="updateUser" />
	
	<spring:bind path="title">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Title</label>
			<div class="col-sm-10">
				<form:input path="title" type="text" class="form-control" id="title"
					placeholder="Title" />
				<form:errors path="title" class="control-label" />
			</div>
		</div>
	</spring:bind>

	<spring:bind path="ctitle">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Chinese Title</label>
			<div class="col-sm-10">
				<form:input path="ctitle" type="text" class="form-control" id="ctitle"
					placeholder="Chinese Title" />
				<form:errors path="ctitle" class="control-label" />
			</div>
		</div>
	</spring:bind>

	<spring:bind path="categoryID">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Parent Category</label>
			<div class="col-sm-5">
				<form:select path="categoryID" class="form-control">
					<form:option value="" label="--- Select ---" />
					<form:options items="${categoryList}" />
				</form:select>
				<form:errors path="categoryID" class="control-label" />
			</div>
			<div class="col-sm-5"></div>
		</div>
	</spring:bind>

	<spring:bind path="enabled">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Enabled</label>
			<div class="col-sm-10">
				<div class="checkbox">
					<label>
						<form:checkbox path="enabled" id="enabled" />
					</label>
					<form:errors path="enabled" class="control-label" />
				</div>
			</div>
		</div>
	</spring:bind>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<c:choose>
				<c:when test="${articleForm['new']}">
					<button type="submit" class="btn-lg btn-primary pull-right">Add
					</button>
				</c:when>
				<c:otherwise>
					<button type="submit" class="btn-lg btn-primary pull-right">Update
					</button>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
</form:form>

<!-- End of Body -->
<jsp:include page="/WEB-INF/views/admin/footer.jsp" flush="true" />