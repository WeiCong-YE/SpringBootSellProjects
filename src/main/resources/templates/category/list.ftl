<!DOCTYPE html>
<html lang="en">
<#include "../comment/header.ftl">
<body>
<div id="wrapper" class="toggled">
<#--边栏布局-->
<#include "../comment/nav.ftl">

<#--主体布局-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>
                                类目ID
                            </th>
                            <th>
                                名称
                            </th>
                            <th>
                                类目类型
                            </th>
                            <th>
                                创建时间
                            </th>
                            <th>
                                修改时间
                            </th>
                            <th colspan="2">
                                操作
                            </th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list productCategories.content as ProductCategory>
                        <tr>
                            <td>
                            ${ProductCategory.categoryId}
                            </td>
                            <td>
                            ${ProductCategory.categoryName}
                            </td>
                            <td>
                            ${ProductCategory.categoryType}
                            </td>
                            <td>
                            ${ProductCategory.createTime}
                            </td>
                            <td>
                            ${ProductCategory.updateTime}
                            </td>
                            <td>
                                <a class="btn btn-info btn-lg"
                                   href="/sell/category/categoryMsg?categoryId=${ProductCategory.categoryId}">修改</a>
                            </td>
                            <td>
                                <button class="btn btn-danger btn-lg" data-toggle="modal" data-target="#myModal">
                                    删除
                                </button>
                            <#--<a href="/sell/category/delete?categoryId=${ProductCategory.categoryId}">删除</a>-->
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
                <div style="align-content: center">
                    <ul class="pagination pagination-sm pull-right">

                    <#if currPage lte 1>
                        <li>
                            <a href="#">上一页</a>
                        </li>
                    <#else >
                        <li>
                            <a href="/sell/category/list?page=${currPage-1}&size=${size}">上一页</a>
                        </li>
                    </#if>
                    <#list  1 ..productCategories.totalPages as index>
                        <#if currPage==index >
                            <li class="disabled"><a
                                    href="/sell/category/list?page=${index}&size=${size}">${index}</a>
                            </li>
                        <#else>
                            <li><a href="/sell/category/list?page=${index}&size=${size}">${index}</a>
                            </li>
                        </#if>

                    </#list>
                    <#if currPage gte productCategories.totalPages>
                        <li>
                            <a href="#">下一页</a>
                        </li>
                    <#else >
                        <li>
                            <a href="/sell/category/list?page=${currPage+1}&size=${size}">下一页</a>
                        </li>
                    </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    商品类目删除
                </h4>
            </div>
            <div class="modal-body">
                是否真的要删除这个类目
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消
                </button>
                <button type="button" class="btn btn-primary">
                    确认删除
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
</html>