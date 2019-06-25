<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getServerName() + ":" + request.getServerPort() + path + "/";
    String baseUrlPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>迪纳无感加油管理系统</title>
    <style>
        #table-show {
            width: 100%;
            height: 50%;
            border: 1px solid #ff7455;
        }

        .td-show {
            /* height: 35%;*/
            width: 25%;
        }

        .input-show {
            color: #F33820;
            font-size: 200%;
            font-weight: bold;
        }

        #tr1 {
            width: 100%;
            height: 45%;
            border: 1px solid #ff7455;
        }

        #tr2 {
            width: 100%;
            height: 45%;
            border: 1px solid #ff7455;
        }

        .no {
            font-size: 400%;
            color: #000000;
            font-family: "Microsoft YaHei UI", "Georgia", "Adobe Fan Heiti Std B", serif;
            font-weight: bold;
        }

        .fueling {
            display: none;
            color: #e1b12c;
        }


        .text2 {
            font-size: 120%;
            color: #000000;
            font-family: "Microsoft YaHei UI", serif;
            font-weight: bold;
        }

        .text3 {
            font-size: 180%;
            color: #13E070;
            font-family: "Microsoft YaHei UI", serif;
            font-weight: bold;
            margin-left: 15px;
        }


        .loading {
            height: 40px;
            width: 40px;
        }

        input {
            margin: 0 auto;
            padding: 0;
            overflow: hidden;
            height: 40px;
            width: 160px;
            line-height: 40px;
            /* border-radius: 21px;*/ /* 圆角边框 */
            text-align: center;
            font-weight: 600;
            position: relative;
            box-sizing: border-box;
            border: 3px solid transparent;
            /* background-clip: padding-box, border-box;
             background-origin: padding-box, border-box;
             background-image: linear-gradient(180deg, #FAFAFA, #FAFAFA), linear-gradient(100deg, #00F5FF, #FF8F23);*/
        }

        /* 定义keyframe动画，命名为blink */
        @keyframes blink {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }

        /* 添加兼容性前缀 */
        @-webkit-keyframes blink {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }

        @-moz-keyframes blink {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }

        @-ms-keyframes blink {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }

        @-o-keyframes blink {
            0% {
                opacity: 1;
            }
            100% {
                opacity: 0;
            }
        }

        /* 定义blink类*/
        .blink {
            color: #13E070;
            animation: blink 1s linear infinite;
            /* 其它浏览器兼容性前缀 */
            -webkit-animation: blink 1s linear infinite;
            -moz-animation: blink 1s linear infinite;
            -ms-animation: blink 1s linear infinite;
            -o-animation: blink 1s linear infinite;
        }
    </style>
    <link rel="stylesheet" type="text/css" href="static/loading.css">
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="static/bootstrap-3.3.7-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="static/layui/css/layui.css" media="all">
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <link rel="stylesheet" href="static/fontawesome-free-5.8.1-web/css/all.min.css">

</head>
<script type="text/javascript" src="static/jquery.js"></script>
<script type="text/javascript" src="media/js/jquery.validate.min.js"></script>
<body>

