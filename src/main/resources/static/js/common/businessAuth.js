document.getElementById('fileInput').addEventListener('change', handleFileSelect);

function handleFileSelect(event) {
  const files = event.target.files;
  const fileList = document.getElementById('fileList');
  const maxFiles = 2; // 최대 파일 개수

  // 현재 업로드된 파일 개수 확인
  const currentFileCount = fileList.getElementsByTagName('li').length;
  const remainingFiles = maxFiles - currentFileCount;

  if (files.length > remainingFiles) {
    alert('최대 2개까지 업로드할 수 있습니다.');
    // 파일 선택을 취소하고 이전에 선택한 파일을 다시 표시합니다.
    event.target.value = null;
    return;
  }

  for (let i = 0; i < files.length; i++) {
    const file = files[i];
    const li = document.createElement('li');
    const deleteBtn = document.createElement('button');
    const img = document.createElement('img');
    const fileNameSpan = document.createElement('span');

    img.src = URL.createObjectURL(file);
    img.onload = function () {
      URL.revokeObjectURL(this.src);
    };

    fileNameSpan.textContent = file.name;

    deleteBtn.textContent = '삭제';
    deleteBtn.addEventListener('click', function () {
      li.remove();
      updateFileCount();
    });
    li.appendChild(fileNameSpan);
    li.appendChild(img);
    li.appendChild(deleteBtn);
    fileList.appendChild(li);
  }

  updateFileCount();
}

function updateFileCount() {
  const fileList = document.getElementById('fileList');
  const fileCount = fileList.getElementsByTagName('li').length;
  const fileCountElement = document.getElementById('fileCount');
  const nextbtn = document.getElementsByName('nextbtn');

  fileCountElement.textContent = '총 ' + fileCount + '개 파일';

  // 파일 개수가 2개 이상이면 파일 선택 버튼을 비활성화합니다.
  if (fileCount >= 2) {
    document.getElementById('fileInput').disabled = true;
  } else {
    document.getElementById('fileInput').disabled = false;
  }
}


document.getElementById('nextbtn').addEventListener('click', function() {
  // 대표자명 입력란
  const representativeInput = document.querySelector('input[name="representative"]');
  // 사업자등록번호 입력란
  const representativeNoInput = document.getElementById('businessNumberInput');
  const fileList = document.getElementById('fileList');
  const fileCount = fileList.getElementsByTagName('li').length;

  // 파일 개수가 2개 미만인 경우에 경고창 표시
  if (representativeInput.value.trim() === '' || representativeNoInput.value.trim() === '') {
    alert('대표자명과 사업자등록번호를 입력하세요.');
  }
   
  if (fileCount < 2) {
    alert('최소 2개의 파일을 업로드해야 합니다.');
    event.preventDefault(); 
  }
});





// 사업자 등록번호 입력 필드에 자동으로 하이픈 추가하고 입력 제한 구현
const businessNumberInput = document.getElementById('businessNumberInput');
businessNumberInput.addEventListener('input', function () {
  let trimmedValue = this.value.replace(/[^0-9]/g, ''); // 숫자 이외의 문자 제거
  if (trimmedValue.length > 10) {
    trimmedValue = trimmedValue.slice(0, 10); // 길이 제한
  }
  if (trimmedValue.length >= 3 && trimmedValue.length < 6) {
    this.value = trimmedValue.replace(/^(\d{3})/, '$1-'); // 3글자 입력 후 하이픈 추가
  } else if (trimmedValue.length >= 6) {
    this.value = trimmedValue.replace(/^(\d{3})(\d{2})/, '$1-$2-'); // 5글자 입력 후 하이픈 추가
  } else {
    this.value = trimmedValue;
  }
});
