$(function(){
	const contents = $("#contents");
	const headerH = $("#header").outerHeight();
	const footerH = $("#footer").outerHeight();
	const contentsH = "calc(100vh - "+(headerH+footerH)+"px)";
	contents.css("min-height",contentsH);
})

$(window).on("scroll", function(){
    if($(window).scrollTop() > 0){
        $("#header").addClass("fixed");
    }else{
        $("#header").removeClass("fixed");
    }
})