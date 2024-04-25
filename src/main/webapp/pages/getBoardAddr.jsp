<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2024-04-24
  Time: 오후 2:22
  To change this template use File | Settings | File Templates.
  cfcdf1ec6a3052ddfc0446b40026bf8f


--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .map_wrap {position:relative;width:100%;height:350px;}
        .title {font-weight:bold;display:block;}
        .hAddr {position:absolute;left:10px;top:10px;border-radius: 2px;background:#fff;background:rgba(255,255,255,0.8);z-index:1;padding:5px;}
        #centerAddr {display:block;margin-top:2px;font-weight: normal;}
        .bAddr {padding:5px;text-overflow: ellipsis;overflow: hidden;white-space: nowrap;}
    </style>
</head>
<body>
<%--<div id="map" style="width:750px;height:350px;"></div>--%>

<script src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=cfcdf1ec6a3052ddfc0446b40026bf8f"></script>

<%--<script>--%>
<%--    var message;--%>

<%--    var mapContainer = document.getElementById('map'), // 지도를 표시할 div--%>
<%--        mapOption = {--%>
<%--            center: new kakao.maps.LatLng(37.56682, 126.97865), // 지도의 중심좌표--%>
<%--            level: 3, // 지도의 확대 레벨--%>
<%--            mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류--%>
<%--        };--%>
<%--    // 지도를 생성한다--%>
<%--    var map = new kakao.maps.Map(mapContainer, mapOption);--%>

<%--    // 마커--%>
<%--    var marker = new kakao.maps.Marker({--%>
<%--        position: new kakao.maps.LatLng(37.56682, 126.97865), // 지도의 중심좌표--%>
<%--        map: map--%>
<%--    });--%>

<%--    //인포윈도우--%>
<%--    var infowindow = new kakao.maps.InfoWindow({--%>
<%--        content: '<div style="padding: 5px;">열로 드루와</div>'--%>
<%--    });--%>

<%--    infowindow.open(map, marker);--%>

<%--    function getInfo() {--%>
<%--        // 지도의 현재 중심좌표를 얻어옵니다--%>
<%--        var center = map.getCenter();--%>
<%--        message = '지도 중심좌표는 위도 ' + center.getLat();--%>
<%--        message += ', 경도 ' + center.getLng() + ' <br>';--%>
<%--    }--%>
<%--    getInfo();--%>
<%--    console.log(message);--%>
<%--</script>--%>
<!-- 이미지 지도를 표시할 div 입니다 -->

<div id="staticMap" style="width:600px;height:350px;"></div>

<script>
    // 이미지 지도에 표시할 마커입니다
    var marker = {
        position: new kakao.maps.LatLng(33.450701, 126.570667),
        text: '열로 드루와' // text 옵션을 설정하면 마커 위에 텍스트를 함께 표시할 수 있습니다
    };

    var staticMapContainer  = document.getElementById('staticMap'), // 이미지 지도를 표시할 div
        staticMapOption = {
            center: new kakao.maps.LatLng(33.450701, 126.570667), // 이미지 지도의 중심좌표
            level: 3, // 이미지 지도의 확대 레벨
            marker: marker // 이미지 지도에 표시할 마커
        };

    // 이미지 지도를 생성합니다
    var staticMap = new kakao.maps.StaticMap(staticMapContainer, staticMapOption);
</script>
</body>
</html>