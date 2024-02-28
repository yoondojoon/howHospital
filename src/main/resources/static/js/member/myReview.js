
$(".closeBtn").on("click",function(){

	var reviewNoElement = document.getElementById("memberNo");
	var reviewNo = reviewNoElement.textContent || reviewNoElement.innerText;
	
	$.ajax({
	
		url : "/member/deleteReview",
		type: "post",
		data: {reviewNo : reviewNo},
		success: function(){
			 $(reviewNoElement).closest('.info').fadeOut();
		},
		error: function(){
		
		
		}
	
	
	});
	

});

