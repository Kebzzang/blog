<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
    <form >
        <div class="form-group">
            <label for="username">Username</label>
            <div class="row">
            <div class="col">
            <input type="text" class="form-control" placeholder="Enter username" id="username">
        </div>
            <div class="col">
            <input type="button" class="myButton" id="temp" value="중복체크">
            </div>
            </div>
            <div class="text-left small mt-2" id="checkMsg" style="color: red"></div>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" placeholder="Enter email" id="email">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>

        <div class="form-group form-check">
            <label class="form-check-label">
                <input class="form-check-input" type="checkbox"> Remember me
            </label>
        </div>

    </form>
    <button id="btn-save" class="btn btn-primary">회원가입</button>
</div>
<br/>
<script src="/js/user.js"></script>

<%@ include file="../layout/footer.jsp"%>
