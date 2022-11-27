<%-- 
    Document   : clientes
    Created on : 31-ago-2022, 17:14:19
    Author     : ACER
Omar Camilo Castellanos Ávila
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Listado de Clientes</h1>
                <ul>
            <c:forEach var="cliente" items="${clientes}">
                <li>${cliente.idCliente} ${cliente.nombre} ${cliente.saldo}</li>
            </c:forEach>
        </ul>
    </body>
</html>
