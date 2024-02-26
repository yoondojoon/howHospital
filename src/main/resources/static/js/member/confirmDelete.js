
/*

$("#memberPassword").on("change", function(event) {

    const memberPassword = $("#memberPassword").val();
    
    $.ajax({
        url: "/member/passwordChk",
        type: "post",
        data: { memberPassword: memberPassword,
        		
        }, 
        success: function(data) {
            if (data === 0) {
                $("#passwordStatus").text("비밀번호가 일치하지 않습니다.").css("color", "red");
                event.preventDefault();                               
            }
        },
        error: function() {
            $("#passwordStatus").text("에러");
            event.preventDefault();
        }
    });
})

*/