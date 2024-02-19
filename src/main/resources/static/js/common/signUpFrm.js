$("#agreeAll").on("click", function() {
    
    
    const isChecked = $(this).is(":checked"); 
    

    

    if(isChecked){ 
        $("#agreeService").prop("checked",true);
        $("#agreeLocation").prop("checked",true);
        $("#agreeAge").prop("checked",true);
    }else{
        $("#agreeService").prop("checked",false);
        $("#agreeLocation").prop("checked",false);
        $("#agreeAge").prop("checked",false);     
    }


});



$("#agreeService").on("click",function(){

    const isChecked = $(this).is(":checked");

    if(!isChecked ){
        $("#agreeAll").prop("checked",false)
    };

});

$("#agreeLocation").on("click",function(){

    const isChecked = $(this).is(":checked");

    if(!isChecked ){
        $("#agreeAll").prop("checked",false)
    };

});

$("#agreeAge").on("click",function(){

    const isChecked = $(this).is(":checked");

    if(!isChecked ){
        $("#agreeAll").prop("checked",false)
    };

});




$("#agreeService, #agreeLocation, #agreeAge").on("click",function(){


    const agreeService = $("#agreeService").is(":checked");
    const agreeLocation = $("#agreeLocation").is(":checked");
    const agreeAge = $("#agreeAge").is(":checked");

    

    if(agreeService == true && agreeLocation == true && agreeAge == true){

        $("#agreeAll").prop("checked",true)
    };

});


