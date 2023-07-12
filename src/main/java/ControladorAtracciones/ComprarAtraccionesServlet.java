package ControladorAtracciones;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Usuario;
import persistence.commons.DAOFactory;
import services.ComprarAtraccionesService;

@WebServlet("/attractions/buy.do")
public class ComprarAtraccionesServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private ComprarAtraccionesService buyAttractionService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.buyAttractionService = new ComprarAtraccionesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer attractionId = Integer.parseInt(req.getParameter("id"));
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		Map<String, String> errors = buyAttractionService.buy(user.getId(), attractionId);
		
		Usuario user2 = DAOFactory.getUsuarioDAO().findById(user.getId());
		req.getSession().setAttribute("user", user2);
		
		if (errors.isEmpty()) {
			req.setAttribute("flash", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/attractions/index.do");
		dispatcher.forward(req, resp);
	}
}

