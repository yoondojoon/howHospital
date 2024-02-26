document.addEventListener("DOMContentLoaded", function() {
    var childRrnArr = document.getElementsByClassName("childRrn");

    for (var i = 0; i < childRrnArr.length; i++) {
        var childRrn = childRrnArr[i].textContent;

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

        let age = currentYear - birthYear;

        const birthMonth = parseInt(childRrn.substr(2, 2));
        const birthDay = parseInt(childRrn.substr(4, 2));
        const currentMonth = today.getMonth() + 1;
        const currentDay = today.getDate();
        
        if (currentMonth < birthMonth || (currentMonth === birthMonth && currentDay < birthDay)) {
            age--;
        }

        var birthDateEl = document.getElementsByClassName("birthDate")[i];
        birthDateEl.textContent = birthYear + "년" + birthMonth + "월" + birthDay + "일 ";

        var ageEl = document.getElementsByClassName("age")[i];
        ageEl.textContent = age + "세 ";

        const gender = childRrn.substr(6, 1);
        const genderText = (gender === "1" || gender === "3") ? "(남)" : "(여)";

        var genderEl = document.getElementsByClassName("gender")[i];
        genderEl.textContent = genderText;
    }
});
