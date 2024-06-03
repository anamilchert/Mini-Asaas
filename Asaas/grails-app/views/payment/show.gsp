<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Mostrar Pagamento</title>
</head>
<body>
     <%-- <div>
      <input type="text" value="${payment.customer}">
      <input type="text" value="${payment.payer}">
      <input type="text" value="${payment.value}">
      <input type="text" value="${payment.type}">
      <input type="text" value="${payment.status}">
      <input type="text" value="${payment.dueDate}">
     </div> --%>
    <form>
        <input type="text" id="customer" name="customer" value="${payment?.customer}" disabled>
        <input type="text" id="payer" name="payer" value="${payment?.payer}" disabled>
        <input type="text" id="value" name="value" value="${payment?.value}">
        <input type="text" id="type" name="type" value="${payment?.type}">
        <input type="text" id="status" name="status" value="${payment?.status}" disabled>
        <input type="text" id="dueDate" name="dueDate" value="${payment?.dueDate}">
        <input type="submit" value="Submit" />
    </form>

</body>
</html>