    // 평일 마감 시간 설정
    function setCloseTime() {
        var openHour = parseInt(document.getElementById("select_dayopen_hour").value);
        var closeSelect = document.getElementById("select_dayclose_hour");
  
        // 선택한 시작 시간에 따라 마감 시간 옵션을 변경
        closeSelect.innerHTML = ""; // 이전 옵션 제거
  
        // 시작 시간을 선택하지 않은 경우 마감 시간 비활성화
        if (isNaN(openHour)) {
            var disabledOption = document.createElement("option");
            disabledOption.text = "마감 시간을 선택하세요.";
            closeSelect.appendChild(disabledOption);
            closeSelect.disabled = true;
            return;
        }
  
        // 시작 시간 다음 시간부터 마감 시간 옵션 생성
        for (var i = openHour + 1; i <= 23; i++) {
            var option = document.createElement("option");
            option.value = i;
            option.text = (i < 10 ? "0" : "") + i + ":00";
            closeSelect.appendChild(option);
        }
  
        closeSelect.disabled = false; // 마감 시간 활성화
    }
  
    // 문서가 로드될 때 초기 설정 적용
    document.addEventListener("DOMContentLoaded", function() {
        // 평일 오픈 시간 옵션 생성
        for (var i = 0; i <= 23; i++) {
            var option = document.createElement("option");
            option.value = i;
            option.text = (i < 10 ? "0" : "") + i + ":00";
            document.getElementById("select_dayopen_hour").appendChild(option);
        }
  
        // 마감 시간 초기화
        setCloseTime();
    });
  
    // 평일 오픈 시간 변경 시 마감 시간 업데이트
    document.getElementById("select_dayopen_hour").addEventListener("change", setCloseTime);
  




// 주말 마감 시간 설정
function setWeekendCloseTime() {
    var openHour = parseInt(document.getElementById("select_weekendopen_hour").value);
    var closeSelect = document.getElementById("select_weekendclose_hour");

    // 선택한 시작 시간에 따라 마감 시간 옵션을 변경
    closeSelect.innerHTML = ""; // 이전 옵션 제거

    // 시작 시간을 선택하지 않은 경우 마감 시간 비활성화
    if (isNaN(openHour)) {
        var disabledOption = document.createElement("option");
        disabledOption.text = "마감 시간을 선택하세요.";
        closeSelect.appendChild(disabledOption);
        closeSelect.disabled = true;
        return;
    }

    // 시작 시간 다음 시간부터 마감 시간 옵션 생성
    for (var i = openHour + 1; i <= 23; i++) {
        var option = document.createElement("option");
        option.value = i;
        option.text = (i < 10 ? "0" : "") + i + ":00";
        closeSelect.appendChild(option);
    }

    closeSelect.disabled = false; // 마감 시간 활성화
}

// 문서가 로드될 때 초기 설정 적용
document.addEventListener("DOMContentLoaded", function() {
    // 주말 오픈 시간 옵션 생성
    for (var i = 0; i <= 23; i++) {
        var option = document.createElement("option");
        option.value = i;
        option.text = (i < 10 ? "0" : "") + i + ":00";
        document.getElementById("select_weekendopen_hour").appendChild(option);
    }

    // 마감 시간 초기화
    setWeekendCloseTime();
});

// 주말 오픈 시간 변경 시 마감 시간 업데이트
document.getElementById("select_weekendopen_hour").addEventListener("change", setWeekendCloseTime);








// 점심 마감 시간 설정
function setLunchTime() {
    var openHour = parseInt(document.getElementById("select_lunchopen_hour").value);
    var closeSelect = document.getElementById("select_lunchclose_hour");

    // 선택한 시작 시간에 따라 마감 시간 옵션을 변경
    closeSelect.innerHTML = ""; // 이전 옵션 제거

    // 시작 시간을 선택하지 않은 경우 마감 시간 비활성화
    if (isNaN(openHour)) {
        var disabledOption = document.createElement("option");
        disabledOption.text = "마감 시간을 선택하세요.";
        closeSelect.appendChild(disabledOption);
        closeSelect.disabled = true;
        return;
    }

    // 시작 시간 다음 시간부터 마감 시간 옵션 생성
    for (var i = openHour + 1; i <= 23; i++) {
        var option = document.createElement("option");
        option.value = i;
        option.text = (i < 10 ? "0" : "") + i + ":00";
        closeSelect.appendChild(option);
    }

    closeSelect.disabled = false; // 마감 시간 활성화
}

// 문서가 로드될 때 초기 설정 적용
document.addEventListener("DOMContentLoaded", function() {
    // 점심 오픈 시간 옵션 생성
    for (var i = 0; i <= 23; i++) {
        var option = document.createElement("option");
        option.value = i;
        option.text = (i < 10 ? "0" : "") + i + ":00";
        document.getElementById("select_lunchopen_hour").appendChild(option);
    }

    // 마감 시간 초기화
    setLunchTime();
});

// 점심 오픈 시간 변경 시 마감 시간 업데이트
document.getElementById("select_lunchopen_hour").addEventListener("change", setLunchTime);








document.getElementById("phoneNumber").addEventListener("input", function() {
    // 숫자 이외의 문자 제거
    var phoneNumber = this.value.replace(/[^\d]/g, '');
    
    // 숫자 8자리까지만 입력 가능하도록 제한
    if (phoneNumber.length > 8) {
        phoneNumber = phoneNumber.slice(0, 8);
    }

    // 7자리면 OOO-OOOO 형식으로 변경
    if (phoneNumber.length === 7) {
        phoneNumber = phoneNumber.replace(/(\d{3})(\d{4})/, '$1-$2');
    }

    // 8자리면 OOOO-OOOO 형식으로 변경
    if (phoneNumber.length === 8) {
        phoneNumber = phoneNumber.replace(/(\d{4})(\d{4})/, '$1-$2');
    }
    
    // 변경된 전화번호를 입력칸에 다시 넣음
    this.value = phoneNumber;
});



    let doctorCount = 1; // 초기 의사 카운트 값
