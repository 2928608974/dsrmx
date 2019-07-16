<%@page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增页面</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.3.7/dist/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/statics/scripts/jquery/jquery-1.7.1.js"></script>
    <!--省市区联动-->
    <script src="${pageContext.request.contextPath}/statics/js/distpicker.data.js"></script>
    <script src="${pageContext.request.contextPath}/statics/js/distpicker.js"></script>
</head>
<body>
<form id="myform" class="form-horizontal"  method="post">
    <div class="form-group">
        <label  class="col-sm-2 control-label">受骗人</label>
        <div class="col-sm-3">
            <input type="text" name="userName" value="${user.userName}" class="form-control" placeholder="受骗人">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label">身份证</label>
        <div class="col-sm-3">
            <input type="text" name="idCard" value="${user.idCard}" class="form-control" placeholder="身份证">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">联系电话</label>
        <div class="col-sm-3">
            <input type="text" name="phone" value="${user.phone}" class="form-control" placeholder="联系电话">
        </div>
    </div>
    <div class="form-group">
        <label  class="col-sm-2 control-label">受骗金额</label>
        <div class="col-sm-3">
            <input type="text" name="money" value="${user.money}" class="form-control" placeholder="受骗金额">
        </div>
    </div>
    <div class="form-group">
        <div data-toggle="distpicker">
            <label  class="col-sm-2 control-label">地址</label>
            <div class="col-sm-3">
                <div class="row">
                    <div class="col-sm-4">
                        <select class="form-control" id="province" name="province" data-province="${user.province}"></select>
                    </div>
                    <div class="col-sm-4">
                        <select class="form-control" id="city" name="city" data-city="${user.city}"></select>
                    </div>
                    <div class="col-sm-4">
                        <select class="form-control" id="area" name="area" data-area="${user.area}"></select>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <div class="checkbox">
                <label>
                   备注<textarea name="remark">
                           ${user.remark}
                       </textarea>
                </label>
            </div>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="button" onclick="formSubmit()" class="btn btn-default">提交</button>
        </div>
    </div>
    <input type="hidden" name="provinceData">
    <input type="hidden" name="cityData">
    <input type="hidden" name="areaData">
    <input type="hidden" name="state" value="${user.state}">
    <input type="hidden" name="userId" value="${user.userId}">
</form>
</body>
<script>
    function formSubmit() {
        //获取省市区的编号
        var provinceData=$("#province option:selected").attr("data-code");
        var city=$("#city option:selected").attr("data-code");
        var area=$("#area option:selected").attr("data-code");

        $("input[name='provinceData']").val(provinceData);
        $("input[name='cityData']").val(city);
        $("input[name='areaData']").val(area);

        if(${user!=null}){
            $("#myform").attr("action","${pageContext.request.contextPath}/front/upd").submit();
        }else {
           $("#myform").attr("action","${pageContext.request.contextPath}/front/frontuser").submit();
        }
    }
</script>
</html>