package kr.or.ddit.company.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.common.service.AuthenticateService;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.company.dao.CompanyMemInfoDAO;
import lombok.RequiredArgsConstructor;

/**
 * @author 김태형
 * @since 2023.11.16
 * @version 1.0
 **/
@Service
@RequiredArgsConstructor
public class CompanyMemInfoServiceImpl implements CompanyMemInfoService {

	private final CompanyMemInfoDAO dao;
	private final AuthenticateService authService;
	
	// 기업회원 정보 조회
	@Override
	public MemberVO retrieveCompany(String memId) {
		MemberVO member = dao.selectCompany(memId);
		if(member == null)
			throw new UsernameNotFoundException(memId);
		return member;
	}

	// 기업회원 정보 수정
	@Override
	public int editPost(MemberVO memberVO) {
		/*
		--기업마이페이지 수정     
		UPDATE MEMBER
		SET    MEM_PASS='1234',MEM_TEL='010-3755-2525',MEM_MAIL='k11@google.com'
		WHERE  MEM_ID = 'kf001';
		*/
		int result = this.dao.editMemberPost(memberVO);
		
		/*
		--기업체 명 수정
		UPDATE COMPANY
		SET    COMPANY_NM = '이서하'
		WHERE  COMPANY_ID = 'kf001';
		 */
		result += this.dao.editCompanyPost(memberVO);
		
		return result;
	}
	
	// 기업회원 탈퇴
	@Override
	public int exitPost(String memId) {
		return this.dao.exitPost(memId);
	}


}
