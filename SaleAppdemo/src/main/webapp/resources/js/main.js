/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function addToCart(productId){
    fetch(`/SpringMVCdemo1/api/cart/${productId}`).then(res => res.json()).then(data =>{
        var d = document.getElementById("cart-counter");
        if(d !== null)
            d.innerText = data;        
    });     
}

function addComment(productId){
    fetch("/TourApp/api/add-comment", {
        method: 'post',
        body: JSON.stringify({
            "content": document.getElementById("commentId").value,
            "productId": productId          
        }),
        headers: {
            "Content-Type": "application/json"
        }
    }).then(function(res){
        console.info(res);
        
        return res.json();
    }).then(function(data){
        console.info(data);
        
        let area = document.getElementById("commentArea");
        
        area.innerHTML = `<div class="row">
                <div class="col-md-2" style="padding: 2px">
                    <img src="https://res.cloudinary.com/dwsyse8jk/image/upload/v1630358291/uuylm0wbfblm3bfd1jdn.png" class="rounded-circle img-fluid" alt="Cinque Terre">
                </div>
                <div class="col-md-10 mydate">
                    <p>${data.content}</p>
                    <i>${moment(data.createdDate).fromNow()}</i>
                </div>
            </div>       
            `
            + area.innerHTML
    })
}


            
