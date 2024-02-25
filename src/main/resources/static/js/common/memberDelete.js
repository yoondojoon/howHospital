// 비밀번호 중복 체크
$("#memberPassword").on("keyup", function(event) {
    const memberPassword = $("#memberPassword").val();
    
    $.ajax({
        url: "/member/passwordChk",
        type: "post",
        data: { memberPassword: memberPassword }, 
        success: function(cnt) {
            if (cnt === 0) {
                $(".pwStatus2").remove();
                if (!$(".passwordStatus").next().hasClass("pwStatus1")) {
                    $(".passwordStatus").after('<div class="msg warning pwStatus1">비밀번호가 일치하지 않습니다.</div>');
                }
                $("#delete").prop("disabled", true);
            } else if (cnt === 1) {
                $(".pwStatus1").remove();
                if (!$(".passwordStatus").next().hasClass("pwStatus2")) {
                    $(".passwordStatus").after('<div class="msg success pwStatus2" style="color: #1065DC;">비밀번호가 일치합니다.</div>');
                }
                $("#delete").prop("disabled", false);
            }
        },
        error: function() {
            $(".pwStatus1").remove();
            $(".pwStatus2").remove();
            $(".pwStatus4").remove();
            $(".pwStatus5").remove();
            if (!$(".passwordStatus").next().hasClass("pwStatus3")) {
                $(".passwordStatus").after('<div class="msg warning pwStatus3" style="color: #1065DC;">에러.</div>');
            }
        }
    });
});
// 회원 탈퇴
$("#delete").on("click", function(event) {
    $.ajax({
        url: "/member/delete",
        type: "post",
        success: function(cnt) {
            if (cnt === 1) {
                window.location.href = "/";
            } else {
                window.location.href = "/member/failDeleteMember"; 
            }
        },
        error: function() {
            window.location.href = "/member/failDeleteMember"; 
        }
    });
});

