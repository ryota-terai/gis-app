// ベースマップを作成する
// ここでは3DのOpenStreetMapを表示する
var readDisaportaldata = true;

var map = new maplibregl.Map({
    container: 'map',
    style: readDisaportaldata ? 'style.json' : 'style_normal.json',
    center: [139.61967, 35.55645],
    zoom: 19,
    hash: true,
    pitch: 30,
    localIdeographFontFamily: false
})

// UIツール
// 右下のズームレベルの＋−ボタンを表示する
map.addControl(new maplibregl.NavigationControl(), 'bottom-right');
// 右上の現在位置の取得ボタンを表示する
map.addControl(new maplibregl.GeolocateControl({positionOptions: {enableHighAccuracy: true}, trackUserLocation: true}), 'top-right');
// 左下の尺度を表示する
map.addControl(new maplibregl.ScaleControl());

// URLを取得
const url = new URL(window.location.href);

// URLSearchParamsオブジェクトを取得
const params = url.searchParams;

// getメソッド
const areaCode = params.get('areaCode');

// 画面がロードされたら地図にレイヤを追加する
map.on('load', function () {
    if (!readDisaportaldata) {
        //土砂災害警戒区域データ
        $.getJSON('/ksj/rest/gml/geoJson?areaCode=' + areaCode.substring(0, 2) + '&type=A33', {},
                function (json) {
                    var alpha = 0.5;
                    var opacity = 0.5, opacityDefault = 0.8;
                    var features = json.features;
                    {
                        //1:急傾斜地の崩壊,3:土砂災害警戒区域(指定前)
                        var filtered = features.filter(function (feature) {
                            return feature.properties.A33_001 === '1' && feature.properties.A33_002 === '3';
                        });
                        var a33_1_3 = json;
                        a33_1_3.features = filtered;

                        map.addSource('a33_1_3', {
                            type: 'geojson',
                            data: a33_1_3
                        });
                        map.addLayer({
                            'id': 'a33_1_3',
                            'type': 'fill',
                            'source': 'a33_1_3',
                            "paint": {
                                "fill-antialias": false,
                                "fill-color": "rgba(255, 255, 0, " + alpha + ")",
                                "fill-opacity": opacity
                            }
                        });
                    }
                    {
                        //1:急傾斜地の崩壊,4:土砂災害特別警戒区域(指定前)
                        var filtered = features.filter(function (feature) {
                            return feature.properties.A33_001 === '1' && feature.properties.A33_002 === '4';
                        });
                        var a33_1_4 = json;
                        a33_1_4.features = filtered;

                        map.addSource('a33_1_4', {
                            type: 'geojson',
                            data: a33_1_4
                        });
                        map.addLayer({
                            'id': 'a33_1_4',
                            'type': 'fill',
                            'source': 'a33_1_4',
                            "paint": {
                                "fill-antialias": false,
                                "fill-color": "rgba(255, 0, 0, " + alpha + ")",
                                "fill-opacity": opacity
                            }
                        });
                    }
                    {
                        //2:土石流,3:土砂災害警戒区域(指定前)
                        var filtered = features.filter(function (feature) {
                            return feature.properties.A33_001 === '2' && feature.properties.A33_002 === '3';
                        });
                        var a33_2_3 = json;
                        a33_2_3.features = filtered;

                        map.addSource('a33_2_3', {
                            type: 'geojson',
                            data: a33_2_3
                        });
                        map.addLayer({
                            'id': 'a33_2_3',
                            'type': 'fill',
                            'source': 'a33_2_3',
                            "paint": {
                                "fill-antialias": false,
                                "fill-color": "rgba(255, 217, 102, " + alpha + ")",
                                "fill-opacity": opacity
                            }
                        });
                    }
                    {
                        //2:土石流,4:土砂災害特別警戒区域(指定前)
                        var filtered = features.filter(function (feature) {
                            return feature.properties.A33_001 === '2' && feature.properties.A33_002 === '4';
                        });
                        var a33_2_4 = json;
                        a33_2_4.features = filtered;

                        map.addSource('a33_2_4', {
                            type: 'geojson',
                            data: a33_2_4
                        });
                        map.addLayer({
                            'id': 'a33_2_4',
                            'type': 'fill',
                            'source': 'a33_2_4',
                            "paint": {
                                "fill-antialias": false,
                                "fill-color": "rgba(198, 89, 17, " + alpha + ")",
                                "fill-opacity": opacity
                            }
                        });
                    }
                    {
                        //3:地滑り,3:土砂災害警戒区域(指定前)
                        var filtered = features.filter(function (feature) {
                            return feature.properties.A33_001 === '3' && feature.properties.A33_002 === '3';
                        });
                        var a33_3_3 = json;
                        a33_3_3.features = filtered;

                        map.addSource('a33_3_3', {
                            type: 'geojson',
                            data: a33_3_3
                        });
                        map.addLayer({
                            'id': 'a33_3_3',
                            'type': 'fill',
                            'source': 'a33_3_3',
                            "paint": {
                                "fill-antialias": false,
                                "fill-color": "rgba(255, 180, 90, " + alpha + ")",
                                "fill-opacity": opacity
                            }
                        });
                    }
                    {
                        //3:地滑り,4:土砂災害特別警戒区域(指定前)
                        var filtered = features.filter(function (feature) {
                            return feature.properties.A33_001 === '3' && feature.properties.A33_002 === '4';
                        });
                        var a33_3_4 = json;
                        a33_3_4.features = filtered;

                        map.addSource('a33_3_4', {
                            type: 'geojson',
                            data: a33_3_4
                        });
                        map.addLayer({
                            'id': 'a33_3_4',
                            'type': 'fill',
                            'source': 'a33_3_4',
                            "paint": {
                                "fill-antialias": false,
                                "fill-color": "rgba(197, 81, 148, " + alpha + ")",
                                "fill-opacity": opacity
                            }
                        });
                    }

                    {
                        //1:急傾斜地の崩壊,1:土砂災害警戒区域(指定済)
                        var filtered = features.filter(function (feature) {
                            return feature.properties.A33_001 === '1' && feature.properties.A33_002 === '1';
                        });
                        var a33_1_1 = json;
                        a33_1_1.features = filtered;

                        map.addSource('a33_1_1', {
                            type: 'geojson',
                            data: a33_1_1
                        });
                        map.addLayer({
                            'id': 'a33_1_1',
                            'type': 'fill',
                            'source': 'a33_1_1',
                            "paint": {
                                "fill-antialias": false,
                                "fill-color": "rgba(255, 255, 0, 1)",
                                "fill-opacity": opacityDefault
                            }
                        });
                    }
                    {
                        //1:急傾斜地の崩壊,2:土砂災害特別警戒区域(指定済)
                        var filtered = features.filter(function (feature) {
                            return feature.properties.A33_001 === '1' && feature.properties.A33_002 === '2';
                        });
                        var a33_1_2 = json;
                        a33_1_2.features = filtered;

                        map.addSource('a33_1_2', {
                            type: 'geojson',
                            data: a33_1_2
                        });
                        map.addLayer({
                            'id': 'a33_1_2',
                            'type': 'fill',
                            'source': 'a33_1_2',
                            "paint": {
                                "fill-antialias": false,
                                "fill-color": "rgba(255, 0, 0, 1)",
                                "fill-opacity": opacityDefault
                            }
                        });
                    }
                    {
                        //2:土石流,1:土砂災害警戒区域(指定済)
                        var filtered = features.filter(function (feature) {
                            return feature.properties.A33_001 === '2' && feature.properties.A33_002 === '1';
                        });
                        var a33_2_1 = json;
                        a33_2_1.features = filtered;

                        map.addSource('a33_2_1', {
                            type: 'geojson',
                            data: a33_2_1
                        });
                        map.addLayer({
                            'id': 'a33_2_1',
                            'type': 'fill',
                            'source': 'a33_2_1',
                            "paint": {
                                "fill-antialias": false,
                                "fill-color": "rgba(255, 217, 102, 1)",
                                "fill-opacity": opacityDefault
                            }
                        });
                    }
                    {
                        //2:土石流,2:土砂災害特別警戒区域(指定済)
                        var filtered = features.filter(function (feature) {
                            return feature.properties.A33_001 === '2' && feature.properties.A33_002 === '2';
                        });
                        var a33_2_2 = json;
                        a33_2_2.features = filtered;

                        map.addSource('a33_2_2', {
                            type: 'geojson',
                            data: a33_2_2
                        });
                        map.addLayer({
                            'id': 'a33_2_2',
                            'type': 'fill',
                            'source': 'a33_2_2',
                            "paint": {
                                "fill-antialias": false,
                                "fill-color": "rgba(198, 89, 17, 1)",
                                "fill-opacity": opacityDefault
                            }
                        });
                    }
                    {
                        //3:地滑り,1:土砂災害警戒区域(指定済)
                        var filtered = features.filter(function (feature) {
                            return feature.properties.A33_001 === '3' && feature.properties.A33_002 === '1';
                        });
                        var a33_3_1 = json;
                        a33_3_1.features = filtered;

                        map.addSource('a33_3_1', {
                            type: 'geojson',
                            data: a33_3_1
                        });
                        map.addLayer({
                            'id': 'a33_3_1',
                            'type': 'fill',
                            'source': 'a33_3_1',
                            "paint": {
                                "fill-antialias": false,
                                "fill-color": "rgba(255, 180, 90, 1)",
                                "fill-opacity": opacityDefault
                            }
                        });
                    }
                    {
                        //3:地滑り,2:土砂災害特別警戒区域(指定済)
                        var filtered = features.filter(function (feature) {
                            return feature.properties.A33_001 === '3' && feature.properties.A33_002 === '2';
                        });
                        var a33_3_2 = json;
                        a33_3_2.features = filtered;

                        map.addSource('a33_3_2', {
                            type: 'geojson',
                            data: a33_3_2
                        });
                        map.addLayer({
                            'id': 'a33_3_2',
                            'type': 'fill',
                            'source': 'a33_3_2',
                            "paint": {
                                "fill-antialias": false,
                                "fill-color": "rgba(197, 81, 148, 1)",
                                "fill-opacity": opacityDefault
                            }
                        });
                    }
                });
    }

    // 災害危険区域レイヤを追加
    $.getJSON('/ksj/rest/gml/geoJson?areaCode=' + areaCode + '&type=A48', {},
            function (json) {
                var features = json.features;
                var filtered = features.filter(function (feature) {
                    return areaCode === null || areaCode === '' || feature.properties.A48_003.startsWith(areaCode);
                });
                json.features = filtered;

                map.addSource('a48', {
                    type: 'geojson',
                    data: json
                });
                map.addLayer({
                    'id': 'a48',
                    'type': 'fill',
                    'source': 'a48',
                    "paint": {
                        "fill-antialias": false,
                        "fill-color": "rgba(0, 0, 0, 1)",
                        "fill-opacity": 0.5
                    }
                });
            });

    // 避難所情報レイヤを追加
    $.getJSON('/GISApp/rest/gisapp/shelterInfo?areaCode=' + areaCode + '&P20_007=true&P20_008=true&P20_009=true&P20_010=true&P20_011=true&open=false', {},
            function (json) {
                var features = json.features;
                var filtered = features.filter(function (feature) {
                    return areaCode === null || areaCode === '' || feature.properties.P20_001.startsWith(areaCode);
                });
                json.features = filtered;

                map.addSource('shelter_point', {
                    type: 'geojson',
                    data: json
                });
                map.loadImage(
                        './img/shelter.png',
                        function (error, image) {
                            if (error)
                                throw error;
                            map.addImage('shelter_icon', image);
                        }
                );

                map.addLayer({
                    'id': 'shelter_point',
                    'type': 'symbol',
                    'source': 'shelter_point',
                    'layout': {
                        'icon-image': 'shelter_icon',
                        'icon-size': 0.1
                    }
                });
            });

    $.getJSON('/GISApp/rest/gisapp/shelterInfo?areaCode=' + areaCode + '&P20_007=true&P20_008=true&P20_009=true&P20_010=true&P20_011=true&open=true', {},
            function (json) {
                var features = json.features;
                var filtered = features.filter(function (feature) {
                    return areaCode === null || areaCode === '' || feature.properties.P20_001.startsWith(areaCode);
                });
                json.features = filtered;

                map.addSource('shelter_open_point', {
                    type: 'geojson',
                    data: json
                });
                map.loadImage(
                        './img/shelter_open.png',
                        function (error, image) {
                            if (error)
                                throw error;
                            map.addImage('shelter_open_icon', image);
                        }
                );

                map.addLayer({
                    'id': 'shelter_open_point',
                    'type': 'symbol',
                    'source': 'shelter_open_point',
                    'layout': {
                        'icon-image': 'shelter_open_icon',
                        'icon-size': 0.1
                    }
                });
            });

    // 投稿情報レイヤを追加
    $.getJSON('/GISApp/rest/gisapp/disasterInfo', {},
            function (json) {
                map.addSource('disaster', {
                    type: 'geojson',
                    data: json
                });

                map.loadImage(
                        './img/comment.png',
                        function (error, image) {
                            if (error)
                                throw error;
                            map.addImage('comment_icon', image);
                        }
                );

                // スタイルを設定
                map.addLayer({
                    'id': 'disaster',
                    'type': 'symbol',
                    'source': 'disaster',
                    'layout': {
                        'icon-image': 'comment_icon',
                        'icon-size': 0.1
                    }
                });
            });

});

