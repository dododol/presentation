<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<security:authorize access="isAuthenticated()">
	<!-- 로그인확인 -->
	<security:authentication property="principal.member" var="member" />
	<!-- memberVO정보를 member변수에 저장 -->
</security:authorize>

<!-- 기업회원 정보 출력 -->
<div class="cmpnyMemInfo">
	<div id="sri_section">
		<div id="content" class="basic">
			<div id="sri_gnb_wrap">
				<ul class="gnb">
					<li class="only">
						<a href="/zf_user/member/persons/main"
							onmousedown="pushDataLayer('ga_lead', 'pc_my_gnb', 'main', '')">
							<svg>
                                <use xlink:href="#svg_my_15"></use>
                            </svg>
							<span class="txt">메인</span>
						</a>
					</li>
					<li class="only selected">
						<a href="/zf_user/member/persons/main"
							onmousedown="pushDataLayer('ga_lead', 'pc_my_gnb', 'main', '')">
							<svg>
                                <use xlink:href="#svg_my_15"></use>
                            </svg>
							<span class="txt">기업정보</span>
						</a>
					</li>
					<li class="only">
						<a href="<%=request.getContextPath() %>/users/companyDetail/${member.company.companyId}"
							onmousedown="pushDataLayer('ga_lead', 'pc_my_gnb', 'mag', '')">
							<svg>
                                <use xlink:href="#svg_my_16"></use>
                            </svg>
							<span class="txt">대표 기업정보</span>
						</a>
					</li>
					<li class="only">
						<a href="<%=request.getContextPath()%>/companyMyPromotionView"
							onmousedown="pushDataLayer('ga_lead', 'pc_my_gnb', 'alarm', '')">
							<svg>
                                <use xlink:href="#svg_my_19"></use>
                            </svg>
							<span class="txt">내 프로모션</span>
						</a>
					</li>
				</ul>
			</div>

			<div class="wrap_content">
				<div id="divTitle" class="wrap_title">
					<h1 class="title_common">기업정보 관리</h1>
				</div>

				<div class="companyinfo_modify_wrap">
					<h2 class="non_blank">
						계정정보 <span class="required"><b>*</b> 표시는 필수입니다.</span>
						<input type="hidden" name="memPass" id="memPassReal" value="${member.memPass}" />
					</h2>
					<div class="tblWrap tblRow tblBorder">
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
									<td colspan="2" class="txtLeft">${member.memId }</td>
								</tr>
								<tr>
									<th scope="row">사업자등록번호</th>
									<td colspan="2" class="txtLeft">${member.company.companyNum }</td>
								</tr>
								<tr>
									<th scope="row">기업 인증</th>
									<td colspan="2">
										<c:choose>
											<c:when test="${member.company.companyPermission eq 'Y'}">승인</c:when>
											<c:when test="${member.company.companyPermission eq 'N'}">미승인</c:when>
											<c:otherwise>기타 상태</c:otherwise>
										</c:choose>
									</td>
								</tr>
								<tr>
									<th scope="row"><span class="required"><b>*</b> 이름</span></th>
									<td colspan="2" class="txtLeft">${member.company.companyDnm }</td>
								</tr>
								<tr>
									<th scope="row"><span class="required"><b>*</b> 연락처</span></th>
									<td class="txtLeft">${member.memTel }</td>
								</tr>
								<tr>
									<th scope="row"><span class="required"><b>*</b> 이메일</span></th>
									<td class="txtLeft" colspan="2">${member.memMail }</td>
								</tr>
								<tr>
									<td colspan="3" class="txtCenter">
										<button type="button" id="btnEdit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">수정</button> 
										<button type="button" id="btnExit" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal">탈퇴</button>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>

				<form id="frmExit" action="/FinalProject/companyMemInfo/exitPost" method="post" hidden>
					<input type="text" name="memId" value="${member.memId}" />
				</form>

			</div>
		</div>
	</div>
</div>

<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title fs-5" id="exampleModalLabel">비밀번호 확인</h1>
				<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<label for="memPass">현재 비밀번호를 입력하세요.</label>
				<input type="password" name="memPass" id="memPass" class="form-control" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
				<button type="button" id="btnMemPassCheck" class="btn btn-primary">확인</button>
				<button type="button" id="btnMemPassExitCheck" class="btn btn-primary" style="display: none;">탈퇴하기</button>
			</div>
		</div>
	</div>
</div>

<script>
	(function($) {
		// 수정 버튼 클릭
		$("#btnEdit").on("click", function() {
			$("#btnMemPassExitCheck").hide();
			$("#btnMemPassCheck").show();
		});

		// 탈퇴 버튼 클릭
		$("#btnExit").on("click", function() {
			$("#btnMemPassExitCheck").show();
			$("#btnMemPassCheck").hide();
		});

		// 수정 처리
		$(document).on("click", "#btnMemPassCheck", function() {
			let memPass = $("#memPass").val(); // 모달창 비번
			let memPassReal = $("#memPassReal").val(); // DB의 member의 비번

			if ($.trim(memPass) === "") {
				alert("비밀번호를 입력해주세요");
				$("#memPass").focus();
				return;
			}

			if (memPass === memPassReal) {
				location.href = "/FinalProject/companyMemInfo/edit";
			} else {
				alert("비밀번호가 틀렸습니다. 다시 입력해주세요");
			}
		});

		// 탈퇴 처리
		$(document).on("click", "#btnMemPassExitCheck", function() {
			let memPass = $("#memPass").val(); // 모달창 비번
			let memPassReal = $("#memPassReal").val(); // DB의 member의 비번

			if ($.trim(memPass) === "") {
				alert("비밀번호를 입력해주세요");
				$("#memPass").focus();
				return;
			}

			if (memPass === memPassReal) {
				$("#frmExit").submit();
			} else {
				alert("비밀번호가 틀렸습니다. 다시 입력해주세요");
			}
		});
	})(jQuery);
</script>
