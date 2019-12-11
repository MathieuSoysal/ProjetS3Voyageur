// noprotect
let villes = [];
let trajet = [];
let totalVilles = villes.length-1;

function setup() {
  createCanvas(400, 400);
  background(102);
  noStroke();
  fill(0, 102);
}

function draw() {
 /* if (mouseIsPressed) {
    fill(255,0,0);
    circle(mouseX, mouseY, 8);
  }*/
  if(villes.length > 2){
  for (var i = 0; i < villes.length-1; i++) {
    trajet.push(line(villes[i].x, villes[i].y, villes[i+1].x, villes[i+1].y));
    //console.log(trajet);
  }
  }
}

function isINT(n){
    return n == parseInt(n);
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
