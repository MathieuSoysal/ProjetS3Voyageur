<?php
    require_once File::build_path(array('model', 'ModelVoiture.php'));

    class ControllerAlgo {

        protected static $object = 'algo';

        public static function error(){
            $controller = 'algo';
            $view = 'error';
            $pagetitle = 'Méthode inexistante';
            require File::build_path(array('view', 'view.php'));
        }

        public static function readAll() {
            $tab_v = ModelAlgo::selectAll();     //appel au modèle pour gerer la BD
            $controller = 'algo';
            $view = 'list';
            $pagetitle = 'Liste des algos';
            require File::build_path(array('view','view.php'));  //"redirige" vers la vue
        }

    }

?>