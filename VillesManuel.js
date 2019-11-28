// noprotect
let villes = [];
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
}

//add a circle whenever the mouse is clicked
function mouseClicked(){
  
  if(villes.length < 15){
    if(mouseX <= 400 && mouseX >= 0 && mouseY <= 400 && mouseY >= 0){
      //push a new circle to our array
      fill(255,0,0);
      villes.push( circle(mouseX, mouseY, 8));
      console.log(mouseX, mouseY);
      console.log(villes.length);
    }
  }
}
