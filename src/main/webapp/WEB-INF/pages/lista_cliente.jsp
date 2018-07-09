<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>

<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <title>Listar produtos</title>
</head>
<body style="margin: 20px">
<div class="container">
    <div class="row">
        <div class="col-12 col-md-12">
            <header>
                <ul class="nav nav-tabs">
                    <li><a href="/">Cadastrar produtos</a></li>
                    <li class="active"><a href="/listarProdutos">Listar produtos</a></li>
                </ul>
            </header>
            <br>
            <c:if test="${not empty produtos}">
                <div class="row">
                    <div class="col-md-6">
                        <table class="table table-striped">
                            <thead>
                            <tr>
                                <th>ID</th>
                                <th>Nome</th>
                                <th>Preço</th>
                                <th>Quantidade</th>
                                <th>Data de cadastro</th>
                                <th>Ações</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="produto" items="${produtos}">
                                <tr>
                                    <td>${produto.id}</td>
                                    <td>${produto.nome}</td>
                                    <td>R$ ${produto.preco}</td>
                                    <td>${produto.quantidade}</td>
                                    <td><fmt:formatDate pattern="dd/MM/yyyy" value="${produto.dataDeCadastro}" /></td>
                                    <td>
                                        <form class="formTableButton">
                                            <button class="tableButton btn btn-xs btn-warning" type="submit" formaction="/editarProduto?id=${produto.id}" formmethod="POST" style="margin-bottom: 4%">Editar</button>
                                            <button class="tableButton btn btn-xs btn-danger" type="submit" formaction="/removerProduto?id=${produto.id}" formmethod="POST" style="margin-bottom: 4%">Deletar</button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty produtos}">
                Lista de produtos está vazia!
            </c:if>
        </div>
    </div>
</div>
</body>
</html>