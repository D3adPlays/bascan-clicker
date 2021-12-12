// Quand une nouvelle <option> est selectionnée
window.onload=function(){
  function pause(ms) 
{
  return new Promise(resolve => setTimeout(resolve, ms));
}
    async function affichertime(){
        while(true){
            await pause(1000);
            var today =new Date();
            var time = today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();
            document.getElementById('time').innerHTML = time;
            var date = today.getDate()+"-"+(today.getMonth()+1)+'-'+today.getFullYear(); 
            document.getElementById('date').innerHTML = date;
            
        }
    }
    affichertime()
   
    
  var selectElem = document.getElementById('select');
  var pElem = document.getElementById('p');
  selectElem.addEventListener("click", handler);
  
  function handler(){
    var index = selectElem.selectedIndex;
  // Rapporter cette donnée au <p>
  pElem.innerHTML = 'selectedIndex: ' + index;
  }
}

var selectElem = document.getElementById('select');
selectElem.addEventListener("click", handler);

function handler(){
  var index = selectElem.selectedIndex;
  if(index==0){
    setCookie("classe", index, 365)
  }
  if(index==1){
    setCookie("classe", index, 365)
  }
  if(index==2){
    setCookie("classe", index, 365)
  }
  if(index==3){
    setCookie("classe", index, 365)
  }
}

function setCookie(cname, cvalue, exdays) {
  const d = new Date();
  d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
  let expires = "expires="+d.toUTCString();
  document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
}


function getCookie(cname) {
  let name = cname + "=";
  let ca = document.cookie.split(';');
  for(let i = 0; i < ca.length; i++) {
    let c = ca[i];
    while (c.charAt(0) == ' ') {
      c = c.substring(1);
    }
    if (c.indexOf(name) == 0) {
      return c.substring(name.length, c.length);
    }
  }
  return "";
}
function checkCookie(){
  classe = getCookie('classe')
  if(classe==""){
    console.log("Seclectionnez un choix")
  } else{
    window.location.replace('../play/')
  }
}











/**$(".custom-select").each(function() {
    var classes = $(this).attr("class"),
        id      = $(this).attr("id"),
        name    = $(this).attr("name");
    var template =  '<div class="' + classes + '">';
        template += '<span class="custom-select-trigger">' + $(this).attr("placeholder") + '</span>';
        template += '<div class="custom-options">';
        $(this).find("option").each(function() {
          template += '<span class="custom-option ' + $(this).attr("class") + '" data-value="' + $(this).attr("value") + '">' + $(this).html() + '</span>';
        });
    template += '</div></div>';
    
    $(this).wrap('<div class="custom-select-wrapper"></div>');
    $(this).hide();
    $(this).after(template);
  });
  $(".custom-option:first-of-type").hover(function() {
    $(this).parents(".custom-options").addClass("option-hover");
  }, function() {
    $(this).parents(".custom-options").removeClass("option-hover");
  });
  $(".custom-select-trigger").on("click", function() {
    $('html').one('click',function() {
      $(".custom-select").removeClass("opened");
    });
    $(this).parents(".custom-select").toggleClass("opened");
    event.stopPropagation();
  });
  $(".custom-option").on("click", function() {
    $(this).parents(".custom-select-wrapper").find("select").val($(this).data("value"));
    $(this).parents(".custom-options").find(".custom-option").removeClass("selection");
    $(this).addClass("selection");
    $(this).parents(".custom-select").removeClass("opened");
    $(this).parents(".custom-select").find(".custom-select-trigger").text($(this).text());
  });*/