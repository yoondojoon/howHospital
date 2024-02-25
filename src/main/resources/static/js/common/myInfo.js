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


