
$("#btn").on("click",function(){
    $(".star-on").css("width","0px"); //초기화 작업
    //const star2 = $(".star-wrap2>span");
    //star2.css("color","lightgray"); //초기화 작업 
    const score = $("#score").val();
    //console.log(score);
    /*
    for(let i=0; i<score ;i++){
        star2.eq(i).css("color","gold");
    }
    */
    
    const changeWidth = score*30; //별 하나 사이즈가 30 이니까
    //$(".star-on").css("width",changeWidth+"px");
    $(".star-on").animate({"width":changeWidth+"px"},3000); //차오르는 시간이 5초 걸림

});
