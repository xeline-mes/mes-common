<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String wsPath = "ws://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
  <title>Home</title>
</head>
<body>
<h1>
  This is a WebSocket demo!
  <div id="message" />
</h1>
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
<!--
<script type="text/javascript" src="js/orgi/highcharts.src.js"></script>
<script type="text/javascript" src="js/orgi/jquery-3.2.1.js"></script>
<script type="text/javascript" src="js/orgi/sockjs.js"></script>
-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/socket.io/1.7.3/socket.io.js"></script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

<script type="text/javascript">
$(function(){
  //建立socket连接
  var sock;
  if ('WebSocket' in window) {
    sock = new WebSocket("<%=wsPath%>socketServer");
  } else if ('MozWebSocket' in window) {
    sock = new MozWebSocket("<%=wsPath%>socketServer");
  } else {
    sock = new SockJS("<%=basePath%>sockjs/socketServer");
  }
  sock.onopen = function (e) {
    console.log(e);
  };
  sock.onmessage = function (e) {
    console.log(e)
    $("#message").append("<p><font color='red'>"+e.data+"</font>")
  };
  sock.onerror = function (e) {
    console.log(e);
  };
    sock.onclose = function (e) {
    console.log(e);
  }
});

Highcharts.chart('container', {
  chart: {
      type: 'line'
  },
  title: {
      text: 'Monthly Average Temperature'
  },
  subtitle: {
      text: 'Source: WorldClimate.com'
  },
  xAxis: {
      categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
  },
  yAxis: {
      title: {
          text: 'Temperature (°C)'
      }
  },
  plotOptions: {
      line: {
          dataLabels: {
              enabled: true
          },
          enableMouseTracking: false
      }
  },
  series: [{
      name: 'Tokyo',
      data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
  }, {
      name: 'London',
      data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
  }]
});


</script>

</body>
</html>
