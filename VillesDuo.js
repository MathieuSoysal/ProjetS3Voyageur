let villes = [];
var nbVilles = prompt('Entrez un nombre de villes entre 3 et 15 :');
while(!isINT(nbVilles) || nbVilles > 15 || nbVilles < 3){
    nbVilles = prompt('Entrez un nombre valide (entre 3 et 15) de villes:');
}
var totalVilles = nbVilles;

function setup() {
  createCanvas(400, 400);
  background(102);
  noStroke();
  fill(0, 102);
  for (var i = 0; i < totalVilles; i++) {
    var v = createVector(random(width), random(height));
    villes[i] = v;
  }
}

function draw() {

  fill(255,0,0);
  for (var i = 0; i < villes.length; i++) {
    circle(villes[i].x, villes[i].y, 8);
  }
}

//add a circle whenever the mouse is clicked
function mouseClicked(){
  if(villes.length < 15){
    if(mouseX <= 400 && mouseX >= 0 && mouseY <= 400 && mouseY >= 0){
      //push a new circle to our array
      fill(255,0,0);
      villes.push(circle(mouseX, mouseY, 8));
    }
  }
}

function isINT(n){
    return n == parseInt(n);
}
