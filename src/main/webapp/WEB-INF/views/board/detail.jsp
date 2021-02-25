<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="../layout/header.jsp"%>

<div class="container">
    <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
    <c:if test="${board.user.id==principal.user.id}">
    <a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
    <button id="btn-delete" class="btn btn-danger">삭제</button></c:if>
    <br/> <br/>
    <div style="float: right ">
        글 번호: <span id="id"><i>${board.id}</i></span>
        작성자: <span><i>${board.user.username}</i></span><br/>
        작성일: <span><i><fmt:formatDate pattern="yyyy-MM-dd hh:mm" value="${board.createDate}"/></i></span>
    </div>
    <br/>
        <div >
          <h3>${board.title}</h3>
        </div>
        <hr/>
        <div>
          <div>${board.content}</div>
             </div>

</div>
<br/>
<script>
    $('.summernote').summernote({
        tabsize: 2,
        height: 300
    });
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp"%>
