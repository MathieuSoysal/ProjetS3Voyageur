<!DOCTYPE html>
<html>
    <head>
        <!-- Materialize -->
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">

        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>



        <!-- Our Site -->
        <link rel="stylesheet" type="text/css" href="./css/CSSGeneral.css">
        <meta charset="UTF-8">
        <title><?php echo $pagetitle; ?></title>
    </head>
    <body class="Site">
        <header>
            <p>Projet du voyageur de Commerce</p>
        </header>
        <main class="Site-content">
            <?php
                $filepath = File::build_path(array("view", static::$object, "$view.php"));
                require $filepath;
            ?>
            <h1>Se renseigner sur vertex() (commande p5)</h1>
        </main>
        <footer>
            <p>DELON Matthias, RAVET-LECOURT Florian, SOYSAL Mathieu, ZAID Nadir</p>
        </footer>
    </body>
</html>
