<?php
    require_once File::build_path(array('model', 'Model.php'));

    class ModelAlgo extends Model{

        private $nomAlgo;

        protected static $object = "algo";
        protected static $primary = "nomAlgo";


        //Constructeur

        public function __construct($nomAlgo = NULL) {
            if(!is_null($nomAlgo)){
                $this->nomAlgo = $nomAlgo;
            }
        }


        //Getter & Setter

        public function get($nomAttribut){
            return $this->$nomAttribut;
        }

        public function set($nomAttribut, $value){
            $this->$nomAttribut = $value;
        }


        //Méthodes & Fonctions

        public static function deleteAlgoByNomAlgo($nomAlgo){
            try{
                $sql = "DELETE FROM algo
                        WHERE nomAlgo = :nomAlgo;";

                $req_prep = Model::$pdo->prepare($sql);
                $values = array(
                    "nomAlgo" => $nomAlgo
                );
                $req_prep->execute($values);
            }catch (PDOException $e){
                if(Conf::getDebug()){
                    echo $e->getMessage();
                }else{
                    echo 'Une erreur est survenue <a href=""> retour a la page d\'accueil </a>';
                }
                die();
            }
        }

        public function save() {
            try{
                $sql = "INSERT INTO algo (nomAlgo) VALUES (:nomAlgo);";
                $req_prep = Model::$pdo->prepare($sql);
                $values = array(
                    "nomAlgo" => $this->nomAlgo
                );
                $req_prep->execute($values);
            }catch(PDOException $e) {
                if(Conf::getDebug()) {
                    echo $e->getMessage(); // affiche un message d'erreur
                } else {
                    echo 'Une erreur est survenue <a href=""> retour a la page d\'accueil </a>';
                }
                die();
            }
        }

    }

?>