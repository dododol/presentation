
package kr.or.ddit.admin.service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.paging.vo.PaginationInfo;

public interface MemberMgmtService {

	/**
	 * 개인회원 리스트 조회
	 */
	public void retrieveMemberList(PaginationInfo<MemberVO> paging);

	/**
	 * 개인회원 탈퇴 처리
	 */
	public ServiceResult removeMember(MemberVO users);

	// 이하 수연관리코드
	/**
	 * 게시글 상세조회
	 */
	public MemberVO retrieveMember(String memId);

}
