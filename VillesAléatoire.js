var villes = [];
var nbVilles = prompt('Entrez le nombre de villes :');
while(!isINT(nbVilles)){
    nbVilles = prompt('Entrez le nombre de villes :');
}
var totalVilles = nbVilles;

function setup() {
  createCanvas(400, 400);
  for (var i = 0; i < totalVilles; i++) {
    var v = createVector(random(width), random(height));
    villes[i] = v;
  }


}

function draw() {
  background(0);
  fill(255,0,0);
  for (var i = 0; i < villes.length; i++) {
    ellipse(villes[i].x, villes[i].y, 8, 8);
  }
}

function isINT(n){
    return parseFloat(n) == parseInt(n);
}
