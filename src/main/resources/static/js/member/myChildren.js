document.addEventListener("DOMContentLoaded", function() {
    var childRrnArr = document.querySelectorAll(".childRrn");

    childRrnArr.forEach(function(childRrnEl, index) {
        var childRrn = childRrnEl.textContent.trim(); 

        let birthYear = parseInt(childRrn.substr(0, 2));

        const today = new Date();
        const currentYear = today.getFullYear(); // 오늘 년도

        if (birthYear >= 0 && birthYear <= currentYear % 100) {
            birthYear += 2000;
        } else {
            birthYear += 1900;
        }

        let age = currentYear - birthYear;

        const birthMonth = parseInt(childRrn.substr(2, 2));
        const birthDay = parseInt(childRrn.substr(4, 2));
        const currentMonth = today.getMonth() + 1;
        const currentDay = today.getDate();

        if (currentMonth < birthMonth || (currentMonth === birthMonth && currentDay < birthDay)) {
            age--;
        }

        
        var parentElement = childRrnEl.parentElement;
        var birthDateEl = parentElement.querySelector(".birthDate");
        if (birthDateEl) {
            birthDateEl.textContent = birthYear + "년 " + birthMonth + "월 " + birthDay + "일";
        }

        var ageEl = parentElement.querySelector(".age");
        if (ageEl) {
            ageEl.textContent = age + "세";
        }

        var genderEl = parentElement.querySelector(".gender");
        if (genderEl) {
            const gender = childRrn.substr(6, 1);
            const genderText = (gender === "1" || gender === "3") ? "(남) " : "(여) ";
            genderEl.textContent = genderText;
        }
    });
});




$(".closeBtn").on("click",function(){

	var childNoElement = document.getElementById("childRrn");
	var childNo = childNoElement.textContent || childNoElement.innerText;
	
	$.ajax({
	
		url : "/member/deleteChild",
		type: "post",
		data: {childNo : childNo},
		success: function(){
			 $(childNoElement).closest('.info').fadeOut();
		},
		error: function(){
		
		
		}
	
	
	});
	

});










