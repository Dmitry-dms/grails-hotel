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
                <f:field bean="hotel" property="name" label="${g.message(code: "hotel.update.name")}"/>
                <f:field bean="hotel" property="site" label="${g.message(code: "hotel.update.site")}"/>
                <f:field bean="hotel" property="stars" label="${g.message(code: "hotel.update.stars")}"/>
                <f:field bean="hotel" property="country" label="${g.message(code: "hotel.update.country")}"/>
<!--                <fieldset class="embedded hotel">-->
<!--                    <f:field bean="hotel" property="country.name" label="Страна"/>-->
<!--                </fieldset>-->
                <button type="submit" class="btn pmd-btn-raised pmd-ripple-effect btn-primary"
                        style="margin-top:20px; right:0;"><g:message code="button.create" />
                </button>
            </fieldset>
        </g:form>
    </div>
</div>

</body>
</html>