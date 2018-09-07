package com.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dao.Dao;
import com.dto.Dto;

@Controller
public class deptController {
	
	private Dao dao;

	@Autowired
	public void setNoticedao(Dao dao) {
		this.dao = dao;
	}
	
	@RequestMapping(value="/list.do")
	public String deptList(Model model) throws SQLException {
		List<Dto> dtoList = dao.searchAll();
		model.addAttribute("dtoList", dtoList);
		System.out.println("dtoList"+ dtoList);
		return "dept.list";
	}
	// insert 입니다
	@RequestMapping(value="/insert.do",method=RequestMethod.GET)
	public String insertForm() throws SQLException {
		
		return "dept.insert";
	}
	@RequestMapping(value="/insert.do",method=RequestMethod.POST)
	public String insert(int deptno, String dname, String loc) throws SQLException {
		String url=null;
		boolean result = dao.insert(deptno, dname, loc);
		if(result) {
			url = "redirect:list.do";
		}else {
			url="insert";
		}
		
		return url;
	}
	
	// update 입니다
		@RequestMapping(value="/update.do",method=RequestMethod.GET)
		public String updateForm(int deptno,Model model) throws SQLException {
			Dto dto = dao.search(deptno);
			
			model.addAttribute("dto", dto);
			return "dept.update";
		}
		@RequestMapping(value="/update.do",method=RequestMethod.POST)
		public String update(Dto dto) throws SQLException {
			System.out.println("넘어왔니??");
			String url=null;
			boolean result = dao.update(dto);
			if(result) {
				url = "redirect:list.do";
			}else {
				url="dept.update";
			}
			
			return url;
		}
		
		//delete 입니다- woonana
		@RequestMapping(value="/delete.do", method=RequestMethod.GET)
		public String deptList(int deptno, Model model) throws SQLException {
			

				dao.delete(deptno);
			

			return "redirect:list.do";
		}
		
		//search입니다.
		
		@RequestMapping(value="/search.do",method=RequestMethod.GET)
		public String search(int deptno,Model model) throws SQLException {
			Dto dto = dao.search(deptno);
			
			List<Dto> dtoList = null;
			dtoList.add(dto);
			System.out.println(dtoList.get(0).getDname());
			model.addAttribute("deptList", dtoList);
			return "list";
		}
	
		======= 새로 추가한 내용입니다. ======
		@RequestMapping(value="/newtech.do",method=RequestMethod.GET)
		public String search(int deptno,Model model) throws SQLException {
			Dto dto = dao.search(deptno);
			
			List<Dto> dtoList = null;
			dtoList.add(dto);
			System.out.println(dtoList.get(0).getDname());
			model.addAttribute("deptList", dtoList);
			return "newtech";
		}

}
