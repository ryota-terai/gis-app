SELECT 
    point.POINT_ID,
    point.PRIVATE,
    point.TYPE,
    X,
    Y,
    Z,
    info1.STRING AS P20_001,
    info2.STRING AS P20_002,
    info3.STRING AS P20_003,
    info4.STRING AS P20_004,
    info5.NUMBER AS P20_005,
    info6.NUMBER AS P20_006,
    info7.NUMBER AS P20_007,
    info8.NUMBER AS P20_008,
    info9.NUMBER AS P20_009,
    info10.NUMBER AS P20_010,
    info11.NUMBER AS P20_011,
    info12.NUMBER AS P20_012,
    info13.BOOLEAN AS OPEN,
    info14.STRING AS COMMENT,
    info14.UPDATE_TIME AS UPDATE_TIME
FROM
    GISApp.POINT AS point
        JOIN
    GISApp.POINT_INFORMATION AS info1 ON info1.NAME = 'P20_001'
        AND point.POINT_ID = info1.POINT_ID
        JOIN
    GISApp.POINT_INFORMATION AS info2 ON info2.NAME = 'P20_002'
        AND point.POINT_ID = info2.POINT_ID
        JOIN
    GISApp.POINT_INFORMATION AS info3 ON info3.NAME = 'P20_003'
        AND point.POINT_ID = info3.POINT_ID
        JOIN
    GISApp.POINT_INFORMATION AS info4 ON info4.NAME = 'P20_004'
        AND point.POINT_ID = info4.POINT_ID
        JOIN
    GISApp.POINT_INFORMATION AS info5 ON info5.NAME = 'P20_005'
        AND point.POINT_ID = info5.POINT_ID
        JOIN
    GISApp.POINT_INFORMATION AS info6 ON info6.NAME = 'P20_006'
        AND point.POINT_ID = info6.POINT_ID
        JOIN
    GISApp.POINT_INFORMATION AS info7 ON info7.NAME = 'P20_007'
        AND point.POINT_ID = info7.POINT_ID
        JOIN
    GISApp.POINT_INFORMATION AS info8 ON info8.NAME = 'P20_008'
        AND point.POINT_ID = info8.POINT_ID
        JOIN
    GISApp.POINT_INFORMATION AS info9 ON info9.NAME = 'P20_009'
        AND point.POINT_ID = info9.POINT_ID
        JOIN
    GISApp.POINT_INFORMATION AS info10 ON info10.NAME = 'P20_010'
        AND point.POINT_ID = info10.POINT_ID
        JOIN
    GISApp.POINT_INFORMATION AS info11 ON info11.NAME = 'P20_011'
        AND point.POINT_ID = info11.POINT_ID
        JOIN
    GISApp.POINT_INFORMATION AS info12 ON info12.NAME = 'P20_012'
        AND point.POINT_ID = info12.POINT_ID
        LEFT JOIN
    GISApp.POINT_INFORMATION AS info13 ON info13.NAME = 'OPEN'
        AND point.POINT_ID = info13.POINT_ID
        LEFT JOIN
    GISApp.POINT_INFORMATION AS info14 ON info14.NAME = 'COMMENT'
        AND point.POINT_ID = info14.POINT_ID
WHERE
    point.TYPE = 'shelter';