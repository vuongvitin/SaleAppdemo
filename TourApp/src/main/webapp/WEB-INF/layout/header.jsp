<%-- 
    Document   : header
    Created on : Sep 13, 2021, 1:29:25 AM
    Author     : PC
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
  <!-- Brand -->
  <a class="navbar-brand" href="<c:url value="/" />">Trang chu</a>

  <!-- Toggler/collapsibe Button -->
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
    <span class="navbar-toggler-icon"></span>
  </button>

  <!-- Navbar links -->
  <div class="collapse navbar-collapse" id="collapsibleNavbar">
    <ul class="navbar-nav">
        <c:forEach var="cate" items="${categories}">
            <li class="nav-item">
                <c:url value="/" var="catPath">
                    <c:param name="CateId" value="${cate.id}"></c:param>
                </c:url>
              <a class="nav-link" href="${catPath}">${cate.name}</a>
            </li>
        </c:forEach>
    </ul>
    <form class="form-inline" action="<c:url value="/"></c:url>">
        <input class="form-control mr-sm-2" type="text" name="kw" placeholder="Nhap tu khoa tiem kiem">
        <button class="btn btn-success" type="submit">Tim kiem</button>
    </form>
  </div>
</nav>
        


