package ControladorPromociones;

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

import services.ComprarPromocionesService;

@WebServlet("/promociones/buy.do")
public class ComprarPromocionesServlet extends HttpServlet {

	private static final long serialVersionUID = 3455721046062278592L;
	private ComprarPromocionesService comprarPromocionesService;

	@Override
	public void init() throws ServletException {
		super.init();
		this.comprarPromocionesService = new ComprarPromocionesService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer promocionId = Integer.parseInt(req.getParameter("id"));
		Usuario user = (Usuario) req.getSession().getAttribute("user");
		Map<String, String> errors = comprarPromocionesService.buy(user.getId(), promocionId);
		
		Usuario user2 = DAOFactory.getUsuarioDAO().findById(user.getId());
		req.getSession().setAttribute("user", user2);
		
		if (errors.isEmpty()) {
			req.setAttribute("flash", "¡Gracias por comprar!");
		} else {
			req.setAttribute("errors", errors);
			req.setAttribute("flash", "No ha podido realizarse la compra");
		}

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/promociones/index.do");
		dispatcher.forward(req, resp);
	}
}

