<%-- 
    Document   : product-stats
    Created on : Oct 6, 2021, 9:25:44 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center text-success">THONG KE DOANH THU THEO SAN PHAM</h1>

<form class="form" action="">
        <div class="form-group">
            <label  class="text-danger font-weight-bold h3"">Từ khoa: </label>
            <input type="text" name="kw" class="form-control"/>
        </div>
        <div class="form-group">
            <label  class="text-danger font-weight-bold h3"">Từ thoi diem:</label>
            <input type="date" name="fromDate" class="form-control"/>
        </div>
        <div class="form-group">
            <label class="text-danger font-weight-bold h3">đến thoi diem:</label>
            <input type="date" name="toDate" class="form-control"/>
        </div>        
        <button class="btn btn-success" type="submit">Báo cáo</button>
</form>


<div>
  <canvas id="myProductStatsChart"></canvas>
</div>

<table class="table">
    <tr>
        <th>Ma san pham</th>
        <th>Ten ten san pham</th>
        <th>Doanh thu</th>
    </tr>
    <c:forEach items="${productStats}" var="p">
    <tr>
        <td>${p[0]}</td>
        <td>${p[1]}</td>
        <td>${p[2]} VND</td>
    </tr>
    </c:forEach>
</table>

<script>
    let productLabels=[], productInfo=[];
    
    <c:forEach items="${productStats}" var="p">
        productLabels.push('${p[1]}')
        productInfo.push(${p[2]})
    </c:forEach>
        
    window.onload = function(){
        productChart("myProductStatsChart", productLabels, productInfo);
    } 
</script>