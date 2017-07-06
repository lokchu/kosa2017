package com.coderby.myapp.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.coderby.myapp.board.dao.IBoardRepository;
import com.coderby.myapp.board.model.BoardVO;

@Service
public class BoardService implements IBoardService {

	@Inject
	IBoardRepository boardRepository;
	
	@Override
	public List<BoardVO> selectList(int page) {
		return boardRepository.selectList(page);
	}

	@Override
	public BoardVO selectBoardDetails(int seq) {
		return boardRepository.selectBoardDetails(seq);
	}

	@Override //여러개의 트랜잭션이 원자적으로 실행되게 함
	public int insertNewBoard(BoardVO board){
		System.out.println("biz()실행 전");
		int seq = boardRepository.getMaxSeq()+1;
		board.setSeq(seq);
		int rowCount = boardRepository.insertNewBoard(board);
		System.out.println("biz()실행 후");
		if(Math.random()<1){  //일부러 항상 예외를 발생하게 했다.
			throw new RuntimeException("강제 예외 발생시킴");  //예외가 발생했으므로 rollback 해야함
		}
		return rowCount;
	}

	@Override
	public int updateBoard(BoardVO board) {
		return boardRepository.updateBoard(board);
	}

	@Override
	public int deleteBoard(int seq) {
		return boardRepository.deleteBoard(seq);
	}

}