// 避難所情報の地物をクリックしたときに、コメントを表示する
map.on('click', 'shelter_point', function (e) {
    var coordinates = e.features[0].geometry.coordinates.slice();
    var name = e.features[0].properties.P20_002;
    var comment = e.features[0].properties.comment;
    if (comment != null) {
        name += '<br>' + comment;
    }

    while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
        coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
    }

    // ポップアップを表示する
    new maplibregl.Popup()
            .setLngLat(coordinates)
            .setHTML(name)
            .addTo(map);

    // 避難所情報欄に避難所名を記載する
    var infoName = $("#info-name")[0];
    infoName.innerHTML = name;

    var info = $("#info-comment")[0];
    var infoComment = '<table><tr><td>' + '施設の種類</td><td>' + e.features[0].properties.P20_004 + '</td></tr>'
            + '<tr><td>地震災害</td><td>' + (e.features[0].properties.P20_007 === 1 ? '〇' : '×') + '</td></tr>'
            + '<tr><td>津波災害</td><td>' + (e.features[0].properties.P20_008 === 1 ? '〇' : '×') + '</td></tr>'
            + '<tr><td>水害</td><td>' + (e.features[0].properties.P20_009 === 1 ? '〇' : '×') + '</td></tr>'
            + '<tr><td>火山災害</td><td>' + (e.features[0].properties.P20_010 === 1 ? '〇' : '×') + '</td></tr>'
            + '<tr><td>その他</td><td>' + (e.features[0].properties.P20_011 === 1 ? '〇' : '×') + '</td></tr>'
            + '<tr><td>指定なし</td><td>' + (e.features[0].properties.P20_012 === 1 ? '〇' : '×') + '</td></tr>'
            + '</table>';
    info.innerHTML = infoComment;
});

