package ControladorPromociones;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.PromocionesService;

@WebServlet("/promociones/delete.do")
public class DeletePromocionesServlet extends HttpServlet {

	private static final long serialVersionUID = 1537949074766873118L;
	private PromocionesService promocionesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionesService = new PromocionesService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		System.out.println("DeletePromocionesServlet, id -> " + id);
		promocionesService.delete(id);

		resp.sendRedirect("/TP003/promociones/index.do");
	}


}

