<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>卖家商品列表</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-striped table-hover">
                <thead>
                <tr>
                    <th>
                        订单ID
                    </th>
                    <th>
                        姓名
                    </th>
                    <th>
                        手机号码
                    </th>
                    <th>
                        地址
                    </th>
                    <th>
                        金额
                    </th>
                    <th>
                        订单状态
                    </th>
                    <th>
                        支付方式
                    </th>
                    <th>
                        支付状态
                    </th>
                    <th>
                        创建时间
                    </th>
                    <th colspan="2">
                        操作
                    </th>
                </tr>
                </thead>
                <tbody>

                <#list pageResult.content as orderDto>
                <tr>
                    <td>
                        ${orderDto.orderId}
                    </td>
                    <td>
                        ${orderDto.buyerName}
                    </td>
                    <td>
                        ${orderDto.buyerPhone}
                    </td>
                    <td>
                        ${orderDto.buyerAddress}
                    </td>
                    <td>
                        ${orderDto.orderAmount}
                    </td>
                    <td>
                        ${orderDto.getOrderStatusEnum().getMessage()}
                    </td>
                    <td>
                        微信
                    </td>
                    <td>
                        ${orderDto.getPayStatusEnum().getMessage()}
                    </td>
                    <td>
                        ${orderDto.createTime}
                    </td>
                    <td>
                        详情
                    </td>
                    <td>
                        <#if orderDto.getOrderStatus()!=2>
                        <a href="#">取消</a>
                        </#if>
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
                     <a href="/sell/seller/list?page=${currPage-1}&size=${size}">上一页</a>
                 </li>
                </#if>
                <#list  1 ..pageResult.totalPages as index>
                    <#if currPage==index >
                             <li class="disabled"><a
                                     href="/sell/seller/list?page=${index}&size=${size}">${index}</a>
                             </li>
                    <#else>
                          <li><a href="/sell/seller/list?page=${index}&size=${size}">${index}</a>
                          </li>
                    </#if>

                </#list>
                <#if currPage gte pageResult.totalPages>
                <li>
                    <a href="#">下一页</a>
                </li>
                <#else >
                 <li>
                     <a href="/sell/seller/list?page=${currPage+1}&size=${size}">下一页</a>
                 </li>
                </#if>
            </ul>
        </div>
    </div>
</div>
</body>
</html>