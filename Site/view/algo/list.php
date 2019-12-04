<?php
    echo '
    <div class="col s12 m7">
        <h2 class="header">Liste des alogrithmes</h2>
        ';
        foreach ($tab_a as $a){
            echo'
        <div class="card horizontal">
            <div class="card-image">
                <img src="./ressources/images/'.$a->get('image') .'" alt="Illustration de '. $a->get('nomAlgo') .'">
            </div>
            <div class="card-stacked">
                <div class="card-content">
                    <p>'. $a->get('description') .'</p>
                </div>
            </div>
            <div class="card-action">
                <a href="#">This is a link</a>
            </div>
        </div>
            ';
        }
        echo '
    </div>
    ';
?>