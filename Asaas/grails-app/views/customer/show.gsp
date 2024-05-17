<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Detalhes do Cliente</title>
</head>
<body>
    <h1>Detalhes do Cliente</h1>

    <div>
        <strong>Nome:</strong> ${customer.name}<br/>
        <strong>Email:</strong> ${customer.email}<br/>
        <strong>CPF/CNPJ:</strong> ${customer.cpfCnpj}<br/>
        <strong>Tipo de Pessoa:</strong> ${customer.personType}<br/>
        <h2>Endereço</h2>
        <strong>Rua:</strong> ${customer.address.street}<br/>
        <strong>Número:</strong> ${customer.address.number}<br/>
        <strong>Bairro:</strong> ${customer.address.neighborhood}<br/>
        <strong>Cidade:</strong> ${customer.address.city}<br/>
        <strong>Estado:</strong> ${customer.address.state}<br/>
        <strong>Complemento:</strong> ${customer.address.complement}<br/>
        <strong>CEP:</strong> ${customer.address.CEP}<br/>
    </div>
</body>
</html>
