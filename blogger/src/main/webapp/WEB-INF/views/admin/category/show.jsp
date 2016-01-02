<%@ include file="/WEB-INF/views/include/setting.jsp" %>
<!-- page header -->
<jsp:include page="/WEB-INF/views/admin/header.jsp" flush="true" >
   <jsp:param name="navStr"	value="Category Detail" />
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
	
	<div class="row">
		<label class="col-sm-2">ID</label>
		<div class="col-sm-10">${category.id}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Name</label>
		<div class="col-sm-10">${category.name}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Chinese Name</label>
		<div class="col-sm-10">${category.cname}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Parent Category</label>
		<div class="col-sm-10">${category.parentID}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Enabled</label>
		<div class="col-sm-10">${category.enabled}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Create Date</label>
		<div class="col-sm-10">${category.createDate}</div>
	</div>
	
	<div class="row">
		<label class="col-sm-2">Create User</label>
		<div class="col-sm-10">${category.createUser}</div>
	</div>
	<div class="row">
		<label class="col-sm-2">Update Date</label>
		<div class="col-sm-10">${category.updateDate}</div>
	</div>
	<div class="row">
		<label class="col-sm-2">Update User</label>
		<div class="col-sm-10">${category.updateUser}</div>
	</div>
	
<!-- End of Body -->
<jsp:include page="/WEB-INF/views/admin/footer.jsp" flush="true" />