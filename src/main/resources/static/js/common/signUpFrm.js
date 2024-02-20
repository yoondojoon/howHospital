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


	// 회원가입 확인
	function signUp() {
    // 모든 약관 동의 체크 확인
    var agreeService = document.getElementById("agreeService").checked;
    var agreeLocation = document.getElementById("agreeLocation").checked;
    var agreeAge = document.getElementById("agreeAge").checked;

    // 모든 입력 필드 값 확인
    var memberEmail = document.getElementById("memberEmail").value;
    var memberPassword = document.getElementById("memberPassword").value;
    var memberName = document.getElementById("memberName").value;
    var memberRrn = document.getElementById("memberRrn").value;
    var memberAddrress = document.getElementById("memberAddrress").value;
    var memberPhone = document.getElementById("memberPhone").value;

    // 모든 약관 동의가 체크되어 있는지 확인
    if (!agreeService || !agreeLocation || !agreeAge) {
        alert("약관에 동의해주세요.");
        return; // 약관 동의가 모두 되어 있지 않으면 함수 종료
    }

    // 입력 필드의 값이 모두 채워져 있는지 확인
    if (memberEmail === "" || memberPassword === "" || memberName === "" || memberRrn === "" || memberAddrress === "" || memberPhone === "") {
        alert("모든 입력 필드를 채워주세요.");
        return; // 입력 필드의 값이 모두 채워져 있지 않으면 함수 종료
    }


    document.getElementById("signUpFrm").submit();
}











	