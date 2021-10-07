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
            <li class="nav-item">
                <a href="<c:url value="/cart"/>" class="nav-link text-warning h5">
                    <i class="fas fa-shopping-cart"></i>
                    <div class="badge badge-danger" id="cartCounter">${cartCounter}</div>
                </a>
            </li>
            <c:if test="${pageContext.request.userPrincipal.name == null}">
                <li class="nav-item">
                    <a href="<c:url value="/login"/>" class="nav-link text-warning h5">
                        <i class="fas fa-user">Dang nhap</i>
                    </a>
                </li>
            </c:if>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <li class="nav-item">
                    <a href="<c:url value="/"/>" class="nav-link text-success h5">                        
                        <c:if test="${currentUser.avatar != null}" >
                            <img style="width: 40px" class="rounded-circle" src="${currentUser.avatar}"  alt="userImg"/>
                        </c:if>
                        <c:if test="${currentUser.avatar == null}" >
                            <i class="fas fa-user">${pageContext.request.userPrincipal.name}</i>
                        </c:if>  
                    </a>
                </li>
                <li class="nav-item">
                    <a href="<c:url value="/logout"/>" class="nav-link text-success h5">
                        <i class="">Dang xuat</i>
                    </a>
                </li>
            </c:if> 
            <li class="nav-item">
                <a href="<c:url value="/register"/>" class="nav-link text-warning h5">
                     <i class="fas fa-check-circle">Dang Ky</i>
                </a>
            </li>
            

  </div>
</nav>

        