map.on('click', 'shelter_open_point', function (e) {
    console.log("click")

    var coordinates = e.features[0].geometry.coordinates.slice();
    var name = e.features[0].properties.P20_002;
    var comment = e.features[0].properties.comment;
    name += '<br>避難所開設中';
    name += '<br><a href=\"https://www.google.com/maps/dir/?api=1&destination='
            + e.features[0].geometry.coordinates.slice()[1]
            + ','
            + e.features[0].geometry.coordinates.slice()[0]
            + '\" target=\"_blank\">'
            + '避難所迄のルートを検索</a>';
    if (comment != null) {
        name += '<br>' + comment;
    }

    while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
        coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
    }

    // ポップアップを表示する
    new maplibregl.Popup()
            .setLngLat(coordinates)
            .setHTML(name)
            .addTo(map);

    // 避難所情報欄に避難所名を記載する
    var infoName = $("#info-name")[0];
    infoName.innerHTML = e.features[0].properties.P20_002;

    var info = $("#info-comment")[0];
    var infoComment = '<table><tr><td>' + '施設の種類</td><td>' + e.features[0].properties.P20_004 + '</td></tr>'
            + '<tr><td>地震災害</td><td>' + (e.features[0].properties.P20_007 === 1 ? '〇' : '×') + '</td></tr>'
            + '<tr><td>津波災害</td><td>' + (e.features[0].properties.P20_008 === 1 ? '〇' : '×') + '</td></tr>'
            + '<tr><td>水害</td><td>' + (e.features[0].properties.P20_009 === 1 ? '〇' : '×') + '</td></tr>'
            + '<tr><td>火山災害</td><td>' + (e.features[0].properties.P20_010 === 1 ? '〇' : '×') + '</td></tr>'
            + '<tr><td>その他</td><td>' + (e.features[0].properties.P20_011 === 1 ? '〇' : '×') + '</td></tr>'
            + '<tr><td>指定なし</td><td>' + (e.features[0].properties.P20_012 === 1 ? '〇' : '×') + '</td></tr>'
            + '</table>';
    info.innerHTML = infoComment;

});

