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
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title>Новый user</title>
        <meta name="description" content="add user Cabinet">
        <meta name="viewport" content="width=1200, minimal-ui">

        <link href="<c:url value="/css/main.css" />" rel="stylesheet">
        <script src="<c:url value="/js/libs/modernizr/modernizr-2.8.3.min.js " />"></script>


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
                    <a href="#" class="logo">
                        <!--<i class="icon-logo"></i>-->
                    </a>
                </div>
                <div class="header-links">          
                    <div class="user-menu-block dropdown">
                        <a class="user-menu" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            <span class="userpic">
                                <img src="http://www.fillmurray.com/32/32" alt="userpic">
                            </span>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li>
                                <a class="last-li" href="/">
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
                    <h1>Настройки профиля</h1>
                    <a href="#" id="saveAccount" class="btn btn-primary">Сохранить изменения</a>
                </div>
                <div class="page-content">
                    <div class="row">
                        <div class="col-9">
                            <div class="tab-content">
                                <div class="tab-pane fade in active" role="tabpanel" id="user-settings-general">
                                    <h5 class="section">Данные для регистрации </h5>
                                    <form:form action="${pageContext.servletContext.contextPath}/portal/publisher/createApp" 
                                               method="post" 
                                               commandName="userAccountForm" 
                                               id="my-form" 
                                               >
                                        <div class="form-group">
                                            <label for="fullname">Имя</label>
                                            <input type="text"  
                                                   class="form-control col-4" 
                                                   placeholder="Константин" 

                                                   data-min-length="3" 
                                                   data-max-length="255" 
                                                   name="name"
                                                   >
                                        </div>

                                        <div class="form-group">
                                            <select class="form-control selectize narrow col-4" name="appType">
                                                <option value="IOS">IOS</option>
                                                <option value="ANDROID">ANDROID</option>
                                                <option value="WEBSITE">WEBSITE</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <select class="form-control selectize narrow col-4" name="contentType">
                                                <option value="VIDEO">VIDEO</option>
                                                <option value="IMAGE">IMAGE</option>
                                                <option value="HTML">HTML</option>
                                            </select>
                                        </div>
                                    </div>                  
                                </div>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>

        <script src="<c:url value="/js/libs/jquery/jquery.min.js" />"></script>

        <!-- -->
        <script src="<c:url value="/js/main.min.js" />"></script>

        <script>
            $.fn.popelValidator.setRuleMessage({
                'not-empty': "<spring:message code="NOT-EMPTY"/>",
                'min-length': "<spring:message code="MIN-LENGTH"/>",
                'max-length': "<spring:message code="MAX-LENGTH"/>"
            });
//            $("#saveAccount").on('click', function () {
//                $('#my-form').submit();
//
//            });
            $("#saveAccount").click(function () {
                $("#my-form").submit();
            });
        </script>
    </body>
</html>
