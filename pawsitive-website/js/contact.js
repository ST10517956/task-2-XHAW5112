document.getElementById('contact').addEventListener('submit',function(e){
  e.preventDefault();
  var s=document.getElementById('status');
  var ok=document.getElementById('n').value.trim()&&/\S+@\S+\.\S+/.test(document.getElementById('e').value)&&document.getElementById('m').value.trim();
  s.className=ok?'':'err';
  s.textContent=ok?'Thanks, your message is ready to send. (Demo form: no server yet.)':'Enter your name, a valid email and a message.';
  if(ok)this.reset();
});
