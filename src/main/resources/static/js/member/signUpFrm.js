
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

//////////////////////////////////////////////////////////////////////////////////////////////////////////////


	//이메일 중복
	$("#memberEmail").on("keyup", function(event) {
		const memberEmail = $("#memberEmail").val();
		const regExp = /^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,}$/;
	
		if (!regExp.test(memberEmail)) {
			if(!$(".emailStatus").next().hasClass("noEmail")){
			$(".emailStatus").after('<div class="msg warning noEmail">잘못된 이메일 형식입니다.</div>');
			$(".duplicate").remove();
			$(".available").remove();
			$(".emailInput").remove();
			}
			$("#sendBtn").prop("disabled", true);
			event.preventDefault();

		} else {
			$.ajax({
				url: "/member/emailChk",
				type: "post",
				data: { memberEmail: memberEmail }, 
				success: function(data) {
					if (data === 0) {
						if(!$(".emailStatus").next().hasClass("available")){
						$(".emailStatus").after('<div class="msg success available">사용가능한 이메일입니다.</div>');
						$(".duplicate").remove();	
						$(".emailInput").remove();
						$(".noEmail").remove();
						}
						$("#sendBtn").prop("disabled", false);
					} else {	
						if(!$(".emailStatus").next().hasClass("duplicate")){
						$(".emailStatus").after('<div class="msg warning duplicate">중복된 이메일입니다.</div>');
						$(".emailInput").remove();
						$(".noEmail").remove();
						$(".available").remove();
						}
						$("#sendBtn").prop("disabled", true);
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

	



	//인증번호
	$("#authBtn").on("click",function(event){
			

			
			
		if(mailCode != null){
			const authCode = $("#authCode").val();
			if(authCode == mailCode){
			if(!$(".authStatus").next().hasClass("codeAuth1")){
				$(".authStatus").after('<div class="msg success codeAuth1">인증이 완료되었습니다.</div>');
				$(".codeAuth2").remove();
				$(".at").remove();	
			}
				
			window.clearInterval(intervalId);
			
			
			//$("#memberEmail").prop("disabled",true);
			$("#sendBtn").prop("disabled",true); 
			$("#authCode").prop("disabled",true); 
			$("#authBtn").prop("disabled",true);
			//시간을 화면에서 삭제
			$("#timeZone").remove();
			
			}else{
				if(!$(".authStatus").next().hasClass("codeAuth2")){
					$(".authStatus").after('<div class="msg warning codeAuth2">인증번호를 확인하세요.</div>');
					$(".codeAuth1").remove();
					$(".at").remove();
				}
					
					event.preventDefault();
			}	
	}			
});	


	//이메일로 인증코드 전송
	let mailCode = null; // <---인증코드가 저장됨

	$("#sendBtn").on("click",function(event){

	const memberEmail = $("#memberEmail").val();
	
	if(memberEmail === ''){
		if(!$(".emailStatus").next().hasClass("emailInput")){
		$(".emailStatus").after('<div class="msg warning emailInput">이메일을 입력해 주세요.</div>');
		}
		event.preventDefault();
		
	}else{
			
		
		$.ajax({
			url : "/member/sendCode",
			data : {memberEmail : memberEmail},
			type : "post",
			success : function(data){
			console.log(data);
			mailCode = data;
			
			$("#auth").show();

			authTime();

			},
			error : function(){
			console.log("에러");
			}
		});
	}
	
	
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
					if(!$(".authStatus").next().hasClass("timeOver")){
					$(".authStatus").after('<div class="msg warning timeOver">인증 시간이 만료되었습니다.</div>');
					$(".at").remove();
				}
					
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




	
	
	
	

		


//회원가입 버튼 
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
	const authCode = $("#authCode").val();
	
		if(authCode !== mailCode){
			if (!$(".authStatus").next().hasClass("at")) {
				$(".authStatus").after('<div class="msg warning at">인증번호를 확인해주세요.</div>')
			}
			event.preventDefault();

		}

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
		  return;
	
	  }
	
    const isChecked = $(".agreeAll").is(":checked");
    
    if(!isChecked){
    	
		
    	
		if (!$(".agreeStatus").next().hasClass("statusAgree")) {
			$(".agreeStatus").after('<div class="msg warning statusAgree">모든 약관에 동의해주셔야 가입이 가능합니다.</div>')
		}
    	event.preventDefault();
    }else if(isChecked == true){
    
    	$("#agreeStatus").text("");

    }
    
	
    
});

$(".agreeAll").on("change", function() {
    const isChecked = $(this).is(":checked");
    if (isChecked) {
        $("#agreeStatus").text(""); 
    }
});
	





	//가입유형
	$("#select select[name=memberType]").on("change", function(event) {
        const selectType = $(this).val();
        if (selectType === "0") {
			
			$(".joinType").after('<div class="msg warning selectType1">가입유형을 선택해주세요.</div>')
			$(".selectType2").remove();
			event.preventDefault();
        } else {
			$(".selectType1").remove();
        }
    });
	


	//이름
	$("#memberName").on("keyup",function(event){
		
		
	const memberName = $("#memberName").val();
	
	const regName = /^[가-힣a-zA-Z]+$/;
	
	if(!regName.test(memberName)){
		if (!$(".nameStatus").next().hasClass("noName")) {
		$(".nameStatus").after('<div class="msg warning noName">이름은 한글이나 영문만 가능합니다.</div>')
		$(".name").remove();
	}
	event.preventDefault();
	}else{
		if (!$(".nameStatus").next().hasClass("name")) {
		$(".noName").remove();
		$(".nameStatus").after('<div class="msg success name">사용가능한 이름입니다.</div>')
		}
	}
		
		
	
	});






	//비밀번호
	$("#memberPassword").on("keyup",function(event){

	const password = $("#memberPassword").val();
	
	
	
	
		if(password.length < 4){//비밀번호가 4보다 커야 true
			
			event.preventDefault();
			if (!$(".password").next().hasClass("password1")) {
				// 메시지가 없는 경우에만 메시지 추가
				$(".password").after('<div class="msg warning password1">비밀번호는 4글자 이상 사용하실 수 있습니다.</div>');
			}
			
			$(".password2").remove();
			
			
		}else if( password.length > 3){
			if (!$(".password").next().hasClass("password2")) {
			$(".password").after('<div class="msg success password2">사용 가능한 비밀번호입니다.</div>')
			$(".password1").remove();
			}
		}
	
});







	//비밀번호 확인
	$("#reMemberPassword").on("keyup",function(event){
	
	const password = $("#memberPassword").val();
	const reMemberPassword = $("#reMemberPassword").val();
	

	if(password === reMemberPassword){
		if(!$(".rePassword").next().hasClass("rePw1")){
		$(".rePassword").after('<div class="msg success rePw1">비밀번호가 일치합니다.</div>')
		$(".rePw2").remove();
		}
		
	}else{
		if(!$(".rePassword").next().hasClass("rePw2")){
		$(".rePassword").after('<div class="msg warning rePw2">비밀번호가 일치하지 않습니다.</div>')
		$(".rePw1").remove();
	}
	event.preventDefault()
	}

	});
	
	
	
	//주민번호
	$("#memberRrn").on("change",function(event){
		
		const memberRrn = $("#memberRrn").val();
		
		if(memberRrn.length < 7){
			if(!$(".rrnStatus").next().hasClass("noRrn")){
			$(".rrnStatus").after('<div class="msg warning noRrn">주민등록번호는 튓 1자리까지 입력해야합니다.</div>')
			
			
		}
		event.preventDefault();
		}else{
			
			$(".noRrn").remove();
			
		}
		
	
	});
	
	//전화번호
	$("#memberPhone").on("keyup",function(event){
		
		const memberPhone = $("#memberPhone").val();
		
		if (memberPhone.length < 13) {
			if (!$(".phoneStatus").next().hasClass("noPhone")) {
				$(".phoneStatus").after('<div class="msg warning noPhone">전화번호를 정확히 입력해주세요.</div>');
			}
			$(".okPhone").remove();
			event.preventDefault();
		} else {
			if (!$(".phoneStatus").next().hasClass("okPhone")) {
				$(".phoneStatus").after('<div class="msg success okPhone">사용가능한 전화번호입니다.</div>');
			}
			$(".noPhone").remove();
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

		




  document.addEventListener('keydown', function(event) {
      if ((event.keyCode || event.which) === 13) {
          event.preventDefault();
      }
  }, true);

		
	