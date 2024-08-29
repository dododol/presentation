
package kr.or.ddit.admin.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import kr.or.ddit.paging.vo.PaginationInfo;
import kr.or.ddit.users.vo.QnAVO;

@Mapper
public interface QuestionMgmtDAO {
	
	/**
	 * 1:1문의 글조회
	 */
	public QnAVO selectQuestion(@Param("qstnNo") String qstnNo);
	
	/**
	 * 페이징
	 */
	public int selectTotalRecord(PaginationInfo<QnAVO> paging);
	
	/**
	 * 1:1문의 리스트 조회
	 */
	public List<QnAVO> selectQuestionList(PaginationInfo<QnAVO> paging);
	
	// 수연코드
	/**
	 * 이전글 다음글
	 * @param qstnNo
	 * @return
	 */
	public QnAVO movePage(String qstnNo);
	
}
