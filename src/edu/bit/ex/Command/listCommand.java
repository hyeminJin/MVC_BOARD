package edu.bit.ex.Command;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.bit.ex.DAO.DAO;
import edu.bit.ex.DTO.DTO;

public class listCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		DAO dao = new DAO();
		ArrayList<DTO> dto = dao.list();
		request.setAttribute("list", dto);

	}

}
