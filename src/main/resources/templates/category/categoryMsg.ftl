<html>
<#include "../comment/header.ftl">

<body>
<div id="wrapper" class="toggled">

<#--边栏sidebar-->
<#include "../comment/nav.ftl">

<#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/category/updateOrSave">
                        <div class="form-group">
                            <label>ID</label>
                            <input name="categoryId" readonly="readonly" type="text" class="form-control"
                                   value="${(productCategory.categoryId)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>名字</label>
                            <input name="categoryName" type="text" class="form-control"
                                   value="${(productCategory.categoryName)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>类型</label>
                            <input name="categoryType" type="text" class="form-control"
                                   value="${(productCategory.categoryType)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>创建时间</label>
                            <input name="createTime" readonly="readonly" type="text" class="form-control"
                                   value="${(productCategory.createTime)!''}"/>
                        </div>
                        <div class="form-group">
                            <label>最后一次更新时间</label>
                            <input name="updateTime" readonly="readonly" type="text" class="form-control"
                                   value="${(productCategory.updateTime)!''}"/>
                        </div>
                        <#--<input hidden type="text" name="categoryId"-->
                               <#--value="${(productCategory.categoryId)!''}">-->
                        <button type="submit" class="btn btn-info btn-lg">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>