$(document).ready(function() {
    // 의사 정보 추가 버튼 클릭 시
    $('.addDoctorBtn').click(function() {
        // 새로운 의사 정보 입력 필드 생성
        let newDoctorInfo = `
            <div class="input_wrap doctor_info">
                <div class="input_item">
					<div class="imgViewDiv" width="200px">
						<img class="img-view">
					</div>
		            <table class="doctors">
		              <tr>
		                <th>이름</th>
		                <td>
			                <div class="input_item">
			                
					  <input type="text" class="doc" name="doctorName" placeholder="이름을 입력하세요." required></li>
					<input type="file" id="docImgInput" class="doc" name="imageFile" accept=".jpg, .png, .jpeg" onchange="loadImg(this);" >
			                  <button type="button" class="btn_primary outline sm deleteDoctorBtn">삭제</button>
							</div>
		                </td>
		              </tr>
		                        <tr>
		                            <th>학력</th>
		                            <td>
		                                <input type="text" class="doc" name="doctorEducation" placeholder="학력을 입력하세요." required>
		                            </td>
		                        </tr>
		                        <tr>
		                            <th>경력</th>
		                            <td>
		                                <input type="text" class="doc" name="doctorExperience" placeholder="예시) 20.03~21.11 강서병원장" required>
		                            </td>
		                        </tr>
		                        
		                        <tr>
		                        	<th>진료과</th>
		                        	<td>
		          <select class="specialty" name="subjectNo">
		          		<option>선택하세요</option>
		                <option value="1">내과</option>
		                <option value="2">신경과</option>
		                <option value="3">정신건강의학과</option>
		                <option value="4">외과</option>
		                <option value="5">정형외과</option>
		                <option value="6">신경외과</option>
		                <option value="7">심장혈관흉부외과</option>
		                <option value="8">성형외과</option>
		                <option value="9">마취통증의학과</option>
		                <option value="10">산부인과</option>
		                <option value="11">소아청소년과</option>
		                <option value="12">안과</option>
		                <option value="13">이비인후과</option>
		                <option value="14">피부과</option>
		                <option value="15">비뇨의학과</option>
		                <option value="16">영상의학과</option>
		                <option value="17">방사선종양학과</option>
		                <option value="18">병리과</option>
		                <option value="19">진단검사의학과</option>
		                <option value="20">재활의학과</option>
		                <option value="21">결핵과</option>
		                <option value="22">예방의학과</option>
		                <option value="23">가정의학과</option>
		                <option value="24">핵의학과</option>
		                <option value="25">직업환경의학과</option>
		                <option value="26">응급의학과</option>
		                <option value="27">구강안악면외과</option>
		                <option value="28">치과</option>
		                <option value="29">한의원</option>
		              </select>
		                        	
		                        	</td>
		                        </tr>
		                        
		                    </table>
                </div>
            </div>
        `;

        // 새로운 의사 정보를 컨테이너에 추가
        $('#doctorContainer').append(newDoctorInfo);

        // 의사 카운트 증가
        doctorCount++;
    });

    // 의사 정보 삭제 버튼 클릭 시
    $(document).on('click', '.deleteDoctorBtn', function() {
        $(this).closest('.doctor_info').remove(); // 클릭된 삭제 버튼의 부모 요소인 .doctor_info를 삭제
    });
});



	












    // 숫자만 입력되도록 하는 함수
    function validateNumberInput(input) {
        // 입력값에서 숫자 이외의 모든 문자를 제거
        input.value = input.value.replace(/[^\d]/g, '');
    }
    
    // 세 자리 단위로 콤마를 추가하는 함수
    function formatCostInput(input) {
        // 입력값에서 숫자와 콤마 이외의 모든 문자를 제거
        let value = input.value.replace(/[^\d]/g, '');
        
        // 입력값이 없는 경우 빈 문자열 반환
        if (!value) {
            input.value = '';
            return;
        }
        

            // 최대  6자리까지만 입력 가능하도록 제한
    if (value.length > 5) {
        value = value.slice(0, 5);
    }
        
        
        // 입력 필드에 적용
        input.value = value;
    }
    
 
    // cost 클래스를 가진 모든 입력 필드에 대해 이벤트 리스너 추가
    document.addEventListener('DOMContentLoaded', function() {
        let costInputs = document.querySelectorAll('.cost');
        
        costInputs.forEach(function(input) {
            input.addEventListener('input', function() {
                validateNumberInput(this);
                formatCostInput(this);
            });
        });
    });
    
    
    
// 의사 경력 입력 필드 선택
let doctorExperienceInputs = document.querySelectorAll('input[name^="doctor_experience_"]');

// 입력 내용이 변경될 때마다 호출되는 이벤트 핸들러 함수 정의
function handleInputChange(event) {
    // 입력 내용의 길이가 20자를 초과하는지 확인
    if (event.target.value.length > 20) {
        // 20자로 잘라내고 다시 입력 필드에 설정
        event.target.value = event.target.value.slice(0, 20);
    }
}

// 모든 의사 경력 입력 필드에 이벤트 핸들러 함수를 연결
doctorExperienceInputs.forEach(input => {
    input.addEventListener('input', handleInputChange);
});

  
    