/**
 * DHTML date validation script for yyyy-mm-dd
 */
// Declaring valid date character, minimum year and maximum year
var dtCh= "-";
var minYear=1900;
var maxYear=2100;

function isInteger(s){
	var i;
    for (i = 0; i < s.length; i++){   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}

function stripCharsInBag(s, bag){
	var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++){   
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function daysInFebruary (year){
	// February has 29 days in any year evenly divisible by four,
    // EXCEPT for centurial years which are not also divisible by 400.
    return (((year % 4 == 0) && ( (!(year % 100 == 0)) || (year % 400 == 0))) ? 29 : 28 );
}

function DaysArray(n) {
	for (var i = 1; i <= n; i++) {
		this[i] = 31
		if (i==4 || i==6 || i==9 || i==11) {this[i] = 30}
		if (i==2) {this[i] = 29}
   } 
   return this
}

function isDate(dtStr){
	var daysInMonth = DaysArray(12)
	var pos1=dtStr.indexOf(dtCh)
	var pos2=dtStr.indexOf(dtCh,pos1+1)
	var strYear=dtStr.substring(0,pos1)
	var strMonth=dtStr.substring(pos1+1,pos2)
	var strDay=dtStr.substring(pos2+1)
	strYr=strYear
	if (strDay.charAt(0)=="0" && strDay.length>1) strDay=strDay.substring(1)
	if (strMonth.charAt(0)=="0" && strMonth.length>1) strMonth=strMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strYr.charAt(0)=="0" && strYr.length>1) strYr=strYr.substring(1)
	}
	month=parseInt(strMonth)
	day=parseInt(strDay)
	year=parseInt(strYr)
	if (pos1==-1 || pos2==-1){
		/*alert("The date format should be : yyyy/mm/dd")*/
		return false
	}
	if (strMonth.length<1 || month<1 || month>12){
		/*alert("Please enter a valid month")*/
		return false
	}
	if (strDay.length<1 || day<1 || day>31 || (month==2 && day>daysInFebruary(year)) || day > daysInMonth[month]){
		/*alert("Please enter a valid day")*/
		return false
	}
	if (strYear.length != 4 || year==0 || year<minYear || year>maxYear){
		/*alert("Please enter a valid 4 digit year between "+minYear+" and "+maxYear)*/
		return false
	}
	if (dtStr.indexOf(dtCh,pos2+1)!=-1 || isInteger(stripCharsInBag(dtStr, dtCh))==false){
		/*alert("Please enter a valid date")*/
		return false
	}
	return true
}

function isDateBefore(smallDate, bigDate) {
	var pos1=smallDate.indexOf(dtCh)
	var pos2=smallDate.indexOf(dtCh,pos1+1)
	var strsYear=smallDate.substring(0,pos1)
	var strsMonth=smallDate.substring(pos1+1,pos2)
	var strsDay=smallDate.substring(pos2+1)
	if (strsDay.charAt(0)=="0" && strsDay.length>1) strsDay=strsDay.substring(1)
	if (strsMonth.charAt(0)=="0" && strsMonth.length>1) strsMonth=strsMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strsYear.charAt(0)=="0" && strsYear.length>1) strsYear=strsYear.substring(1)
	}
	smonth=parseInt(strsMonth)
	sday=parseInt(strsDay)
	syear=parseInt(strsYear)
	
	var pos3=bigDate.indexOf(dtCh)
	var pos4=bigDate.indexOf(dtCh,pos3+1)
	var strbYear=bigDate.substring(0,pos3)
	var strbMonth=bigDate.substring(pos3+1,pos4)
	var strbDay=bigDate.substring(pos4+1)
	if (strbDay.charAt(0)=="0" && strbDay.length>1) strbDay=strbDay.substring(1)
	if (strbMonth.charAt(0)=="0" && strbMonth.length>1) strbMonth=strbMonth.substring(1)
	for (var i = 1; i <= 3; i++) {
		if (strbYear.charAt(0)=="0" && strbYear.length>1) strbYear=strbYear.substring(1)
	}
	bmonth=parseInt(strbMonth)
	bday=parseInt(strbDay)
	byear=parseInt(strbYear)
	
	if (byear>syear) {return true}
	if (byear==syear) {
		if (bmonth>smonth) {return true}
		if (bmonth==smonth) {
			if (bday>sday) {return true}
		}
	}
	return false
}

function getNow() {
	var d = new Date();
	var curr_year = d.getFullYear();
	var curr_month = d.getMonth() + 1;
	var curr_date = d.getDate();
	if (curr_month<10) curr_month = "0" + curr_month;
	if (curr_date<10) curr_date = "0" + curr_date;
	return (curr_year+"-"+curr_month+"-"+curr_date);
}

function getMonthLastDate(yyyy, mm) {
	var mmInt = parseInt(mm);
	var yyyyInt = parseInt(yyyy);
	if (yyyyInt<100) yyyyInt = yyyyInt + 2000;
	if (mmInt>11) {
		yyyyInt = yyyyInt + 1;
		mmInt = 0;
	}
	var d = new Date(yyyyInt, mmInt, 1, 0, 0, 0, 0);
	d.setDate(d.getDate()-1);
	var target_year = d.getFullYear();
	var target_month = d.getMonth() + 1;
	var target_date = d.getDate();
	if (target_month<10) target_month = "0" + target_month;
	if (target_date<10) target_date = "0" + target_date;
	return (target_year+"-"+target_month+"-"+target_date);
}

function shiftYear(aDate, shiftBy) {
	var pos1=aDate.indexOf(dtCh)
	var pos2=aDate.indexOf(dtCh,pos1+1)
	var strsYear=aDate.substring(0,pos1)
	var strsMonth=aDate.substring(pos1+1,pos2)
	var strsDay=aDate.substring(pos2+1)
	for (var i = 1; i <= 3; i++) {
		if (strsYear.charAt(0)=="0" && strsYear.length>1) strsYear=strsYear.substring(1)
	}
	syear=parseInt(strsYear)
	sftyr=parseInt(shiftBy)
	syear=syear+sftyr
	return syear+"-"+strsMonth+"-"+strsDay
}