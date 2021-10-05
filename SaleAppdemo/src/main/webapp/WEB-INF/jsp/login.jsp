<%-- 
    Document   : login
    Created on : Aug 28, 2021, 5:56:43 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:url value="/login" var="action"  />

<h1 class="text-center text-danger">DANG NHAP</h1>

<c:if test="${param.error != null}">
    <div class="alert alert-danger"> 
        <p>Da co loi xay ra! Vui long quay lai sau!</p>
    </div>
</c:if>

<c:if test="${param.accessDenied != null}">
    <div class="alert alert-danger"> 
        Bạn khong co quyen truy cap!
    </div>
</c:if>

<form method="post" action="${action}">
    <div class="form-group"> 
        <label for="username">Username</label>
        <input type="text" id="username" name = "username" class="form-control"/> 
    </div>
    <div class="form-group"> 
        <label for="password">Password</label>
        <input type="password" id="password" name = "password"" class="form-control"/> 
    </div>
    <div class="form-group">
        <input type="submit" value="DANG NHAP" class="btn btn-danger"/>
    </div>
</form>