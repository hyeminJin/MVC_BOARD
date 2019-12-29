package edu.bit.ex.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.bit.ex.DAO.DAO;
import edu.bit.ex.DTO.DTO;

public class contentCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String bId = request.getParameter("bId");
		DAO dao = new DAO();
		DTO dto = dao.contentView(bId);
		request.setAttribute("content_view", dto);
	}

}
