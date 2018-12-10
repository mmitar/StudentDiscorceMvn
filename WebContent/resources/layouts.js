
function phoneWriter(evt) {

  let length = evt.value.length;
  
  let lastValue = function() {
    if(length > 0)
      return evt.value.substring(length-1, length);
    else
      return "";
  }();
  
  if(isInt(lastValue) && length < 11) {
    if(length == 10) { 
      let set1 = evt.value.substring(0,3);
      let set2 = evt.value.substring(3,6);
      let set3 = evt.value.substring(6,10);
      evt.value = "("+set1+") "+set2+"-"+set3;
    }
  }
  else if(length < 11) {
    evt.value = evt.value.substring(0, length-1);
  }
  
  if(length == 13) {
      let array1 = evt.value.split(")");
      let array2 = array1[1].split("-");
      let numba = array1[0].substring(1,length) + "" + array2[0].trim() + "" + array2[1];
      evt.value = numba;
    }
  }

function isInt(value) {
  return !isNaN(value) && 
         parseInt(Number(value)) == value && 
         !isNaN(parseInt(value, 10));
}

/** DOM LISTENER. Load when ready. **/
document.addEventListener("DOMContentLoaded", function(event) 
{
    console.log("DOM fully loaded and parsed");
    
    // Add Event Listener to phone label
    if(document.getElementById('phoneInput'))
	{
    	let phone = document.getElementById('phoneInput');
        phone.addEventListener('input', function() { phoneWriter(this);}, false);
	}
    
    // Enable menuLeft channels to be sortable only on the y axis
    $( "#sortable" ).sortable();
    
});

