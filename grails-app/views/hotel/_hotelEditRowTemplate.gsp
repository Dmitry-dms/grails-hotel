<div class="table-row" id="${hotel?.id}">

    <td class="custom">
        ${hotel?.name}
        <g:if test="${hotel.site != null}">
            <g:link target="_blank" base="${hotel.site}"><g:message code="table.site" /></g:link>
        </g:if>
    </td>

        <td class="custom">
            ${hotel?.country?.name}
        </td>
    <td class="custom">
        <div class="container">
            <div class="row">
                <div class="col align-items-start">
                    ${hotel?.stars}
                </div>
                <div class="col align-items-end">
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <g:link class="edit" action="edit" resource="${hotel}">
                            <button type="submit" class="btn btn-primary" style="margin-right:10px">
                                <g:message code="button.edit" />
                            </button>
                        </g:link>
                        <g:form resource="${hotel}" method="DELETE">
                            <button type="submit" class="btn btn-primary">
                                <g:message code="button.delete" />
                            </button>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </td>

</div>