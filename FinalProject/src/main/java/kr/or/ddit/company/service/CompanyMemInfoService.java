package kr.or.ddit.company.service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.common.vo.MemberVO;

/**
 * @author 김태형
 * @since 2023.11.16
 * @version 1.0
 **/
public interface CompanyMemInfoService {

	// 기업회원 정보 조회
	public MemberVO retrieveCompany(String memId);
	
	// 기업마이페이지 수정(여기서 Member + Company 합친다)
	public int editPost(MemberVO memberVO);

	// 기업회원탈퇴
	public int exitPost(String memId);
}
