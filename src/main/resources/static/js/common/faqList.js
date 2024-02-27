document.addEventListener('DOMContentLoaded', function() {
 	const accordionItems = document.querySelectorAll('.accordion-item');

	accordionItems.forEach(item => {
	 const header = item.querySelector('.accordion-header');
	 const toggleBtn = header.querySelector('.toggle-btn');
	 const content = item.querySelector('.accordion-content');
	 const subAccordion = item.querySelector('.accordion-sub');

	// 1. 처음에는 subAccordion이 hide 상태
	 if (subAccordion) {
	 	subAccordion.style.display = 'none';
	 	}

	toggleBtn.addEventListener('click', function() {
		 item.classList.toggle('active');
		 // 2. header의 toggleBtn 클릭 시 subAccordion이 펼쳐짐
		 if (subAccordion) {
		 subAccordion.style.display = item.classList.contains('active') ? 'block' : 'none';
		
		// 2-1. subAccordion에서 content는 hide 상태
		 content.style.display = 'none';
	 	}
	
	// 2-2. subAccordion의 toggleBtn 클릭 시 content가 토글
		 if (subAccordion) {
		 const subToggleBtn = subAccordion.querySelector('.toggle-btn');
		 subToggleBtn.addEventListener('click', function() {
			 content.style.display = content.style.display === 'none' ? 'block' : 'none';
		 	});
		 }
		
		toggleBtn.textContent = item.classList.contains('active') ? 'expand_less' : 'expand_more';
		 });
	 });
});