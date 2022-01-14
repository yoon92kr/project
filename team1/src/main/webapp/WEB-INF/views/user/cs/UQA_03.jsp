<!--  2021.11.29 강보석 -->
<!--2022.01.14 윤상현 수정 -->

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />



<div class="container-fluid">

	<div class="row">
		<div class="col-lg-4 offset-lg-4 text-center">
			<h1 class="page_title">고객센터</h1>
		</div>
	</div>

	<div class="row">
		<div class="offset-lg-2 col-lg-4 text-left cs_01_subtitle">
			<h3>1:1 문의 내용 작성</h3>
		</div>
	</div>

	<div class="row">
		<div class="offset-lg-2 col-lg-8 cs_02_02_row">
			<hr>
		</div>
	</div>
	
	<form id="add_UQA" action="${contextPath}/cs/add_UQA.do" method="post">
		
		<div class="row">
			<div class="offset-lg-3 col-lg-2 text-center notice_02_box01">
				<span>제목</span>
			</div>
			<div class="col-lg-4 text-left cs_02_02_box02">
				<input type="text" class="form-control notice_upload_check" name="notice_title" 
				placeholder="제목을 입력하세요.">
			</div>
		</div>
	
		<div class="row">
			<div class="offset-lg-3 col-lg-2 text-center cs_02_02_box01">
				<span>문의유형</span>
			</div>
			<div class="col-lg-4 text-left cs_02_02_box02">
				<select class="cs_02_select" name="notice_type">
					<option value="상품문의">상품문의</option>
					<option value="계정문의">계정문의</option>
					<option value="결제문의">결제문의</option>
					<option value="기타문의">기타문의</option>
				</select>
			</div>
		</div>
	
		<div class="row">
			<div class="offset-lg-3 col-lg-2 text-center cs_02_02_box01">
				<span>공개여부</span>
			</div>
			<div class="col-lg-4 cs_02_02_box02">
				<input id="NPY" type="radio" name="notice_private" value="1" 
				onClick="this.form.notice_pw.disabled=true" checked="checked"> 공개 
				<input id="NPN" class="cs_02_01_private_btn" type="radio" 
				onClick="this.form.notice_pw.disabled=false"
				name="notice_private" value="0"> 비공개
			</div>
		</div>
	
		<div class="row">
			<div class="offset-lg-3 col-lg-2 text-center notice_02_box01">
				<span>비밀번호</span>
			</div>
			<div class="col-lg-1 text-left cs_02_02_ex01box02">
				<input id="NPPwd" type="password" class="form-control" name="notice_pw" 
				disabled placeholder="비밀번호" maxlength='10'> 
					
			</div>
		
			<div class="col-lg-3 text-left cs_02_02_ex02box02"><span class="admin_product_Form_notice">※ 비밀번호는 4자리 이상 ~ 10자리 이하로 입력해주세요. </span></div>
		</div>
	
		<div class="row">
			<div class="offset-lg-3 col-lg-2 text-center cs_02_02_box03">
				<span>내용</span>
			</div>
			<div class="col-lg-4 text-left cs_02_02_box04">
				<textarea class="form-control notice_upload_check" name="notice_body"
				rows="8" placeholder="내용을 입력하세요."></textarea>
			</div>
		</div>
		
		<input type="hidden" name="user_id" value="${userInfo.user_id}" >
		<input type="hidden" name="notice_category" value="UQA" >
		<input type="hidden" name="notice_parent_no" value="0" >
	</form>
		
		<div class="row">
			<div class="offset-lg-4 col-lg-2 text-center">
				<div class="cs_correct_btn">
					<input onclick="submit_add_notice()" class="user_btn_Bgreen" type="button" id="cs_02_02_update_btn" value="등록하기">							
				</div>
			</div>
			<div class="col-lg-2 text-center">
				<div class="notice_back_btn">
					<a href="${contextPath}${lastViewName}.do">			
						<input class="user_btn_gray" type="button" id="cs_02_02_list_btn" value="돌아가기">
					</a>
				</div>
			</div>
		</div>

	
</div>

<script>

// 공지 등록 전 rank 및 null 확인 스크립트
function submit_add_notice() {
	// 전체 input 태그 value를 체크하기위한 클래스 select
	var elements = document.getElementsByClassName('notice_upload_check');
	var checkFlag = true;
	var private_Flag = $('input:radio[name=notice_private]:checked').val();
	for (var i = 0; i < elements.length; i++) {

		let uploadItem = elements[i].value;
		// 비어있는 파일이 하나라도 있다면 flag를 false로 대입.
		if (!uploadItem) {
			checkFlag = false;
		}

	}
	
	if(private_Flag == 0) {
		
		var private_value = document.getElementById('NPPwd').value;
		
		if(private_value.length < 4) {
			alert("비밀번호를 4글자 이상 입력해주세요");
			checkFlag = false;
		}

		
	}
	else if(!checkFlag) {
		alert("제목과 내용은 반드시 입력해주셔야 합니다.");
	}
	
	if (checkFlag) {
		document.getElementById('add_UQA').submit();
	}
}
</script>