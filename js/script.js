
var theme = 0;                                                      // Theme sombre=0   Theme clair=1

var captcha = "null";
var accestoken = "null";                                                     // Deffinition du captcha
var server = "localhost";

var onloadCallback = function() {
    if(theme==0){                                                   // Si le theme est strictement égal a 0 (theme sombre)
        captchaSombre = grecaptcha.render('captcha', {                  // definire l'object captcha
            'sitekey' : '6Ld1iTsdAAAAALKr6tpRhB49f4omeqmy6TDAiiIW',     // ajouter la clef d'api 
            'callback' : verifyCallback,                                // déffinire la fonction a éxécuter lors de la validation du captcha 
            'theme' : 'dark'                                            // Mettre le theme en theme sombre 
        });
    } else {                                                        // Si non 
        captchaClair = grecaptcha.render('captcha', {                   // definire l'object captcha
            'sitekey' : '6Ld1iTsdAAAAALKr6tpRhB49f4omeqmy6TDAiiIW',     // ajouter la clef d'api
            'callback' : verifyCallback,                                // déffinire la fonction a éxécuter lors de la validation du captcha 
            'theme' : 'light'                                           // Mettre le theme en theme sombre
          });
    }
};

var verifyCallback = function(response) {
    captcha = response;
    accestoken = httpGet("http://localhost:8080/getToken?key=" + captcha);
}; //<div id="captcha" class="fadeIn third"><form action="javascript:alert(grecaptcha.getResponse(widgetId1));"></form></div>

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
   
    
    if(theme == 0){
        document.getElementById('tile').style.backgroundColor="#222222"
        document.getElementById('input').style.backgroundColor="#222222"
    }


function replaceContentInContainer(target, source) {
    window.location = source;
    document.getElementById(target).innerHTML = source;
};
function fadeOutEffect(target) {
    var fadeTarget = document.getElementById(target);
    var fadeEffect = setInterval(async function () {
        if (!fadeTarget.style.opacity) {
            fadeTarget.style.opacity = 1;
        }
        if (fadeTarget.style.opacity > 0) {
            fadeTarget.style.opacity -= 0.01;
            await pause(100)
            window.location.replace("http://localhost:8000/select/index.html")
        } else {
            clearInterval(fadeEffect);
        }
    }, 2);
};

function pullToken(){
    if (captcha == "null") {
        console.log("capcha non valide")
    } else {
        setCookie("accestoken", accestoken, 365);
        fadeOutEffect("tile")
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

  