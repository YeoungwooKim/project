package project.project.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
class BoardRestController {
//	
//	Logger log = LoggerFactory.getLogger(this.getClass());
//	
//	@Autowired
//	private BoardService boardService;
//
//	
//	@GetMapping("/board")
//	List<BoardDto> list() throws Exception { // 전체 리스트 출력
//		return boardService.selectBoardList();//repository.findAll();
//	} 
//	
//	@PostMapping("/board/write") // 신규 게시글 등록
//	void save(@RequestBody BoardDto board) throws Exception {
//		boardService.insertBoardList(board);
//		//repository.save(newBoardDto);
//	}
//	  
//	@GetMapping("/board/{id}") // 게시글 상세 조회
//	BoardDto search(@PathVariable Long id) throws Exception {
//		return boardService.searchBoard(id.intValue());//repository.findById(id) .orElseThrow(() -> new BoardDtoNotFoundException(id));
//	}
//	
//	@PutMapping("/board/{id}") // 게시글 수정
//	void modify(@RequestBody BoardDto board, @PathVariable Long id) throws Exception {
//		boardService.modifyBoardList(board, id.intValue());
//	}
//	
//	@DeleteMapping("/board/{id}") // 게시글 삭제
//	void deleteBoardDto(@PathVariable Long id) {
//		//repository.deleteById(id);
//	}
}