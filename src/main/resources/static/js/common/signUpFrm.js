$("#agreeAll").on("click", function() {
    
    
    const isChecked = $(this).is(":checked"); 
    

    

    if(isChecked){ 
        $("#agreeService").prop("checked",true);
        $("#agreeLocation").prop("checked",true);
        $("#agreeAge").prop("checked",true);
    }else{
        $("#agreeService").prop("checked",false);
        $("#agreeLocation").prop("checked",false);
        $("#agreeAge").prop("checked",false);     
    }


});



$("#agreeService").on("click",function(){

    const isChecked = $(this).is(":checked");

    if(!isChecked ){
        $("#agreeAll").prop("checked",false)
    };

});



//주소
function searchAddr(){
    new daum.Postcode({
        oncomplete: function(data) {
            $("#postcode").val(data.zonecode);
            $("#address").val(data.address);
            $("#detailAddress").focus();
        }
    }).open();
    
    
    
    
};




$("#agreeLocation").on("click",function(){

    const isChecked = $(this).is(":checked");

    if(!isChecked ){
        $("#agreeAll").prop("checked",false)
    };

});

$("#agreeAge").on("click",function(){

    const isChecked = $(this).is(":checked");

    if(!isChecked ){
        $("#agreeAll").prop("checked",false)
    };

});




$("#agreeService, #agreeLocation, #agreeAge").on("click",function(){


    const agreeService = $("#agreeService").is(":checked");
    const agreeLocation = $("#agreeLocation").is(":checked");
    const agreeAge = $("#agreeAge").is(":checked");

    

    if(agreeService == true && agreeLocation == true && agreeAge == true){

        $("#agreeAll").prop("checked",true)
    };

});

let mailCode = null;

$("#sendBtn").on("click",function(){

	const memberEmail = $("#memberEmail").val();
	
	$.ajax({
		url : "/member/sendCode",
		data : {memberEmail : memberEmail},
		type : "post",
		success : function(data){
			console.log(data);
			mailCode = data;
			
			$("#auth").show();
		},
		error : function(){
			console.log("에러");
		}
	});
	
});



		let intervalId;
		
		function authTime(){
			$("#timeZone").html("<span id='min'>3</span> : <span id='sec'>00</span>");
			intervalId = window.setInterval(function(){
				timeCount();
			},1000);
			
		}
		
		function timeCount(){
			const min = $("#min"). text();
			const sec = $("#sec"). text ();
			if(sec == "00" ){
				if(min == "0"){
					window.clearInterval(intervalId);
					mailCode = null;
					$("#authMsg").text("인증 시간이 만료되었습니다.");
					$("#authMsg").css("color","red");
				}else{
					const newMin = Number(min) - 1;
					$("#min").text(newMin);
					$("#sec"). text (59);		
				}
			
			}else{
			const newsec = Number (sec) - 1;
			if(newsec < 10){
				$ ("#sec"). text("0"+newsec);
				}else{
				$("#sec"). text(newsec) ;
				}
			}
		}
		










	$("#authBtn").on("click",function(){
			
			
			
				if(mailCode != null){
					const authCode = $("#authCode").val();
					if(authCode == mailCode){
					$("#authMsg").text("인증완료");
					$("#authMsg").css("color","blue");
					window.clearInterval(intervalId);
					
					
					$("#memberEmail").prop("disabled",true);
					$("#sendBtn").prop("disabled",true); 
					$("#authCode").prop("disabled",true); 
					$("#authBtn").prop("disabled",true);
					//시간을 화면에서 삭제
					$("#timeZone").remove();
					
					}else{
						$("#authMsg").text("인증번호를 확인하세요");
						$("#authMsg").css("color","red");
					
					}
				
			}	
		});




//회원가입 버튼
$("#signUpBtn").on("click",function(){
	
	//가입유형
	const selectType = $("select[name=select]").val();
	
	//이메일
	const memberEmail = $("#memberEmail").val();
	
	
	
	//비밀번호
	const memberPassword = $("#memberPassword").val();
	
	const rePassword = $("#reMemberPassword").val();
	
	//이름
	const memberName = $("#memberName").val();
	
	
	
	//주민번호
	const memberRrn = $("#memberRrn").val();
	
	
	
	//주소
	const postcode = $("#postcode").val();
	const address = $("#address").val();
	const detailAddress = $("#detailAddress").val();
	
	
	//console.log(postcode+" "+address+detailAddress);
	
	
	//전화번호
	const memberPhone = $("#memberPhone").val();
	
	
	
	//console.log(selectType, memberEmail, memberPassword, memberName, memberRrn, postcode, address, detailAddress);

	
	
	if((selectType, memberEmail, memberPassword, memberName, memberRrn, postcode, address, detailAddress) == null){
		
		
		
		
		
		
	};
	
	
});










	