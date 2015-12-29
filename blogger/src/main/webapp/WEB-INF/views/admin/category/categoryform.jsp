<%@ include file="/WEB-INF/views/include/setting.jsp"%>
<!-- page header -->
<jsp:include page="/WEB-INF/views/admin/header.jsp" flush="true">
	<jsp:param name="navStr" value="Add/Update Category" />
</jsp:include>
<jsp:include page="/WEB-INF/views/admin/message.jsp" flush="true" />
<spring:url value="/admin/category/save" var="categoryActionUrl" />
<form:form class="form-horizontal" method="post"
	modelAttribute="categoryForm" action="${categoryActionUrl}">
	<form:hidden path="id" />

	<spring:bind path="name">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Name</label>
			<div class="col-sm-10">
				<form:input path="name" type="text" class="form-control" id="name"
					placeholder="Name" />
				<form:errors path="name" class="control-label" />
			</div>
		</div>
	</spring:bind>

	<spring:bind path="cname">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Chinese Name</label>
			<div class="col-sm-10">
				<form:input path="cname" type="text" class="form-control" id="cname"
					placeholder="Chinese Name" />
				<form:errors path="cname" class="control-label" />
			</div>
		</div>
	</spring:bind>

	<spring:bind path="parentID">
		<div class="form-group ${status.error ? 'has-error' : ''}">
			<label class="col-sm-2 control-label">Parent Category</label>
			<div class="col-sm-5">
				<form:select path="parentID" class="form-control">
					<form:option value="" label="--- Select ---" />
					<form:options items="${categoryList}" />
				</form:select>
				<form:errors path="parentID" class="control-label" />
			</div>
			<div class="col-sm-5"></div>
		</div>
	</spring:bind>

	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<c:choose>
				<c:when test="${categoryForm['new']}">
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