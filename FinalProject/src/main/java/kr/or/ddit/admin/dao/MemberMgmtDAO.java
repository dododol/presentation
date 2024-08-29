
package kr.or.ddit.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.paging.vo.PaginationInfo;

@Mapper
public interface MemberMgmtDAO {

	/**
	 * 페이징 처리
	 */
	public int selectTotalRecord(PaginationInfo<MemberVO> paging);

	/**
	 * 개인회원 리스트조회
	 */
	public List<MemberVO> selectMemberList(PaginationInfo<MemberVO> paging);

	/**
	 * 개인회원 탈퇴 처리
	 */
	public int deleteMember(String memId);

	// 이하 수연관리코드
	/**
	 * 글조회
	 * 
	 * @param memId
	 * @return
	 */
	public MemberVO selectMember(@Param("memId") String memId);

	/**
	 * 이전글 다음글
	 * 
	 * @param memId
	 * @return
	 */
	public MemberVO movePage(String memId);

}