// 投稿情報の地物をクリックしたときに、コメントを表示する
map.on('click', 'disaster', function (e) {
    console.log("click")

    var coordinates = e.features[0].geometry.coordinates.slice();
    var comment = e.features[0].properties.comment;
    var id = e.features[0].properties.id;
    var picture = e.features[0].properties.picture;

    // コメントに改行コードが含まれている場合、改行タグに変換する
    if (comment.match('\n')) {
        comment = comment.replace('\n', '<br>');
    }
    if (picture === true) {
        comment += '<br><iframe src=\"/GISApp/faces/post/view/picture.xhtml?id=' + id + '\" width="200" height="150"></iframe>';
        comment += '<br><a href=\"/GISApp/faces/post/view/view.xhtml?id=' + id + '\" target=\"_blank\">投稿情報画面で確認</a>';
    }

    while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
        coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
    }

    new maplibregl.Popup()
            .setLngLat(coordinates)
            .setHTML(comment)
            .addTo(map);
});

// 災害危険区域レイヤを追加
map.on('click', 'a48', function (e) {
    console.log("click")

    var coordinates;
    if (e.features[0].geometry.type === 'Polygon') {
        coordinates = e.features[0].geometry.coordinates[0][0].slice();
    } else if (e.features[0].geometry.type === 'MultiPolygon') {
        coordinates = e.features[0].geometry.coordinates[0][0][0].slice();
    }
    var html = e.features[0].properties.A48_005;

    while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
        coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
    }

    // ポップアップを表示する
    new maplibregl.Popup()
            .setLngLat(coordinates)
            .setHTML(html)
            .addTo(map);


    var infoName = $("#info-name")[0];
    infoName.innerHTML = e.features[0].properties.A48_005;

    var info = $("#info-comment")[0];
    var infoComment = '<table>'
            + '<tr><td>' + '都道府県名</td><td>' + e.features[0].properties.A48_001 + '</td></tr>'
            + '<tr><td>' + '市町村名</td><td>' + e.features[0].properties.A48_002 + '</td></tr>'
            + '<tr><td>' + '代表行政コード</td><td>' + e.features[0].properties.A48_003 + '</td></tr>'
            + '<tr><td>' + '指定主体区分</td><td>' + e.features[0].properties.A48_004 + '('
            + (e.features[0].properties.A48_004 === 1 ? '都道府県' :
                    (e.features[0].properties.A48_004 === 2 ? '市町村' : '')
                    )
            + ')</td></tr>'
            + '<tr><td>' + '区域名</td><td>' + e.features[0].properties.A48_005 + '</td></tr>'
            + '<tr><td>' + '所在地</td><td>' + e.features[0].properties.A48_006 + '</td></tr>'
            + '<tr><td>' + '指定理由コード</td><td>' + e.features[0].properties.A48_007 + '('
            + (e.features[0].properties.A48_007 === 1 ? '水害(河川)' :
                    (e.features[0].properties.A48_007 === 2 ? '水害(海)' :
                            (e.features[0].properties.A48_007 === 3 ? '水害(河川・海)' :
                                    (e.features[0].properties.A48_007 === 4 ? '急傾斜地崩壊等' :
                                            (e.features[0].properties.A48_007 === 5 ? '地すべり等' :
                                                    (e.features[0].properties.A48_007 === 6 ? '火山被害' :
                                                            (e.features[0].properties.A48_007 === 7 ? 'その他' : '')
                                                            )
                                                    )
                                            )
                                    )
                            )
                    )
            + ')</td></tr>'
            + '<tr><td>' + '指定理由詳細</td><td>' + e.features[0].properties.A48_008 + '</td></tr>'
            + '<tr><td>' + '告示年月日</td><td>' + e.features[0].properties.A48_009 + '</td></tr>'
            + '<tr><td>' + '告示番号</td><td>' + e.features[0].properties.A48_010 + '</td></tr>'
            + '<tr><td>' + '根拠条例</td><td>' + e.features[0].properties.A48_011 + '</td></tr>'
            + '<tr><td>' + '面積</td><td>' + e.features[0].properties.A48_012 + 'ha</td></tr>'
            + '<tr><td>' + '縮尺</td><td>' + e.features[0].properties.A48_013 + '</td></tr>'
            + '<tr><td>' + 'その他</td><td>' + e.features[0].properties.A48_014 + '</td></tr>'
            + '</table>';
    info.innerHTML = infoComment;
});

