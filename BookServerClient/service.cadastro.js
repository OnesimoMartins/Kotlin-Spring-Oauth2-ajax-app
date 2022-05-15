 function matriculate() {

   const name=document.getElementById("nome").value
   const email =document.getElementById("email").value
   const senha =document.getElementById("senha").value

   const newUser={
      name:name, email:email,password:senha
   }

console.log(newUser);
postUser(JSON.stringify( newUser))
 }

function postUser(user) {
   
   console.log(user);
   const URL="http://localhost:8080/users"
   fetch(URL,{
      method:'POST',
      body: user,
      
      headers:{
         'Authorization':'Basic '+btoa("client:123"),
         'content-type':'application/json'
      }
   }).then(r=>window.location.href="login.html")
}

function GoBack(){
   history.back()
}