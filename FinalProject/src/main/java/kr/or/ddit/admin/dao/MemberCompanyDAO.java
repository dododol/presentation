
package kr.or.ddit.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.company.vo.ComInfoVO;
import kr.or.ddit.paging.vo.PaginationInfo;

@Mapper
public interface MemberCompanyDAO {
	
	/**
	 * 기업회원 리스트 조회
	 */
	public List<MemberVO> selectMemberList(PaginationInfo<MemberVO> paging);
	
	/**
	 * 기업회원 탈퇴 처리
	 */
	public int deleteMember(String memId);
	
	/**
	 * 기업회원 상세조회
	 */
	public ComInfoVO selectCompanyInfo(@Param("companyId") String companyId);
	
	/**
	 * 페이징
	 * @param paging
	 * @return
	 */
	public int countMembers(PaginationInfo<MemberVO> paging); 
	
	// 이하 수연코드
	/**
	 * 이전글 다음글
	 */
	/* public MemberVO movePage(String memId); */
	
	/**
	 * 글조회
	 * @param memId
	 * @return
	 */
	public MemberVO selectMember(@Param("memId") String memId);

}
