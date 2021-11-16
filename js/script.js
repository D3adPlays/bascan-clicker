
var theme = 1;                                                      // Theme sombre=0   Theme clair=1

var captcha;                                                        // Deffinition du captcha

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

window.onload = function() {
    yourFunction(param1, param2);
};

var verifyCallback = function(response) {
    /* console.log("Captcha reussi !")
    console.log("==================== BEGIN TOKEN ====================")
    console.log(response);
    console.log("https://www.google.com/recaptcha/api/siteverify?secret=6Ld1iTsdAAAAAIVUgfanoRz_nmCCnez_pWKVcz9n&response=" + encodeURIComponent(response));
    console.log("====================  END TOKEN  ====================")
    fadeOutEffect("login");
    window.open("https://www.google.com/recaptcha/api/siteverify?secret=6Ld1iTsdAAAAAIVUgfanoRz_nmCCnez_pWKVcz9n&response=" + encodeURIComponent(response)); */
    
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