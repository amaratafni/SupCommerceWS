package com.supinfo.supcommercews.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.supinfo.supcommercews.entity.Product;

public class PaymentServlet extends HttpServlet {

	private static final long serialVersionUID = 2536321436186889860L;

	/**
	 * Redirect the user to the payment form
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect("payment.jsp");
	}
	
	/**
	 * Check CB values and the date (after today)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		String numCB = request.getParameter("numCB");
		String cryptogramme = request.getParameter("cryptogramme");

		try {
			SimpleDateFormat formatter = new SimpleDateFormat("MM/yy");
			Date endValidation = formatter.parse(request.getParameter("endValidation"));
			
			Date today = new Date();
			
			if(numCB!=null && cryptogramme!=null && endValidation!=null && today.compareTo(endValidation) < 0) {
				// paiement accepté //
				request.getSession().setAttribute("shoppingCart", new ArrayList<Product>());
				request.setAttribute("paymentState", "Votre paiement a bien été effectué. Merci d'avoir utilisé cette application.");
				request.getRequestDispatcher("paymentFinished.jsp").forward(request, resp);
			} else {
				// paiement refusé //
				request.setAttribute("paymentState", "Votre paiement a été refusé.");
				request.getRequestDispatcher("paymentFinished.jsp").forward(request, resp);
			}
			
		} catch(ParseException ex) {
			System.err.println(ex.getMessage());
			resp.sendRedirect("payment");
		}
	}
}
