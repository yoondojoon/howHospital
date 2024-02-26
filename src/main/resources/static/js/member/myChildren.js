document.addEventListener("DOMContentLoaded", function() {
    
    
	const childRrn = document.getElementById("childRrn").textContent;

  let birthYear = parseInt(childRrn.substr(0, 2)); 


if (birthYear === 0) {
    
    let fullYear = parseInt(childRrn.substr(0, 4)); 
    birthYear = fullYear + 2000;
} else if (birthYear > 0 && birthYear <= 20) { 
    birthYear += 2000;
} else { 
    birthYear += 1900;
}

    
    const today = new Date();
    const currentYear = today.getFullYear();

    //현재 년도 
    let age = currentYear - birthYear;

    // 생일이 지났는지 체크하여 나이 조정
    const birthMonth = parseInt(childRrn.substr(2, 2)); // 생월(MM 형식)
    const birthDay = parseInt(childRrn.substr(4, 2)); // 생일(DD 형식)
    const currentMonth = today.getMonth() + 1; // 현재 월
    const currentDay = today.getDate(); // 현재 일
    if (currentMonth < birthMonth || (currentMonth === birthMonth && currentDay < birthDay)) {
        age--;
        }
        
  	document.getElementById("birthDate").textContent = birthYear + "년" + birthMonth + "월" + birthDay + "일 "; 

    // 결과를 화면에 표시
    document.getElementById("age").textContent = age 

    const gender = childRrn.substr(6, 1);		//'?'삼항 조건 연산자 : 조건이 참이면 첫 번째(남자)를 반환 거짓이면 두 번째(여자)를 반환.
    const genderText = (gender === "1" || gender === "3") ? "(남)" : "(여)";

    document.getElementById("age").textContent = age + "세 ";
    document.getElementById("gender").textContent = genderText;
});


	



