<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.6.2.min.js"></script>
    <title>${action} produto</title>
</head>
<body style="margin: 20px">
<div class="container">
    <div class="row">
        <div class="col-12 col-md-12">
            <header>
                <ul class="nav nav-tabs">
                    <li class="active"><a href="/">${action} produto</a></li>
                    <li><a href="/listarProdutos">Listar produtos</a></li>
                </ul>
            </header>
            <form:form id="form" class=".form-inline" method="POST" action="/alterarProduto" modelAttribute="produto">

                <div class="form-group" id="id">
                    <form:label path="id">Id:</form:label>
                    <form:input path="id" class="form-control" placeholder="ID do produto" value="${id}" readonly="true"/>
                </div>
                <div class="form-group">
                    <form:label path="nome">Nome:</form:label>
                    <form:input path="nome" class="form-control" placeholder="Nome do produto" value="${nome}"/>
                </div>
                <div class="form-group">
                    <form:label path="preco">Preço:</form:label>
                    <form:input type="number" path="preco" class="form-control" placeholder="R$ 0,00" step="0.01" value="${preco}"/>
                </div>
                <div class="form-group">
                    <form:label path="quantidade">Quantidade:</form:label>
                    <form:input type="number" path="quantidade" class="form-control" placeholder="Quantidade de produtos" value="${quantidade}"/>
                </div>
                <div class="form-group">
                    <form:label path="dataDeCadastro">Data de registro:</form:label>
                    <form:input type="date" id="dataDeCadastro" path="dataDeCadastro" class="form-control" placeholder="Data de cadastro" max="${dataDeCadastro}" value="${dataDeCadastro}"/>
                </div>
                <button type="submit" id="enviar" class="btn btn-primary">${action}</button>
            </form:form>


            <script type="text/javascript">
                function mostrarAlert(type, error) {
                    var msg = $('#alert-msg');
                    if (msg != null)
                        msg.remove();
                    $('div.col-12').append('<div id="alert-msg" class="alert alert-' + type + '" role="alert">' + error + "</div>");
                    $('#alert-msg').delay(5000).fadeOut(500);
                }
                function validarNumero(tipo, numero, min, maxLength) {
                    if (numero <= min) {
                        mostrarAlert('danger', tipo + ' precisa ser um número maior que ' + min + '!');
                        return false;
                    }
                    if (numero.legend > maxLength) {
                        mostrarAlert('danger', 'Valor muito alto para ' + tipo.toLowerCase() + ' do produto!');
                        return false;
                    }
                    return true;
                }
                $(document).ready(function() {
                    $('div#id').hide();
                    if (${not empty sucesso})
                        mostrarAlert("success", "Produto cadastrado com sucesso!");
                    $("form").submit(function() {
                        console.log($("#dataDeCadastro").val());
                        var nome = $("#nome").val().trim();
                        if (nome.length === 0) {
                            mostrarAlert('danger', 'O nome do produto não pode estar vazio!');
                            return false;
                        }
                        if (nome.length > 255) {
                            mostrarAlert('danger', 'O nome do produto é muito grande!');
                            return false;
                        }
                        if (!validarNumero('O preço', $("#preco").val(), 0, 17) ||
                                !validarNumero('A quantidade', $("#quantidade").val(), 0, 9))
                            return false;
                        if ($("#dataDeCadastro").val().trim().length === 0) {
                            mostrarAlert('danger', 'A data de registro não pode ficar em branco.');
                            return false;
                        }
                        return true;
                    });
                });
            </script>
        </div>
    </div>
</div>
</body>
</html>