<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="card mx-auto" style="width:450px">
    <div id="create-country" class="content scaffold-create" role="main">
        <g:if test="${flash.error}">
            <ul class="errors" role="alert">
                <g:message error="${flash.error}"/>
            </ul>
        </g:if>
        <g:form resource="${this.hotel}" method="POST" action="update">
            <fieldset class="form">
                <f:field bean="hotel" property="name" label="${g.message(code: "hotel.update.name")}"/>
                <f:field bean="hotel" property="site" label="${g.message(code: "hotel.update.site")}"/>
                <f:field bean="hotel" property="stars" label="${g.message(code: "hotel.update.stars")}"/>
                <div class="row">
                    <div class="col-md-auto" style="align-self:center">
                        <g:message code="hotel.update.country"/>
                    </div>
                    <div class="col-md-auto">
                        <g:select class="form-control" value="${hotel?.country.id}" name="country"
                                  from="${countries}"
                                  optionValue="name"
                                  optionKey="id"
                        />
                    </div>
                </div>
                <button type="submit" class="btn pmd-btn-raised pmd-ripple-effect btn-primary"
                        style="margin-top:20px; right:0;">
                    <g:message code="button.update"/>
                </button>
            </fieldset>
        </g:form>
    </div>
</div>

</body>
</html>
