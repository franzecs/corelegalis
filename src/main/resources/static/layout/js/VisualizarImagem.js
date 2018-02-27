// adiciona a imagem ao campo html img
function visualizarImg(idximg,idxfile) {
	var preview = document.querySelectorAll('img').item(idximg);
	var file = document.querySelector('input[type=file]').files[idxfile];
	var reader = new FileReader();

	reader.onloadend = function() {
		preview.src = reader.result;// carrega em base64 a img
	};

	if (file) {
		reader.readAsDataURL(file);
	} else {
		preview.src = "";
	}
}