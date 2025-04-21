<?php

session_start();
if(isset($_GET['display'])&& !empty($_GET['display'])){
    $_SESSION['display']= $_GET['display'];
}
else{
    header('location:index.php');
}


?>