<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="<%=request.getContextPath()%>/resources/js/app/company/companyMypage/companyMypageList.js"></script>

<!-- 사이드메뉴 -->
<div class="cmpnyMemInfo">
	<div id="sri_section">
		<div id="content" class="basic">
			<div id="sri_gnb_wrap">
				<ul class="gnb">
					<li class="only"><a href="/zf_user/member/persons/main"
						onmousedown="pushDataLayer('ga_lead', 'pc_my_gnb', 'main', '')">
						<svg>
                            <use xlink:href="#svg_my_15"></use>
                        </svg>
                        <span class="txt">메인</span>
					</a></li>
					<li class="only selected"><a href="/zf_user/member/persons/main"
						onmousedown="pushDataLayer('ga_lead', 'pc_my_gnb', 'main', '')">
						<svg>
                            <use xlink:href="#svg_my_15"></use>
                        </svg>
                        <span class="txt">기업정보</span>
					</a></li>
					<li class="only"><a href="/zf_user/member/persons/main"
						onmousedown="pushDataLayer('ga_lead', 'pc_my_gnb', 'mag', '')">
						<svg>
                            <use xlink:href="#svg_my_16"></use>
                        </svg>
                        <span class="txt">대표 기업정보</span>
					</a></li>
					<li class="only"><a href="<c:url value='/scrap'/>"
						onmousedown="pushDataLayer('ga_lead', 'pc_my_gnb', 'scrap', '')">
						<svg>
                            <use xlink:href="#svg_my_18"></use>
                        </svg>
                        <span class="txt">알림</span>
					</a></li>
					<li class="only"><a href="/zf_user/persons/scrap-recruit"
						onmousedown="pushDataLayer('ga_lead', 'pc_my_gnb', 'alarm', '')">
						<svg>
                            <use xlink:href="#svg_my_19"></use>
                        </svg>
                        <span class="txt">내 프로모션</span>
					</a></li>
				</ul>
			</div>

			<!-- 기업회원정보 메인 -->
			<div class="wrap_content">
				<div id="divTitle" class="wrap_title">
					<h1 class="title_common">기업정보 관리</h1>
				</div>

				<ul class="tabList companyinfo_tab_list">
					<li class="select"><button type="button" class="inTab"
						onclick="location.href='/zf_user/company-join/edit?tabType=basic_info'; return false;">
						<span>기본정보</span>
					</button></li>
					<li>
						<div class="txt_box updown">직접 등록해보세요!</div>
						<button type="button" class="inTab"
							onclick="location.href='/zf_user/company-join/edit?tabType=main_company'; return false;">
							<span>대표 기업정보</span>
						</button>
					</li>
				</ul>
				<div class="companyinfo_modify_wrap">
					<!-- 계정 정보 -->
					<h2 class="non_blank">
						계정정보 <span class="required"><b>*</b> 표시는 필수입니다.</span>
					</h2>
					<div class="tblWrap tblRow tblBorder">
						<form id="frm" name="frm" method="post"
							action="/FinalProject/companyMemInfo/editPost">
							<table>
								<caption class="blind">계정정보 내용</caption>
								<colgroup>
									<col style="width: 200px">
									<col style="">
									<col style="width: 300px">
								</colgroup>
								<tbody>
									<tr>
										<th scope="row">아이디</th>
										<td colspan="2" class="txtLeft">
											${member.memId }
											<input type="hidden" name="memId" value="${member.memId}" />
										</td>
									</tr>
									<tr>
										<th scope="row">새 비밀번호</th>
										<td colspan="2" class="txtLeft">
											<input type="password" name="memPass" id="memPass" class="form-controller" />
										</td>
									</tr>
									<tr>
										<th scope="row">비밀번호 확인</th>
										<td colspan="2" class="txtLeft">
											<input type="password" id="memPassConfirm" required class="form-controller" />
										</td>
									</tr>
									<tr>
										<th scope="row">사업자등록번호</th>
										<td colspan="2" class="txtLeft">${member.company.companyNum}</td>
									</tr>
									<tr>
										<th scope="row">기업 인증</th>
										<td colspan="2">
											<c:choose>
												<c:when test="${member.company.companyPermission eq 'Y'}">
													승인
												</c:when>
												<c:when test="${member.company.companyPermission eq 'N'}">
													미승인
												</c:when>
												<c:otherwise>
													기타 상태
												</c:otherwise>
											</c:choose>
										</td>
									</tr>
									<tr>
										<th scope="row"><span class="required"><b>*</b> 이름</span></th>
										<td colspan="2" class="txtLeft">
											<input type="text" name="company.companyDnm" class="form-controller" value="${member.company.companyDnm}">
										</td>
									</tr>
									<tr>
										<th scope="row"><span class="required"><b>*</b> 연락처</span></th>
										<td class="txtLeft">
											<input type="text" name="memTel" class="form-controller" value="${member.memTel}">
										</td>
									</tr>
									<tr>
										<th scope="row"><span class="required"><b>*</b> 이메일</span></th>
										<td class="txtLeft">
											<input type="email" name="memMail" class="form-controller" value="${member.memMail}">
										</td>
									</tr>
									<tr>
										<td colspan="3" class="txtCenter">
											<input type="button" id="btnSubmit" value="저장" class="btn btn-primary" /> 
											<a href="<c:url value='/companyMemInfo'/>" class="btn btn-danger">취소</a>
										</td>
									</tr>
								</tbody>
							</table>
						</form>
					</div>
					<div class="dimmed" style="display: none;"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 비밀번호 확인 -->
<script>
	(function($) {
		$("#btnSubmit").on("click", function() {
			// 비밀번호 확인
			let memPass = $("#memPass").val();
			let memPassConfirm = $("#memPassConfirm").val();

			if (memPass === memPassConfirm) {
				$("#frm").submit();
			} else {
				alert("비밀번호를 확인해주세요");
				return;
			}
		});
	})(jQuery);
</script>
