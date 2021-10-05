/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//function addToCart(productId){
//    fetch(`/SpringMVCdemo1/api/cart/${productId}`).then(res => res.json()).then(data =>{
//        var d = document.getElementById("cart-counter");
//        if(d !== null)
//            d.innerText = data;        
//    });     
//}

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
                    <img src="${data.user.avatar}" class="rounded-circle img-fluid" alt="Cinque Terre">
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

function addToCart(id, name, price){
    event.preventDefault()
    
    fetch("/TourApp/api/cart", {
        method: 'post',
        body: JSON.stringify({
            "productId": id,
            "productName": name,
            "price": price,
            "quantity": 1
        }),
        headers:{
            "Content-Type": "application/json"
        }
    }).then(function(res){
        return res.json();
    }).then(function(data){
        let counter = document.getElementById("cartCounter")
        counter.innerText = data
    })
}

function updateCart(obj, productId){
   fetch("/TourApp/api/cart", {
        method: 'put',
        body: JSON.stringify({
            "productId": productId,
            "productName": "",
            "price": 0,
            "quantity": obj.value
        }),
        headers:{
            "Content-Type": "application/json"
        }
    }).then(function(res){
        return res.json()
    }).then(function (data){
        document.getElementById("cartCounter").innerText = data.counter
        let amount = document.getElementById("amountCart")
        amount.innerText = data.amount
    })
}            

function deleteCart(productId){
    
    if(confirm("Ban co chac chan xoa khong???") == true){
            fetch(`/TourApp/api/cart/${productId}`, {
        method: 'delete'
        }).then(function(res){
            return res.json()
        }).then(function(data){
            document.getElementById("cartCounter").innerText = data.counter
            let amount = document.getElementById("amountCart")
            amount.innerText = data.amount
//            location.reload()
            let row = document.getElementById(`Product${productId}`)

            row.style.display = "none"
        }) 
    }  
}
     function pay(){
        if(confirm("Ban co chac chan thanh toan???") == true){
            fetch("/TourApp/api/pay", {
                method: "post"
            }).then(function(res){
                return res.json();
            }).then(function(code){
                console.info(code)
                
                location.reload();
            })
        }
}