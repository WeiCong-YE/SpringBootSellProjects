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
                                商品ID
                            </th>
                            <th>
                                名称
                            </th>
                            <th>
                                图片
                            </th>
                            <th>
                                单价
                            </th>
                            <th>
                                库存
                            </th>
                            <th>
                                描述
                            </th>
                            <th>
                                类目
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

                <#list productInfoPage.content as ProductInfo>
                <tr>
                    <td>
                        ${ProductInfo.productId}
                    </td>
                    <td>
                        ${ProductInfo.productName}
                    </td>
                    <td>
                        <img src="${ProductInfo.productIcon}" width="90" height="90"/>
                    </td>
                    <td>
                        ${ProductInfo.productPrice}
                    </td>
                    <td>
                        ${ProductInfo.productStock}
                    </td>
                    <td>
                        ${ProductInfo.getProductDescription()}
                    </td>
                    <td>
                        ${ProductInfo.categoryType}
                    </td>
                    <td>
                        ${ProductInfo.createTime}
                    </td>
                    <td>
                        ${ProductInfo.updateTime}
                    </td>
                    <td>
                        <a href="#">修改</a>
                    </td>
                    <td>
                         <#assign hrefUrl="/sell/products/on_sale?id=${ProductInfo.productId}"/>
                        <#if ProductInfo.getProductStatusEnum().getCode()==1>
                        <#--上架-->
                            <#assign hrefUrl="/sell/products/on_sale?id=${ProductInfo.productId}"/>
                        <#else >
                        <#--下架-->
                            <#assign hrefUrl="/sell/products/off_sale?id=${ProductInfo.productId}"/>
                        </#if>
                        <a href="${hrefUrl}">${ProductInfo.getProductStatusEnum().getMessage()}</a>
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
                     <a href="/sell/products/list?page=${currPage-1}&size=${size}">上一页</a>
                 </li>
                </#if>
                <#list  1 ..productInfoPage.totalPages as index>
                    <#if currPage==index >
                             <li class="disabled"><a
                                     href="/sell/products/list?page=${index}&size=${size}">${index}</a>
                             </li>
                    <#else>
                          <li><a href="/sell/products/list?page=${index}&size=${size}">${index}</a>
                          </li>
                    </#if>

                </#list>
                <#if currPage gte productInfoPage.totalPages>
                <li>
                    <a href="#">下一页</a>
                </li>
                <#else >
                 <li>
                     <a href="/sell/products/list?page=${currPage+1}&size=${size}">下一页</a>
                 </li>
                </#if>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>