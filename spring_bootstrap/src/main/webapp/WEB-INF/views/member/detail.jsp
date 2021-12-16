<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<title>회원상세보기</title>

<body>

  <!-- Content Wrapper. Contains page content -->
  <div >
  	 <section class="content-header">
	  	<div class="container-fluid">
	  		<div class="row md-2">
	  			<div class="col-sm-6">
	  				<h1>상세페이지</h1>  				
	  			</div>
	  			<div class="col-sm-6">
	  				<ol class="breadcrumb float-sm-right">
			        <li class="breadcrumb-item">
			        	<a href="#">
				        	<i class="fa fa-dashboard">회원관리</i>
				        </a>
			        </li>
			        <li class="breadcrumb-item active">
			        	상세보기
			        </li>		        
	    	  </ol>
	  			</div>
	  		</div>
	  	</div>
  	</section>
    <!-- Main content -->
    <section class="content register-page">
		<div class="register-box">
			<div class="login-logo">
    			<a href=""><b>회원 상세보기</b></a>
  			</div>
  			<div class="card">
	        	<div class="register-card-body" >
	            	<div class="row"  style="height:200px;">
						<div class="mailbox-attachments clearfix col-md-12" style="text-align: center;">							
							<div id="pictureView" data-id="${member.id }" style="border: 1px solid green; height: 200px; width: 140px; margin: 0 auto;"></div>														
						</div>
					</div>
					<br />
	                <div class="form-group row" >
	                  <label for="inputEmail3" class="col-sm-3 control-label text-right">아이디</label>
	
	                  <div class="col-sm-9">
	                    <input name="id" type="text" class="form-control" id="inputEmail3"  value="${member.id }" readonly>
	                  </div>
	                </div>	               
	                <div class="form-group row">
	                  <label for="inputPassword3" class="col-sm-3 control-label text-right">이  름</label>
	
	                  <div class="col-sm-9">
	                    <input name="pwd" type="text" class="form-control" id="inputPassword3" value="${member.name }" readonly>
	                  </div>
	                </div>
	                 <div class="form-group row">
	                  <label for="inputPassword3" class="col-sm-3 control-label text-right">이메일</label>
	
	                  <div class="col-sm-9">
	                    <input name="email" type="email" class="form-control" id="inputPassword3" value="${member.email }" readonly>
	                  </div>
	                </div>
	                 <div class="form-group row">
	                  <label for="inputPassword3" class="col-sm-3 control-label text-right">전화번호</label>
	                  <div class="col-sm-9">   
	                  	<input name="phone" type="text" class="form-control" id="inputPassword3" value="${member.phone }" readonly>	                
	                  </div>                  
	                </div>               
	              </div>  
		          <div class="card-footer" style="padding:5px 0;" >
		          		<div class="row">		          		
			          		<div class="col-sm-3 text-center">			          		
			          			<button type="button" onclick="location.href='modifyForm.do?id=${member.id}';" 
			          					<%--style="display:${loginUser.id eq member.id ? 'visible':'none'} ;" --%>
			          					id="modifyBtn" class="btn btn-warning ">수 정</button>
			          		</div>
		          		
			          		<div class="col-sm-3 text-center">
			          			<button type="button" onclick="" 
			          			id="deleteBtn" class="btn btn-danger" >삭 제</button>
			          		</div>
		          			
			          		<div class="col-sm-3 text-center">
			          			<c:if test="${member.enabled ne 0 }">
			          			<button type="button" onclick="location.href='enabled.do?id=${member.id}&enabled=0';" id="stopBtn" class="btn btn-info" >비활성</button>
			          			</c:if>
			          			<c:if test="${member.enabled eq 0 }">
			          			<button type="button" onclick="location.href='enabled.do?id=${member.id}&enabled=1';" id="activeBtn" class="btn btn-info" >활&nbsp;&nbsp;성</button>
			          			</c:if>
			          		</div>
		          		<%-- </c:if> --%>
		          	
			          		<div class="col-sm-3 text-center">
			            		<button type="button" id="listBtn" onclick="CloseWindow();" class="btn btn-primary pull-right">닫 기</button>
			            	</div>
		          	    </div>  	
		          </div>
      	  	</div>
        </div>
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
  <script>
	window.onload=function(){
		MemberPictureThumb(document.querySelector('[data-id="${member.id}"]'),
				'${member.picture}','<%=request.getContextPath()%>');
	}
</script>    
</body>




