
package kr.or.ddit.admin.service;

import kr.or.ddit.paging.vo.PaginationInfo;
import kr.or.ddit.users.vo.QnAVO;

public interface QuestionMgmtService {
	
	/**
	 * 1:1문의 상세조회
	 */
	public QnAVO retrieveQuestion(String qstnNo);
	
	/**
	 * 1:1문의 리스트 조회
	 */
	public void retrieveQuestionList(PaginationInfo<QnAVO> paging);
	
}
