<%@ include file="/WEB-INF/views/include/setting.jsp" %>
<!-- page header -->
<jsp:include page="/WEB-INF/views/admin/header.jsp" flush="true" >
   <jsp:param name="navStr"	value="Categories" />
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
					<th>Name</th>
					<th>Email</th>
					<th>framework</th>
					<th>Action</th>
				</tr>
			</thead>

			<c:forEach var="category" items="${categoryList}">
			    <tr>
					<td>
						${category.id}
					</td>
					<td>${category.name}</td>
					<td>${category.cname}</td>
					<td>
					  <spring:url value="/admin/category/${category.id}" var="categoryUrl" />
					  <spring:url value="/admin/category/${category.id}/delete" var="deleteUrl" /> 
					  <spring:url value="/admin/category/${category.id}/update" var="updateUrl" />
	
					  <button class="btn btn-info" 
	                                          onclick="location.href='${categoryUrl}'">Query</button>
					  <button class="btn btn-primary" 
	                                          onclick="location.href='${updateUrl}'">Update</button>
					  <button class="btn btn-danger" 
	                                          onclick="this.disabled=true;post('${deleteUrl}')">Delete</button>
	                </td>
			    </tr>
			</c:forEach>
		</table>

<!-- End of Body -->
<jsp:include page="/WEB-INF/views/admin/footer.jsp" flush="true" />