<!DOCTYPE html>
<html>
    <head>
        <!-- Our Site -->
        <link rel="stylesheet" type="text/css" href="./css/CSSGeneral.css">
        <meta charset="UTF-8">
        <title><?php echo $pagetitle; ?></title>
    </head>
    <body class="Site">
        <header>

        </header>
        <main class="Site-content">
            <?php
                $filepath = File::build_path(array("view", static::$object, "$view.php"));
                require $filepath;
            ?>
        </main>
        <footer>

        </footer>
    </body>
</html>
