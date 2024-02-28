// 이름 입력란 이벤트 처리
$(document).on("keyup", "#childName", function(event) {
    const childName = $(this).val();
    const regName = /^[가-힣a-zA-Z]*$/; // 수정된 정규식 패턴

    if (!regName.test(childName)) {
        if (!$(".nameStatus").next().hasClass("noName")) {
            $(".nameStatus").after('<div class="msg warning noName">이름은 한글이나 영문만 가능합니다.</div>');
            $(".name").remove();
        }
        event.preventDefault();
    } else {
        if (!$(".nameStatus").next().hasClass("name")) {
            $(".noName").remove();
            $(".nameStatus").after('<div class="msg success name">사용가능한 이름입니다.</div>');
        }
    }
    
    updateCompleteButtonState();
});

// 주민번호 입력란 이벤트 처리
$(document).on("input", "#childRrn", function(event) {
    let childRrn = $(this).val();
    childRrn = childRrn.replace(/[^0-9]/g, ''); // 숫자 이외의 문자 제거

    if (childRrn.length > 7) {
        childRrn = childRrn.substr(0, 7); // 주민번호 길이 제한
    }

    $(this).val(childRrn); // 처리된 주민번호 입력란에 반영

    if (childRrn.length !== 7) {
        if (!$(".rrnStatus").next().hasClass("noRrn")) {
            $(".rrnStatus").after('<div class="msg warning noRrn">주민등록번호는 정확히 7자리여야 합니다.</div>');
        }
        event.preventDefault();
    } else {
        $(".noRrn").remove();
    }

    // 완료 버튼 활성화/비활성화 상태 변경
    updateCompleteButtonState();
});


// 자녀 폼 추가 버튼 클릭 시 동작
function addChildForm() {
    var childForm = `
        <div class="childWrap">
            <button type="button" class="closeBtn"><span class="material-icons close">close</span></button>
            <div class="input_wrap">
                <div class="input_title">
                    <label for="childName">이름</label>
                </div>
                <div class="input_item nameStatus">
                    <input type="text" name="childName" id="childName" placeholder="이름을 입력하세요">
                </div>
            </div>
            <div class="input_wrap">
                <div class="input_title">
                    <label for="childRrn">주민번호</label>
                </div>
                <div class="input_item rrnStatus">
                    <input oninput="rrnMaxLength(this)" maxLength="7" type="text" name="childRrn" id="childRrn" placeholder="주민등록번호 뒷1자리까지 입력하세요('-'미포함)">
                </div>
            </div>
        </div>
    `;
    $(".childAddBtn").before(childForm);
}

// 자녀 폼 닫기 버튼 클릭 시 동작
$(document).on("click", ".closeBtn", function() {
    $(this).closest(".childWrap").remove();
});

// 주민번호 입력 길이 제한 함수
function rrnMaxLength(e) {
    if (e.value.length > e.maxLength) {
        e.value = e.value.slice(0, e.maxLength);
    }
}


function updateCompleteButtonState() {
    const childName = $("#childName").val();
    const childRrn = $("#childRrn").val();
    const regName = /^[가-힣a-zA-Z]*$/;

    // 이름과 주민번호가 둘 다 유효한 경우에만 완료 버튼 활성화
    if (childName !== "" && childRrn !== "" && regName.test(childName) && childRrn.length === 7) {
        $(".btn_primary[type='submit']").prop("disabled", false); // 버튼 활성화
    } else {
        $(".btn_primary[type='submit']").prop("disabled", true); // 버튼 비활성화
    }
}

$(document).ready(function() {
    // 페이지 로드 시 완료 버튼 비활성화
    $("button[type='submit']").prop("disabled", true);
});
