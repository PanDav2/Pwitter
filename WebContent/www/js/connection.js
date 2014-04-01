$(function() {
	
	/* config */
	
	var form_login = "#form-login";
	var form_register = "#form-register";
	var firstname = "#firstname";
	var lastname = "#lastname";
	var email = "#email";
	var password = "#password";
	var error = "#error";
	var form_login_email = form_login + " > " + email;
	var form_login_password = form_login + " > " + password;
	var form_register_firstname = form_register + " > " + firstname;
	var form_register_lastname = form_register + " > " + lastname;
	var form_register_email = form_register + " > " + email;
	var form_register_password = form_register + " > " + password;
	
	/* event on form login */
	$(form_login_email).keyup(function(){
		verifyemail(form_login_email);
	});
	$(form_login_password).keyup(function(){
		verifyPassword(form_login_password);
	});
	$(form_login).submit(function(){
		if(canLogin(form_login_email,form_login_password)){
			ajaxLogin(form_login_email,form_login_password,error);
		}
		return false;
	});
	
	
	/* event on form register */
	$(form_register).submit(function(){
		if(canRegister(
				form_register_firstname,form_register_lastname,
				form_register_email,form_register_password
		)){
			ajaxRegister(
				form_register_firstname,form_register_lastname,
				form_register_email,form_register_password,error
			);
		}
		return false;
	});


});

function ajaxRegister(firstname,lastname,email,password,error){
	firstnameVal = $(firstname).val();
	lastnameVal = $(lastname).val();
	emailVal = $(email).val();
	passwordVal = $(password).val();
	url = "../user/register?firstname="+firstnameVal+"&lastname="+lastnameVal+"&email="+emailVal+"&password="+passwordVal;
	ajax = $.ajax({
	  type: "GET",
	  url: url,
	  dataType: "json"
	});
	ajax.done(function(json){
		alert( "Data Saved: " + JSON.stringify(json) );
		if(json.status == "error"){
			if(json.error_code = '2000'){
				$(email).next(".error").show().text(error_msg);
			}else if(json.error_code = '2001'){
				$(password).next(".error").show().text(error_msg);
			}else{
				$(error).show().text(error_code + " : " + error_msg);
			}
		}else{
			alert(json.user.email);
			location.replace("connection.html?email="+json.user.email);
		}
	});
	ajax.fail(function(){
		$(error).show().text("Ajax : Fail to connect to server" );
	});
}

function canRegister(firstname,lastname,email,password){
	valid = true;
	if(isEmpty(email)){
		valid = false;
	} else if(!isValid(email,/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/)){
		valid = false;
	} else {
		fadeOut(email);
	}
	if(isEmpty(password)){
		return false;
	} else if(!isValid(password,/^[a-z0-9_]{5,30}$/i)){
		valid = false;
	} else {
		fadeOut(password);
	}
	return valid;
}

function ajaxLogin(email,password,error){
	emailVal = $(email).val();
	passwordVal = $(password).val();
	url = "../user/login?email="+emailVal+"&password="+passwordVal;
	ajax = $.ajax({
	  type: "GET",
	  url: url,
	  dataType: "json"
	});
	ajax.done(function(json){
		alert( "Data Saved: " + JSON.stringify(json) );
		if(json.status == "error"){
			if(json.error_code = '2000'){
				$(email).next(".error").show().text(error_msg);
			}else if(json.error_code = '2001'){
				$(password).next(".error").show().text(error_msg);
			}else{
				$(error).show().text(error_code + " : " + error_msg);
			}
		}else{
			location.replace("index.html?sessionKey="+json.user.sessionKey);
		}
	});
	ajax.fail(function(){
		$(error).show().text("Ajax : Fail to connect to server" );
	});
}

function canLogin(email,password){
	valid = true;
	if(isEmpty(email)){
		valid = false;
	} else if(!isValid(email,/^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/)){
		valid = false;
	} else {
		fadeOut(email);
	}
	if(isEmpty(password)){
		return false;
	} else if(!isValid(password,/^[a-z0-9_]{5,30}$/i)){
		valid = false;
	} else {
		fadeOut(password);
	}
	return valid;
}

function verifyemail(email){
	if(!isValid(email,/^[A-Z0-9.-_]*@?[A-Z0-9.-_]*$/i)){
		return false;
	}
	fadeOut(email);
}

function verifyPassword(password){
	if(!isValid(password,/^[a-z0-9_]{0,30}$/i)){
		return false;
	}
	fadeOut(password);
}

function isEmpty(id){
	if($(id).val() == ""){
		$(id).css("border-color","#FF0000");
		$(id).next(".error").show().text("Invalid input");
		return true;
	}
	return false;
}

function isValid(id,reg){
	if(!$(id).val().match(reg)){
		$(id).css("border-color","#FF0000");
		$(id).next(".error").show().text("Invalid input");
		return false;
	}
	return true;
}

function fadeOut(id){
	$(id).next(".error").fadeOut();
}