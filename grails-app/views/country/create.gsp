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
        <g:form resource="${this.country}" method="POST">
                <fieldset class="form">
                    <f:field bean="country" property="name" label="Название"/>
                    <f:field bean="country" property="capital" label="Столица"/>
                    <form action="save" method="post" style="margin-top:20px">
                        <button type="submit" class="btn pmd-btn-raised pmd-ripple-effect btn-primary" style="margin-top:20px; right:0;">Создать</button>
                    </form>
                </fieldset>
        </g:form>
    </div>
</div>

</body>
</html>
