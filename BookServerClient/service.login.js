async function login() {

   const tokens = await TokenRequest()
   const userDetails = await GetUserDetailsFromToken(tokens.access_token)

   const localUser = {
      authorities: userDetails.authorities,
      tokens: tokens
   }
   
  window.localStorage.setItem("localUser",JSON.stringify( localUser))
  const user= JSON.parse( window.localStorage.getItem("localUser"))

  if(loginAsAdminActive() && user.authorities.length==2) window.location.href="adminPage.html"
   else  window.location.href="userPage.html"
  
}

function loginAsAdminActive() {
   return document.getElementById("administrator").checked
}

async function GetUserDetailsFromToken(token) {
   let promise = await fetch("http://localhost:8080/oauth/check_token?token=" + token, {
      method: 'post',
      headers: {
         'Authorization': 'Basic ' + btoa('client:123')
      }
   }
   )
   let data = await promise.json()
   console.log(data)

   return data
}

   
async function TokenRequest() {
   const email = document.getElementById("email").value
   const senha = document.getElementById("senha").value
   const URI = "http://localhost:8080/oauth/token?grant_type=password&username=" + email + "&password=" + senha + "&scope=read"

   const promise = await fetch(URI, {
      method: 'post',
      headers: {
         'Authorization': 'Basic ' + btoa('client:123')
      }
   }).then(response=>{
      if(response.status>=400){ 
         throw Error("Sem permissao")
      }else 
       return response
      })
   .catch(err => document.getElementById("error").removeAttribute("hidden", ""))


   const data = await promise.json()

   let tokens = {
      access_token: data.access_token,
      refresh_token: data.refresh_token
   }

   return tokens
}



function GoBack(){
   history.back()
}