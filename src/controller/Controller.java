package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import model.DAO;

/**
 * The Class Controller.
 */
@WebServlet(urlPatterns = {"/Controller", "/main", "/cadastro", "/pagina_altera", "/mudar", "/deletar","/relatorio"})
public class Controller extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
    /**
     * Instantiates a new controller.
     */
    public Controller() {
    }

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String rota = request.getServletPath();
		
		if (rota.equals("/main")) {
			listarContatos(request,response);
		}else if (rota.contains("/pagina_altera")) {
			String[] contato = dao.procuraContato(request.getParameter("_id"));
			request.setAttribute("con", contato);
			RequestDispatcher rd = request.getRequestDispatcher("alterar.jsp");
			rd.forward(request, response);
		}else if(rota.contains("/deletar")) {
			dao.deletarContato(request.getParameter("_id"));
			listarContatos(request,response);
		}else if (rota.equals("/relatorio")) {
			gerarRelatorio(request,response);
		}
		
	}
	
	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String rota = request.getServletPath();
		
		if (rota.equals("/cadastro")) {
			String nome =request.getParameter("nome");
			String tel = request.getParameter("telefone");
			String email= request.getParameter("email");
			dao.salvarContato(nome, tel, email);
			listarContatos(request,response);
		}if(rota.equals("/mudar")) {
			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String tel = request.getParameter("telefone");
			String email = request.getParameter("email");
			dao.alteraContato(id, nome, tel, email);
			listarContatos(request,response);
		}
	}
	
	
	/**
	 * Listar contatos.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	public void listarContatos (HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		ArrayList<String[]> contatos = dao.listarContatos();
		request.setAttribute("cont", contatos);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request,response);
	}
	
	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ServletException the servlet exception
	 */
	public void gerarRelatorio (HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		Document documento = new Document();
		ArrayList<String[]> contatos = dao.listarContatos();
		try {
			
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "inline; filename=contatos.pdf");
			PdfWriter.getInstance(documento, response.getOutputStream());
			PdfPTable tabela = new PdfPTable(3);
									
			PdfPCell cabecalho1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell cabecalho2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell cabecalho3 = new PdfPCell(new Paragraph("Email"));
			tabela.addCell(cabecalho1);
			tabela.addCell(cabecalho2);
			tabela.addCell(cabecalho3);
			contatos.stream().forEach(str->{
				tabela.addCell(str[1]);
				tabela.addCell(str[2]);
				tabela.addCell(str[3]);
			});
			documento.open();
			documento.add(new Paragraph("Lista de contatos:\n\n"));
			documento.add(tabela);
			documento.close();
		}catch(Exception ex) {
			System.err.println("Erro:"+ex.getMessage());
			documento.close();
		}
	}
}
