<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix='c'%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Customer | Edit Address</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:wght@100;300;400;500;600;700;800&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/profile.css' />">
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/reset.css' />">
<link rel="stylesheet"
	href="<c:url value ='/common/assets/css/layout/sidebar.css' />">
</head>
<body>
	<div class="container-cts">
		<%@include file="/WEB-INF/views/layout/sidebar.jsp"%>
		<main class="content">
			<div class="content-container">
				<div class="list-header">
					<div class="header-breadcrumb">
						<h3 class="heading">Sửa địa chỉ</h3>
						<nav aria-label="breadcrumb">
							<ul class="breadcrumb">
								<li class="breadcrumb-item"><a class="breadcrumb__link"
									href="">Trang chủ</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item"><a class="breadcrumb__link"
									href="">Người dùng</a></li>
								<li class="breadcrumb__divider"></li>
								<li class="breadcrumb__item">Sửa địa chỉ</li>
							</ul>
						</nav>
					</div>
				</div>
				<form action="editAddress.htm" method="post" id = "myForm">
					<div class="input-group">
						<div class="select-container">
							<select name="province" id="province" class="select"
								aria-invalid="false" onchange="submitForm()">
								<option value="0" selected="selected">Trống</option>
								<c:forEach items="${listPros}" var="element">
									<option value="${element.id}"
										${element.id == selectedProvince ? 'selected' : ''}>${element.name }</option>
								</c:forEach>
							</select> <label for="province">Tỉnh</label> <span class="select-icon"><i
								class="fa-solid fa-angle-down"></i></span>
						</div>
						<div class="select-container">
							<select name="district" id="district" class="select"
								aria-invalid="false" onchange="submitForm()"
								<c:if test="${enableDistrict}">disabled</c:if>>
								<option value="0" selected="selected">Trống</option>
								<c:forEach items="${listDicts}" var="element">
									<option value="${element.id}"
										${element.id == selectedDistrict ? 'selected' : ''}>${element.name }</option>
								</c:forEach>
							</select> <label for="district">Huyện</label> <span class="select-icon"><i
								class="fa-solid fa-angle-down"></i></span>
						</div>
						<div class="select-container">
							<select name="ward" id="ward" class="select" aria-invalid="false"
								<c:if test="${enableWard}">disabled</c:if>>
								<option value="0" selected="selected">Trống</option>
								<c:forEach items="${listWards}" var="element">
									<option value="${element.id}"
										${element.id == selectedWard ? 'selected' : ''}>${element.name }</option>
								</c:forEach>
							</select> <label for="ward">Xã</label> <span class="select-icon"><i
								class="fa-solid fa-angle-down"></i></span>
						</div>
						<div class="input-container">
							<input type="number" required="required" id="phone"
								 name="phone" value="${phone}"
								aria-labelledby="phone"><span class="highlight"></span><span
								class="bar"></span> <label for="phone">Số điện thoại</label>
						</div>
						<div class="input-container">
							<input type="text" required="required" id="details"
								name="details" value="${details}" aria-labelledby="details"><span
								class="highlight"></span><span class="bar"></span> <label
								for="details">Chi tiết</label>
						</div>
						<input type="hidden" name="id" value="${id }">
					</div>
				</form>	
					<div class="button-group">
						<button class="button button-submit"
							onclick="submit('editAddress/done.htm')">
							<span> <i class="fa-solid fa-plus"></i>
							</span> <span> Chỉnh sửa</span>
						</button>
						<button class="button button-cancel" type="button">
							<span> <i class="fa-regular fa-circle-xmark"></i>
							</span> <span> Huỷ </span>
						</button>
					</div>
				
			</div>
		</main>
	</div>
	<script type="text/javascript"
		src="<c:url value='/common/assets/js/navbar.js'/>"></script>
	<script>
		function submitForm() {
			document.querySelector('form').submit();
		}
	</script>
	<script>
    function submit(a) {
        var form = document.getElementById('myForm');
        console.log(a);
        form.action = a;
        form.submit(); 
    }
</script>
</body>
</html>