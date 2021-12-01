
var theme = 1;                                                      // Theme sombre=0   Theme clair=1

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
    accestoken = httpGet("http://77.206.85.165:8080/getToken?key=" + captcha);
};

function pullToken(){
    if (captcha == "null") {
        console.log("capcha non valide")
    } else {
        setCookie("accestoken", accestoken, 365);
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
  
/**
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
*/
  