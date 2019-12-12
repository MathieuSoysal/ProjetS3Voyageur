<?php
    require_once File::build_path(array('model', 'ModelAlgo.php'));

    class ControllerAlgo {

        protected static $object = 'algo';

        public static function error(){
            $controller = 'algo';
            $view = 'error';
            $pagetitle = 'Méthode inexistante';
            require File::build_path(array('view', 'view.php'));
        }

        public static function readAll() {
            $tab_a = ModelAlgo::selectAll();     //appel au modèle pour gerer la BD
            $controller = 'algo';
            $view = 'list';
            $pagetitle = 'Liste des algos';
            require File::build_path(array('view','view.php'));  //"redirige" vers la vue
        }

        public static function execute() {
            $a = ModelAlgo::execute($_GET['nomAlgo']);
            $controller = 'algo';
            if($a != false){
                $view = 'execute';
                $pagetitle = "Exécution: {$_GET['nomAlgo']}";
                require File::build_path(array('view', 'view.php'));
            }else{
                $view = 'error';
                $pagetitle = 'Erreur';
                require File::build_path(array('view', 'view.php'));
            }
        }

    }

?>