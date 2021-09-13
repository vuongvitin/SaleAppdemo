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
        <textarea class="form-control" cols="6" id="comment" placeholder="Nhập bình luận..."></textarea>
        <button type="submit" class="btn btn-primary">Gui binh luan</button>
    </div>   
</form> 

<div class="row">
    <div class="col-md-2" style="padding: 2px">
        <img src="<c:url value="/images/Uyen.jpg" />" class="rounded-circle img-fluid" alt="Cinque Terre">
    </div>
    <div>
        <p> Tuyet voi!!</p>
        <i>13/9/2021 6:11</i>
    </div>
</div>
<br></br>    
<div class="row">
    <div class="col-md-2" style="padding: 2px">
        <img src="<c:url value="/images/Uyen.jpg" />" class="rounded-circle img-fluid" alt="Cinque Terre">
    </div>
    <div>
        <p> Tuyet voi anh oi!!</p>
        <i>13/9/2021 6:11</i>
    </div>
</div>    
     