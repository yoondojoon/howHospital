$(window).on("scroll", function(){
    if($(window).scrollTop() > 0){
    	$("#header").addClass("fixed");
    }else{
    	$("#header").removeClass("fixed");
    }
})