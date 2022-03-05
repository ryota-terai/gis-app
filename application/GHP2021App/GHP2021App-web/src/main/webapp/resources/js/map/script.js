let lat = 32.986804; // 緯度
let lon = 129.768337; // 経度
let zoom = 16; // ズームレベル

// ベースマップを作成する
// ここでは3DのOpenStreetMapを表示する
var map = new maplibregl.Map({
    container: 'map',
    style: '/GHP2021App/style_normal.json',
    center: [lon, lat],
    zoom: zoom,
    hash: true,
    pitch: 30,
    localIdeographFontFamily: false
});

let marker;
if (document.getElementById("frm:latitude").value !== ''
        && document.getElementById("frm:longitude").value !== '') {
    lat = document.getElementById("frm:latitude").value;
    lon = document.getElementById("frm:longitude").value;
    marker = new maplibregl.Marker()
            .setLngLat([lon, lat])
            .addTo(map);
}

map.setCenter([lon, lat]);

// UIツール
// 右下のズームレベルの＋−ボタンを表示する
map.addControl(new maplibregl.NavigationControl(), 'bottom-right');
// 右上の現在位置の取得ボタンを表示する
map.addControl(new maplibregl.GeolocateControl({positionOptions: {enableHighAccuracy: true}, trackUserLocation: true}), 'top-right');
// 左下の尺度を表示する
map.addControl(new maplibregl.ScaleControl());


map.on("click", function (e) {
    let lat = e.lngLat.lat;
    let lon = e.lngLat.lng;

    if (marker) {
        marker.remove();
    }
    marker = new maplibregl.Marker()
            .setLngLat([lon, lat])
            .addTo(map);

    document.getElementById("frm:latitude").value = lat;
    document.getElementById("frm:longitude").value = lon;

});