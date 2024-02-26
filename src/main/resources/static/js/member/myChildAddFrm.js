//이름
	$("#childName").on("keyup",function(event){
		
		
	const childName = $("#childName").val();
	
	const regName = /^[가-힣a-zA-Z]+$/;
	
	if(!regName.test(childName)){
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

	
		//주민번호
	$("#childRrn").on("change",function(event){
		
		const childRrn = $("#childRrn").val();
		
		if(childRrn.length < 7){
			if(!$(".rrnStatus").next().hasClass("noRrn")){
			$(".rrnStatus").after('<div class="msg warning noRrn">주민등록번호는 튓 1자리까지 입력해야합니다.</div>')
			
			
		}
		event.preventDefault();
		}else{
			
			$(".noRrn").remove();
			
		}
		
	
	});
	
		
	//주민번호제한
	function rrnMaxLength(e){

        if(e.value.length > e.maxLength){

            e.value = e.value.slice(0, e.maxLength);
    	    }
    	    
    	    
	
    	}
    	
// 폼 추가
function addChildForm() {
    var childForm = `
        <div class="childWrap">
            <button type="button" class="closeBtn"><span class="material-icons close">close</span></button>
            <div class="input_wrap">
                <div class="input_title">
                    <label for="childName">이름</label>
                </div>
                <div class="input_item nameStatus">
                    <input type="text" name="childName" placeholder="이름을 입력하세요">
                </div>
            </div>
            <div class="input_wrap">
                <div class="input_title">
                    <label for="childRrn">주민번호</label>
                </div>
                <div class="input_item rrnStatus">
                    <input oninput="rrnMaxLength(this)" maxLength="7" type="number" name="childRrn" placeholder="주민등록번호 뒷1자리까지 입력하세요('-'미포함)">
                </div>
            </div>
        </div>
    `;
    $(".childAddBtn").before(childForm);
}

// 닫기 버튼 클릭 시 해당 폼 제거 (이벤트 위임 사용)
$(document).on("click", ".closeBtn", function() {
    $(this).closest(".childWrap").remove();
});
