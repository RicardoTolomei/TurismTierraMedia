package ControladorPromociones;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promocion;

import services.PromocionesService;

@WebServlet("/promociones/edit.do")
public class EditPromocionesServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private PromocionesService promocionesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionesService = new PromocionesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));

		Promocion promocion = promocionesService.find(id);
		req.setAttribute("promocion", promocion);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promociones/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Integer id = Integer.parseInt(req.getParameter("id"));
		String nombre = req.getParameter("nombre");
		Integer tipoDePromocion = Integer.parseInt(req.getParameter("tipodepromocion"));
		Double costoTotal = Double.parseDouble(req.getParameter("costototal"));
		Integer descuentoPorcentual = Integer.parseInt(req.getParameter("descuentoporcentual"));
		String preferencias = req.getParameter("preferencias");
		Integer atraccion1 = Integer.parseInt(req.getParameter("atraccion1"));
		Integer atraccion2 = Integer.parseInt(req.getParameter("atraccion2"));
		Integer atraccionP = Integer.parseInt(req.getParameter("atraccionP"));
		
		Promocion promocion = promocionesService.update(id, nombre, tipoDePromocion, costoTotal, descuentoPorcentual, atraccion1, atraccion2, atraccionP, preferencias);
		
		System.out.println("posterioremnte actualizacion");
		
		if (promocion.isValid()) {
			resp.sendRedirect("/TP003/promociones/index.do");
		} else {
			req.setAttribute("promocion", promocion);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promociones/edit.jsp");
			dispatcher.forward(req, resp);
		}
	}
}

