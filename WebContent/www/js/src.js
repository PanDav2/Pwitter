/** OBJECTS DECLARATIONS **/

/* Object Static Environnement */
	
state = {}; //new Object;
state.users = []; //new Array();
state.session = 0; //TODO session key
state.id = 1; //TODO my id
state.currentUser = {}; //TODO new User(...);
state.pwittsFind = {}; //TODO new PwittsFind(...);

/* Object User */

function User(id, email, firstName, lastName, isFriend)
{
	this.id = id;
	this.email = email;
	this.firstName = firstName;
	this.lastName = lastName;
	this.isFriend = false;
	if(isFriend != undefined){
		this.isFriend = isFriend;
	}

	if(state == undefined){
		state = {};
	}
	if(state.users == undefined){
		state.users = [];
	}
	state.users[id] = this;
}

User.prototype.changeFriendship = function()
{
	this.isFriend = !this.isFriend;
};

/* Object Pwitt */

function Pwitt(id, user, content, date, score)
{
	this.id = id;
	this.user = user;
	this.content = content;
	this.date = date;
	this.score = score; //undefined
}

Pwitt.prototype.getHtml = function()
{
	return "<div id=\"Pwitt_" + this.id + "\">\n"
		+ "<h2>" + this.user.firstName + this.user.lastName + "<small> :: " + this.user.mail + " :: " + this.date + "</small></h2>\n"
		+ "<p>" + this.content + "</p>\n"
		+ "</div>";
};

/* Object PwittsFind */

function PwittsFind(pwitts, author, date, words, doFriends)
{
	this.pwitts = pwitts;
	
	this.words = words;
	if(words == undefined){
		this.words = "";
	}

	this.doFriends = doFriends;
	if(doFriends == undefined){
		this.doFriends = false;
	}

	this.author = author; //undefined par défaut

	this.date = date;
	if(date == undefined){
		this.date = new Date();
	}

	state.pwitts = this;	
}

PwittsFind.prototype.getHtml = function()
{
	var s = "<div class=\"maincol\">\n";
	for(var i=0; i<this.pwitts.length; i++){
		s += this.pwitts[i].getHtml()+"\n";
	}
	s += "</div>\n<div class=\"maincol_bottom\"></div";
	return s;
};

PwittsFind.reviver = function(key,value)
{
	if(key == ''){
		return new PwittsFind(
			value.pwitts, value.words, value.doFriends,
			value.author, value.date
		);
	}
	if(key == 'pwitts'){
		var tab = [value.length];
		for(var i=0;i<value.length;i++){
			tab[i] = new Pwitt(
				value[i].id,
				value[i].user,
				value[i].content,
				value[i].date,
				value[i].score
			);
		}	
		return tab;
	}
	if(key == 'author'){
		return new User(value.id, value.email, value.firstName, value.lastName, value.isFriend);
	}
	if(key == 'date'){
		return new Date(value);
	}
	return value;
};

PwittsFind.onSuccess = function(response)
{
	var obj = JSON.parse(response,PwittsFind.reviver);
	if(obj.error != undefined){
		return throwError(obj.error);
	}
	
	$("#pwitts").prepend(obj.getHtml());
};

/* Object PwittAdd */

function PwittAdd() {
	
}

PwittAdd.prototype.ajax = function(content)
{
	$.ajax({
		type:"GET",
		url:"http://li328.lip6.fr/CADENE_PANOU/PwittAdd",
		data:"session="+state.session+"&content="+content,
		datatype:"json",
		success: PwittAdd.onSuccess(response),
		error: function(error){
			throwError(error);
		}
	});
};

PwittAdd.prototype.onSuccess = function(response)
{
	if(response.error != undefined){
		return throwError(error);
	}
	
	$("form").prepend(box);
};

/* Object FriendAdd */

function FriendAdd(){
	
}

FriendAdd.prototype.ajax = function(friend_id)
{
	$.ajax({
		type:"GET",
		url:"http://li328.lip6.fr/CADENE_PANOU/FriendAdd",
		data:"session="+state.session+"&friend_id="+friend_id,
		datatype:"json",
		success: FriendAdd.onSuccess(response,friend_id),
		error: function(error){
			throwError(error);
		}
	});
};

FriendAdd.prototype.onSuccess = function(response,friend_id)
{
	if(response.error != undefined){
		return throwError(error);
	}
		
	var user = state.users[id];
	user.modifyFriendship();
	var pwitts = state.pwittsFind.pwitts;
	for(var i in pwitts){
		// TODO
		if(pwitts[i].user.id == friend_id){
			$("#pwitt_"+pwitts[i].id).replacewith(pwitts[i].getHTML());
		}
	}
	
};


/* Object FriendRemove */

function FriendRemove(){
	
}

FriendRemove.prototype.ajax = function(friend_id)
{
	$.ajax({
		type:"GET",
		url:"http://li328.lip6.fr/CADENE_PANOU/FriendRemove",
		data:"session="+state.session+"&friend_id="+friend_id,
		datatype:"json",
		success: FriendRemove.onSuccess(response,friend_id),
		error: function(error){
			throwError(error);
		}
	});
};

// TODO
FriendRemove.prototype.onSuccess = function()
{
	if(response.error != undefined){
		return throwError(error);
	}
	var user = state.users[id];
	user.modifyFriendship();
	var pwitts = state.pwittsFind.pwitts;
	for(var i in pwitts){
		// TODO
		if(pwitts[i].user.id == friend_id){
			$("#pwitt_"+pwitts[i].id).replacewith(pwitts[i].getHTML());
		}
	}
};

/* Object UserLogout */

function UserLogout(){
	
}

UserLogout.prototype.ajax = function()
{
	$.ajax({
		type:"GET",
		url:"http://li328.lip6.fr/CADENE_PANOU/UserLogout",
		data:"session="+state.session,
		datatype:"json",
		success: UserLogout.onSuccess(response),
		error: function(error){
			throwError(error);
		}
	});
};


UserLogout.prototype.onSuccess = function(response){
	state.actif = undefined;
};

	
/* Function EnvoiCommentaires */

function envoiCommentaires()
{
	var user1 = new User(1,"Jean",true);
	var user2 = new User(2,"Jeanne",false);
	var user3 = new User(3,"Roger",false);
	var com1 = new Commentaire(23,user2,"Texte texte texte",new Date(), 45);
	var com2 = new Commentaire(22,user1,"Texte texte texte",new Date(), 40);
	var tab = [com1,com2];
	var reco = new PwittsFind(tab,"nimp", false, user3);
	return (JSON.stringify(reco));
}


/* Function throwError */

//TODO améliorer
function throwError(error) {
	var box = "<div id=\"error\">["+error.code+"] "+error.msg+"</div>";
	$("form").prepend(box);
	return true;
}






