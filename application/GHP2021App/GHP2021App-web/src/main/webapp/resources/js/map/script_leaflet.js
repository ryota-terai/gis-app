let lat = 32.9328313249096; // 緯度
let lon = 129.64286026888269; // 経度
let zoom = 16; // ズームレベル

let version = "Y2020"; //地図バージョン
let eqcase = "AVR"; // 確率ケース
let eqcode = "TTL_MTTL"; //地震コード
let format = "geojson"; // 出力形式
let epsg = 4326; //測地系
let attr = "T30_I60_PS"; //30年間で震度6強以上となる確率

let marker;
let map = L.map("map");

if (document.getElementById("frm:latitude").value !== '' 
        && document.getElementById("frm:longitude").value !== ''){
  lat = document.getElementById("frm:latitude").value;
  lon = document.getElementById("frm:longitude").value;
  marker = L.marker([lat, lon]).addTo(map);
}

map.setView([lat, lon], zoom);

L.tileLayer("https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png", {
  attribution:
    '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
}).addTo(map);

map.on("click", function (e) {
  let lat = e.latlng.lat;
  let lon = e.latlng.lng;
  
  if (marker){
    map.removeLayer(marker);
  }
  marker = L.marker([lat, lon]).addTo(map);
  
  document.getElementById("frm:latitude").value = lat;
  document.getElementById("frm:longitude").value = lon;

});