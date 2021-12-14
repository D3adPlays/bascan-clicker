window.onload = function(){
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
   
    
}
function count(){
  console.log(getCookie('classe'))
  if (getCookie('classe') == 'Seconde'){
    httpGet("http://localhost:8080/count?seconde=" + getCookie('accestoken'));
  }
    
}
function httpGet(theUrl)
  {
    var xmlHttp = null;

    xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", theUrl, false );
    xmlHttp.send( null );
    return xmlHttp.responseText;
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

   