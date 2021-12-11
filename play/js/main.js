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


   