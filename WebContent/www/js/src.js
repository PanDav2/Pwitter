/** OBJECTS DECLARATIONS **/

/* Object Static Environnement */
	
state = {}; //new Object;
state.users = []; //new Array();
state.myProfil = {}; //TODO new myProfil
state.session;
state.currentUser = {}; //TODO new CurrentUser
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
	this.user_id = user.id;
	this.content = content;
	this.date = date;
	this.score = score; //undefined
}

Pwitt.prototype.getHtml = function()
{
	return "<div id=\"Pwitt_" + this.id + "\">\n"
		+ "<h2>" + this.user.firstName + " " + this.user.lastName + "<small> :: " + this.user.email + " :: " + formatDate(this.date) + "</small></h2>\n"
		+ "<p>" + this.content + "</p>\n"
		+ "</div>";
};

/* Object PwittSend */

function PwittSend(){
	
}
// TODO finir
PwittSend.prototype.ajax = function (){
	$.ajax({
		type:"GET",
		url:"http://li328.lip6.fr/CADENE_PANOU/PwittSend",
		data:"session="+state.session+"&content="+content,
		datatype:"json",
		success: PwittSend.onSuccess(response),
		error: function(error){
			throwError(error);
		}
	});
};


/* Object PwittsFind */

function PwittsFind(pwitts, user, date, words, doFriends)
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

	this.user_id = user.id;

	this.date = date;
	if(date == undefined){
		this.date = new Date();
	}

	state.pwittsFind = this;	
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
			value.user, value.date
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
	if(key == 'user_id'){
		return value;//new User(value.id, value.email, value.firstName, value.lastName, value.isFriend);
	}
	if(key == 'date'){
		return new Date(value);
	}
	return value;
};

PwittsFind.ajax = function()
{
	$.ajax({
		type:"GET",
		url:"http://li328.lip6.fr/CADENE_PANOU/PwittsFind",
		//data:"session="+state.session+"&content="+content,
		datatype:"json",
		success: PwittsFind.onSuccess(response),
		error: function(error){
			throwError(error);
		}
	});
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

/* Object CurrentUser */

function CurrentUser(){
	
}

/*
CurrentUser.change = function()
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
*/

/* Object MyProfil */

function MyProfil(session){
	this.session = session;
	this.id = null;
}

MyProfil.ajax = function (){
	$.ajax({
		type:"GET",
		url:"http://li328.lip6.fr/CADENE_PANOU/UserLogin",
		data:"session="+this.session,
		datatype:"json",
		success: MyProfil.onSuccess(response),
		error: function(error){
			throwError(error);
		}
	});
}

/*
CurrentUser.change = function()
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
*/

	
/** FUNCTIONS **/

//TODO améliorer
function throwError(error) {
	var box = "<div id=\"error\">["+error.code+"] "+error.msg+"</div>";
	$("form").prepend(box);
	return true;
}

function verifySession()
{
	console.log(getQueryString("session"));
}

function getQueryString(key, default_) {
    if (default_==null) default_="";
    key = key.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
    var regex = new RegExp("[\\?&]"+key+"=([^&#]*)");
    var qs = regex.exec(window.location.href);
    if(qs == null) return default_; else return qs[1];
}

function formatDate(date){
	diff = dateDiff(date, new Date());
	if(diff.day != 0)
		return diff.day + " day(s)";
	if(diff.hour != 0)
		return diff.hour + " hour(s)";
	if(diff.min != 0)
		return diff.min + " min(s)";
	return diff.sec + " sec(s)";
}

function dateDiff(date1, date2){
    var diff = {};                          // Initialisation du retour
    var tmp = date2.valueOf() - date1.valueOf();
    
    console.log(date2 + "-" + date1 + "=" + tmp);
 
    tmp = Math.floor(tmp/1000);             // Nombre de secondes entre les 2 dates
    diff.sec = tmp % 60;                    // Extraction du nombre de secondes
 
    tmp = Math.floor((tmp-diff.sec)/60);    // Nombre de minutes (partie entière)
    diff.min = tmp % 60;                    // Extraction du nombre de minutes
 
    tmp = Math.floor((tmp-diff.min)/60);    // Nombre d'heures (entières)
    diff.hour = tmp % 24;                   // Extraction du nombre d'heures
     
    tmp = Math.floor((tmp-diff.hour)/24);   // Nombre de jours restants
    diff.day = tmp;
     
    return diff;
}


