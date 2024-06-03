<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Editar Cliente</title>
</head>
<body>
    <h1>Editar Cliente</h1>
    
    <g:if test="${flash.error}">
        <div class="alert alert-danger">${flash.error}</div>
    </g:if>

    <g:if test="${flash.message}">
        <div class="alert alert-success">${flash.message}</div>
    </g:if>

    <g:form action="update" method="post">
        <g:hiddenField name="id" value="${customer?.id}"/>
        

        <div class="form-group">
            <label for="name">Nome:</label>
            <g:fieldValue bean="${customer}" field="name" class="form-control" readonly="true"/>
        </div>
        
        <div class="form-group">
            <label for="email">Email:</label>
            <g:fieldValue bean="${customer}" field="email" class="form-control" readonly="true"/>
        </div>

        <div class="form-group">
            <label for="street">Rua:</label>
            <g:textField name="address.street" value="${customer?.address?.street}" class="form-control" required=""/>
        </div>

        <div class="form-group">
            <label for="number">Número:</label>
            <g:textField name="address.number" value="${customer?.address?.number}" class="form-control" required=""/>
        </div>

        <div class="form-group">
            <label for="province">Bairro:</label>
            <g:textField name="address.province" value="${customer?.address?.province}" class="form-control" required=""/>
        </div>

        <div class="form-group">
            <label for="city">Cidade:</label>
            <g:textField name="address.city" value="${customer?.address?.city}" class="form-control" required=""/>
        </div>

        <div class="form-group">
            <label for="state">Estado:</label>
            <g:textField name="address.state" value="${customer?.address?.state}" class="form-control" required=""/>
        </div>

        <div class="form-group">
            <label for="complement">Complemento:</label>
            <g:textField name="address.complement" value="${customer?.address?.complement}" class="form-control"/>
        </div>

        <div class="form-group">
            <label for="zipCode">zipCode:</label>
            <g:textField name="address.zipCode" value="${customer?.address?.zipCode}" class="form-control" required=""/>
        </div>

        <div class="form-group">
            <g:submitButton name="update" class="btn btn-primary" value="Atualizar"/>
            <g:link action="index" class="btn btn-secondary">Cancelar</g:link>
        </div>
    </g:form>
</body>
</html>