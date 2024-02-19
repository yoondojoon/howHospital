//전화번호 -> 형식 재검토
const HOSPITAL_TEL = document.getElementById('hospital_tel');
//input 이벤트는 사용자가 입력 필드에 텍스트를 입력하거나 수정할 때마다 발생
HOSPITAL_TEL.addEventListener('input', function () {
  let trimmedValue = this.value.replace(/[^0-9]/g, ''); // 숫자 이외의 문자 제거
  if (trimmedValue.length > 10) {
    trimmedValue = trimmedValue.slice(0, 10); // 길이 제한
  }
  if (trimmedValue.length >= 2 && trimmedValue.length < 7) {
    this.value = trimmedValue.replace(/^(\d{2})/, '$1-'); // 3글자 입력 후 하이픈 추가
  } else if (trimmedValue.length >= 7 && trimmedValue.length <= 9) {
    this.value = trimmedValue.replace(/^(\d{2})(\d{3})/, '$1-$2'); // 하이픈 추가
  } else if (trimmedValue.length >= 10) {
    this.value = trimmedValue.replace(/^(\d{2})(\d{4})(\d{4})/, '$1-$2-$3'); // 하이픈 추가
  } else {
    this.value = trimmedValue;
  }
});




//의사정보
  $(document).ready(function() {
    let doctorCount = 1; // 초기 의사 카운트 값

    // 의사 정보 추가 버튼 클릭 시
    $('#addDoctorBtn').click(function(e) {
      e.preventDefault();
      
      // 새로운 의사 정보 입력 필드 생성
      let newDoctorInfo = `
        <div class="doctor_info" id="doctor${doctorCount}">
          <div class="doctor_img">
            <img src="https://upload.wikimedia.org/wikipedia/ko/thumb/4/4a/%EC%8B%A0%EC%A7%B1%EA%B5%AC.png/230px-%EC%8B%A0%EC%A7%B1%EA%B5%AC.png" alt="의사 사진">
          </div>
          <div class="doctor_detail">
            <table class="doctors">
              <tr>
                <th>이름</th>
                <td>
              
                  <input type="text" class="doc_name" name="doctor_name_${doctorCount}" placeholder="김길동" required></li>
              
               
                  <button type="button" class="deleteDoctorBtn">삭제</button>
             
                </td>
              </tr>
              <tr>
                <th>학력</th>
                <td>
<input type="text" name="doctor_education_${doctorCount}" placeholder="샤울대학교" required>
                </td>
              </tr>
              <tr>
                <th>경력</th>
                <td>

<input type="text" name="doctor_experience_${doctorCount}" placeholder="20.03~21.11 샤울대" required>
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
    
    $(document).on('click', '.deleteDoctorBtn', function() {
      $(this).closest('.doctor_info').remove(); // 클릭된 삭제 버튼의 부모 요소인 .doctor_info를 삭제
    });
  });

 