package kr.or.ddit.company.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import kr.or.ddit.common.enumpkg.ServiceResult;
import kr.or.ddit.common.vo.MemberVO;
import kr.or.ddit.company.service.CompanyMemInfoService;
import kr.or.ddit.utils.ValidationUtils;
import kr.or.ddit.validate.grouphint.DeleteGroup;
import kr.or.ddit.validate.grouphint.UpdateGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/companyMemInfo")
public class CompanyMemInfoController {

	@Inject
	private CompanyMemInfoService service;

	// 기업회원정보 조회
	@GetMapping
	public String companyMemInfo(Model model, @SessionAttribute("authId") String memId) {
		// 아이디 : kf001
		log.info("아이디 : " + memId);
		MemberVO member = service.retrieveCompany(memId);
		log.info("companyMemInfo->member : " + member);
		model.addAttribute("member", member);

		return "company/companymypage/companyMemInfo";
	}

	// 기업회원정보 수정UI
	@GetMapping("/edit")
	public String usersInfoEdit(Model model, @SessionAttribute("authId") String memId) {
		MemberVO member = service.retrieveCompany(memId);
		model.addAttribute("member", member);

		// forwarding : jsp
		return "company/companymypage/companyMemInfoEdit";
	}

	// 기업회원정보 수정 실행
	@PostMapping("/editPost")
	public String editPost(Model model, MemberVO memberVO, @SessionAttribute("authId") String memId) {
		// memberVO : MemberVO(memId=kf001, memPass=1234, memTel=010-3755-2525,
		// memMail=k11@google.com, memZip=0, memAddr1=null
		// , memAddr2=null, memDivision=null, memDelete=null, users=null,
		// company=CompanyVO(companyId=null, companyNum=0, companyDnm=이서하,
		// companyNm=null, companyDate=null,
		// companyPermission=null, companyFile=null))
		log.info("editPost->memberVO : " + memberVO);

		int result = this.service.editPost(memberVO);
		log.info("result : " + result);

		// redirect : url
		return "redirect:/companyMemInfo";
	}

	//기업회원 탈퇴
	// /companyMemInfo/exitPost
	@PostMapping("/exitPost")
	public String exitPost(String memId, HttpSession session) {
		/*
		 * UPDATE MEMBER SET MEM_DELETE = 'Y' WHERE MEM_ID = 'test02'
		 */
		log.info("exitPost->memId : " + memId);

		int result = this.service.exitPost(memId);
		log.info("result : " + result);

		if (result > 0) {// 탈퇴 성공 시
			session.invalidate();// 세션 삭제
		}

		return "redirect:/";
	}

}
