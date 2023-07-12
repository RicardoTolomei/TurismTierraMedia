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

@WebServlet("/attractions/create.do")
public class CrearAtraccionServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private AtraccionService attractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.attractionService = new AtraccionService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/attractions/create.jsp");
		dispatcher.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nombre = req.getParameter("nombre");
		Double costo = Double.parseDouble(req.getParameter("costo"));
		Double duracion = Double.parseDouble(req.getParameter("duracion"));
		Integer cupoActual = Integer.parseInt(req.getParameter("cupoActual"));
		Integer cupoMaximo = Integer.parseInt(req.getParameter("cupoMaximo"));
		Integer posicionX = Integer.parseInt(req.getParameter("posicionX"));
		Integer posicionY = Integer.parseInt(req.getParameter("posicionY"));
		
		String preferencias = req.getParameter("preferencias");
		System.out.println("valor tomado ->" + preferencias);
		
		Atraccion attraction = attractionService.create(nombre, costo, duracion, cupoActual, cupoMaximo, posicionX, posicionY, preferencias);
		if (attraction.isValid()) {
			resp.sendRedirect("/TP003/attractions/index.do");
		} else {
			req.setAttribute("attraction", attraction);

			RequestDispatcher dispatcher = getServletContext()
					.getRequestDispatcher("/views/attractions/create.jsp");
			dispatcher.forward(req, resp);
		}

	}

}
