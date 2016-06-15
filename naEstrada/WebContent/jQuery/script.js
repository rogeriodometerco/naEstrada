window.onload = function() {
  initMap();
};

function initMap() {
  var map = new google.maps.Map(document.getElementById('map'), {
    zoom: 13,
	 mapTypeControlOptions: {
        style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
        position: google.maps.ControlPosition.LEFT_BOTTOM
    },
    center: {lat: 59.325, lng: 18.070}
  });
  //alert(map)
}

function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
    document.getElementById("main").style.marginLeft = "250px";
}

function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
    document.getElementById("main").style.marginLeft= "0";
}