# bascan-clicker

Besoins api format URL
http://lien/api/gtoken&hcapcha=<token>
return:
    - 400 erreur //si token hcapcha invalide
    - <token> // comportant l'ip du demandeur et Un uuid généré avec seed l'ip le tout encrypté avec une clef privé
    https://docs.hcaptcha.com pour l'api simple

