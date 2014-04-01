/* Object Static Environnement */
	
environnement = {}; //new Object;
environnement.users = []; //new Array();

/* Object User */

function User(id, login, contact)
{
	this.id = id;
	this.login = login;
	this.contact = false;
	if(contact != undefined){
		this.contact = contact;
	}

	if(environnement == undefined){
		environnement = {};
	}
	if(environnement.users == undefined){
		environnement.users = [];
	}
	environnement.users[id] = this;
}

User.prototype.modifieStatus = function()
{
	this.contact = !this.contact;
}

/* Object Commentaire */

function Commentaire(id, auteur, texte, date, score)
{
	this.id = id;
	this.auteur = auteur;
	this.texte = texte;
	this.date = date;
	this.score = score; //undefined
	
}

Commentaire.prototype.getHtml = function()
{
	return "<div id=\"Commentaire_" + this.id + "\">\n"
		+ "<h2>" + this.auteur.login + "<small> @" + this.auteur.login + " : " + this.date + "</small></h2>\n"
		+ "<p>" + this.texte + "</p>\n"
		+ "</div>";
}

/* Object RechercheCommentaires */

function RechercheCommentaires(resultats, recherche, contacts_only, auteur, date)
{
	this.resultats = resultats;
	
	this.recherche = recherche;
	if(recherche == undefined){
		this.recherche = "";
	}

	this.contacts_only = contacts_only;
	if(contacts_only == undefined){
		this.contacts_only = false;
	}

	this.auteur = auteur; //undefined par défaut

	this.date = date;
	if(date == undefined){
		this.date = new Date();
	}

	environnement.recherche = this;	
}

RechercheCommentaires.prototype.getHtml = function()
{
	var s = "<div class=\"maincol\">\n";
	for(var i=0; i<this.resultats.length; i++){
		s += this.resultats[i].getHtml()+"\n";
	}
	s += "</div>\n<div class=\"maincol_bottom\"></div";
	return s;
}

RechercheCommentaires.reviver = function(key,value)
{
	if(key == ''){
		return new RechercheCommentaires(
			value.resultats, value.recherche, value.contacts_only,
			value.auteur, value.date
		);
	}
	if(key == 'resultats'){
		var tab = [value.length];
		for(var i=0;i<value.length;i++){
			tab[i] = new Commentaire(
				value[i].id,
				value[i].auteur,
				value[i].texte,
				value[i].date,
				value[i].score
			);
		}	
		return tab;
	}
	if(key == 'auteur'){
		return new User(value.id, value.login, value.contact);
	}
	if(key == 'date'){
		return new Date(value);
	}
	return value;
}

RechercheCommentaires.traiterReponseJSON = function(json_text)
{
	//alert(json_text);
	var obj = JSON.parse(json_text,RechercheCommentaires.reviver);
	if(obj.erreur == undefined){
		//alert(obj.getHtml());
		$("#comments").prepend(obj.getHtml());
	}else{
		alert(obj.erreur);
	}
}


/* Objet Follower */

function Follower()
{
	
}

// TODO modifier
Follower.prototype.ajouter = function(id)
{
	$.ajax({
		type:"GET",
		url:"http://li328.lip6.fr/CADENE_PANOU/addComment",
		data:"key="+environnement.key+"&text="+text,
		datatype:"json",
		success: Follower.traiterReponseJSON(reponse,id),
		error: function(msg){alert(msg);}
	});
}

// TODO modifier
Follower.prototype.traiterAjouter = function(reponse,id)
{
	if((reponse.erreur != undefined) && (reponse.erreur != 0)){
		alert(reponse.erreur);
	}else{
		var user = environnement.user[id];
		user.modifierStatus();
		var comments = environnement.recherche.resultats;
		for(var i in comments){
			if(comments[i].auteur.id == id){
				$("#commentaire_"+comments[i].id).replacewith(comments[i].getHTML());
			}
		}
	}
}

Follower.prototype.supprimer = function()
{
	
}

	
/* Function EnvoiCommentaires */

function envoiCommentaires()
{
	var user1 = new User(1,"Jean",true);
	var user2 = new User(2,"Jeanne",false);
	var user3 = new User(3,"Roger",false);
	var com1 = new Commentaire(23,user2,"Texte texte texte",new Date(), 45);
	var com2 = new Commentaire(22,user1,"Texte texte texte",new Date(), 40);
	var tab = [com1,com2];
	var reco = new RechercheCommentaires(tab,"nimp", false, user3);
	return (JSON.stringify(reco));
}

/* Function SupFriend */

function unFollow(id){

}

/* Function throwError */

// TODO modifier
function throwError(msg){
	var box = "<div id=\"error\">" + msg + "</div>";
	$("form").prepend(box);
}

/* Function disconnect */

function disconnect(){
	// TODO .actif ?
	environnement.actif = undefined;
	// TODO ajax gererDeconnexion		
}


