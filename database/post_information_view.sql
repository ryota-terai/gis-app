SELECT 
    point.POINT_ID,
    point.PRIVATE,
    point.TYPE,
    X,
    Y,
    Z,
    info1.STRING AS INFORMATION,
    info2.BOOLEAN AS APPROVED,
    info2.UPDATE_TIME AS UPDATE_TIME
FROM
    GISApp.POINT AS point
        JOIN
    GISApp.POINT_INFORMATION AS info1 ON info1.NAME = 'INFORMATION'
        AND point.POINT_ID = info1.POINT_ID
        JOIN
    GISApp.POINT_INFORMATION AS info2 ON info2.NAME = 'APPROVED'
        AND point.POINT_ID = info2.POINT_ID
WHERE
    point.TYPE = 'post_information';