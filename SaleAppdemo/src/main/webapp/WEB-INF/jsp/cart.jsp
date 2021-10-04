<%-- 
    Document   : cart
    Created on : Oct 4, 2021, 11:20:26 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center text-success">Gio hang</h1>

<c:if test="${carts == null}">
    <h4 class="text-danger">Khong co san pham trong gio</h4>
</c:if>
    
<c:if test="${carts != null}">
    <table class="table">
        <tr>
            <th>Ma san pham</th>
            <th>Ten san phma</th>
            <th>Don gia</th>
            <th>So luong</th>
            <th>Xoa</th>
        </tr>
        <c:forEach items="${carts}" var="c">
            <tr id="Product${c.productId}">
                <td>${c.productId}</td>
                <td>${c.productName}</td>
                <td>${c.price} VND</td>
                <td>${c.quantity}</td>
                <td>
                    <div class="form-group">
                        <input type="number" onblur="updateCart(this, ${c.productId})"
                               value="${c.quantity}" class="form-control" />                       
                    </div>
                </td>
                <td>
                    <input onclick="deleteCart(${c.productId})" type="button" value="Xoa" class="btn btn-danger" />    
                </td>
            </tr>
        </c:forEach>    
    </table>
   <input type="button" value="Thanh toan" class="btn btn-danger" />    
    
</c:if>    