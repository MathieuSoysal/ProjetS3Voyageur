let villes = [];
var nbVilles = prompt('Entrez un nombre de villes entre 3 et 15 :');
while(!isINT(nbVilles) || nbVilles > 15 || nbVilles < 3){
    nbVilles = prompt('Entrez un nombre valide (entre 3 et 15) de villes:');
}
var totalVilles = nbVilles;
let trajet = [];

function setup() {
  createCanvas(400, 400);
  background(102);
  noStroke();
  fill(0, 102);
  for (var i = 0; i < totalVilles; i++) {
    var v = createVector(random(width), random(height));
    villes[i] = (i, v);
  }
}

function draw() {
  
 // console.log("pi");

  fill(255,0,0);
  for (var i = 0; i < villes.length; i++) {
    ellipse(villes[i].x, villes[i].y, 8, 8);
  }
  
  stroke(255, 0, 0);
  strokeWeight(4);
  
  for (var i = 0; i < villes.length-1; i++) {
    trajet.push(line(villes[i].x, villes[i].y, villes[i+1].x, villes[i+1].y));
    //console.log(trajet);
  }
}

//add a circle whenever the mouse is clicked
function mouseClicked(){
  if(villes.length < 15){
    if(mouseX <= 400 && mouseX >= 0 && mouseY <= 400 && mouseY >= 0){
      //push a new circle to our array
      fill(255,0,0);
      let nouvVilles = villes.slice(totalVilles++, 0, ellipse(mouseX, mouseY, 8, 8));
      var v = createVector(mouseX, mouseY);
      villes[villes.length] = (villes.length, v);
    }
  }
}

function isINT(n){
    return n == parseInt(n);
}
