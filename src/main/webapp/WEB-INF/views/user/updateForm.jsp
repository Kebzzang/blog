<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form >
        <input type="hidden" id="id" value="${principal.user.id}"/>
        <div class="form-group">
            <label for="username">Username</label>
            <div class="row">
            <div class="col">
            <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
        </div>
            </div>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" value="${principal.user.email}" placeholder="Enter email" id="email">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control"  placeholder="Enter password" id="password">
        </div>


    </form>
    <button id="btn-update" class="btn btn-primary">정보수정</button>
</div>
<br/>
<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>
