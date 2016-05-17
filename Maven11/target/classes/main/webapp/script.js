var message=[];
var messageRead=[];
var i=0;
function nMessage(author,description) {
	return {
		author: author,
		text:description,	
		id: String(uniqueId()),
	    timestamp:Number(new Date().getTime()),
	};
}

function run(){
   var appContainer = document.getElementsByClassName('todos')[0];
   appContainer.addEventListener('click', delegateEvent);
   loadMessages(function(messageRead){
	    var m=messageRead.messages;
	    
	    for(var i=0; i<m.length; i++) {
			addTodo(m[i].author,m[i].text); 	
		}    
   });
  
}

function loadMessages(callback) {
	/* if(typeof(Storage) == "undefined") {
		alert('localStorage is not accessible');
		return;
	}

	var item = localStorage.getItem("TODOs taskList");

	return item && JSON.parse(item);
} */
	
 	 var url = 'http://localhost:8080/chat?token=TN11EN';
     var xhttp = new XMLHttpRequest();
  
     xhttp.onreadystatechange = function() {
		if (xhttp.readyState == 4 && xhttp.status == 200) {
		  var json = JSON.parse(xhttp.responseText);
		  /* var text = JSON.stringify(json, null, '\t');   */
		  /* console.log(json);  */
		  callback(json);
	  }
	};
  
   xhttp.open("GET", url, true);
   xhttp.send();
  } 
 
	

	


function delegateEvent(evtObj) {	
	if(evtObj.type==='click' && evtObj.target.classList.contains('send')){
		var login=document.getElementsByName ('login')[0];
		var todoText=document.getElementsByClassName ('inputText')[0];
		if(login.value!=''){
		onAddButtonClick(login.value,todoText.value);
		}
	}
	if(evtObj.type==='click' && evtObj.target.classList.contains('login_button')){
	var login=document.getElementsByName ('login')[0];
	if(login.value!=''){
		onAddButtonClick(login.value,'NEW User! ');
	}
	}
	
	if(evtObj.type==='click' && evtObj.target.classList.contains('delete')){
		deleteMessage(evtObj.target);
	}
}

function onAddButtonClick(login,todoText){
	message.push(nMessage(login,todoText));  
	addTodo(login,todoText);
	todoText.value='';
	saveMessages(message); 
}



function addTodo(login,value){
	if(!value){
		return;
	}
	var item=createText(login,value);
	var items=document.getElementsByClassName("message-history")[0];
	items.appendChild(item);
}


function createText(login,text){
	var tableItem=document.createElement('table');
	var tr=document.createElement('tr');
	
	var tdMessage=document.createElement('td');
	tdMessage.classList.add('message');
	tdMessage.appendChild(document.createTextNode(login+':'+text));
	
	var tdEdit=document.createElement('td');
	var Edit=document.createElement('div');  
	Edit.classList.add('edit');
	tdEdit.appendChild(Edit);

	var tdDelete=document.createElement('td');
	var Delete=document.createElement('div');  
	Delete.classList.add('delete');
	tdDelete.appendChild(Delete);
	
	tr.appendChild(tdMessage);
	tr.appendChild(tdEdit);
	tr.appendChild(tdDelete);

	tableItem.appendChild(tr);	
	return tableItem;
}

function saveMessages(login,value) {
	
	var json;
	var url = 'http://localhost:8080/chat?token=TN11EN';
	var xhttp  = new XMLHttpRequest();
    json = JSON.stringify(login,value);
    xhttp.open("POST", url, true);
    xhttp.setRequestHeader("Content-Type","application/json"); 
	
	if(i==0){
    xhttp.send(json.slice(1,json.length-1));
		i++;
	}
	else{
	xhttp.send(json.slice(json.lastIndexOf('},')+2,json.length-1));
	}

}

function uniqueId() {
	var date = Date.now();
	var random = Math.random() * Math.random();
	return Math.floor(date * random);
}



/* ��������� cmd �� git bash */