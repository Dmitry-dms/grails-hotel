<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="card mx-auto" style="width:400px">
    <div id="create-country" class="content scaffold-create" role="main">
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
        <g:form resource="${this.country}" method="POST" action="update">
            <fieldset class="form">
                <f:field bean="country" property="name"/>
                <f:field bean="country" property="capital"/>
<!--                <g:link controller="country" action="update" resource="${this.country}">-->
                    <button type="submit" class="btn pmd-btn-raised pmd-ripple-effect btn-primary" style="margin-top:20px; right:0;">Обновить</button>
<!--                </g:link>-->
<!--                <form action="country/update" method="post" style="margin-top:20px">-->
<!--                    <button type="submit" class="btn pmd-btn-raised pmd-ripple-effect btn-primary" style="margin-top:20px; right:0;">Обновить</button>-->
<!--                </form>-->
            </fieldset>
        </g:form>
    </div>
</div>

</body>
</html>