// 土砂災害警戒区域レイヤを追加
if (!readDisaportaldata) {
    map.on('click', 'a33_1_1', function (e) {
        setA33PopupAndComment(e);
    });
    map.on('click', 'a33_1_2', function (e) {
        setA33PopupAndComment(e);
    });
    map.on('click', 'a33_1_3', function (e) {
        setA33PopupAndComment(e);
    });
    map.on('click', 'a33_1_4', function (e) {
        setA33PopupAndComment(e);
    });
    map.on('click', 'a33_2_1', function (e) {
        setA33PopupAndComment(e);
    });
    map.on('click', 'a33_2_2', function (e) {
        setA33PopupAndComment(e);
    });
    map.on('click', 'a33_2_3', function (e) {
        setA33PopupAndComment(e);
    });
    map.on('click', 'a33_2_4', function (e) {
        setA33PopupAndComment(e);
    });
    map.on('click', 'a33_3_1', function (e) {
        setA33PopupAndComment(e);
    });
    map.on('click', 'a33_3_2', function (e) {
        setA33PopupAndComment(e);
    });
    map.on('click', 'a33_3_3', function (e) {
        setA33PopupAndComment(e);
    });
    map.on('click', 'a33_3_4', function (e) {
        setA33PopupAndComment(e);
    });
}

