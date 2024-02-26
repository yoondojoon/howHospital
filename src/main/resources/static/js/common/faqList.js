//sub-menu를 포함한 메뉴에 +버튼 자동으로 추가
$(".sub-menu").append("<span class='more'>+</span>")

$(".more").on("click",function(event){
    $(this).addClass("active");
    $(this).parent().next().slideToggle();
    event.stopPropagation();//이벤트버블링 제거
});

$(".more").parent().on("click",function(){
    $(this).children().last().click();
});