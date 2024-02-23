
$(".agreeAll").on("click", function() {

    
    const isChecked = $(this).is(":checked"); 
    

    

    if(isChecked){ 
        $(".agreeService").prop("checked",true);
        $(".agreeLocation").prop("checked",true);
        $(".agreeAge").prop("checked",true);
    }else{
        $(".agreeService").prop("checked",false);
        $(".agreeLocation").prop("checked",false);
        $(".agreeAge").prop("checked",false);     
    }


	});



	$(".agreeService").on("click",function(){

    const isChecked = $(this).is(":checked");

    if(!isChecked ){
        $(".agreeAll").prop("checked",false)
    };

	});






	$(".agreeLocation").on("click",function(){

    const isChecked = $(this).is(":checked");

    if(!isChecked ){
        $(".agreeAll").prop("checked",false)
    };

	});

	$(".agreeAge").on("click",function(){

    const isChecked = $(this).is(":checked");

    if(!isChecked ){
        $(".agreeAll").prop("checked",false)
    };

	});




	$(".agreeService, .agreeLocation, .agreeAge").on("click",function(){


    const agreeService = $(".agreeService").is(":checked");
    const agreeLocation = $(".agreeLocation").is(":checked");
    const agreeAge = $(".agreeAge").is(":checked");

    

    if(agreeService == true && agreeLocation == true && agreeAge == true){

        $(".agreeAll").prop("checked",true)
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







	//이메일인증
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







$("#signUpBtn").on("click", function(event) {

    const selectType = $("select[name=memberType]").val();
    const memberEmail = $("#memberEmail").val();
    const memberPassword = $("#memberPassword").val();
    const rePassword = $("#reMemberPassword").val();
    const memberName = $("#memberName").val();
    const memberRrn = $("#memberRrn").val();
    const postcode = $("#postcode").val();
    const address = $("#address").val();
    const detailAddress = $("#detailAddress").val();
    const memberPhone = $("#memberPhone").val();
	
	
	  if (
        selectType === "" ||
    	memberEmail === "" ||
    	memberPassword === "" ||
    	rePassword === "" ||
    	memberName === "" ||
    	memberRrn === "" ||
    	postcode === "" ||
    	address === "" ||
    	detailAddress === "" ||
    	memberPhone === ""
    ) {
        event.preventDefault();
        alert("모든 항목을 정확히 입력해 주세요."); 
    }
    
    const isChecked = $(".agreeAll").is(":checked");
    
    if(!isChecked){
    	
    	$("#agreeStatus").text("모든 약관에 동의해주셔야 가입이 가능합니다.").css("color","red");
    	
    }else{
    
    	$("#agreeStatus").hide();

    }
    
    form.submit();
    
});
	
	$("#select select[name=memberType]").on("change", function() {
        const selectType = $(this).val();
        if (selectType === "0") {
            $("#typeStatus").text("가입유형을 선택해주세요.").css("color","red");
        	event.preventDefault();
        } else {
            $("#typeStatus").text(""); 
        }
    });
  
	$("#memberName").on("change",function(){
		
		
	const memberName = $("#memberName").val();
	
	const regName = /^[가-힣a-zA-Z]+$/;
	
	if(!regName.test(memberName)){
	
		$("#nameStatus").text("이름은 한글이나 영문만 가능합니다.").css("color","red");
		event.preventDefault();
	}else{
	
		$("#nameStatus").hide();
	}
		
		
	
	});







	$("#memberPassword").on("change",function(){

	const password = $("#memberPassword").val();
	
	
	
	
		if(password.length < 4){//비밀번호가 4보다 커야 true
			$("#passwordStatus1").text("비밀번호는 4글자 이상 사용하실 수 있습니다.");
			$("#passwordStatus1").css("color","red");
			event.preventDefault();
			
			
		}else if( password.length > 3){
			$("#passwordStatus1").text("사용가능한 비밀번호 입니다.");
			$("#passwordStatus1").css("color","blue");
			$("#passwordStatus1").hide();
		}
	
});






	$("#reMemberPassword").on("change",function(){
	
	const password = $("#memberPassword").val();
	const reMemberPassword = $("#reMemberPassword").val();
	
	if(password === reMemberPassword){
	
		$("#passwordStatus2").text("비밀번호가 일치합니다.");
		$("#passwordStatus2").css("color","blue");
		
		
	}else{
	
		$("#passwordStatus2").text("비밀번호가 일치하지 않습니다.");
		$("#passwordStatus2").css("color","red");
		event.preventDefault()
	}

	});
	
	
	
	
	$("#memberRrn").on("change",function(){
		
		const memberRrn = $("#memberRrn").val();
		
		if(memberRrn.length < 7){
			
			$("#rrnStatus").text("주민등록번호는 뒷1자리까지 입력해야 합니다. XXXXXX-X");
			$("#rrnStatus").css("color","red");
			event.preventDefault()
			
		}else{
			$("#rrnStatus").text("사용가는한 주민등록번호 입니다.");
			$("#rrnStatus").css("color","blue");
			
		}
		
	
	});
	
	
	$("#memberPhone").on("change",function(){
		
		const memberPhone = $("#memberPhone").val();
		
		if(memberPhone.length < 13){
		
			$("#phoneStatus").text("전화번호를 정확히 입력해주세요.");
			$("#phoneStatus").css("color","red");
			event.preventDefault()
		
		}else{
			
			$("#phoneStatus").text("사용가능한 전화번호 입니다.");
			$("#phoneStatus").css("color","blue");
			
		}
		
		
	});
	
	//이메일 중복
	$("#memberEmail").on("change", function() {
    const memberEmail = $("#memberEmail").val();
    const regExp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;

    if (!regExp.test(memberEmail)) {
        $("#emailStatus").text("잘못된 형식의 이메일입니다.").css("color", "red");
        event.preventDefault();
    } else {
        $.ajax({
            url: "/member/emailChk",
            type: "post",
            data: { memberEmail: memberEmail }, 
            success: function(data) {
                if (data === 0) {
                    $("#emailStatus").text("사용 가능한 이메일입니다.").css("color","blue");
                    
                } else {
                    $("#emailStatus").text("중복된 이메일입니다.").css("color","red");
                    event.preventDefault();
                }
            },
            error: function() {
                $("#emailStatus").text("에러");
                event.preventDefault();
            }
        });
    }
});
		
		
//전화번호 하이픈
	const autoHyphen = (target) => {
 	target.value = target.value
 	.replace(/[^0-9]/g, '')
  	.replace(/^(\d{0,3})(\d{0,4})(\d{0,4})$/g, "$1-$2-$3").replace(/(\-{1,2})$/g, "");
 	
   	
		}








	
	//주민번호제한
	function rrnMaxLength(e){

        if(e.value.length > e.maxLength){

            e.value = e.value.slice(0, e.maxLength);
    	    }
    	    
    	    
	
    	}



	