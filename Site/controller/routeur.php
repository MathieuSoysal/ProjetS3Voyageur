<?php
    require_once File::build_path(array('controller', 'ControllerAlgo.php'));

    if(isset($_GET['action'])){
        // On récupère l'action passée dans l'URL
        $action = $_GET['action'];
    }else{
        // On dit que l'action est readAll
        $action = "readAll";
    }

    if(isset($_GET['controller'])){
        // On récupère le controller passé dans l'URL
        $controller = $_GET['controller'];
    }else {
        // On dit que le controller est algo
        $controller = "algo";
    }

    $controller_class = "Controller".ucfirst($controller);

    if(class_exists($controller_class)){
        if(in_array($action, get_class_methods($controller_class))){
            // Appel de la méthode static $action de $controller_class
            $controller_class::$action();
        }else{
            // Appel de la méthode static error de $controller_class
            $controller_class::error();
        }
    }else{
        ControllerAlgo::error();
    }
?>