package ControladorAtracciones;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import services.AtraccionService;

@WebServlet("/attractions/edit.do")
public class EditAtraccionesServlet extends HttpServlet {

	private static final long serialVersionUID = 7598291131560345626L;
	private AtraccionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AtraccionService();
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		Atraccion attraction = attractionService.find(id);
		req.setAttribute("attraction", attraction);

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/attractions/edit.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer id = Integer.parseInt(req.getParameter("id"));
		
		String nombre = req.getParameter("nombre");
		Double costo = Double.parseDouble(req.getParameter("costo"));
		Double duracion = Double.parseDouble(req.getParameter("duracion"));
		Integer cupoActual = Integer.parseInt(req.getParameter("cupoActual"));
		Integer cupoMaximo = Integer.parseInt(req.getParameter("cupoMaximo"));
		Integer posicionX = Integer.parseInt(req.getParameter("posicionX"));
		Integer posicionY = Integer.parseInt(req.getParameter("posicionY"));
		
		//
		String preferencias = req.getParameter("preferencias");
		System.out.println(preferencias);
		//
		
		Atraccion attraction = attractionService.update(id, nombre, costo, duracion, cupoActual, cupoMaximo, posicionX, posicionY, preferencias);
		if (attraction.isValid()) {
			resp.sendRedirect("/TP003/attractions/index.do");
		} else {
			req.setAttribute("attraction", attraction);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/attractions/edit.jsp");
			dispatcher.forward(req, resp);
		}

	}

}