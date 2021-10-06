<%-- 
    Document   : product-month-stats
    Created on : Oct 6, 2021, 11:25:43 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center text-success">THONG KE DOANH THU THEO THANGM</h1>

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
  <canvas id="myProductMonthStatsChart"></canvas>
</div>

<table class="table">
    <tr>
        <th>Thang</th>
        <th>Doanh thu</th>
    </tr>
    <c:forEach items="${productMonthStats}" var="p">
    <tr>
        <td>${p[0]}/${p[1]}</td>
        <td>${p[2]} VND</td>
    </tr>
    </c:forEach>
</table>

<script>
    let productMonthLabels=[], productMonthInfo=[];
    
    <c:forEach items="${productMonthStats}" var="pm">
        productMonthLabels.push('${pm[0]}/${pm[1]}')
        productMonthInfo.push(${pm[2]})
    </c:forEach>
        
    window.onload = function(){
        productMonthChart("myProductMonthStatsChart", productMonthLabels, productMonthInfo);
    } 
</script>
