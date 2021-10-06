<%-- 
    Document   : category-stats
    Created on : Oct 6, 2021, 4:43:14 PM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1 class="text-center text-danger">Thong ke san pham theo doanh muc</h1>

<div>
  <canvas id="myCateStatsChart"></canvas>
</div>

<table class="table">
    <tr>
        <th>Ma danh muc</th>
        <th>Ten danh muc</th>
        <th>So luong san pham</th>
    </tr>
    <c:forEach items="${cateStats}" var="ct">
    <tr>
        <td>${ct[0]}</td>
        <td>${ct[1]}</td>
        <td>${ct[2]}</td>
    </tr>
    </c:forEach>
</table>


<script>
    let cateLabels=[], cateInfo=[];
    
    <c:forEach items="${cateStats}" var="ct">
        cateLabels.push('${ct[1]}')
        cateInfo.push(${ct[2]})
    </c:forEach>
    
    window.onload = function(){
        cateChart("myCateStatsChart", cateLabels, cateInfo)
    }
</script>