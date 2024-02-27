document.addEventListener('DOMContentLoaded', function() {
  const accordionItems = document.querySelectorAll('.accordion-item');

  accordionItems.forEach(item => {
    const header = item.querySelector('.accordion-header');

    header.addEventListener('click', function() {
      item.classList.toggle('active');
      const content = item.querySelector('.accordion-content');
      content.classList.toggle('show', item.classList.contains('active'));

      const subAccordion = item.querySelector('.accordion-sub');
      if (subAccordion) {
        subAccordion.classList.toggle('show', item.classList.contains('active'));
      }
    });
  });
});