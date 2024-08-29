
package kr.or.ddit.admin.service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.company.vo.ComInfoVO;
import kr.or.ddit.paging.vo.PaginationInfo;

public interface MemberCompanyService {
	
	/**
	 * 기업회원 상세조회
	 */
	public ComInfoVO retrieveMember(String companyId);
	
	/**
	 * 기업회원 리스트 조회
	 */
	public void retrieveMemberList(PaginationInfo<MemberVO> paging);
	
	/**
	 * 기업회원 탈퇴 처리
	 */
	public ServiceResult removeMember(MemberVO users);

	/**
	 * 페이징
	 * @param 
	 */
	public int countMembers(PaginationInfo<MemberVO> paging);
	
	
}