<div id="show" class="container-fluid">
    <div id="table-1">
        <table id="table-show" class="table table-bordered">
            <tr id="tr1" class="info">
                <td class="active td-show" id="1">
                    <form class="form-inline">
                        <table>
                            <tr>
                                <td rowspan="2"><span class="no">1</span></td>
                                <td rowspan="2">&nbsp;<span></span>&nbsp;</td>
                                <%--<td><span class="text" id="2-type"></span></td>--%>
                            </tr>
                            <tr>
                                <td><span class="text3 blink" id="1-plate">空闲&nbsp;<i
                                        class="fas fa-gas-pump"></i></span></td>
                            </tr>
                        </table>
                    </form>
                    <br/>
                    <form class="form-horizontal">

                        <table style="width: 100%;text-align: center" id="t1-fueling">
                            <tr>
                                <td>
                                    <h1 id="t1-fueling-tip1" style="color: #4cd137;text-align: center">正在等待</h1>
                                    <h1 id="t1-fueling-tip2" class="fueling">正在加油</h1>
                                </td>
                                <td rowspan="2">
                                    <div id="1-loading" class="loading">
                                        <div></div>
                                        <div></div>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <table style="display: none;width: 100%;text-align: center" id="t1-fuel-end">
                            <tr>
                                <td><label><span class="text2">结算金额&nbsp;&nbsp;</span></label>
                                    <label><input type="text" id="1-money" class="input-show">
                                        <span class="text2">&nbsp;&nbsp;元</span>
                                    </label>
                                </td>
                                <td>
                                    <button type="button" id="btn-1" class="btn btn-info">确认</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>


                <%--ex - start --%>

                <td class="active td-show" id="2">
                    <form class="form-inline">
                        <table>
                            <tr>
                                <td rowspan="2"><span class="no">2</span></td>
                                <td rowspan="2">&nbsp;<span></span>&nbsp;</td>
                                <%--<td><span class="text" id="2-type"></span></td>--%>
                            </tr>
                            <tr>
                                <td><span class="text3 blink" id="2-plate">空闲&nbsp;<i
                                        class="fas fa-gas-pump"></i></span></td>
                            </tr>
                        </table>
                    </form>
                    <br/>
                    <form class="form-horizontal">

                        <table style="width: 100%;text-align: center" id="t2-fueling">
                            <tr>
                                <td>
                                    <h1 id="t2-fueling-tip1" style="color: #4cd137;text-align: center">正在等待</h1>
                                    <h1 id="t2-fueling-tip2" style="display: none;color: #4b7bec">正在加油</h1>
                                </td>
                                <td rowspan="2">
                                    <div id="2-loading" class="loading">
                                        <div></div>
                                        <div></div>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <table style="display: none;width: 100%;text-align: center" id="t2-fuel-end">
                            <tr>
                                <td><label><span class="text2">结算金额&nbsp;&nbsp;</span></label>
                                    <label><input type="text" id="2-money" class="input-show">
                                        <span class="text2">&nbsp;&nbsp;元</span>
                                    </label>
                                </td>
                                <td>
                                    <button type="button" id="btn-2" class="btn btn-info">确认</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
                <%--ex - end --%>


                <td class="active td-show" id="3">
                    <form class="form-inline">
                        <table>
                            <tr>
                                <td rowspan="2"><span class="no">3</span></td>
                                <td rowspan="2">&nbsp;<span></span>&nbsp;</td>
                                <%--<td><span class="text" id="2-type"></span></td>--%>
                            </tr>
                            <tr>
                                <td><span class="text3 blink" id="3-plate">空闲&nbsp;<i
                                        class="fas fa-gas-pump"></i></span></td>
                            </tr>
                        </table>
                    </form>
                    <br/>
                    <form class="form-horizontal">

                        <table style="width: 100%;text-align: center" id="t3-fueling">
                            <tr>
                                <td>
                                    <h1 id="t3-fueling-tip1" style="color: #4cd137;text-align: center">正在等待</h1>
                                    <h1 id="t3-fueling-tip2" style="display: none;color: #273c75">正在加油</h1>
                                </td>
                                <td rowspan="2">
                                    <div id="3-loading" class="loading">
                                        <div></div>
                                        <div></div>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <table style="display: none;width: 100%;text-align: center" id="t3-fuel-end">
                            <tr>
                                <td><label><span class="text2">结算金额&nbsp;&nbsp;</span></label>
                                    <label><input type="text" id="3-money" class="input-show">
                                        <span class="text2">&nbsp;&nbsp;元</span>
                                    </label>
                                </td>
                                <td>
                                    <button type="button" id="btn-3" class="btn btn-info">确认</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>


                <td class="active td-show" id="4">
                    <form class="form-inline">
                        <table>
                            <tr>
                                <td rowspan="2"><span class="no">4</span></td>
                                <td rowspan="2">&nbsp;<span></span>&nbsp;</td>
                                <%--<td><span class="text" id="2-type"></span></td>--%>
                            </tr>
                            <tr>
                                <td><span class="text3 blink" id="4-plate">空闲&nbsp;<i
                                        class="fas fa-gas-pump"></i></span></td>
                            </tr>
                        </table>
                    </form>
                    <br/>
                    <form class="form-horizontal">

                        <table style="width: 100%;text-align: center" id="t4-fueling">
                            <tr>
                                <td>
                                    <h1 id="t4-fueling-tip1" style="color: #4cd137;text-align: center">正在等待</h1>
                                    <h1 id="t4-fueling-tip2" style="display: none;color: #273c75">正在加油</h1>
                                </td>
                                <td rowspan="2">
                                    <div id="4-loading" class="loading">
                                        <div></div>
                                        <div></div>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <table style="display: none;width: 100%;text-align: center" id="t4-fuel-end">
                            <tr>
                                <td><label><span class="text2">结算金额&nbsp;&nbsp;</span></label>
                                    <label><input type="text" id="4-money" class="input-show">
                                        <span class="text2">&nbsp;&nbsp;元</span>
                                    </label>
                                </td>
                                <td>
                                    <button type="button" id="btn-4" class="btn btn-info">确认</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>

            <tr id="tr2" class="info">

                <td class="active td-show" id="5">
                    <form class="form-inline">
                        <table>
                            <tr>
                                <td rowspan="2"><span class="no">5</span></td>
                                <td rowspan="2">&nbsp;<span></span>&nbsp;</td>
                                <%--<td><span class="text" id="2-type"></span></td>--%>
                            </tr>
                            <tr>
                                <td><span class="text3 blink" id="5-plate">空闲&nbsp;<i
                                        class="fas fa-gas-pump"></i></span></td>
                            </tr>
                        </table>
                    </form>
                    <br/>
                    <form class="form-horizontal">

                        <table style="width: 100%;text-align: center" id="t5-fueling">
                            <tr>
                                <td>
                                    <h1 id="t5-fueling-tip1" style="color: #4cd137;text-align: center">正在等待</h1>
                                    <h1 id="t5-fueling-tip2" style="display: none;color: #273c75">正在加油</h1>
                                </td>
                                <td rowspan="2">
                                    <div id="5-loading" class="loading">
                                        <div></div>
                                        <div></div>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <table style="display: none;width: 100%;text-align: center" id="t5-fuel-end">
                            <tr>
                                <td><label><span class="text2">结算金额&nbsp;&nbsp;</span></label>
                                    <label><input type="text" id="5-money" class="input-show">
                                        <span class="text2">&nbsp;&nbsp;元</span>
                                    </label>
                                </td>
                                <td>
                                    <button type="button" id="btn-5" class="btn btn-info">确认</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>

                <td class="active td-show" id="6">
                    <form class="form-inline">
                        <table>
                            <tr>
                                <td rowspan="2"><span class="no">6</span></td>
                                <td rowspan="2">&nbsp;<span></span>&nbsp;</td>
                                <%--<td><span class="text" id="2-type"></span></td>--%>
                            </tr>
                            <tr>
                                <td><span class="text3 blink" id="6-plate">空闲&nbsp;<i
                                        class="fas fa-gas-pump"></i></span></td>
                            </tr>
                        </table>
                    </form>
                    <br/>
                    <form class="form-horizontal">

                        <table style="width: 100%;text-align: center" id="t6-fueling">
                            <tr>
                                <td>
                                    <h1 id="t6-fueling-tip1" style="color: #4cd137;text-align: center">正在等待</h1>
                                    <h1 id="t6-fueling-tip2" style="display: none;color: #273c75">正在加油</h1>
                                </td>
                                <td rowspan="2">
                                    <div id="6-loading" class="loading">
                                        <div></div>
                                        <div></div>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <table style="display: none;width: 100%;text-align: center" id="t6-fuel-end">
                            <tr>
                                <td><label><span class="text2">结算金额&nbsp;&nbsp;</span></label>
                                    <label><input type="text" id="6-money" class="input-show">
                                        <span class="text2">&nbsp;&nbsp;元</span>
                                    </label>
                                </td>
                                <td>
                                    <button type="button" id="btn-6" class="btn btn-info">确认</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>


                <td class="active td-show" id="7">
                    <form class="form-inline">
                        <table>
                            <tr>
                                <td rowspan="2"><span class="no">7</span></td>
                                <td rowspan="2">&nbsp;<span></span>&nbsp;</td>
                                <%--<td><span class="text" id="2-type"></span></td>--%>
                            </tr>
                            <tr>
                                <td><span class="text3 blink" id="7-plate">空闲&nbsp;<i
                                        class="fas fa-gas-pump"></i></span></td>
                            </tr>
                        </table>
                    </form>
                    <br/>
                    <form class="form-horizontal">

                        <table style="width: 100%;text-align: center" id="t7-fueling">
                            <tr>
                                <td>
                                    <h1 id="t7-fueling-tip1" style="color: #4cd137;text-align: center">正在等待</h1>
                                    <h1 id="t7-fueling-tip2" style="display: none;color: #273c75">正在加油</h1>
                                </td>
                                <td rowspan="2">
                                    <div id="7-loading" class="loading">
                                        <div></div>
                                        <div></div>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <table style="display: none;width: 100%;text-align: center" id="t7-fuel-end">
                            <tr>
                                <td><label><span class="text2">结算金额&nbsp;&nbsp;</span></label>
                                    <label><input type="text" id="7-money" class="input-show">
                                        <span class="text2">&nbsp;&nbsp;元</span>
                                    </label>
                                </td>
                                <td>
                                    <button type="button" id="btn-7" class="btn btn-info">确认</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>

                <td class="active td-show" id="8">
                    <form class="form-inline">
                        <table>
                            <tr>
                                <td rowspan="2"><span class="no">8</span></td>
                                <td rowspan="2">&nbsp;<span></span>&nbsp;</td>
                                <%--<td><span class="text" id="2-type"></span></td>--%>
                            </tr>
                            <tr>
                                <td><span class="text3 blink" id="8-plate">空闲&nbsp;<i
                                        class="fas fa-gas-pump"></i></span></td>
                            </tr>
                        </table>
                    </form>
                    <br/>
                    <form class="form-horizontal">

                        <table style="width: 100%;text-align: center" id="t8-fueling">
                            <tr>
                                <td>
                                    <h1 id="t8-fueling-tip1" style="color: #4cd137;text-align: center">正在等待</h1>
                                    <h1 id="t8-fueling-tip2" style="display: none;color: #273c75">正在加油</h1>
                                </td>
                                <td rowspan="2">
                                    <div id="8-loading" class="loading">
                                        <div></div>
                                        <div></div>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <table style="display: none;width: 100%;text-align: center" id="t8-fuel-end">
                            <tr>
                                <td><label><span class="text2">结算金额&nbsp;&nbsp;</span></label>
                                    <label><input type="text" id="8-money" class="input-show">
                                        <span class="text2">&nbsp;&nbsp;元</span>
                                    </label>
                                </td>
                                <td>
                                    <button type="button" id="btn-8" class="btn btn-info">确认</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </td>
            </tr>
        </table>
    </div>

    <div id="table-2" class="table-show">
        <table class="table table-bordered">
            <!-- On rows -->
            <tr class="info text2" id="row-2-1">
                <td>油枪编号</td>
                <td>油枪名称</td>
                <td>车牌</td>
                <td>油品</td>
                <td>单价（元/升）</td>
                <td>加油量（升）</td>
                <td>实收金额（元）</td>
                <td>开始时间</td>
                <td>结束时间</td>
                <!-- <td></td>
                <td></td>  -->
            </tr>
            <tr class="active" id="his-0">
                <td style="height: 30px" id="his-0-1"></td>
                <td style="height: 30px" id="his-0-2"></td>
                <td style="height: 30px" id="his-0-3"></td>
                <td style="height: 30px" id="his-0-4"></td>
                <td style="height: 30px" id="his-0-5"></td>
                <td style="height: 30px" id="his-0-6"></td>
                <td style="height: 30px" id="his-0-7"></td>
                <td style="height: 30px" id="his-0-8"></td>
                <td style="height: 30px" id="his-0-9"></td>
                <!-- <td style="height: 30px" id="his-0-10"></td>
                <td style="height: 30px" id="his-0-11"></td> -->
            </tr>
            <tr class="warning" id="his-2">
                <td style="height: 30px" id="his-1-1"></td>
                <td style="height: 30px" id="his-1-2"></td>
                <td style="height: 30px" id="his-1-3"></td>
                <td style="height: 30px" id="his-1-4"></td>
                <td style="height: 30px" id="his-1-5"></td>
                <td style="height: 30px" id="his-1-6"></td>
                <td style="height: 30px" id="his-1-7"></td>
                <td style="height: 30px" id="his-1-8"></td>
                <td style="height: 30px" id="his-1-9"></td>
                <!-- <td style="height: 30px" id="his-1-10"></td>
                <td style="height: 30px" id="his-1-11"></td> -->
            </tr>
            <tr class="active" id="his-3">
                <td style="height: 30px" id="his-2-1"></td>
                <td style="height: 30px" id="his-2-2"></td>
                <td style="height: 30px" id="his-2-3"></td>
                <td style="height: 30px" id="his-2-4"></td>
                <td style="height: 30px" id="his-2-5"></td>
                <td style="height: 30px" id="his-2-6"></td>
                <td style="height: 30px" id="his-2-7"></td>
                <td style="height: 30px" id="his-2-8"></td>
                <td style="height: 30px" id="his-2-9"></td>
                <!-- <td style="height: 30px" id="his-2-10"></td>
                <td style="height: 30px" id="his-2-11"></td> -->
            </tr>
            <tr class="warning" id="his-4">
                <td style="height: 30px" id="his-3-1"></td>
                <td style="height: 30px" id="his-3-2"></td>
                <td style="height: 30px" id="his-3-3"></td>
                <td style="height: 30px" id="his-3-4"></td>
                <td style="height: 30px" id="his-3-5"></td>
                <td style="height: 30px" id="his-3-6"></td>
                <td style="height: 30px" id="his-3-7"></td>
                <td style="height: 30px" id="his-3-8"></td>
                <td style="height: 30px" id="his-3-9"></td>
                <!-- <td style="height: 30px" id="his-3-10"></td>
                <td style="height: 30px" id="his-3-11"></td> -->
            </tr>
            <tr class="active" id="his-5">
                <td style="height: 30px" id="his-4-1"></td>
                <td style="height: 30px" id="his-4-2"></td>
                <td style="height: 30px" id="his-4-3"></td>
                <td style="height: 30px" id="his-4-4"></td>
                <td style="height: 30px" id="his-4-5"></td>
                <td style="height: 30px" id="his-4-6"></td>
                <td style="height: 30px" id="his-4-7"></td>
                <td style="height: 30px" id="his-4-8"></td>
                <td style="height: 30px" id="his-4-9"></td>
                <!-- <td style="height: 30px" id="his-4-10"></td>
                <td style="height: 30px" id="his-4-11"></td> -->
            </tr>
            <tr class="warning" id="his-6">
                <td style="height: 30px" id="his-5-1"></td>
                <td style="height: 30px" id="his-5-2"></td>
                <td style="height: 30px" id="his-5-3"></td>
                <td style="height: 30px" id="his-5-4"></td>
                <td style="height: 30px" id="his-5-5"></td>
                <td style="height: 30px" id="his-5-6"></td>
                <td style="height: 30px" id="his-5-7"></td>
                <td style="height: 30px" id="his-5-8"></td>
                <td style="height: 30px" id="his-5-9"></td>
                <!-- <td style="height: 30px" id="his-5-10"></td>
                <td style="height: 30px" id="his-5-11"></td> -->
            </tr>
            <tr class="active" id="his-7">
                <td style="height: 30px" id="his-6-1"></td>
                <td style="height: 30px" id="his-6-2"></td>
                <td style="height: 30px" id="his-6-3"></td>
                <td style="height: 30px" id="his-6-4"></td>
                <td style="height: 30px" id="his-6-5"></td>
                <td style="height: 30px" id="his-6-6"></td>
                <td style="height: 30px" id="his-6-7"></td>
                <td style="height: 30px" id="his-6-8"></td>
                <td style="height: 30px" id="his-6-9"></td>
                <!-- <td style="height: 30px" id="his-6-10"></td>
                <td style="height: 30px" id="his-6-11"></td> -->
            </tr>
            <tr class="warning" id="his-8">
                <td style="height: 30px" id="his-7-1"></td>
                <td style="height: 30px" id="his-7-2"></td>
                <td style="height: 30px" id="his-7-3"></td>
                <td style="height: 30px" id="his-7-4"></td>
                <td style="height: 30px" id="his-7-5"></td>
                <td style="height: 30px" id="his-7-6"></td>
                <td style="height: 30px" id="his-7-7"></td>
                <td style="height: 30px" id="his-7-8"></td>
                <td style="height: 30px" id="his-7-9"></td>
                <!-- <td style="height: 30px" id="his-7-10"></td>
                <td style="height: 30px" id="his-7-11"></td> -->
            </tr>
            <tr class="active" id="his-9">
                <td style="height: 30px" id="his-8-1"></td>
                <td style="height: 30px" id="his-8-2"></td>
                <td style="height: 30px" id="his-8-3"></td>
                <td style="height: 30px" id="his-8-4"></td>
                <td style="height: 30px" id="his-8-5"></td>
                <td style="height: 30px" id="his-8-6"></td>
                <td style="height: 30px" id="his-8-7"></td>
                <td style="height: 30px" id="his-8-8"></td>
                <td style="height: 30px" id="his-8-9"></td>
                <!-- <td style="height: 30px" id="his-8-10"></td>
                <td style="height: 30px" id="his-8-11"></td> -->
            </tr>
            <tr class="warning" id="his-10">
                <td style="height: 30px" id="his-9-1"></td>
                <td style="height: 30px" id="his-9-2"></td>
                <td style="height: 30px" id="his-9-3"></td>
                <td style="height: 30px" id="his-9-4"></td>
                <td style="height: 30px" id="his-9-5"></td>
                <td style="height: 30px" id="his-9-6"></td>
                <td style="height: 30px" id="his-9-7"></td>
                <td style="height: 30px" id="his-9-8"></td>
                <td style="height: 30px" id="his-9-9"></td>
                <!-- <td style="height: 30px" id="his-9-10"></td>
                <td style="height: 30px" id="his-9-11"></td> -->
            </tr>
        </table>
    </div>

    <%--音频--%>

    <audio id="bgMusic">
        <source src="hangge.mp3" type="audio/mp3">
        <source src="hangge.ogg" type="audio/ogg">
    </audio>


