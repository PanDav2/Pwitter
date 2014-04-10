$(function() {
	
	var session = getQueryString("session");
	var user_id = getQueryString("user_id");
	
	
	/* Version : Session */
	
	if (session !== undefined) {
		
		MyProfil.ajax(session);
		
		if (user_id !== undefined){
			
			/* Version : Session, mais user_id != */
			CurrentProfil.ajax(user_id);
			$("#button-PwittsFind").hide();
			
		}else{
			
			/* Version : Session & pas de user_id */
			
			$("#form-PwittSend").submit(function(){
				var content = $("#input-PwittSend").val();
				if(content.length != 0){
					PwittSend.ajax(content);
				}
				return false;
			});
			
			$("#menu-MyFriends").click(function(){
				FriendsFind.ajax(state.myProfil.session);
				return false;
			});
			
			$("#menu-MyPwitts").click(function(){
				PwittsFind.ajaxMyPwitts();
				return false;
			});
			
			$("#menu-AllPwitts").click(function(){
				PwittsFind.ajaxAllPwitts();
				return false;
			});
		
		}
		
	}else{
		/* Version : !Session, mais user_id */
		if (user_id !== undefined) {
			CurrentProfil.ajax(user_id);
		}else{
			//window.location = "connection.html";
		}
	}
		
});


/* Function  */

function JSONPwittsTest()
{
	var user1 = new User(1,"jean@pwitter.fr","Jean","JEAN",true);
	var user2 = new User(2,"jeanne@pwitter.fr","Jeanne","JEANNE",false);
	var user3 = new User(3,"roger@pwitter.fr","Roger","ROGER",false);
	var com00 = new Pwitt(23,user3,"Testons la date",new Date(2014, 3, 7, 17, 21), 45);
	var com0 = new Pwitt(23,user2,"Testons la date",new Date(2014, 3, 7, 17), 45);
	var com1 = new Pwitt(23,user2,"Ceci est le contenu d'un Pwitt",new Date(2014, 3, 7), 45);
	var com2 = new Pwitt(22,user1,"Ca ausi :)",new Date(2014, 3, 1), 40);
	var tab = [com00,com0,com1,com2];
	var reco = new PwittsFind(tab,"nimp", false, user3);
	return (JSON.stringify(reco));
}

