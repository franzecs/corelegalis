$(document).ready(function() {
	changePageAndSize();
	changeOrder()
});

function changePageAndSize(url) {
	$('#pageSizeSelect').change(function(evt) {
			
		window.location.replace(url + "?pageSize=" + this.value + "&page=1");
	});
}
