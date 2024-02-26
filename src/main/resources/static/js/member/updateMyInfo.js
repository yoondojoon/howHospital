document.addEventListener("DOMContentLoaded", function() {
    
    
	const memberRrn = document.getElementById("memberRrn").textContent;

    
    let birthYear = parseInt(memberRrn.substr(0, 2)); //97
    if (birthYear > 00) { 	   //1900년생
        birthYear += 1900;
    	} else { 			   //2000년생
        birthYear += 2000;
    	}

    
    const today = new Date();
    const currentYear = today.getFullYear();

    // 현재 연도와 생년을 비교하여 나이 계산
    let age = currentYear - birthYear;

    // 생일이 지났는지 체크하여 나이 조정
    const birthMonth = parseInt(memberRrn.substr(2, 2)); // 생월(MM 형식)
    const birthDay = parseInt(memberRrn.substr(4, 2)); // 생일(DD 형식)
    const currentMonth = today.getMonth() + 1; // 현재 월
    const currentDay = today.getDate(); // 현재 일
    if (currentMonth < birthMonth || (currentMonth === birthMonth && currentDay < birthDay)) {
        age--;
        }

    // 결과를 화면에 표시
    document.getElementById("age").textContent = age + "세";

    const gender = memberRrn.substr(6, 1);		//'?'삼항 조건 연산자 : 조건이 참이면 첫 번째(남자)를 반환 거짓이면 두 번째(여자)를 반환.
    const genderText = (gender === "1" || gender === "3") ? "남자" : "여자";

    document.getElementById("age").textContent = age + "세";
    document.getElementById("gender").textContent = genderText;
});




	//비밀번호
	$("#memberPassword").on("keyup",function(event){

	const password = $("#memberPassword").val();
	
	
	
	
		if(password.length < 4){//비밀번호가 4보다 커야 true
			
			
			if (!$(".password").next().hasClass("password1")) {
				// 메시지가 없는 경우에만 메시지 추가
				$(".password").after('<div class="msg warning password1">비밀번호는 4글자 이상 사용하실 수 있습니다.</div>');
			}
			$(".complete").prop("disabled", true);
			$(".complete").prop("disabled", true);
			$(".password2").remove();
			
			
		}else if( password.length > 3){
			if (!$(".password").next().hasClass("password2")) {
			$(".password").after('<div class="msg success password2">사용 가능한 비밀번호입니다.</div>')
			$(".password1").remove();
			}
			$(".complete").prop("disabled", false);
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
		$(".complete").prop("disabled", false);
		
		
	}else{
		if(!$(".rePassword").next().hasClass("rePw2")){
		$(".rePassword").after('<div class="msg warning rePw2">비밀번호가 일치하지 않습니다.</div>')
		$(".rePw1").remove();
	}
	$(".complete").prop("disabled", true);
	}

	});
	
	
	
$(document).ready(function() {
    $(".complete").prop("disabled", true); // 페이지 로드될 때 버튼 비활성화

    $("input").on("input", function() { // 사용자 입력 시마다 이벤트 감지
        const memberAddress = $("#memberAddress").val();
        const memberPassword = $("#memberPassword").val();
        const memberPhone = $("#memberPhone").val();
        const reMemberPassword = $("#reMemberPassword").val();

        if ((memberAddress !== ""||
        	memberPassword !== "" ||
        	reMemberPassword !== "" ||
        	memberPhone !== ""
        ) && memberPassword.length >= 4 && memberPassword === reMemberPassword && memberPhone !== "") {
            $(".complete").prop("disabled", false); // 모든 조건을 만족하면 버튼 활성화
        } else {
            $(".complete").prop("disabled", true); // 아니면 버튼 비활성화
        }
    });
});