<%-- 
    Document   : product-detail
    Created on : Sep 13, 2021, 5:19:46 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center text-danger">CHI TIET SAN PHAM</h1>
<div class="row"> 
    <div class="col-md-6">

            <c:choose>
                   <c:when test="${productId.image != null && productId.image.startsWith('http') == true }">
                       <img class="card-img-top img-fluid" src="${productId.image}" alt="${productId.name}"/>
                   </c:when>
                   <c:when test="${productId.image == null || productId.image.startsWith('http') == false }" >
                        <img class="card-img-top img-fluid" src="<c:url value="/images/Uyen.jpg" />" alt="${productId.name}"/>
                   </c:when>
            </c:choose>

    </div>
    <div class="col-md-6">
        <h1>${productId.name}</h1>
        <h3 class="text-danger">${productId.price} VND</h3>
        <p>${productId.description}</p>
        <div>
            <input type="button" value="Dat hang" class="btn btn-danger"  />
        </div>
    </div>
</div>
        
        
<br></br>
        
<form>
    <div class="form-group" style="padding: 10px">
        <label for="comment">Binh luan:</label>
        <textarea class="form-control" cols="6" id="commentId" placeholder="Nhập bình luận..."></textarea>
        <button type="button" onclick="addComment(${productId.id})" class="btn btn-primary">Gui binh luan</button>
    </div>   
</form> 
<div class="container">
    <i id="button" class="fa fa-thumbs-up"></i>
</div>

<div id="commentArea">
    <c:forEach items="${comment}" var="comments" >
    <div class="row">
        <div class="col-md-2" style="padding: 2px">
            <img src="https://res.cloudinary.com/dwsyse8jk/image/upload/v1630358291/uuylm0wbfblm3bfd1jdn.png"" class="rounded-circle img-fluid" alt="Cinque Terre">
        </div>
        <div class="col-md-10 mydate">
            <p>${comments.content}</p>
            <i>${comments.createdDate}</i>
        </div>
    </div>
    <br></br>
    </c:forEach>
</div>
<ul class="pagination">
    <c:forEach begin="1" end="${Math.ceil(countComment/3)}" var="page">
        <li class="page-item"><a class="page-link" href="<c:url value="/products/${productId.id}"/>?page=${page}">${page}</a></li>
    </c:forEach>    
</ul>    


<script>
        $("#button").click(function(){
            var allClass = this.classList.toString();
            if (allClass.indexOf('active') == -1) {
                this.classList += ' active';
            }
            if (allClass.indexOf('active') !== -1) {
                document.getElementById("button").classList.remove("active");
            }
        })
</script>

<script>
    window.onload = function() {
        let dates = document.querySelectorAll(".mydate > i")
        for (var i = 0; i < dates.length; i++) {
            let d = dates[i]
            d.innerText = moment(d.innerText).fromNow();
        }
    }
</script>



 
     