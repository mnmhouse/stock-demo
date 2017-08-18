<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>My WebSocket</title>
</head>

<body>
	<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://cdn.bootcss.com/bootstrap/3.0.1/js/bootstrap.min.js"></script>
	<br />
	<input id="text" type="text" />
	<button onclick="send()">订阅</button>
	<!-- 	<button onclick="closeWebSocket()">Close</button>
 -->
	<div class="stockTitle">
		<div class="topName">
			<span class="stockName"><strong
				title="Shanghai Stock Exchange" class="stockName">上证指数(SH:000001)</strong></span>
		</div>
	</div>

	<div class="stockQuote">
		<div id="currentQuote">
			<div class="currentInfo">
				<strong class="stockUp" data-current="3256.35">3256.35</strong><span
					class="stockUp"><span id="upordown">+9.90</span><span
					class="quote-percentage" id="quote-percentage">&nbsp;&nbsp;(+0.3%)</span></span>
			</div>
			<div class="stockInfo">
				<span id="timeInfo">08-17 11:16:22 (北京时间)&nbsp;&nbsp;</span>
			</div>
		</div>
		<table>
			<tbody>
				<tr>
					<td>今开：<span id="today_open">3253.85</span></td>
					<td>昨收：<span id="last_close">3246.45</span></td>
					<td>最高：<span id="quote-high">3262.09</span></td>
					<td>最低：<span id="quote-low">3251.46</span></td>
				</tr>
				<tr>
					<td title="当日成交量，单位：股">成交量：<span id="quote-volume">108.87亿股</span></td>
					<td title="过去30个交易日平均成交量，单位：股">30日均量：<span>332.70亿</span></td>
					<td>成交额：<span>1156.61亿</span></td>
					<td>52周最高：<span>3305.43</span></td>
				</tr>
				<tr>
					<td>52周最低：<span>2969.13</span></td>
					<td>总市值：<span id="quote-marketCapital">314689.64亿</span></td>
					<td>流通股本：<span>25683.00亿</span></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div id="message"></div>

	<div id="container" style="margin-top: 10px;">阿萨德</div>
</body>
<script type="text/javascript"
	src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
<script type="text/javascript">
	var chartde = null;
	$(document)
			.ready(
					function() {

						Highcharts.setOptions({
							global : {
								useUTC : false
							}
						});
						function activeLastPointToolip(chart) {
							var points = chart.series[0].points;
							chart.tooltip.refresh(points[points.length - 1]);
						}

						$('#container')
								.highcharts(
										{
											chart : {
												type : 'spline',
												animation : Highcharts.svg, // don't animate in old IE
												marginRight : 10,
												events : {
													load : function() {
														// set up the updating of the chart each second
														var series = this.series[0], chart = this;
														chartde = this;
														setInterval(
																function() {
																	var x = (new Date())
																			.getTime(), // current time
																	y = Math
																			.random();
																	series
																			.addPoint(
																					[
																							x,
																							y ],
																					true,
																					true);
																	//activeLastPointToolip(chart)
																}, 100000);
													}
												}
											},
											title : {
												text : '动态模拟股票实时数据'
											},
											xAxis : {
												type : 'datetime',
												tickPixelInterval : 150
											},
											yAxis : {
												title : {
													text : '上证指数'
												},
												plotLines : [ {
													value : 0,
													width : 1,
													color : '#808080'
												} ]
											},
											tooltip : {
												formatter : function() {
													return '<b>'
															+ this.series.name
															+ '</b><br/>'
															+ Highcharts
																	.dateFormat(
																			'%Y-%m-%d %H:%M:%S',
																			this.x)
															+ '<br/>'
															+ Highcharts
																	.numberFormat(
																			this.y,
																			2);
												}
											},
											legend : {
												enabled : false
											},
											exporting : {
												enabled : false
											},
											series : [ {
												name : 'disrupter返回数据',
												data : (function() {
													// generate an array of random data
													var data = [], time = (new Date())
															.getTime(), i;
													for (i = -19; i <= 0; i += 1) {
														data
																.push({
																	x : time
																			+ i
																			* 1000,
																	y : Math
																			.random() + 3000
																});
													}
													return data;
												}())
											} ]
										}, function(c) {
											activeLastPointToolip(c)
										});
					});

	var websocket = null;

	//判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://localhost:8080/stock-demo/stocksocket");
	} else {
		alert('Not support websocket')
	}

	//连接发生错误的回调方法
	websocket.onerror = function() {
		setMessageInnerHTML("error");
	};

	//连接成功建立的回调方法
	websocket.onopen = function(event) {
		setMessageInnerHTML("open");
	}

	//接收到消息的回调方法
	websocket.onmessage = function(event) {
		setMessageInnerHTML(event.data);
	}

	//连接关闭的回调方法
	websocket.onclose = function() {
		setMessageInnerHTML("close");
	}

	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
	window.onbeforeunload = function() {
		websocket.close();
	}

	//将消息显示在网页上
	function setMessageInnerHTML(data) {

		var json = JSON.parse(data);
		var startDate = new Date().Format('yyyy-MM-dd hh:mm:ss');
		document.getElementsByTagName("strong")[1].innerHTML = json.current;
		document.getElementById("today_open").innerHTML = json.open;
		document.getElementById("last_close").innerHTML = json.last_close;
		document.getElementById("quote-high").innerHTML = json.hign;
		document.getElementById("quote-low").innerHTML = json.low;

		document.getElementById("timeInfo").innerHTML = startDate + "(北京时间)";

		var currentData = parseFloat(json.current);
		var openData = parseFloat(json.open);
		var subResult = currentData - openData;
		var resutRate = subResult / openData;
		document.getElementById("upordown").innerHTML = "+" + subResult;

		document.getElementById("quote-percentage").innerHTML = "("
				+ resutRate.toPercent() + ")";

		dynamicDataChart(currentData);

	}

	function dynamicDataChart(resutRate) {

		var series = chartde.series[0]
		var x = (new Date()).getTime(), y = resutRate;
		series.addPoint([ x, y ], true, true);
		var points = chartde.series[0].points;
		chartde.tooltip.refresh(points[points.length - 1]);

	}
	//关闭连接
	function closeWebSocket() {
		websocket.close();
	}

	//发送消息
	function send() {
		var message = document.getElementById('text').value;
		websocket.send(message);
	}

	Number.prototype.toPercent = function() {
		return (Math.round(this * 10000) / 100).toFixed(2) + '%';
	}
	Date.prototype.Format = function(fmt) { //author: meizz 
		var o = {
			"M+" : this.getMonth() + 1, //月份 
			"d+" : this.getDate(), //日 
			"h+" : this.getHours(), //小时 
			"m+" : this.getMinutes(), //分 
			"s+" : this.getSeconds(), //秒 
			"q+" : Math.floor((this.getMonth() + 3) / 3), //季度 
			"S" : this.getMilliseconds()
		//毫秒 
		};
		if (/(y+)/.test(fmt))
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		for ( var k in o)
			if (new RegExp("(" + k + ")").test(fmt))
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	}
</script>
</html>