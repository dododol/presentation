
package kr.or.ddit.admin.service;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import kr.or.ddit.BoardNotFoundException;
import kr.or.ddit.admin.dao.QuestionMgmtDAO;
import kr.or.ddit.paging.vo.PaginationInfo;
import kr.or.ddit.users.vo.QnAVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionMgmtServiceImpl implements QuestionMgmtService{
	
	private final QuestionMgmtDAO questionMgmtDAO;
	
	// 1:1문의 상세조회
	@Override
	public QnAVO retrieveQuestion(String qstnNo) {
		QnAVO question = questionMgmtDAO.selectQuestion(qstnNo);
		if(question==null)
			throw new BoardNotFoundException(HttpStatus.NOT_FOUND, String.format("%d 해당 게시글이 없음.", qstnNo));
		
		return question;
	}
	
	// 1:1문의 리스트 조회
	@Override
	public void retrieveQuestionList(PaginationInfo<QnAVO> paging) {
		int totalRecord = questionMgmtDAO.selectTotalRecord(paging);
		paging.setTotalRecord(totalRecord);
		List<QnAVO> dataList = questionMgmtDAO.selectQuestionList(paging);
		paging.setDataList(dataList);
	}

}


	
