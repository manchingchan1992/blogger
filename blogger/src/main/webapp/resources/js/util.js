function MsgConfirm(form) { 
	if (confirm("Are you sure to proceed?")){
		form.submit();
	}else{
	}
}

function MsgConfirmFlag() { 
	if (confirm("Are you sure to proceed?")){
		return true;
	}else{
		return false;
	}
}