//http://www.grafikart.fr/tutoriels/jquery/valider-formulaire-jquery-57

$(function(){
	if($(#form-username).val())


})


function verif_formulaire_connexion(username,password) {
	if(login.length == 0){
		func_error("username obligatoire");
		return false;
	}
	// TODO
}

function func_error(msg){
	var box = "<div id=\"msg_err_connexion\">" + msg + "</div>";
	$("#form-connexion").prepend(box);
	//TODO changer peut être la positier du box
}



