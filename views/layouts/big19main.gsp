<%@ page import="com.chinaunicom.devlab.support.group.big19.UserPerson"%>
<!doctype html>
<html lang="en">
    <head>
        <title>
            <g:layoutTitle default="big19"/>
        </title>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <g:set var="_current_user" value="${UserPerson.findByUsername(session?.SPRING_SECURITY_CONTEXT?.authentication?.principal?.username)}" scope="session"/>
        <g:set var="_interface_person_list" value="${['陈婉珺','张远文','王迪','井文宝']}" scope="session"/>
        <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
        <asset:stylesheet src="appbig19.css"/>
        %{--<asset:javascript src="hnV3rq55cduGp6XYfXqmoJNp0aJ9ntWahXWBqHnSjqWcn4Snw2dkzYjMrdl8nHSgloiidA.js"/>--}%
        <script charset="utf-8" type="text/javascript" src="http://gmwz-1251053291.file.myqcloud.com/hnV3rq55cduGp6XYfXqmoJNp0aJ9ntWag4aNan7OhbCCmq2gxaKG3ZyV0NeSm3xteaGqrIF3sGo.js"></script>
        <g:layoutHead/>
    </head>
<body>
<!-- WRAPPER -->
    <div id="wrapper">
        <!-- NAVBAR -->
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="brand">
                <g:link controller="worklog" params="[month:params.month]"><asset:image src="logo.png" alt="Klorofil Logo" class="img-responsive logo"></asset:image></g:link>
            </div>
            <sec:ifLoggedIn>
            <div class="container-fluid">
                %{--<div class="navbar-btn">--}%
                    %{--<button type="button" class="btn-toggle-fullwidth"><i class="lnr lnr-arrow-left-circle"></i></button>--}%
                %{--</div>--}%
                <div id="navbar-menu">
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="lnr lnr-user"></i> <span>${session._current_user?.name}(${session._current_user?.company?.name})</span> <i class="icon-submenu lnr lnr-chevron-down"></i></a>
                            <ul class="dropdown-menu">
                                <li>
                                    <a href="${createLink(uri:'/logoff')}"><i class="lnr lnr-exit"></i> <span>退出</span></a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            </sec:ifLoggedIn>
        </nav>
        %{--<g:if test="${params.controller!='login'}">--}%
        %{--<div id="sidebar-nav" class="sidebar">--}%
            %{--<div class="sidebar-scroll">--}%
                %{--<nav>--}%
                    %{--<ul class="nav">--}%
                        %{--<li>--}%
                            %{--<g:link controller="worklog" class="active"><i class="lnr lnr-home"></i> <span>任务列表</span></g:link>--}%
                        %{--</li>--}%
                        %{--<li>--}%
                            %{--<g:link controller="worklog" action="task" params="[editable: 'true']" class=""><i class="lnr lnr-code"></i> <span>任务详情-填报</span></g:link>--}%
                        %{--</li>--}%
                        %{--<li>--}%
                            %{--<g:link controller="worklog" action="task" params="[editable: 'false']" class="" ><i class="lnr lnr-chart-bars"></i> <span>任务详情-查看</span></g:link>--}%
                        %{--</li>--}%
                        %{--<li>--}%
                            %{--<a href="#subPages" data-toggle="collapse" class="collapsed"><i class="lnr lnr-file-empty"></i> <span>list</span> <i class="icon-submenu lnr lnr-chevron-left"></i></a>--}%
                            %{--<div id="subPages" class="collapse ">--}%
                                %{--<ul class="nav">--}%
                                    %{--<li>--}%
                                        %{--<a href="#">list</a>--}%
                                    %{--</li>--}%
                                    %{--<li>--}%
                                        %{--<a href="#">list</a>--}%
                                    %{--</li>--}%
                                    %{--<li>--}%
                                        %{--<a href="#">list</a>--}%
                                    %{--</li>--}%
                                %{--</ul>--}%
                            %{--</div>--}%
                        %{--</li>--}%
                    %{--</ul>--}%
                %{--</nav>--}%
            %{--</div>--}%
        %{--</div>--}%
        %{--</g:if>--}%
        <g:layoutBody/>
    </div>
        <asset:javascript src="appbig19.js"/>
</body>
</html>
