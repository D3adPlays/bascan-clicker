
var theme = 1;                                                      // Theme sombre=0   Theme clair=1

var captcha = "null";                                                        // Deffinition du captcha
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
}; //<div id="captcha" class="fadeIn third"><form action="javascript:alert(grecaptcha.getResponse(widgetId1));"></form></div>

window.onload = function() {
    document.getElementById()
};

var verifyCallback = function(response) {
    //window.location.replace("http://localhost:8080/getToken?key=" + response);
};

function replaceContentInContainer(target, source) {
    document.getElementById(target).innerHTML = document.getElementById(source).innerHTML;
};
function fadeOutEffect(target) {
    var fadeTarget = document.getElementById(target);
    var fadeEffect = setInterval(function () {
        if (!fadeTarget.style.opacity) {
            fadeTarget.style.opacity = 1;
        }
        if (fadeTarget.style.opacity > 0) {
            fadeTarget.style.opacity -= 0.01;
        } else {
            clearInterval(fadeEffect);
        }
    }, 2);
};

function login(){
    if(captcha != "null"){
        fadeOutEffect("login"); 
    } else {
        fadeOutEffect("login");
    }
}