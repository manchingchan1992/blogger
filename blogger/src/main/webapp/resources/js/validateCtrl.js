String.prototype.trim = function() {
	return this.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g,"");
}

function alertFocus(msg, eleId, onEleId) {
	alert(msg);
	document.getElementById(eleId).value = "";
	if (onEleId=="") {document.getElementById(eleId).focus();}
	else {document.getElementById(onEleId).focus();}
}

function alertClear(msg, eleId) {
	alert(msg);
	document.getElementById(eleId).value = "";
}

function clearField(eleId) {
	document.getElementById(eleId).value = "";
}

function valueUpCase(eleId) {
	document.getElementById(eleId).value = document.getElementById(eleId).value.toUpperCase();
}

function displayItem(eleId, yesno) {
	if (yesno=="no") {document.getElementById(eleId).style.display = 'none';}
	else {document.getElementById(eleId).style.display = '';}
}

function hkidFilter(eleId) {
	if (document.getElementById(eleId).value.trim().length>0) {
		var hkid8digit = document.getElementById(eleId).value;
		var sb = "";
		for (var i = 0; i < hkid8digit.length; i++) {
			if (((hkid8digit.charAt (i)).charCodeAt (0) >= ('0').charCodeAt (0) && (hkid8digit.charAt (i)).charCodeAt (0) <= ('9').charCodeAt (0)) || ((hkid8digit.charAt (i)).charCodeAt (0) >= ('a').charCodeAt (0) && (hkid8digit.charAt (i)).charCodeAt (0) <= ('z').charCodeAt (0)) || ((hkid8digit.charAt (i)).charCodeAt (0) >= ('A').charCodeAt (0) && (hkid8digit.charAt (i)).charCodeAt (0) <= ('Z').charCodeAt (0))) sb = sb + hkid8digit.charAt (i);
		}
		document.getElementById(eleId).value = sb;
		checkValidation(eleId);
	} else {return;}
}

function creditcardFilter(eleId) {
	if (document.getElementById(eleId).value.trim().length>0) {
		var creditCard16digit = document.getElementById(eleId).value;
		var sb = "";
		for (var i = 0; i < creditCard16digit.length; i++) {
			if (((creditCard16digit.charAt (i)).charCodeAt (0) >= ('0').charCodeAt (0) && (creditCard16digit.charAt (i)).charCodeAt (0) <= ('9').charCodeAt (0))) sb = sb + creditCard16digit.charAt (i);
		}
		document.getElementById(eleId).value = sb;
		checkValidation(eleId);
	} else {return;}
}

function isValidHKID(hkid8digit) {
	var sb = "";
	for (var i = 0; i < hkid8digit.length; i++) {
	if (((hkid8digit.charAt (i)).charCodeAt (0) >= ('0').charCodeAt (0) && (hkid8digit.charAt (i)).charCodeAt (0) <= ('9').charCodeAt (0)) || ((hkid8digit.charAt (i)).charCodeAt (0) >= ('a').charCodeAt (0) && (hkid8digit.charAt (i)).charCodeAt (0) <= ('z').charCodeAt (0)) || ((hkid8digit.charAt (i)).charCodeAt (0) >= ('A').charCodeAt (0) && (hkid8digit.charAt (i)).charCodeAt (0) <= ('Z').charCodeAt (0))) sb = sb + hkid8digit.charAt (i);
	}
	hkid8digit = sb;
	if (hkid8digit.length != 8) return false;
	hkid8digit = hkid8digit.toUpperCase ();
	var sum = (hkid8digit.charAt (0)).charCodeAt (0) - 64;
	if (sum < 1 || sum > 26) return false;
	sum *= 8;
	for (var i = 1; i <= 6; i++) {
	var digit = (hkid8digit.charAt (i)).charCodeAt (0) - 48;
	if (digit < 0 || digit > 9) return false;
	sum += digit * (8 - i);
	}
	sum = 11 - (sum % 11);
	var check = String.fromCharCode ((sum + 48));
	if (sum == 10) check = 'A';
	 else if (sum == 11) check = '0';
	return (check).charCodeAt (0) == (hkid8digit.charAt (7)).charCodeAt (0);
}

function isValidCreditCard(creditCard16digit) {
	var sb = "";
	for (var i = 0; i < creditCard16digit.length; i++) {
	if (((creditCard16digit.charAt (i)).charCodeAt (0) >= ('0').charCodeAt (0) && (creditCard16digit.charAt (i)).charCodeAt (0) <= ('9').charCodeAt (0))) sb = sb + creditCard16digit.charAt (i);
	}
	creditCard16digit = sb;
	if (creditCard16digit.length != 16) return false;
	var sum = 0;
	for (var i = 0; i < creditCard16digit.length; i++) {
	var digit = (creditCard16digit.charAt (i)).charCodeAt (0) - 48;
	if (i % 2 == 0) {
	digit *= 2;
	if (digit > 9) digit = 1 + (digit % 10);
	}sum += digit;
	}
	return (sum % 10) == 0;
}

function isMoney(money, effectiveDP) {
	if (money == null) money = "";
	money = money.trim ();
	if (money.length == 0) return true;
	var regExp = "(0|[1-9][0-9]*)";
	if (effectiveDP > 0) regExp = regExp + "(\\.\\d{1," + effectiveDP + "})?";
	regExp = "^" + regExp + "$";
	return money.match(regExp);
}
