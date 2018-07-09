package com.github.leandropa.entrevista.controle;

import com.github.leandropa.entrevista.dominio.Produto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PreDestroy;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class ControleProduto {

	private EntityManagerFactory mFactory = Persistence.createEntityManagerFactory("produtos");
	private Logger logger = Logger.getLogger("ControleProduto");

	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request) {
		logger.info("Carregando pagina inicial");
		Produto produto = (Produto) request.getSession().getAttribute("produto");
		logger.info("Produto da sessão: " + produto);
		if (produto != null && produto.getId() > 0) {
			model.addAttribute("action", "Editar");
			request.getSession().removeAttribute("produto");
		} else {
			model.addAttribute("action", "Cadastrar");
			produto = new Produto();
		}
		logger.info("Retornar mensagem de sucesso: " + request.getSession().getAttribute("sucesso"));
		if (request.getSession().getAttribute("sucesso") != null) {
			model.addAttribute("sucesso", true);
			request.getSession().removeAttribute("sucesso");
		}
		model.addAttribute("produto", produto);
		return "index";
	}

	@RequestMapping(value = "/alterarProduto", method = RequestMethod.POST)
	public String alterarProduto(@ModelAttribute("produto") Produto produto, HttpServletRequest request) {
		logger.info("Recebendo produto para alterar: " + produto);
		boolean foiEditado = false;
		if (produto != null && produto.validate()) {
			foiEditado = produto.getId() > 0;//request.getSession().getAttribute("action").equals("Editar");

			logger.info("Foi editado? " + foiEditado);
			EntityManager manager = getEntityManager();
			manager.getTransaction().begin();
			if (foiEditado)
				manager.merge(produto);
			else
				manager.persist(produto);
			manager.getTransaction().commit();

			manager.close();
			if (!foiEditado)
				request.getSession().setAttribute("sucesso", true);
			logger.info("Produto alterado");
		}
		return  "redirect:/" + ( foiEditado ? "listarProdutos" : "");
	}

	@RequestMapping(value = "/editarProduto", method = RequestMethod.POST)
	public String editarProduto(@RequestParam(required = false) long id, HttpServletRequest request) {
		logger.info("Recebendo produto para editar com id: " + id);
		EntityManager manager = getEntityManager();
		Produto produto = manager.find(Produto.class, id);
		logger.info("Produto achado: " + produto);
		if (produto != null)
			request.getSession().setAttribute("produto", produto);
		return "redirect:/";
	}
	@RequestMapping(value = "/removerProduto", method = RequestMethod.POST)
	public String removerProduto(@RequestParam(required = false) long id) {
		logger.info("Recebendo produto para deletar com id: " + id);
		EntityManager manager = getEntityManager();
		manager.getTransaction().begin();
		int resultado = manager.createQuery("delete from Produto where id = " + id).executeUpdate();
		logger.info("Resultado da transação: " + resultado);
		manager.getTransaction().commit();
		manager.close();
		logger.info("Produto removido");
		return "redirect:/listarProdutos";
	}


	@RequestMapping(value = "/listarProdutos", method = RequestMethod.GET)
	public String listarProdutos(HttpServletRequest request) {
		logger.info("Recebendo requisicao para listar produto");
		EntityManager manager = getEntityManager();
		logger.info("Consultando banco de dados");
		List<Produto> produtos = manager.createQuery("select p from Produto as p").getResultList();
		request.getSession().setAttribute("produtos", produtos);
		manager.close();
		logger.info("Total de produtos encontrados: " + produtos.size());
		return "lista_cliente";
	}

	private EntityManager getEntityManager() {
		return mFactory.createEntityManager();
	}

	@PreDestroy
	public void close() {
		if (mFactory != null)
			mFactory.close();
	}
}
