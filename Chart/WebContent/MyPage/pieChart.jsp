<%@ page contentType = "text/html; charset=utf-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<% request.setCharacterEncoding("utf-8");%>
<html>
  <head>
    <c:set var="b" value="0"/>
            <c:forEach var="a" items="${availableCarList}">	
     <c:set var="b" value="${a.agency_no }"/>
    
  

    </c:forEach>
    
    
    
  <c:out value="${Operating}"/>	 
 

   <c:out value="${Operating}"/>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js">
</script>
    <script type="text/javascript"> 
    
    google.charts.load("current", {packages:["corechart"]});
    google.charts.setOnLoadCallback(drawChart);


    function drawChart() {
    	
    	
    	
        var data = google.visualization.arrayToDataTable([
              ['Language', 'Speakers (in millions)'],
              ['운행중',${b}], ['예약대기', ${Ready}], ['예약완료', ${Paid}],
              ['정비중', ${Reparing}], ['연체중',${Delay}]
            ]);		
  
   	
      			//운행중 : Operating, 예약대기 : Ready, 예약완료 : Paid, 정비중: Reparing, 연체중 :  Delay

        var options = {
          title: 'Indian Language Use',
          legend: 'none',
          pieSliceText: 'label',
/*    slices: {  0: {offset: 0.1},				//튀어나와보이게 하는 애들. 0부터 시작이다.
                    3: {offset: 0.1}
                   
          },   */
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        chart.draw(data, options);
        var chart1 = new google.visualization.PieChart(document.getElementById('piechart1'));
        chart1.draw(data, options);
      }
    </script>
  </head>
  <body>


    
    <div id="piechart" style="width: 900px; height: 500px;"></div>
        <div id="piechart1" style="width: 1000px; height: 600px;"></div>

  </body>
</html>






<%-- <c:forEach var="article" items="${articleList}">			<!-- articleList에서 하나씩 뽑아서 article에 저장한다. -->
   


  ${number}		<!-- 굳이 c out 안써주고 그냥 출력해도 될 듯. -->
		<!-- ★이건 왜 하는거지? 왜 -1 ? -->


    
  <c:if test="${article.re_level > 0}">
  <c:out value="${article.re_level }"/>
  ${article.re_level }			<!-- 굳이 c:out 쓸 필요는 없는 것. -->
  <c:set var="re_revel" value="1"/>

  <img src="images/${article.re_level}.jpg" width="${115 * article.re_level}" height="16">
    <img src="images/re.gif">
  </c:if>
  

      <a href="/Before/MVC/content.do?num=${article.num}&pageNum=${currentPage}">
          ${article.subject}			<!-- 제목 -->



 
       <a href="mailto:${article.email}">${article.writer}<!-- 작성자 -->

 ${article.reg_date}	<!-- 작성일 -->

  ${article.readcount}	<!-- 몇번읽었는지 -->
${article.ip}	
 

  
  </c:forEach> --%>