document.addEventListener('DOMContentLoaded', function() {
    const accordionItems = document.querySelectorAll('.accordion-item');

    accordionItems.forEach(item => {
        const header = item.querySelector('.accordion-header');
        const toggleBtn = header.querySelector('.toggle-btn');
        const contentAll = item.querySelectorAll('.accordion-content');
        const subAccordionItems = item.querySelectorAll('.accordion-sub');

        // 1. 처음에는 subAccordion이 hide 상태
        if (subAccordionItems) {
            subAccordionItems.forEach(subAccordionItem => {
                subAccordionItem.style.display = 'none';
            });
        }

        toggleBtn.addEventListener('click', function() {
            item.classList.toggle('active');
            // 2. header의 toggleBtn 클릭 시 subAccordion이 펼쳐짐
            if (subAccordionItems) {
                subAccordionItems.forEach(subAccordionItem => {
                    subAccordionItem.style.display = item.classList.contains('active') ? 'block' : 'none';
                });

            }

           subAccordionItems.forEach(subAccordionItem => {
			    const subAccordion = subAccordionItem.querySelector('.accordion-sub');
			    const content = subAccordionItem.querySelector('.accordion-content');
			    const subToggleBtn = subAccordionItem.querySelector('.toggle-btn');
				
				
			    // 2-2. subAccordion의 toggleBtn 클릭 시 content가 토글
			    if (!subAccordion) {
			        subToggleBtn.addEventListener('click', function() {
			            content.style.display = content.style.display === 'none' ? 'block' : 'none';
			            subToggleBtn.textContent = content.style.display === 'none' ? 'expand_more' : 'expand_less';
			        });
			    }
			});

            toggleBtn.textContent = item.classList.contains('active') ? 'expand_less' : 'expand_more';
        });
    });
});