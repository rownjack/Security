/**
 * 给URL加上时间戳
 **
 */

function covertURL(url) {
	// var timstamp = (new date).valueOf();

	var timstamp = new Date().getTime();

	if (url.indexOf("?") >= 0) {
		url = url + "&t=" + timstamp
	} else {

		url = url + "?t=" + timstamp
	}

	return url;
}






