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
<hr/>
    <div class="input-group mb-3">
        <input type="hidden" id="userId" value="${principal.user.id}"/>
        <input type="hidden" id="boardId" value="${board.id}"/>
        <textarea id="reply-content" type="text" class="form-control" placeholder="Leave your comment here..."></textarea>
        <div class="input-group-append">
            <button id="btn-reply-save" class="btn btn-light test" type="button">✏</button>
        </div>
    </div>
<br/>
    <div class="card">
        <div class="card-header">Comments</div>
        <ul class="list-group" id="reply--box">
            <c:forEach var="reply" items="${board.replys}">
                <li class="list-group-item d-flex justify-content-between" id="${reply.id}">
                    <div>${reply.content} </div>

                    <div class="d-flex ">
                        <div class="font-italic"> 작성자: ${reply.user.username}&nbsp;&nbsp;</div>   <c:if test="${reply.user.id==principal.user.id}">
                        <button onClick="index.replyDelete(${board.id}, ${reply.id})" class="badge badge-secondary">삭제</button>
                    </c:if>
                    </div>
                </li>
            </c:forEach>

        </ul>
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
<style>
.test{
    width:60px;
}


</style>