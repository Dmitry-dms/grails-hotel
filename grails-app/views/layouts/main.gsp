<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>
<div class="container">
    <header class="d-flex justify-content-center py-3">
        <ul class="nav nav-pills">
            <li class="nav-item"><a href="${createLink(uri: '/')}" class="nav-link active" aria-current="page"><g:message code="nav.main" /></a></li>
            <g:link class="nav-item" controller="country" action="index"><g:message code="nav.countries" /></g:link>
            <g:link class="nav-item" controller="hotel" action="index"><g:message code="nav.hotels" /></g:link>
        </ul>
    </header>
    <div class="h-divider"></div>
</div>

<div class="container-fluid">
    <g:layoutBody/>
</div>

<asset:javascript src="application.js"/>

</body>
</html>
