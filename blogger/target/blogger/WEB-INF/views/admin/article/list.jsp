<%@ include file="/WEB-INF/views/include/setting.jsp" %>
<!-- page header -->
<jsp:include page="/WEB-INF/views/admin/header.jsp" flush="true" >
   <jsp:param name="navStr"	value="Articles" />
</jsp:include>
<jsp:include page="/WEB-INF/views/admin/message.jsp" flush="true"/>
<!-- start of Body -->
		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" 
                                aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>#Category ID</th>
					<th>Title</th>
				</tr>
			</thead>

			<c:forEach var="article" items="${articleList}">
			    <tr>
					<td>
						${article.id}
					</td>
					<td>${article.categoryID}</td>
					<td>${article.title}</td>
					<td>${article.ctitle}</td>
					<td>
					  <spring:url value="/admin/article/${article.id}" var="articleUrl" />
					  <spring:url value="/admin/article/${article.id}/delete" var="deleteUrl" /> 
					  <spring:url value="/admin/article/${article.id}/update" var="updateUrl" />
					  <button class="btn btn-info" 
	                                          onclick="location.href='${articleUrl}'">Query</button>
					  <button class="btn btn-primary" 
	                                          onclick="location.href='${updateUrl}'">Update</button>
					  <button class="btn btn-danger" 
	                                          onclick="this.disabled=true;location.href='${deleteUrl}'">Delete</button>
	                </td>
			    </tr>
			</c:forEach>
		</table>
		<div>
			<spring:url value="/admin/article/add" var="urlAddPost" />
			<a href="${urlAddPost}">Add Post</a>
		</div>
<!-- End of Body -->
<jsp:include page="/WEB-INF/views/admin/footer.jsp" flush="true" />