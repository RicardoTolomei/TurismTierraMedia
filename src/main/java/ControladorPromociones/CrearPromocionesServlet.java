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

@WebServlet("/promociones/create.do")
public class CrearPromocionesServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private PromocionesService promocionesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.promocionesService = new PromocionesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/promociones/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nombre = req.getParameter("nombre");
		Integer tipoDePromocion = Integer.parseInt(req.getParameter("tipodepromocion"));
		Double costoTotal = Double.parseDouble(req.getParameter("costototal"));
		Integer descuentoPorcentual = Integer.parseInt(req.getParameter("descuentoporcentual"));
		String preferencias = req.getParameter("preferencias");
		Integer atraccion1 = Integer.parseInt(req.getParameter("atraccion1"));
		Integer atraccion2 = Integer.parseInt(req.getParameter("atraccion2"));
		Integer atraccionP = Integer.parseInt(req.getParameter("atraccionP"));

		Promocion promocion = promocionesService.create(nombre, tipoDePromocion, costoTotal, descuentoPorcentual, atraccion1, atraccion2, atraccionP, preferencias);
		
		if (promocion.isValid()) {
			resp.sendRedirect("/TP003/promociones/index.do");
		} else {
			req.setAttribute("promocion", promocion);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/attractions/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
