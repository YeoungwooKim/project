package project.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/board")
public class BoardController {
//	Logger log = LoggerFactory.getLogger(this.getClass());
 
//	@Autowired
//	private BoardService boardService;
	
//	@GetMapping("/list")
//	public String list() throws Exception {
//		return "board/list";
//	}
	
	@GetMapping("/list")
	public ModelAndView list() throws Exception {
		ModelAndView mv = new ModelAndView("project/list");
		mv.addObject("title", "Board");
		return mv;
	}
	
//	@GetMapping(value="/list")
//	public ModelAndView openBoardList() throws Exception {
//		ModelAndView mv = new ModelAndView("project/board");
//		List<BoardDto> list = boardService.selectBoardList();
//		mv.addObject("list", list);
//		return mv;
//	}
}
