<?php
require_once('config.php');

function execute($sql) {
    $con = mysqli_connect('localhost', 'root', '', 'cuahangxemay', 3306);
    if (!$con) {
        die("Connection failed: " . mysqli_connect_error());
    }

    mysqli_set_charset($con, 'UTF8');
    mysqli_query($con, $sql);
    mysqli_close($con);
}

function executeResult($sql) {
    $con = mysqli_connect('localhost', 'root', '', 'cuahangxemay', 3306);
    if (!$con) {
        die("Connection failed: " . mysqli_connect_error());
    }

    mysqli_set_charset($con, 'UTF8');
    $result = mysqli_query($con, $sql);
    $data = [];

    while ($row = mysqli_fetch_array($result, 1)) {
        $data[] = $row;
    }

    mysqli_close($con);
    return $data;
}
?>