function setA33PopupAndComment(e) {
    var coordinates;
    if (e.features[0].geometry.type === 'Polygon') {
        coordinates = e.features[0].geometry.coordinates[0][0].slice();
    } else if (e.features[0].geometry.type === 'MultiPolygon') {
        coordinates = e.features[0].geometry.coordinates[0][0][0].slice();
    }
    var html = e.features[0].properties.A33_005;

    while (Math.abs(e.lngLat.lng - coordinates[0]) > 180) {
        coordinates[0] += e.lngLat.lng > coordinates[0] ? 360 : -360;
    }

    // ポップアップを表示する
    new maplibregl.Popup()
            .setLngLat(coordinates)
            .setHTML(html)
            .addTo(map);


    var infoName = $("#info-name")[0];
    infoName.innerHTML = e.features[0].properties.A33_005;

    var info = $("#info-comment")[0];
    var infoComment = '<table>'
            + '<tr><td>' + '現象の種類</td><td>' + e.features[0].properties.A33_001 + '('
            + (e.features[0].properties.A33_001 === '1' ? '急傾斜地の崩壊' :
                    (e.features[0].properties.A33_001 === '2' ? '土石流' :
                            (e.features[0].properties.A33_001 === '3' ? '地滑り' : ''
                                    )
                            )
                    )
            + ')</td></tr>'
            + '<tr><td>' + '区域区分</td><td>' + e.features[0].properties.A33_002 + '('
            + (e.features[0].properties.A33_002 === '1' ? '土砂災害警戒区域(指定済)' :
                    (e.features[0].properties.A33_002 === '2' ? '土砂災害特別警戒区域(指定済)' :
                            (e.features[0].properties.A33_002 === '3' ? '土砂災害警戒区域(指定前)' :
                                    (e.features[0].properties.A33_002 === '4' ? '土砂災害特別警戒区域(指定前)' : '')
                                    )
                            )
                    )
            + ')</td></tr>'
            + '<tr><td>' + '都道府県コード</td><td>' + e.features[0].properties.A33_003 + '</td></tr>'
            + '<tr><td>' + '区域番号</td><td>' + e.features[0].properties.A33_004 + '</td></tr>'
            + '<tr><td>' + '区域名</td><td>' + e.features[0].properties.A33_005 + '</td></tr>'
            + '<tr><td>' + '所在地</td><td>' + e.features[0].properties.A33_006 + '</td></tr>'
            + '<tr><td>' + '告示日</td><td>' + e.features[0].properties.A33_007 + '</td></tr>'
            + '<tr><td>' + '特別警戒未指定フラグ</td><td>' + e.features[0].properties.A33_008 + '('
            + (e.features[0].properties.A33_008 === '0' ? '特別警戒区域指定済み' :
                    (e.features[0].properties.A33_008 === '1' ? '特別警戒区域未指定' : '')
                    )
            + ')</td></tr>'
            + '</table>';
    info.innerHTML = infoComment;
}

// Change the cursor to a pointer when the mouse is over the places layer.
map.on('mouseenter', 'shelter_point', function () {
    map.getCanvas().style.cursor = 'pointer';
});

// Change it back to a pointer when it leaves.
map.on('mouseleave', 'shelter_point', function () {
    map.getCanvas().style.cursor = '';
});

// Change the cursor to a pointer when the mouse is over the places layer.
map.on('mouseenter', 'disaster', function () {
    map.getCanvas().style.cursor = 'pointer';
});

// Change it back to a pointer when it leaves.
map.on('mouseleave', 'disaster', function () {
    map.getCanvas().style.cursor = '';
});

/* // チェックボックスのオンオフでレイヤの表示/非表示を切り替える
 
 $(#shelter-layer).click(function(){
 if(!$(this).prop('checked')){
 map.removeLayer('shelter_point');
 }
 }); */