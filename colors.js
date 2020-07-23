var Links = {
  setColor:function(color){
    var alist = document.querySelectorAll('a');
    var i = 0;
    while(i < alist.length){
      alist[i].style.color = color;
      i = i + 1;
    }
  }
}
var Body = {
  setColor:function (color){
    document.querySelector('body').style.color = color;
  },
setBackgroundColor:function(color){
  document.querySelector('body').style.backgroundColor = color;
}
}
function nightDayTogle(self){
var target = document.querySelector('body');
if(target.dataset.mode === 'day'){
  Body.setBackgroundColor('black');
  Body.setColor('white');
  target.dataset.mode = 'night';
  self.value = '라이트모드';

  Links.setColor('powderblue');
} else {
  Body.setBackgroundColor('white');
  Body.setColor('black');
  target.dataset.mode = 'day';
  self.value = '다크모드';

  Links.setColor('black');
}
}
