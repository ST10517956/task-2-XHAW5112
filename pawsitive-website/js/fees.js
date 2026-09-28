document.getElementById('fees').addEventListener('submit',function(e){
  e.preventDefault();
  var picked=[].slice.call(document.querySelectorAll('input[name=course]:checked'));
  var out=document.getElementById('result');
  if(!picked.length){out.innerHTML='<span class="err">Select at least one course.</span>';return;}
  var sub=picked.reduce(function(s,i){return s+Number(i.value)},0);
  var n=picked.length,rate=n>3?.15:n===3?.10:n===2?.05:0;
  var disc=sub*rate;
  out.innerHTML='Courses: '+n+'<br>Subtotal: R'+sub.toFixed(2)+'<br>Discount ('+(rate*100)+'%): -R'+disc.toFixed(2)+'<br><strong>Total: R'+(sub-disc).toFixed(2)+'</strong>';
});
