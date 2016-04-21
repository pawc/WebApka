$(document).ready(function() {
	
	
	$("#rejestracja").hide();
	$("#logowanie").hide();
	
	setTimeout(function() {
		$("#logowanie").fadeIn(500, function(){
			$(".cloud").width("100%");
			$(".plane").css({
				"right" : "100%"
			});
		}) 
	}, 500);
	

	
	$("#a-rejestr").click(function(event){
		event.preventDefault();
		$("#logowanie").slideUp(500, function(){
			$("#rejestracja").slideDown(500);
		});
	});
	
	$("#a-loguj").click(function(event){
		event.preventDefault();
		$("#rejestracja").fadeOut(500, function(){
			$("#logowanie").fadeIn(500);
		});
	});
});