<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/7/17
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>trendPic</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/plugins/layui/css/layui.css" media="all"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/css/global.css"/>
    <link rel="stylesheet" type="text/css" href="http://www.jq22.com/jquery/font-awesome.4.6.0.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/layui/layui.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/plugins/echarts/echarts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.4.4.js"></script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div>
    <div id="main" style="width: 1000px;height:700px;margin-right: auto;margin-left: auto"></div>

    <script type="text/javascript">
        var myChart = echarts.init(document.getElementById('main'));
        //直接获取
        var id = '';
        id = ${param.id};
        $.ajax({
            type: "post",
            async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
            url: "${pageContext.request.contextPath}/report/trendPic",    //请求发送到TestServlet处
            data: {"userId": id},
            dataType: "json",        //返回数据形式为json
            success: function (result) {
                console.log(result);
                fc(result);
            },
            error: function (errorMsg) {
                //请求失败时执行该函数
                // alert("图表请求数据失败!");
                myChart.hideLoading();
            }
        });

        function fc(result) {
            if (result) {
                var time = [];    //time（实际用来盛放X轴坐标值）
                //repayAmount 待收总额
                var repayAmount = [];
                //usableAmount 可用金额
                var usableAmount = [];
                // rechargeAmountOneMonth 最近一个月充值金额
                var rechargeAmountOneMonth = [];
                //withdrawAmountOneMonth
                var withdrawAmountOneMonth = [];
                var yi = '待收总额';
                var er = '可用金额';
                var san = '最近一个月充值金额';
                var si = '最近一个月提现金额';


                for (var i = 0; i < result.length; i++) {
                    //挨个取出类别并填入类别数组
                    time.push(result[i].targetTime);
                    repayAmount.push(result[i].repayAmount);
                    usableAmount.push(result[i].usableAmount);
                    rechargeAmountOneMonth.push(result[i].rechargeAmountOneMonth);
                    withdrawAmountOneMonth.push(result[i].withdrawAmountOneMonth);
                };

                myChart.hideLoading();    //隐藏加载动画
                option = {
                    title: {
                        text: ''
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    color:["#FFB6C1","#00FF00","#4B0082","#00BFFF"],
                    legend: {
                        data: [yi, er, san,si]
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '110px',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        data: time,
                    },

                    yAxis: {
                        type: 'value'
                    },
                    series: [

                        {
                            name: yi,
                            type: 'line',
                            data: repayAmount,
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    formatter: '{c}元'
                                }
                            },
                            lineStyle: {
                                normal: {
                                    color :"#FFB6C1",
                                    width: 3,
                                }
                            },
                        },
                        {
                            name: er,
                            type: 'line',
                            data: usableAmount,
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    formatter: '{c}元'
                                }
                            },
                            lineStyle: {
                                normal: {
                                    color :"#00FF00",
                                    width: 3,
                                }
                            },
                        },
                        {
                            name: san,
                            type: 'line',
                            data: rechargeAmountOneMonth,
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    formatter: '{c}元'
                                }
                            },
                            lineStyle: {
                                normal: {
                                    color :"#4B0082",
                                    width: 3,
                                }
                            },
                        },
                        {
                            name: si,
                            type: 'line',
                            data: withdrawAmountOneMonth,
                            label: {
                                normal: {
                                    show: true,
                                    position: 'top',
                                    formatter: '{c}元'
                                }
                            },
                            lineStyle: {
                                normal: {
                                    color :"#00BFFF",
                                    width: 3,
                                }
                            },
                        },
                    ],
                };
                myChart.setOption(option);
            }
        }
        //点击搜索
        $("#search3").click(function () {
            var id = '';
            id = ${param.id};
            console.log(id);
            $.ajax({
                type: "post",
                async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                url: "${pageContext.request.contextPath}/report/trendPic",    //请求发送到TestServlet处
                dataType: "json",        //返回数据形式为json
                data: {"userId": id},
                success: function (result) {

                    //请求成功时执行该函数内容，result即为服务器返回的json对象
                    if (result) {
                        if (result.msg != null) {
                            alert(result.msg);
                        } else {
                            fc(result);
                        }
                    }
                },
                error: function (errorMsg) {
                    //请求失败时执行该函数
                    // alert("图表请求数据失败!");
                    myChart.hideLoading();
                }
            })
        });
    </script>
</div>
</body>
</html>
