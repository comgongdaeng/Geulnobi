
var temp = true;
const $onsite = document.querySelector('#onsite');

var switch1 = $("#gdbSwitch");
switch1.click(function(){
  $("#gdbOFF, #gdbON").toggle();
});

var switch2 = $("#CorSwitch");
switch2.click(function(){
  $("#CorOFF, #CorON").toggle();
});

var switch3 = $("#formalSwitch");
switch3.click(function(){
  $("#formalOFF, #formalON").toggle();
});

var switch4 = $("#wordrecSwitch");
switch4.click(function(){
  $("#wordrecOFF, #wordrecON").toggle();
});
