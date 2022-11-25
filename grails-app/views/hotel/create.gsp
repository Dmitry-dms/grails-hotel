<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>

</head>
<body>
<body>
<div class="card mx-auto" style="width:450px">
    <div class="content scaffold-create" role="main">
        <g:if test="${flash.error}">
            <ul class="errors" role="alert">
                <g:message error="${flash.error}"/>
            </ul>
        </g:if>
        <g:form resource="${this.hotel}" method="POST" action="save">
            <fieldset class="form">
                <f:field bean="hotel" property="name" label="Название"/>
                <f:field bean="hotel" property="site" label="Сайт"/>
                <f:field bean="hotel" property="stars" label="Звездность"/>
                <f:field bean="hotel" property="country" label="Страна"/>
<!--                <fieldset class="embedded hotel">-->
<!--                    <f:field bean="hotel" property="country.name" label="Страна"/>-->
<!--                </fieldset>-->
                <button type="submit" class="btn pmd-btn-raised pmd-ripple-effect btn-primary"
                        style="margin-top:20px; right:0;">Создать
                </button>
            </fieldset>
        </g:form>
    </div>
</div>

</body>
</html>