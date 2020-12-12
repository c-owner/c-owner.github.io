var Links = {
  setColor:function(color){
    // var alist = document.querySelectorAll('a');
    // var i = 0;
    // while(i < alist.length){
    //   alist[i].style.color = color;
    //   i = i + 1;
    // }
    $('a').css('color', color);
  }
}
var Body = {
  setColor:function (color){
    // document.querySelector('body').style.color = color;
    $('body').css('color', color);
  },
  setBackgroundColor:function(color){
  // document.querySelector('body').style.backgroundColor = color;
  $('body').css('backgroundColor', color);
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
}    else {
    Body.setBackgroundColor('white');
    Body.setColor('black');
    target.dataset.mode = 'day';
    self.value = '다크모드';

    Links.setColor('black');
  }
}
// 사이드바 메뉴 링크 호출
  // function sidemenu(self){
  // var target = document.write('sidemenu');
  // }
  //
  // var sidemenu = ['index.html','1.html','2.html','3.html','album.html','edm.html'];
  // var i = 0;
  // while(i < sidemenu.length){
  //   document.write('<li><a href="https://eight-corner.github.io/iu/'+sidemenu[i]+'">)'+sidemenu[i]+'</li>');
  //   i = i + 1;
  // }
