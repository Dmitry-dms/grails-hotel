<div class="table-row" id="${country?.id}">

    <td class="custom" >
        <div class="elems-pad">
            ${country?.name}
        </div>

    </td>

    <td class="custom" style="width:100%;">
        <div class="container">
            <div class="row">
                <div class="col-sm" style="align-self:center">
                    ${country?.capital}
                </div>
                <div class="col-sm">
                    <div class="btn-group" role="group" aria-label="Basic example">
                        <g:link class="edit" action="edit" resource="${country}">
                            <button type="submit" class="btn btn-primary" style="margin-right:10px">
                                <g:message code="button.edit"/>
                            </button>
                        </g:link>
                        <g:form resource="${country}" method="DELETE">
                            <button type="submit" class="btn btn-primary">
                                <g:message code="button.delete"/>
                            </button>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </td>

</div>