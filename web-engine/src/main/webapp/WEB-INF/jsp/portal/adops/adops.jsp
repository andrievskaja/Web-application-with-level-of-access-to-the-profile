<!doctype html>
<%@ page import="java.text.*,java.util.*" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html class="no-js">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="" content="IE=edge,chrome=1">
        <title>adops-panel</title>
        <meta name="description" content="admin Cabinet">
        <meta name="viewport" content="width=1200, minimal-ui">

        <link href="<c:url value="/css/main.css" />" rel="stylesheet">             
        <!-- Cached build -->
        <script src="<c:url value="/js/libs/modernizr/modernizr-2.8.3.min.js " />"></script>
        <!-- / 
        <!-- -->

        <style>

            .page-preloader {
                position: fixed;
                left: 0;
                top: 0;
                right: 0;
                bottom: 0;
                background: #fff;
                z-index: 999999;
            }

            .loader {
                position: absolute;
                top: 50%;
                left: 50%;
                margin-left: -40px;
                margin-top:-40px;
                border: 3px solid #dbe0e3; /* Light grey */
                border-top: 3px solid #16d977; /* Blue */
                border-radius: 50%;
                width: 80px;
                height: 80px;
                animation: spin 2s linear infinite;
            }

            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
            }
        </style>
    </head>
    <body>
        <div class="page-preloader">
            <div class="loader"></div>
        </div>

        <div class="layout-header" data-adapt-to="modal">
            <div class="layout-container">
                <div class="header-logo">
                    <a href="#" class="logo"  >
                        <!--<i><img src="../images/java.jpg"></i>-->
                    </a>
                </div>
                <div class="header-links">           
                    <div class="user-menu-block dropdown">
                        <a class="user-menu"  type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <span class="userpic" id="logout">
                                <img src="http://www.fillmurray.com/32/32" alt="userpic">
                            </span>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li role="separator" class="divider"></li>
                            <li>
                                <a class="last-li" href="#">
                                    <span class="li-text">Sign out</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <div class="layout-content">
            <div class="layout-container">
                <div class="page-header">
                    <h1>Пользователи <span class="badge" id="users-size">${users.size()}</span></h1>
                    <a href="<c:url value="/portal/adops/add-user"/>" class="btn btn-primary">Добавить пользователя</a>
                </div>
                <div class="page-content">
                    <div class="table-container">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Все пользователи</th>
                                    <th></th>
                                    <th>Имя</th>
                                    <th></th>
                                    <th class="cell-align-right">Операции</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="user" items="${users}">
                                    <tr id="record-tr-${user.id}" class="merchant-inactive">
                                        <td>
                                            <div class="merchant-info">
                                                <span class="label label-inactive" id="email-${user.id}">${user.email}</span>
                                            </div>
                                            <a class="link-small">${user.role}</a>
                                        </td>
                                        <td> <a href="#"  onclick="edit(${user.id});" id="edit-user-href" class="link-small">Редактировать </a></td>
                                        <td id="name-${user.id}">${user.name}</td>
                                        <td><a href="<c:url  value="/portal/adops/add-app-page?userId=${user.id}"/>">add app</a></td>
                                        <td class="cell-align-right">
                                            <div  class="merchant-delete dropdown">
                                                <a onclick="deleteRecord(${user.id},${users.size()})" href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                    <i class="icon-bin"></i>
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                    <br>
                    <h2>APP <span class="badge" id="app-size">${apps.size()}</span></h2>
                    <div class="table-container">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th>Все app</th>
                                    <td></td>
                                    <th>ContentType</th>
                                    <th>AppType</th>           
                                    <th class="cell-align-right">Операции</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="app" items="${apps}">
                                    <tr id="app-tr-${app.id}" class="merchant-inactive">
                                        <td>
                                            <div class="merchant-info">
                                                <span class="label label-inactive" id="name-${app.id}">${app.name}</span>
                                            </div>
                                        </td>
                                        <td> <a href="#"  onclick="editApp(${app.id},${app.user.id});" id="edit-user-href" class="link-small">Редактировать </a></td>

                                        <td id="contentType-${app.id}">${app.contentType}</td>
                                        <td  id="appType-${app.id}">${app.appType}</td>
                                        <td class="cell-align-right">
                                            <div  class="merchant-delete dropdown">
                                                <a onclick="deleteApp(${app.id},${app.user.id},${apps.size()})" href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                    <i class="icon-bin"></i>
                                                </a>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!--user modal window-->
        <div class="modal fade" id="edit-user-modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-content">
                    <form id="my-form" name="user-form" >
                        <div class="form-group">
                            <label for="fullname">Имя</label>
                            <input type="text"  
                                   class="form-control col-4" 
                                   placeholder="Константин" 
                                   id="name"
                                   data-min-length="3" 
                                   data-max-length="255" 
                                   name="name"
                                   >
                        </div>
                        <input hidden="true" id="edit-user-id">
                        <input hidden="true" value="ROLE_PUBLISHER" name="role">
                        <div class="form-group">
                            <label for="email">E-mail</label>
                            <input type="email" 
                                   id="email" 
                                   class="form-control col-4"
                                   placeholder="example@example.com" 
                                   name="email"
                                   >
                        </div>
                        <div class="form-group">
                        </div>
                        <p>
                            <button  type="submit" class="btn btn-primary btn-small">Редактировать</button>
                        </p>

                    </form>
                </div>                  
            </div>
        </div>
        <!--app modal window-->
        <div class="modal fade" id="edit-app-modal" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-content">
                    <form id="app-form" name="user-form" >
                        <div class="form-group">
                            <label for="fullname">Имя</label>
                            <input type="text"  
                                   class="form-control col-4" 
                                   placeholder="Константин" 
                                   id="name-app"
                                   data-min-length="3" 
                                   data-max-length="255" 
                                   name="name"
                                   >
                        </div>
                        <input hidden="true" id="appId" name="id">
                        <input hidden="true" id="userId" name="userId">
                        <div class="form-group">
                            <select class="form-control selectize narrow col-4" id="appType" name="appType">
                                <option value="IOS">IOS</option>
                                <option value="ANDROID">ANDROID</option>
                                <option value="WEBSITE">WEBSITE</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <select class="form-control selectize narrow col-4" id="contentType" name="contentType">
                                <option value="VIDEO">VIDEO</option>
                                <option value="IMAGE">IMAGE</option>
                                <option value="HTML">HTML</option>
                            </select>
                        </div>
                        <div class="form-group">
                        </div>
                        <p>
                            <button  type="submit" class="btn btn-primary btn-small">Редактировать</button>
                        </p>

                    </form>
                </div>                  
            </div>
        </div>
        <script src="<c:url value="/js/libs/jquery/jquery.min.js" />"></script>

        <script src="<c:url value="/js/main.min.js" />"></script>
        <form:form action="/portal/user/logout" id="form-logout"/>
        <script>
                                                    $("#logout").click(function () {
                                                        $("#form-logout").submit();
                                                    });
                                                    var editApp = function (id, userID) {
                                                        $("#edit-app-modal").modal('show');
                                                        $.post('${pageContext.servletContext.contextPath}/portal/adops/getApp', {id: id, userId:userID}, function (data) {
                                                            console.log(data.name);
                                                            $("#name-app").val(data.name);
                                                            $("#appId").val(id);
                                                            $("#userId").val(userID);
                                                        });
                                                    };
                                                    var edit = function (id) {
                                                        $("#edit-user-modal").modal('show');
                                                        $.post('${pageContext.servletContext.contextPath}/portal/adops/getOne', {id: id}, function (data) {
                                                            $("#email").val(data.email);
                                                            $("#name").val(data.name);
                                                            $("#edit-user-id").val(id);
                                                        });
                                                    };
                                                    $('#my-form').on('submit', function (event) {
                                                        event.preventDefault();
                                                        $.post('${pageContext.servletContext.contextPath}/portal/adops/editUser', {email: $('#email').val(), name: $('#name').val(), role: "ROLE_PUBLISHER", userId: $("#edit-user-id").val()}, function (data) {
                                                            console.log(data);
                                                            $("#edit-user-modal").modal('toggle');
                                                            $("#email-" + data.id).text(data.email);
                                                            $("#name-" + data.id).text(data.name);
                                                        });
                                                    });
                                                    $('#app-form').on('submit', function (event) {
                                                        event.preventDefault();
                                                        $.post('${pageContext.servletContext.contextPath}/portal/adops/editApp',
                                                        {name: $('#name-app').val(), userId: $("#userId").val(), id:$("#appId").val(),
                                                        appType:$("#appType").val(),contentType:$("#contentType").val()}, 
                                                        function (data) {
                                                            console.log(data);
                                                            $("#edit-app-modal").modal('toggle');              
                                                            $("#name-" + data.id).text(data.name);
                                                            $("#appType-" + data.id).text(data.appType);
                                                            $("#contentType-" + data.id).text(data.contentType);
                                                        });
                                                    });

                                                    var deleteRecord = function (id, size) {
                                                        $.post('${pageContext.servletContext.contextPath}/portal/adops/delete', {id: id}, function (data) {
                                                            if (data === true) {
                                                                $("#record-tr-" + id).remove();
                                                                $("#users-size").text(size - 1);
                                                            }
                                                        });
                                                    };
                                                    var deleteApp = function (id, userId, size) {
                                                        $.post('${pageContext.servletContext.contextPath}/portal/adops/deleteApp', {id: id,userId:userId}, function (data) {
                                                            if (data === true) {
                                                                $("#app-tr-" + id).remove();
                                                                $("#app-size").text(size - 1);
                                                            }
                                                        });
                                                    };
        </script>
    </body>
</html>