</div>
<script src="static/layui/layui.js" charset="utf-8"></script>
<script>

    /*#######################################################*/


    $(function () {

        /*测试使用数据 --- start*/
        /*var data1 = {
            "stationId": "2",
            "stationName": "1",
            "deviceId": "30011401302",
            "vehicleId": "000420524c3f4931a2c2755c9b8281fa",
            "rfId": "e20000193119018121103bb0",
            "plateNo": "迪V013027",
            "fuelType": 92,
            "price": 7.15,
            "simulationType": 0,
            "refuelQuantity": 120,
            "maxPricel": 100,
            "second": 22,
            "eventType": 1
        };
        var data2 = {
            "stationId": "2",
            "stationName": "1",
            "deviceId": "30011401302",
            "vehicleId": "000420524c3f4931a2c2755c9b8281fa",
            "rfId": "e20000193119018121103bb0",
            "plateNo": "迪V013027",
            "fuelType": 92,
            "price": 7.15,
            "simulationType": 0,
            "refuelQuantity": 120,
            "maxPricel": 100,
            "second": 22,
            "eventType": 0
        };
        var data3 = {
            "stationId": "7",
            "stationName": "1",
            "deviceId": "30011401302",
            "vehicleId": "000420524c3f4931a2c2755c9b8281fa",
            "rfId": "e20000193119018121103bb0",
            "plateNo": "迪V01302",
            "fuelType": 92,
            "price": 7.15,
            "simulationType": 0,
            "refuelQuantity": 120,
            "maxPricel": 200,
            "second": 22,
            "eventType": 1
        };
        var data4 = {
            "stationId": "7",
            "stationName": "1",
            "deviceId": "30011401302",
            "vehicleId": "000420524c3f4931a2c2755c9b8281fa",
            "rfId": "e20000193119018121103bb0",
            "plateNo": "迪V01302",
            "fuelType": 92,
            "price": 7.15,
            "simulationType": 0,
            "refuelQuantity": 120,
            "maxPricel": 200,
            "second": 22,
            "eventType": 0
        };
        test();

        function test() {
            setTimeout(function () {
                getObj(data1);
            }, 3000);

            setTimeout(function () {
                getObj(data3);
            }, 5000);

            setTimeout(function () {
                getObj(data2);
            }, 8000);

            setTimeout(function () {
                getObj(data4);
            }, 12000);

        }*/

        /*测试使用数据 --- end */


        /*加载历史加油列表*/
        refuelHisShow();

        var ws;
        /*接受的参数*/
        //var param;
        webSocket();

        function webSocket() {
            if ("WebSocket" in window) {
                console.log("浏览器支持 WebSocket!");
                // 打开一个 web socket
                // var url = 'ws://<%=basePath%>/webSocketServer';
                var url = 'ws://<%=basePath%>/webSocketServer?version=1';
                ws = new WebSocket(url);
                ws.onopen = function () {
                    console.log("ws 连接建立...");
                    //  sendObj();
                };

                ws.onmessage = function (evt) {
                    console.log(evt);
                    var data = evt.data;
                    var obj = eval('(' + data + ')');
                    //param = obj;
                    console.log("eval--->>>" + obj);
                    getObj(obj);
                };

                ws.onclose = function () {
                    // 关闭 websocket
                    console.log("连接已关闭...");
                };

                ws.onerror = function () {
                    console.log("error...");
                }
            } else {
                // 浏览器不支持 WebSocket
                alert("您的浏览器不支持 WebSocket!");
            }
        }

        function getObj(data) {
            /*拼接选择器*/
            var oil = "#" + data.stationId + "-oil";
            var money = "#" + data.stationId + "-money";
            var stationId = "#" + data.stationId;
            var licencePlate = data.plateNo + "  <i class=\"fas fa-car\"></i>";
            var licencePlateSelect = "#" + data.stationId + "-plate";
            var loading = "#" + data.stationId + "-loading";
            /*选择器*/
            var selectedOil = $(oil);
            var selectedMoney = $(money);
            var selectedColor = $(stationId);
            var selectedLoading = $(loading);
            var selectedLicencePlate = $(licencePlateSelect);

            var tip1 = $("#t" + data.stationId + "-fueling-tip1");
            var tip2 = $("#t" + data.stationId + "-fueling-tip2");

            /*收到开始加油消息*/
            if (data.eventType === 1) {
                console.log("start.........");
                /*start*/
                tip1.hide();
                tip2.show();
                tip2.addClass("blink");
                toRefuel(licencePlate, selectedLicencePlate, selectedOil, selectedMoney, selectedLoading, selectedColor, data);
            }
            /*收到结束加油消息*/
            else if (data.eventType === 0) {
                console.log("stop!!!!!!!");
                tip2.hide();
                $("#t" + data.stationId + "-fuel-end").show();
                /*todo:播放音频*/
                var audio = document.getElementById("bgMusic");
                audio.play();

                selectedLicencePlate.append("<span style='color: #4cd137'>&nbsp;&nbsp;加油结束</span>");
                /*清除加载动画*/
                $(loading).hide();
                /*清除颜色*/
                selectedColor.removeClass("danger");
                selectedColor.addClass("active");
            }
        }

        /* 金钱数值校验 表达式 */
        var reg = /(^[1-9]([0-9]+)?(\.[0-9]{1,2})?$)|(^(0){1}$)|(^[0-9]\.[0-9]([0-9])?$)/;

        /*确认支付按钮*/
        $.each([1, 2, 3, 4, 5, 6, 7, 8], function (key, value) {
            $("#btn-" + value).click(function () {
                var money = $("#" + value + "-money").val();

                if (!reg.test(money)) {
                    layui.use('layer', function () {
                        layer.alert('输入金额格式不对')
                    });
                } else {
                    /*弹框二次确认*/
                    layui.use('layer', function () {
                        var layer = layui.layer;
                        layer.confirm(value + " 号站台 确认支付：" + money + "元", {
                                btn: ['确认', '重新输入金额'] //按钮
                            },
                            function () {
                                /*开始支付*/
                                pay(value, money, value + " 号站台 确认支付：" + money);
                                layer.msg(value + " 号站台 确认支付：" + money + '元 支付成功', {icon: 1});
                                $("#t" + value + "-fuel-end").hide();
                                $("#t" + value + "-fueling-tip1").show();
                            },
                            function () {

                            }
                        );
                    });
                }
            });
        });

        /*支付*/
        function pay(stationId, money, data) {
            console.log(data + "pay do someThing ....");
            fuelOverToPay(stationId, money);
            /*加油历史显示*/
            refuelHisShow();
        }

        /*支付完成----ws发送消息*/
        function fuelOverToPay(stationId, money) {
            var rfId = sessionStorage.getItem("stationId" + stationId);
            console.log("rfid == " + rfId + "--money=" + money);
            var eve = {
                "rfId": rfId,
                "refuelPrice": money
            };
            var s = JSON.stringify(eve);
            ws.send(s);
        }


        /*历史数据展示*/
        function refuelHisShow() {
            var url2 = 'http://<%=basePath%>refuel/queryRefuelHis';
            $.ajax(
                {
                    url: url2,
                    type: "post",
                    success: function (data) {
                        data = eval(data);
                        $.each(data, function (index, value) {

                            var selectedHisStationId = $("#his-" + index + "-1");
                            var selectedHisStationName = $("#his-" + index + "-2");
                            var selectedHisPlateNo = $("#his-" + index + "-3");
                            var selectedHisFuelType = $("#his-" + index + "-4");
                            var selectedHisPrice = $("#his-" + index + "-5");
                            var selectedHisFuelQuantity = $("#his-" + index + "-6");
                            var selectedHisPaidFee = $("#his-" + index + "-7");
                            var selectedHisStartTime = $("#his-" + index + "-8");
                            var selectedHisEndTime = $("#his-" + index + "-9");


                            selectedHisStationId.html(value.stationId);
                            selectedHisStationName.html(value.stationName);
                            selectedHisPlateNo.html(value.plateNo);
                            var fuelType = value.fuelType;
                            var fuelTypeStr = "";
                            if (fuelType === 0) {
                                fuelTypeStr = "0#柴油";
                            } else {
                                fuelTypeStr = fuelType + "#汽油";
                            }
                            selectedHisFuelType.html(fuelTypeStr);
                            selectedHisFuelQuantity.html(value.fuelQuantity);
                            selectedHisPaidFee.html(value.paidFee);
                            selectedHisPrice.html(value.price);
                            selectedHisStartTime.html(value.startTime);
                            selectedHisEndTime.html(value.endTime);
                        })
                    }
                }
            );
        }


        function toRefuel(licencePlate, selectedLicencePlate, oil, money, loading, color, data) {
            /*记录站台id与rfid*/
            sessionStorage.setItem("stationId" + data.stationId, data.rfId);
            /*展示正在加油的车牌*/
            showLicencePlate(selectedLicencePlate, licencePlate);
            /*样式变化*/
            loading.addClass("lds-ripple");
            $(loading).show();
            color.toggleClass("danger");
        }


        function showLicencePlate(selectedLicencePlate, licencePlate) {
            selectedLicencePlate.removeClass("blink");
            selectedLicencePlate.addClass("text4");
            selectedLicencePlate.html(licencePlate);
        }

    });
</script>
</body>
</html>