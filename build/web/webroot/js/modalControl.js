/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function modalUpdate(id){
    $.ajax({
        url: 'modifier',
        type: 'GET',
        data: "id="+id,
        dataType: "JSON",  
        success:function(data,status){
            addDataToModalUpdate(data);
            $('#modal').show();
        },
        error:function(data,statut,c){
            alert("Ce que vous demandez n'existe pas");
        },
        complete:function(data,statut){

        }

    });
}
function modalInsert(tacheMere, idTacheMere){
    addDataToModalInsert(tacheMere, idTacheMere);
    $('#modal').show();
}
$(document).ready(function(){
    
    $(".showModal").click(function(valeur){
        //$('#modal').show();
        alert(valeur);
    });
    
    $(".hideModal").click(function(){
        $('#modal').hide();
    });
    
    $(".maDate").focus(function(){
        $('.modal-warning').show();
    });
    
    
    
    $("#post").click(function(){
    		var title = $('#title').val();
    		var content = $('#content').val();
    	$.ajax({
            url: 'add_post.php',
            type: 'POST',
            data: "title="+title+"&content="+content+"&user=toavina RALAMBOSOA",
            dataType: "JSON",  
            success:function(data,status){
                    createPost(data,'oldPost');
            },
            error:function(data,statut,c){

            },
            complete:function(data,statut){

            }

    	});
    });

    $("#form").on("submit",function(e){
    	e.preventDefault();
    	var email = $('#email').val();
    	var phone = $('#phone').val();
    	var ismail = isEmail(email);
    	var isphone = isPhoneNumber(phone);
    	//alert(email);
    	if (ismail && isphone) {
    		console.log("it's true");
    	}else{
    		console.log("misy diso");
    	}
    	
    });


  });


