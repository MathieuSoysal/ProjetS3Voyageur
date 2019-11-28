<?php
    require_once File::build_path(array('config', 'Conf.php'));

    class Model
    {

        public static $pdo;

        public static function Init()
        {
            $hostname = Conf::getHostname();
            $database_name = Conf::getDatabase();
            $login = Conf::getLogin();
            $password = Conf::getPassword();
            try {
                self::$pdo = new PDO("mysql:host=$hostname;dbname=$database_name", $login, $password, array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"));
                //On active le mode d'affichage des erreurs, et le lancement d'exception en cas d'erreur
                self::$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
            } catch (PDOException $e) {
                if (Conf::getDebug()) {
                    echo $e->getMessage(); // affiche un message d'erreur
                } else {
                    echo 'Une erreur est survenue <a href=""> retour a la page d\'accueil </a>';
                }
                die();
            }
        }

        public static function selectAll()
        {
            try {
                $table_name = static::$object;
                $class_name = 'Model' . ucfirst(static::$object);

                $rep = Model::$pdo->query("SELECT * FROM $table_name");
                $rep->setFetchMode(PDO::FETCH_CLASS, $class_name);
                $tab_o = $rep->fetchAll();

                return $tab_o;
            } catch (PDOException $e) {
                if (Conf::getDebug()) {
                    echo $e->getMessage(); // affiche un message d'erreur
                } else {
                    echo 'Une erreur est survenue <a href=""> retour a la page d\'accueil </a>';
                }
                die();
            }
        }

        public static function select($primary_value)
        {

            try {
                $table_name = static::$object;
                $primary_key = static::$primary;
                $class_name = 'Model' . ucfirst(static::$object);

                $sql = "SELECT * FROM $table_name WHERE $primary_key=:nom_tag";
                //Préparation de la requête
                $req_prep = Model::$pdo->prepare($sql);

                $values = array(
                    "nom_tag" => $primary_value
                );
                $req_prep->execute($values);
                $req_prep->setFetchMode(PDO::FETCH_CLASS, $class_name);
                $tab_o = $req_prep->fetchAll();

                if (empty($tab_o)) {
                    return false;
                } else {
                    return $tab_o[0];
                }
            } catch (PDOException $e) {
                if (Conf::getDebug()) {
                    echo $e->getMessage(); // affiche un message d'erreur
                } else {
                    echo 'Une erreur est survenue <a href=""> retour a la page d\'accueil </a>';
                }
                die();
            }
        }

        public static function delete($primary_value)
        {
            try {
                $table_name = static::$object;
                $primary_key = static::$primary;

                $sql = "DELETE FROM $table_name WHERE $primary_key=:nom_tag;";

                $req_prep = Model::$pdo->prepare($sql);

                $values = array(
                    "nom_tag" => $primary_value
                );
                $req_prep->execute($values);
            } catch (PDOException $e) {
                if (Conf::getDebug()) {
                    echo $e->getMessage();
                } else {
                    echo 'Une erreur est survenue <a href=""> retour a la page d\'accueil </a>';
                }
                die();
            }
        }

        public static function update($data)
        {
            $table_name = static::$object;
            $primary_key = static::$primary;
            try {
                $sql = "UPDATE $table_name SET ";
                $values = array();
                foreach ($data as $key => $value) {
                    if (strcmp($primary_key, $key) != 0) {
                        $values[$key] = $value;
                        $sql = $sql . " $key =:$key ,";
                    }
                }
                /*$primary_value = $data[$primary_key];*/

                $sql = rtrim($sql, ",");
                $sql = $sql . "WHERE $primary_key='$data[$primary_key]' ; ";
                $req_prep = Model::$pdo->prepare($sql);

                /*echo $sql;
                echo "<pre>";print_r($values);echo "</pre>";*/

                $req_prep->execute($values);
            } catch (PDOException $e) {
                if (Conf::getDebug()) {
                    return false;
                    /*echo $e->getMessage();*/ // affiche un message d'erreur

                } else {
                    echo 'Une erreur est survenue <a href=""> retour a la page d\'accueil </a>';
                }
                die();
            }

        }
    }

    Model::Init()
?>
